package com.chainxi.system.convert.organization;

import com.chainxi.system.entity.organization.SysDeptDo;
import com.chainxi.system.reqvo.organization.DeptCreateReqVo;
import com.chainxi.system.reqvo.organization.DeptUpdateReqVo;
import com.chainxi.system.reqvo.organization.DeptUpdateStatusReqVo;
import com.chainxi.system.respvo.organization.SysDeptDetailRespVo;
import com.chainxi.system.respvo.organization.SysDeptMapRespVo;
import com.chainxi.system.respvo.organization.SysDeptRespVo;
import com.chainxi.system.respvo.user.user.UserIndexMapRespVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysDeptConvert {

    SysDeptConvert INSTANCE = Mappers.getMapper(SysDeptConvert.class);

    SysDeptRespVo convertDo2Vo(SysDeptDo deptDo, String leaderName);

    default SysDeptDetailRespVo convertDo2DetailVo(SysDeptDo deptDo, UserIndexMapRespVo leader) {
        return convertDo2DetailVo(deptDo).setLeader(leader);
    }

    SysDeptDetailRespVo convertDo2DetailVo(SysDeptDo deptDo);

    SysDeptMapRespVo convertDo2MapVo(SysDeptDo deptDo);

    SysDeptDo convertCreateReq2Do(DeptCreateReqVo reqVo);

    SysDeptDo convertUpdateReq2Do(DeptUpdateReqVo reqVo);

    SysDeptDo convertUpdateReq2Do(DeptUpdateStatusReqVo reqVo);


}
