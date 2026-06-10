package com.example.demo.processer;

import com.example.demo.annotation.InsertSql;
import com.example.demo.annotation.SqlColumn;

import java.lang.reflect.Field;

public class SqlProcess {
    public static String process(Object object){
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(InsertSql.class))
        {
            return "-1";
        }
        //拿到表名
        InsertSql insertSql = clazz.getAnnotation(InsertSql.class);
        String tableName = insertSql.tableName();
        //拼接
        sql.append(tableName).append("(");
        //接下来是属性值,这里有很多属性值，所以需要遍历出来
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(SqlColumn.class)){
                SqlColumn column = field.getAnnotation(SqlColumn.class);
                if(!columns.isEmpty()){
                    columns.append(",");
                    values.append(",");
                }
                columns.append(column.column());
                field.setAccessible(true);
                try {
                    values.append("'").append(field.get(object)).append("'");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        sql.append(columns).append(") ").append("VALUES ").append("(");
        sql.append(values).append(")").append(";");
        String insertsql = sql.toString();
        return insertsql;
    }
}
