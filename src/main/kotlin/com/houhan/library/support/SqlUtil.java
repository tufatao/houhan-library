package com.houhan.library.support;

import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author tufatao
 * @version V 0.1
 * @describe {}
 * @time 2017/11/15 16:11.
 */
public class SqlUtil {
    /**
     * 插入实体
     *
     * @param tableName
     * @param fieldMap
     * @return
     */
    public static String genInsertPreSql(String tableName, Map<String, ?> fieldMap) {
        if (CollectionUtils.isEmpty(fieldMap)) {
            return null;
        }
        String sql = "INSERT INTO " + tableName;
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String fieldName :
                fieldMap.keySet()) {
            columns.append(fieldName).append(", ");
            values.append(":").append(fieldName).append(", ");

        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + " VALUES " + values;
        return sql;
    }

}
