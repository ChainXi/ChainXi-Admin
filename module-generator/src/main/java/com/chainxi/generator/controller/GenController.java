package com.chainxi.generator.controller;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.chainxi.common.web.pojo.PageResult;
import com.chainxi.common.web.pojo.ResponseResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.chainxi.generator.config.GenProperties;
import com.chainxi.generator.domain.GenTable;
import com.chainxi.generator.domain.GenTableColumn;
import com.chainxi.generator.reqvo.GenTablePageQueryReqVo;
import com.chainxi.generator.reqvo.ImportGenTableReqVo;
import com.chainxi.generator.service.IGenTableColumnService;
import com.chainxi.generator.service.IGenTableService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author chainxi
 */
@Slf4j
@RestController
@RequestMapping("/tool/gen")
public class GenController {
    private final IGenTableService genTableService;
    private final IGenTableColumnService genTableColumnService;
    private final GenProperties properties;

    public GenController(IGenTableService genTableService,
            IGenTableColumnService genTableColumnService, GenProperties properties) {
        this.genTableService       = genTableService;
        this.genTableColumnService = genTableColumnService;
        this.properties            = properties;
    }

    /**
     * 查询代码生成列表
     */
    @GetMapping("/list")
    public ResponseResult<PageResult<GenTable>> genList(GenTablePageQueryReqVo genTable) {
        return ResponseResult.success(genTableService.selectGenTableList(genTable));
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping(value = "/{tableId}")
    public ResponseResult getInfo(@PathVariable Long tableId) {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list =
                genTableColumnService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return ResponseResult.success(map);
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/db/list")
    public ResponseResult<List<GenTable>> dataList(GenTable genTable) {
        return ResponseResult.success(genTableService.selectDbTableList(genTable));
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{tableId}")
    public ResponseResult<PageResult<GenTableColumn>> columnList(@PathVariable Long tableId) {
        List<GenTableColumn> list =
                genTableColumnService.selectGenTableColumnListByTableId(tableId);
        return null;
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public ResponseResult importTableSave(@RequestBody ImportGenTableReqVo reqVo) {
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(reqVo.getTables());
        genTableService.importGenTable(tableList);
        return ResponseResult.success();
    }



    /**
     * 创建表结构（保存）
     */
    @PostMapping("/createTable")
    public ResponseResult createTableSave(String sql) {
        try {
            List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, DbType.mysql);
            List<String> tableNames = new ArrayList<>();
            for (SQLStatement sqlStatement : sqlStatements) {
                if (sqlStatement instanceof MySqlCreateTableStatement createTableStatement) {
                    if (genTableService.createTable(createTableStatement.toString())) {
                        String tableName = createTableStatement
                                .getTableName()
                                .replaceAll("`", "");
                        tableNames.add(tableName);
                    }
                }
            }
            List<GenTable> tableList =
                    genTableService.selectDbTableListByNames(tableNames.toArray(new String[0]));
            genTableService.importGenTable(tableList);
            return ResponseResult.success();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseResult.error("创建表结构异常");
        }
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    public ResponseResult editSave(@Validated @RequestBody GenTable genTable) throws JsonProcessingException {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return ResponseResult.success();
    }

    /**
     * 删除代码生成
     */
    @DeleteMapping("/{tableIds}")
    public ResponseResult remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return ResponseResult.success();
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableId}")
    public ResponseResult preview(@PathVariable("tableId") Long tableId) {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return ResponseResult.success(dataMap);
    }

    /**
     * 生成代码（自定义路径）
     */
    @GetMapping("/genCode/{tableName}")
    public ResponseResult genCode(@PathVariable("tableName") String tableName) {
        if (!properties.getAllowOverwrite()) {
            return ResponseResult.error("【系统预设】不允许生成文件覆盖到本地");
        }
        genTableService.generatorCode(tableName);
        return ResponseResult.success();
    }

    /**
     * 同步数据库
     */
    @GetMapping("/synchDb/{tableName}")
    public ResponseResult synchDb(@PathVariable("tableName") String tableName) {
        genTableService.synchDb(tableName);
        return ResponseResult.success();
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String[] tables) throws IOException {
        byte[] data = genTableService.downloadCode(tables);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"chainxi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}