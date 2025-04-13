package com.chainxi.generator.reqvo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class ImportGenTableReqVo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    private String[] tables;
}