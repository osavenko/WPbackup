package com.homework;

import java.util.ArrayList;
import java.util.Formatter;

public class WPTableSchema {
    private String tableName;
    private ArrayList<WPField> list = new ArrayList<>();

    public void addField(WPField field){
        list.add(field);
    }
    public String getTableName() {
        return tableName;
    }


    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    public void printRows(){
        System.out.println("-----------------");
        String strPrimaryKey = "";
        for(WPField field:list){
            System.out.print("NAME:"+field.getName());
            System.out.print(" "+field.getType());
            if (field.isNullable()){
                System.out.print(" NOT NULL");
            }

            if (field.getDefaultValue()!=null){
                System.out.print(" "+field.getDefaultValue());
            }
            if (field.isAutoincrement()){
                System.out.print(" auto_increment");
            }
            if(field.isPrimary()){
                strPrimaryKey = "PRIMARY KEY ("+field.getName()+")";
            }
            System.out.print("\n");
        }
        if (strPrimaryKey.length()>0)
            System.out.println(strPrimaryKey);
        System.out.println("-----END TABLE-----");
    }
    @Override
    public String toString() {
        String filds = "";
        if(list.size()>0){
            filds="[columns:";
            for (int i = 0; i < list.size(); i++) {
                if(i==0){
                    filds+=list.get(i).getName();
                } if((i+1)==list.size()){
                    filds+=list.get(i).getName()+"]}";
                } else{
                    filds+=", "+list.get(i).getName();
                }
            }
        }
        return "table{"+tableName+filds;
    }
}
