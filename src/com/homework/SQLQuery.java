package com.homework;

public class SQLQuery {
    public static String getSQLScanTables(String prefix){
        String query = "";
        if (prefix.length()>0){
            query = "SELECT * FROM information_schema.tables "+
                    "WHERE TABLE_NAME LIKE '"+prefix+"%'";
        } else{
            query = "SELECT * FROM information_schema.tables ";
        }
        return query;
    }
    public static String getSQLScanRowsInTable(String tableName){
        String query = "";
        if (tableName.length()>0){
            query = "SELECT COLUMN_NAME,COLUMN_DEFAULT, IS_NULLABLE, COLUMN_TYPE, "+
                            "EXTRA, COLUMN_KEY "+
                    "FROM information_schema.COLUMNS "+
                    "WHERE TABLE_NAME LIKE '"+tableName+"'";
        }
        return query;
    }
}
