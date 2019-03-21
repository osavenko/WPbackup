package com.homework;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WPSchema {
    private ArrayList<WPTableSchema> list = new ArrayList();


    public WPSchema(){}

    /*
    * Метод возвращает количество таблиц в схеме
    * @return целое число, которое указывает на то,
    *         сколько таблиц в текущей схеме
    * @see WPSchema#scanFromWP(Connection) сканирует таблици из базе данных источника
    *                                      и загружает их в текущую схему.
    * @see WPSchema#loadFromFile(String) загружает из файла настроек
    *                                    схему таблиц для работы
    */
    public int getTableInSchema(){
        return list.size();
    }

    public ArrayList<WPTableSchema> getTableList(){
        return list;
    }

    public void scanFromWP(Connection conn) throws SQLException {
        ArrayList<String> listTable = new ArrayList<>();
        if (conn != null){
            Statement statement = conn.createStatement();
            ResultSet rsTables = statement.executeQuery(SQLQuery.getSQLScanTables("wp"));
            while(rsTables.next()){
                listTable.add(rsTables.getString("TABLE_NAME"));
            }
            rsTables.close();
            for(String name:listTable){
                WPTableSchema tableSchema = new WPTableSchema();
                tableSchema.setTableName(name);
                ResultSet rsRows = statement.executeQuery(SQLQuery.getSQLScanRowsInTable(tableSchema.getTableName()));
                while (rsRows.next()){
                    WPField field = new WPField();
                    field.setName(rsRows.getString("COLUMN_NAME"));
                    tableSchema.addField(field);
                }
                list.add(tableSchema);
                rsRows.close();
            }
            conn.close();
        }
    }
    public void createOnRecipient(Connection conn) throws SQLException {
        if(getTableInSchema()!=0){

        }else{
            conn.close();
        }
    }
    public void loadFromFile(String fName){

    }
}
