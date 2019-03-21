package com.homework;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static final String fName = "d:\\config.xml";
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, SQLException {
	// write your code here
        try {
            Connection conn = WPConnection.getSource(fName);
            WPSchema schema = new WPSchema();
            schema.scanFromWP(conn);
            if (schema.getTableInSchema()>0){
                ArrayList<WPTableSchema> list = schema.getTableList();
                for(WPTableSchema tableSchema:list){
                    System.out.println(tableSchema);
                }
            }

        }catch (SQLException ex){
            System.out.println("Проблема в работе с БД.");
        }
        //System.out.println(WPConnection.getRecipient(fName));
    }
}
