package com.chainxi.generator.util;

import cn.hutool.json.JSONObject;
import com.chainxi.common.web.utils.DateUtils;
import com.chainxi.generator.constants.GenConstants;
import com.chainxi.generator.domain.GenTable;
import com.chainxi.generator.domain.GenTableColumn;
import org.apache.velocity.VelocityContext;

import java.util.*;

/**
 * 模板处理工具类
 *
 * @author chainxi
 */
public class VelocityUtils {
    /**
     * 项目空间路径
     */
    private static final String PROJECT_PATH = "main/java";

    /**
     * mybatis空间路径
     */
    private static final String MYBATIS_PATH = "main/resources/mapper";

    /**
     * 默认上级菜单，系统工具
     */
    private static final String DEFAULT_PARENT_MENU_ID = "3";

    /**
     * 设置模板变量信息
     *
     * @return 模板列表
     */
    public static VelocityContext prepareContext(GenTable genTable) {
        String moduleName = genTable.getModuleName();
        String businessName = genTable.getBusinessName();
        String packageName = genTable.getPackageName();
        String tplCategory = genTable.getTplCategory();
        String functionName = genTable.getFunctionName();

        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("tplCategory", genTable.getTplCategory());
        velocityContext.put("tableName", genTable.getTableName());
        velocityContext.put("functionName", StringUtils.isNotEmpty(functionName) ?
                functionName :
                "【请填写功能名称】");
        velocityContext.put("ClassName", genTable.getClassName());
        velocityContext.put("className", StringUtils.uncapitalize(genTable.getClassName()));
        velocityContext.put("moduleName", genTable.getModuleName());
        velocityContext.put("BusinessName", StringUtils.capitalize(genTable.getBusinessName()));
        velocityContext.put("businessName", genTable.getBusinessName());
        velocityContext.put("basePackage", getPackagePrefix(packageName));
        velocityContext.put("packageName", packageName);
        velocityContext.put("author", genTable.getFunctionAuthor());
        velocityContext.put("datetime", DateUtils.of(new Date()));
        velocityContext.put("pkColumn", genTable.getPkColumn());
        velocityContext.put("importList", getImportList(genTable));
        velocityContext.put("permissionPrefix", getPermissionPrefix(moduleName, businessName));
        velocityContext.put("columns", genTable.getColumns());
        velocityContext.put("table", genTable);
        velocityContext.put("dicts", getDicts(genTable));
        setMenuVelocityContext(velocityContext, genTable);
        if (GenConstants.TPL_CRUD.equals(tplCategory)) {
            velocityContext.put("QueryReqClassName", genTable.getClassName() + "PageReqVo");
        }
        if (GenConstants.TPL_TREE.equals(tplCategory)) {
            setTreeVelocityContext(velocityContext, genTable);
            velocityContext.put("QueryReqClassName", genTable.getClassName() + "ListReqVo");
        }
        return velocityContext;
    }

    public static void setMenuVelocityContext(VelocityContext context, GenTable genTable) {
        String options = genTable.getOptions();
        JSONObject paramsObj = new JSONObject(options);
        String parentMenuId = getParentMenuId(paramsObj);
        context.put("parentMenuId", parentMenuId);
    }

