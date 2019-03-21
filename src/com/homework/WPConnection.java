package com.homework;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/*
*Класс реализует два статических метода.
*   Каждый метод возвращает соединение к БД
*
* @see WPConnection#getRecipient(String)
* @see WPConnection#getSource(String)
 */
public class WPConnection {
    /*
    * Попытка установить соединение с БД для резервного копирования или переноса.
    * @param fName - имя файла с параметрами для соединения
    * @return Connection - возвращает соединение
    * @see WPConnection#getRecipient(String)
    */
    public static Connection getSource(String fName) throws ParserConfigurationException, IOException, SAXException, SQLException {
        Connection conn = null;
        Document doc = getDocument(fName);
        NodeList nList = doc.getElementsByTagName("source");
        conn = DriverManager.getConnection(getConnectionString(nList, 0));
        return conn;
    }
    /*
     * Попытка установить соединение с БД для загрузки в нее информации.
     * @param fName - имя файла с параметрами для соединения
     * @return Connection - возвращает соединение
     * @see WPConnection#getSource(String)
     */
    public static Connection getRecipient(String fName) throws IOException, SAXException, ParserConfigurationException, SQLException {
        Connection conn = null;
        Document doc = getDocument(fName);
        NodeList nList = doc.getElementsByTagName("recipient");
        conn = DriverManager.getConnection(getConnectionString(nList, 0));
        return conn;
    }

    private static String getConnectionString(NodeList nList, int i) {
        Node node = nList.item(i);
        String strConnection = "";
        if (node.getNodeType()== Node.ELEMENT_NODE){
            Element element = (Element)node;
            String strServerName = "jdbc:mysql://"+element.getElementsByTagName("site").item(0).getTextContent()+"/";
            String strBaseName = element.getAttribute("name");
            String strUserName = element.getElementsByTagName("user").item(0).getTextContent();
            String strPassword = element.getElementsByTagName("password").item(0).getTextContent();
            strConnection = strServerName+strBaseName+"?"+"user="+strUserName+"&password="+strPassword;
        }
        return strConnection;
    }

    private static Document getDocument(String fName) throws ParserConfigurationException, SAXException, IOException {
        File configFile = new File(fName);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(configFile);
        doc.getDocumentElement().normalize();
        return doc;
    }
}
