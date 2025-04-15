package com.chainxi.system.convert.auth;

import com.chainxi.system.bo.DataPermissionDeptMatcherBo;
import com.chainxi.system.entity.datapermission.DataPermissionDeptMatcherDo;
import com.chainxi.system.enums.auth.DataPermissionDeptMatchBucketType;
import com.chainxi.system.reqvo.datapermission.DataPermissionDeptMatcherUpdateReqVo;
import com.chainxi.system.respvo.datapermission.DataPermissionDeptMatcherRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper
public interface DataPermissionConvert {
    DataPermissionConvert INSTANCE = Mappers.getMapper(DataPermissionConvert.class);
    default List<DataPermissionDeptMatcherDo> deptMatcherReqVo2Do(DataPermissionDeptMatcherUpdateReqVo reqVo) {
        List<DataPermissionDeptMatcherDo> collect = reqVo
                .getTarget()
                .stream()
                .map(e -> new DataPermissionDeptMatcherDo()
                        .setUserId(reqVo.getUserId())
                        .setTarget(e))
                .collect(Collectors.toList());
        Optional
                .ofNullable(reqVo.getMatchType())
                .ifPresent(e -> collect.add(new DataPermissionDeptMatcherDo()
                        .setUserId(reqVo.getUserId())
                        .setMatchType(e)));
        return collect;
    }

    default DataPermissionDeptMatcherRespVo deptMatcherDos2RespVo(List<DataPermissionDeptMatcherDo> matcherDos) {
        return new DataPermissionDeptMatcherRespVo()
                .setMatchType(matcherDos
                        .stream()
                        .max((o1, o2) -> {
                            DataPermissionDeptMatchBucketType o1MatchType = o1.getMatchType();
                            DataPermissionDeptMatchBucketType o2MatchType = o2.getMatchType();
                            return (o2MatchType != null ?
                                    o2MatchType.ordinal() :
                                    Integer.MAX_VALUE) - (o1MatchType != null ?
                                    o1MatchType.ordinal() :
                                    Integer.MAX_VALUE);
                        })
                        .map(DataPermissionDeptMatcherDo::getMatchType)
                        .orElse(null))
                .setTarget(matcherDos
                        .stream()
                        .map(DataPermissionDeptMatcherDo::getTarget)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList()));
    }

    default DataPermissionDeptMatcherBo deptMatcherDos2Bo(List<DataPermissionDeptMatcherDo> matcherDos) {
        return new DataPermissionDeptMatcherBo()
                .setMatchType(matcherDos
                        .stream()
                        .map(DataPermissionDeptMatcherDo::getMatchType)
                        .filter(Objects::nonNull)
                        .min(Comparator.comparingInt(Enum::ordinal))
                        .orElse(null))
                .setDeptIds(matcherDos
                        .stream()
                        .map(DataPermissionDeptMatcherDo::getTarget)
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet()));
    }

}