    public static void setTreeVelocityContext(VelocityContext context, GenTable genTable) {
        String options = genTable.getOptions();
        JSONObject paramsObj = new JSONObject(options);
        String treeCode = getTreecode(paramsObj);
        String treeParentCode = getTreeParentCode(paramsObj);
        String treeName = getTreeName(paramsObj);

        context.put("treeCode", treeCode);
        context.put("treeParentCode", treeParentCode);
        context.put("treeName", treeName);
        context.put("expandColumn", getExpandColumn(genTable));
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
            context.put("tree_parent_code", paramsObj.getStr(GenConstants.TREE_PARENT_CODE));
        }
        if (paramsObj.containsKey(GenConstants.TREE_NAME)) {
            context.put("tree_name", paramsObj.getStr(GenConstants.TREE_NAME));
        }
    }

    public static void setSubVelocityContext(VelocityContext context, GenTable genTable) {
        GenTable subTable = genTable.getSubTable();
        String subTableName = genTable.getSubTableName();
        String subTableFkName = genTable.getSubTableFkName();
        String subClassName = genTable
                .getSubTable()
                .getClassName();
        String subTableFkClassName = StringUtils.convertToCamelCase(subTableFkName);

        context.put("subTable", subTable);
        context.put("subTableName", subTableName);
        context.put("subTableFkName", subTableFkName);
        context.put("subTableFkClassName", subTableFkClassName);
        context.put("subTableFkclassName", StringUtils.uncapitalize(subTableFkClassName));
        context.put("subClassName", subClassName);
        context.put("subclassName", StringUtils.uncapitalize(subClassName));
        context.put("subImportList", getImportList(genTable.getSubTable()));
    }

    /**
     * 获取模板信息
     *
     * @param tplCategory 生成的模板
     * @param tplWebType  前端类型
     * @return 模板列表
     */
    public static List<String> getTemplateList(String tplCategory, String tplWebType) {
        String useWebType = "vm/vue";
        if ("element-plus".equals(tplWebType)) {
            useWebType = "vm/vue/v4";
        }
        List<String> templates = new ArrayList<String>();
        templates.add("vm/java/domain.java.vm");
        templates.add("vm/java/mapper.java.vm");
        templates.add("vm/java/service.java.vm");
        templates.add("vm/java/serviceImpl.java.vm");
        templates.add("vm/java/controller.java.vm");
        templates.add("vm/sql/sql.vm");
        templates.add("vm/js/index.ts.vm");
        templates.add(useWebType + "/form.vue.vm");
        templates.add("vm/java/req-create.java.vm");
        templates.add("vm/java/req-update.java.vm");
        templates.add("vm/java/convert.java.vm");
        templates.add("vm/java/req-query.java.vm");
        if (GenConstants.TPL_CRUD.equals(tplCategory)) {
            templates.add(useWebType + "/index.vue.vm");
        } else if (GenConstants.TPL_TREE.equals(tplCategory)) {
            templates.add(useWebType + "/index-tree.vue.vm");
        }
        return templates;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, GenTable genTable) {
        // 文件名称
        String fileName = "";
        // 包路径
        String packageName = genTable.getPackageName();
        // 模块名
        String moduleName = genTable.getModuleName();
        // 大写类名
        String className = genTable.getClassName();
        // 业务名称
        String businessName = genTable.getBusinessName();

        String javaPath = PROJECT_PATH + "/" + StringUtils.replace(packageName, ".", "/");
        String vuePath = "vue";

        if (template.contains("domain.java.vm")) {
            fileName =
                    StringUtils.format("{}/domain/{}/{}.java", javaPath, businessName, className);
        }
        if (template.contains("req-query.java.vm")) {
            fileName =
                    StringUtils.format("{}/reqvo/{}/{}{}ReqVo.java", javaPath, businessName,
                            className,
                            GenConstants.TPL_CRUD.equals(genTable.getTplCategory()) ?
                                    "Page" :
                                    "List");
        } else if (template.contains("req-create.java.vm")) {
            fileName =
                    StringUtils.format("{}/reqvo/{}/{}CreateReqVo.java", javaPath, businessName,
                            className);
        } else if (template.contains("req-update.java.vm")) {
            fileName =
                    StringUtils.format("{}/reqvo/{}/{}UpdateReqVo.java", javaPath, businessName,
                            className);
        } else if (template.contains("convert.java.vm")) {
            fileName =
                    StringUtils.format("{}/convert/{}/{}Convert.java", javaPath, businessName,
                            className);
        } else if (template.contains("mapper.java.vm")) {
            fileName =
                    StringUtils.format("{}/mapper/{}/{}Mapper.java", javaPath, businessName,
                            className);
        } else if (template.contains("service.java.vm")) {
            fileName =
                    StringUtils.format("{}/service/{}/I{}Service.java", javaPath, businessName,
                            className);
        } else if (template.contains("serviceImpl.java.vm")) {
            fileName =
                    StringUtils.format("{}/service/{}/{}ServiceImpl.java", javaPath, businessName
                            , className);
        } else if (template.contains("controller.java.vm")) {
            fileName =
                    StringUtils.format("{}/controller/{}/{}Controller.java", javaPath,
                            businessName, className);
        } else if (template.contains("sql.vm")) {
            fileName = businessName + "Menu.sql";
        } else if (template.contains("index.ts.vm")) {
            fileName =
                    StringUtils.format("{}/api/{}/{}/index.ts", vuePath, moduleName, businessName);
        } else if (template.contains("index.vue.vm")) {
            fileName =
                    StringUtils.format("{}/views/{}/{}/index.vue", vuePath, moduleName,
                            businessName);
        } else if (template.contains("form.vue.vm")) {
            fileName =
                    StringUtils.format("{}/views/{}/{}/form.vue", vuePath, moduleName,
                            businessName);
        } else if (template.contains("index-tree.vue.vm")) {
            fileName =
                    StringUtils.format("{}/views/{}/{}/index.vue", vuePath, moduleName,
                            businessName);
        }
        return fileName;
    }

    /**
     * 获取包前缀
     *
     * @param packageName 包名称
     * @return 包前缀名称
     */
    public static String getPackagePrefix(String packageName) {
        int lastIndex = packageName.lastIndexOf(".");
        return StringUtils.substring(packageName, 0, lastIndex);
    }

    /**
     * 根据列类型获取导入包
     *
     * @param genTable 业务表对象
     * @return 返回需要导入的包列表
     */
    public static HashSet<String> getImportList(GenTable genTable) {
        List<GenTableColumn> columns = genTable.getColumns();
        GenTable subGenTable = genTable.getSubTable();
        HashSet<String> importList = new HashSet<String>();
        if (StringUtils.isNotNull(subGenTable)) {
            importList.add("java.util.List");
        }
        for (GenTableColumn column : columns) {
            if (GenConstants.TYPE_DATE.equals(column.getJavaType())) {
                importList.add("java.time.LocalDateTime");
                importList.add("com.fasterxml.jackson.annotation.JsonFormat");
            } else if (GenConstants.TYPE_BIGDECIMAL.equals(column.getJavaType())) {
                importList.add("java.math.BigDecimal");
            }
        }
        return importList;
    }

    /**
     * 根据列类型获取字典组
     *
     * @param genTable 业务表对象
     * @return 返回字典组
     */
    public static String getDicts(GenTable genTable) {
        List<GenTableColumn> columns = genTable.getColumns();
        Set<String> dicts = new HashSet<String>();
        addDicts(dicts, columns);
        if (StringUtils.isNotNull(genTable.getSubTable())) {
            List<GenTableColumn> subColumns = genTable
                    .getSubTable()
                    .getColumns();
            addDicts(dicts, subColumns);
        }
        return StringUtils.join(dicts, ", ");
    }

    /**
     * 添加字典列表
     *
     * @param dicts   字典列表
     * @param columns 列集合
     */
    public static void addDicts(Set<String> dicts, List<GenTableColumn> columns) {
        for (GenTableColumn column : columns) {
            if (!column.isSuperColumn() && StringUtils.isNotEmpty(column.getDictType()) &&
                    StringUtils.equalsAny(column.getHtmlType(),
                            new String[]{GenConstants.HTML_SELECT, GenConstants.HTML_RADIO,
                                    GenConstants.HTML_CHECKBOX})) {
                dicts.add("'" + column.getDictType() + "'");
            }
        }
    }

    /**
     * 获取权限前缀
     *
     * @param moduleName   模块名称
     * @param businessName 业务名称
     * @return 返回权限前缀
     */
    public static String getPermissionPrefix(String moduleName, String businessName) {
        return StringUtils.format("{}:{}", moduleName, businessName);
    }

    /**
     * 获取上级菜单ID字段
     *
     * @param paramsObj 生成其他选项
     * @return 上级菜单ID字段
     */
    public static String getParentMenuId(JSONObject paramsObj) {
        if (StringUtils.isNotEmpty(paramsObj) &&
                paramsObj.containsKey(GenConstants.PARENT_MENU_ID) &&
                StringUtils.isNotEmpty(paramsObj.getStr(GenConstants.PARENT_MENU_ID))) {
            return paramsObj.getStr(GenConstants.PARENT_MENU_ID);
        }
        return DEFAULT_PARENT_MENU_ID;
    }

    /**
     * 获取树编码
     *
     * @param paramsObj 生成其他选项
     * @return 树编码
     */
    public static String getTreecode(JSONObject paramsObj) {
        if (paramsObj.containsKey(GenConstants.TREE_CODE)) {
            return StringUtils.toCamelCase(paramsObj.getStr(GenConstants.TREE_CODE));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取树父编码
     *
     * @param paramsObj 生成其他选项
     * @return 树父编码
     */
    public static String getTreeParentCode(JSONObject paramsObj) {
        if (paramsObj.containsKey(GenConstants.TREE_PARENT_CODE)) {
            return StringUtils.toCamelCase(paramsObj.getStr(GenConstants.TREE_PARENT_CODE));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取树名称
     *
     * @param paramsObj 生成其他选项
     * @return 树名称
     */
    public static String getTreeName(JSONObject paramsObj) {
        if (paramsObj.containsKey(GenConstants.TREE_NAME)) {
            return StringUtils.toCamelCase(paramsObj.getStr(GenConstants.TREE_NAME));
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取需要在哪一列上面显示展开按钮
     *
     * @param genTable 业务表对象
     * @return 展开按钮列序号
     */
    public static int getExpandColumn(GenTable genTable) {
        String options = genTable.getOptions();
        JSONObject paramsObj = new JSONObject(options);
        String treeName = paramsObj.getStr(GenConstants.TREE_NAME);
        int num = 0;
        for (GenTableColumn column : genTable.getColumns()) {
            if (column.isList()) {
                num++;
                String columnName = column.getColumnName();
                if (columnName.equals(treeName)) {
                    break;
                }
            }
        }
        return num;
    }
}
