package com.learningobjects.dean;


import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class TestOlapConnection {
    static String JDBC_DRIVER = credentials("JDBC_DRIVER");
    static String DB_URL = credentials("DB_URL");

    static String USER = credentials("USER");
    static String PASS = credentials("PASS");

    public TestOlapConnection() throws IOException, SAXException, ParserConfigurationException {
    }

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        //DatabaseMetaData meta = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");


            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //meta = conn.getMetaData();


            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Person_Dimension";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int person_id = rs.getInt("person_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String given_name = rs.getString("given_name");
                String middle_name = rs.getString("middle_name");
                String family_name = rs.getString("family_name");

                System.out.println("ID: " + person_id);
                System.out.println("  Username: " + username);
                System.out.println("  Email: " + email);
                System.out.println("  Given Name: " + given_name);
                System.out.println("  Middle Name: " + middle_name);
                System.out.println("  Family Name: " + family_name);
                //System.out.println("************");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Exit");
    }


    private static String credentials(String requestedIdentifier) {
        File file = new File("credentials.xml");
        String identifierData = null;

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document document = null;
        try {
            document = documentBuilder.parse(file);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap<String, String> credentialMap = new HashMap();
        credentialMap.put("JDBC_DRIVER", document.getElementsByTagName("driver").item(0).getTextContent());
        credentialMap.put("DB_URL", document.getElementsByTagName("url").item(0).getTextContent());
        credentialMap.put("USER", document.getElementsByTagName("user").item(0).getTextContent());
        credentialMap.put("PASS", document.getElementsByTagName("password").item(0).getTextContent());


        if (requestedIdentifier.equals("JDBC_DRIVER")) {
            identifierData = credentialMap.get("JDBC_DRIVER");

        } else if (requestedIdentifier.equals("DB_URL")) {
            identifierData =  credentialMap.get("DB_URL");

        } else if (requestedIdentifier.equals("USER")) {
            identifierData =  credentialMap.get("USER");

        } else if (requestedIdentifier.equals("PASS")) {
            identifierData = credentialMap.get("PASS");

        }
        return identifierData;
    }
}


