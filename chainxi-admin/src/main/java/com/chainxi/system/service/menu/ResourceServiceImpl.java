package com.chainxi.system.service.menu;

import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.system.bo.RequestMappingBo;
import com.chainxi.system.constants.system.CacheKeyConstants;
import com.chainxi.system.convert.auth.SysResourceConvert;
import com.chainxi.system.entity.menu.SysResourceDo;
import com.chainxi.system.entity.role.SysRoleResourceDo;
import com.chainxi.system.enums.auth.ResourceAccessType;
import com.chainxi.system.enums.auth.ResourceMappingType;
import com.chainxi.system.mapper.menu.SysResourceMapper;
import com.chainxi.system.mapper.role.SysRoleResourceMapper;
import com.chainxi.system.reqvo.menu.SysResourcePageReqVo;
import com.chainxi.system.reqvo.menu.SysResourceUpdateReqVo;
import jakarta.annotation.Nonnull;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Request;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.util.ServletRequestPathUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author : ChainXi
 * @Date : 2024/6/13 0:20
 * @Desc :
 */
@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {
    private final SysResourceMapper sysResourceMapper;
    private final SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public int createResource(SysResourceUpdateReqVo reqVo) {
        return sysResourceMapper.insert(SysResourceConvert.INSTANCE.convertToDo(reqVo));
    }

    @Override
    public int updateResource(SysResourceUpdateReqVo reqVo) {
        return sysResourceMapper.updateById(SysResourceConvert.INSTANCE.convertToDo(reqVo));
    }

    @Override
    public SysResourceDo queryById(Long id) {
        return sysResourceMapper.selectById(id);
    }

    @Override
    public int deleteResourceById(Long id) {
        return sysResourceMapper.deleteById(id);
    }

    @Override
    public PageResult<SysResourceDo> selectPage(SysResourcePageReqVo reqVo) {
        return sysResourceMapper.selectPage(reqVo);
    }

    @Override
    public List<SysResourceDo> selectAll() {
        return sysResourceMapper.selectList();
    }

    @Override
    @CachePut(CacheKeyConstants.ROLE_RES)
    public RequestMappingBo refreshRequestMapping() {
        return getResourceRolesMap();
    }

    @Override
    @Cacheable(CacheKeyConstants.ROLE_RES)
    public RequestMappingBo getResourceRolesMap() {
        List<SysResourceDo> sysResourceDos = sysResourceMapper.selectList();
        List<SysRoleResourceDo> sysRoleResourceDos = sysRoleResourceMapper.selectList();
        Map<Long, List<Long>> map = sysRoleResourceDos
                .stream()
                .collect(Collectors.groupingBy(SysRoleResourceDo::getResourceId,
                        Collectors.mapping(SysRoleResourceDo::getRoleId, Collectors.toList())));
        Map<ResourceMappingType, List<Pair<SysResourceDo, List<Long>>>> groupingByMappingType =
                sysResourceDos
                        .stream()
                        .collect(Collectors.groupingBy(SysResourceDo::getMappingType,
                                Collectors.mapping(resource -> new Pair<>(resource,
                                        map.getOrDefault(resource.getId(),
                                                new ArrayList<>())), Collectors.toList())));
        List<Pair<SysResourceDo, List<Long>>> bucketPairs = groupingByMappingType.getOrDefault(
                ResourceMappingType.BUCKET,
                new ArrayList<>());
        List<Pair<SysResourceDo, List<Long>>> blockPairs = groupingByMappingType.getOrDefault(
                ResourceMappingType.BLOCK,
                new ArrayList<>());
        blockPairs.forEach(blockPair -> {
            Request request = new Request(null);
            org.apache.coyote.Request coyoteRequest = new org.apache.coyote.Request();
            SysResourceDo blockPairKey = blockPair.getKey();
            coyoteRequest.requestURI().setString(blockPairKey.getPatterns());
            request.setCoyoteRequest(coyoteRequest);
            request.getMappingData().wrapperPath.setString(blockPairKey.getPatterns());
            ServletRequestPathUtils.parseAndCache(request);
            bucketPairs.forEach(bucketPair -> {
                SysResourceDo bucketPairKey = bucketPair.getKey();
                RequestMappingInfo requestMappingInfo =
                        RequestMappingInfo.paths(bucketPairKey.getPatterns()).methods().build();
                RequestMappingInfo matchingCondition =
                        requestMappingInfo.getMatchingCondition(request);
                if (matchingCondition != null) {
                    blockPairKey.setAccessType(blockPairKey.getAccessType().ordinal() >
                            bucketPairKey.getAccessType().ordinal() ?
                            blockPairKey.getAccessType() :
                            bucketPairKey.getAccessType());
                    blockPair.getValue().addAll(bucketPair.getValue());
                }
            });
        });
        Map<ResourceAccessType, List<Pair<SysResourceDo, List<Long>>>> listMap = blockPairs
                .stream()
                .collect(Collectors.groupingBy(sysResourceListPair -> sysResourceListPair
                                .getKey()
                                .getAccessType(),
                        Collectors.mapping(Function.identity(), Collectors.toList())));
        RequestMappingBo requestMappingBo = new RequestMappingBo();
        Map<RequestMappingInfo, List<Long>> registryAuthority = listMap
                .getOrDefault(ResourceAccessType.AUTHORIZATION, Collections.emptyList())
                .stream()
                .collect(Collectors.toMap(sysResourceListPair -> {
                    SysResourceDo key = sysResourceListPair.getKey();
                    return RequestMappingInfo
                            .paths(key.getPatterns())
                            .methods(key.getMethods())
                            .build();
                }, Pair<SysResourceDo, List<Long>>::getValue));
        Set<RequestMappingInfo> registryAuthenticated =
                groupingByAccessType(listMap, ResourceAccessType.AUTHENTICATION);
        Set<RequestMappingInfo> registryAnonymous =
                groupingByAccessType(listMap, ResourceAccessType.ANONYMOUS);
        requestMappingBo.setRegistryAuthority(registryAuthority);
        requestMappingBo.setRegistryAuthenticated(registryAuthenticated);
        requestMappingBo.setRegistryAnonymous(registryAnonymous);
        return requestMappingBo;
    }

    @Nonnull
    private Set<RequestMappingInfo> groupingByAccessType(Map<ResourceAccessType,
            List<Pair<SysResourceDo, List<Long>>>> listMap,
            ResourceAccessType accessType) {
        return listMap
                .getOrDefault(accessType, Collections.emptyList())
                .stream()
                .map(sysResourceListPair -> {
                    SysResourceDo key = sysResourceListPair.getKey();
                    return RequestMappingInfo
                            .paths(key.getPatterns())
                            .methods(key.getMethods())
                            .build();
                })
                .collect(Collectors.toSet());
    }
}
