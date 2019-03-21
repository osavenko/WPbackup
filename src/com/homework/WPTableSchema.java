package com.homework;

import java.util.ArrayList;

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
