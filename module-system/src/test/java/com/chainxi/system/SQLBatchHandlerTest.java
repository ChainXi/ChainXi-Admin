package com.chainxi.system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
public class SQLBatchHandlerTest {
    @Autowired
    private JdbcTemplate template;
    private final String[] DEL_COLUMNS_NAMES =
            {"created_at", "updated_at", "created_time", "create_by", "create_time", "update_by",
                    "update_time", "updated_time", "updater", "creator", "del_flag", "deleted"};
    String sqlSelectAllColumns =
            "SELECT COLUMN_NAME from information_schema.COLUMNS where table_name = ";
    String sqlSelectAllTables =
            "SELECT t.table_name FROM information_schema.tables t WHERE t.table_schema='lylloan'";


    @Test
    public void setColumns() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String formattedDate = date.format(formatter);
        SingleColumnRowMapper<String> rowMapper = new SingleColumnRowMapper<>();
        List<String> tables = template.query(sqlSelectAllTables, rowMapper);
        System.out.println(tables);
        for (String table : tables) {
            List<String> tableColumns =
                    template.query(sqlSelectAllColumns + "'" + table + "'", rowMapper);

            RowCountCallbackHandler rowCountCallbackHandler = new RowCountCallbackHandler();
            template.query("SHOW COLUMNS FROM "+table+" LIKE 'deleted';",
                    rowCountCallbackHandler);
            if (rowCountCallbackHandler.getRowCount() == 1) {
                template.execute("DELETE FROM " + table + " WHERE deleted = b'1'");
            }
            for (String delColumnsName : DEL_COLUMNS_NAMES) {
                if (tableColumns.contains(delColumnsName)) {
                    template.execute(
                            "ALTER TABLE " + table + " DROP COLUMN " + delColumnsName + "");
                }
            }
            template.execute("alter table " + table + " add create_time datetime, " +
                    " add update_time datetime, " +
                    " add updater VARCHAR(255) NULL DEFAULT '' COMMENT '更新者'," +
                    " add deleted bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除'");
            template.execute("UPDATE " + table + " SET update_time = '" + formattedDate +
                    "', create_time = '" + formattedDate + "'");
        }
    }


    @Test
    public void getColumns() {
        RowCountCallbackHandler rowCountCallbackHandler = new RowCountCallbackHandler();
        template.query("SHOW COLUMNS FROM sys_role_resource LIKE 'deleted';",
                rowCountCallbackHandler);
        System.out.println(rowCountCallbackHandler.getRowCount());
    }
}
