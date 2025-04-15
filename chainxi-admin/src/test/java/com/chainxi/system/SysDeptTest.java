package com.chainxi.system;

import com.chainxi.system.mapper.organization.SysDeptMapper;
import com.chainxi.system.reqvo.organization.DeptCreateReqVo;
import com.chainxi.system.service.organization.SysDeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SysDeptTest {

    @Autowired
    private SysDeptService service;
    @Autowired
    private SysDeptMapper mapper;

    @Test
    public void testSysDeptService() {
        for(int i=0;i<5;i++){
            service.createDept(new DeptCreateReqVo()
                    .setDeptName("ChainXi_child")
                    .setSort(0)
                    .setLeaderId(0L)
                    .setPid(1L));
        }
    }

    @Test
    public void testSelectChildNode() {
        System.out.println(mapper.deleteNode(257L));
    }
}
