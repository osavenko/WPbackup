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
            //Read all tables name from information_schema.tables with  prefix the tables
            ResultSet rsTables = statement.executeQuery(SQLQuery.getSQLScanTables("wp"));
            while(rsTables.next()){
                listTable.add(rsTables.getString("TABLE_NAME"));
            }
            rsTables.close();
            // for each table read her fields(columns) from information_schema.COLUMNS
            //
            for(String name:listTable){
                WPTableSchema tableSchema = new WPTableSchema();
                tableSchema.setTableName(name);
                ResultSet rsRows = statement.executeQuery(SQLQuery.getSQLScanRowsInTable(tableSchema.getTableName()));
                while (rsRows.next()){
                    WPField field = new WPField();
                    field.setName(rsRows.getString("COLUMN_NAME"));

                    String defValue = rsRows.getString("COLUMN_DEFAULT");
                    if (defValue!=null){
                        String temp = "";
                        try{
                            int tempI = Integer.parseInt(defValue);
                            temp = Integer.toString(tempI);
                        } catch (Exception e){
                            temp = "'"+defValue+"'";
                        }
                        field.setDefaultValue("default "+temp);
                    }else {
                        field.setDefaultValue("");
                    }

                    String isNullable = rsRows.getString("IS_NULLABLE");
                    if (isNullable.equals("YES")){
                        field.setNullable(true);
                    } else{
                        field.setNullable(false);
                    }
                    field.setType(rsRows.getString("COLUMN_TYPE"));

                    String tmpExtra = rsRows.getString("EXTRA");
                    if (tmpExtra.equals("auto_increment")){
                        field.setAutoincrement(true);
                    }else{
                        field.setAutoincrement(false);
                    }

                    String tmpPrimary = rsRows.getString("COLUMN_KEY");
                    if (tmpPrimary.equals("PRI")){
                        field.setPrimary(true);
                    }else{
                        field.setPrimary(false);
                    }

                    if (tmpPrimary.equals("MUL")){
                        field.setIndex(true);
                    } else{
                        field.setIndex(false);
                    }
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
