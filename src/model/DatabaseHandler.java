/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nizoodxs
 */
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;

public final class DatabaseHandler {

    private static DatabaseHandler handler = null;

    private static Connection conn = null;
    private static Statement stmt = null;

    private DatabaseHandler() {
        createConnection();
        setupFilesTable();

    }

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ediscovery", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cant load database", "Database Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    void setupFilesTable() {
        String TABLE_NAME = "Files";
        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + "already exists. Ready for go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + "	id int(11) primary key,\n"
                        + "	file_name varchar(20),\n"
                        + "	created_date date,\n"
                        + "	modified_date date,\n"
                        + "	author varchar(50),\n"
                        + "	path varchar(100),\n"
                        + "	document_type varchar(10)\n"
                        + " )");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " --- setupDatabase");
        } finally {
        }
    }

    public static void insertMetadata(HashMap<String, String> metadataMap, String name, String type) {
        String TABLE_NAME = "Files";
        String modified_date = "";
        String created_date = "";
        String author = "";
        String path = "";

        Iterator it = metadataMap.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry pair = (Map.Entry) it.next();

            if (pair.getKey().toString().contains("modified")) {
                modified_date = pair.getValue().toString();
            }
            if (pair.getKey().toString().contains("created")) {
                created_date = pair.getValue().toString();
            }
            if (pair.getKey().toString().contains("path")) {
                path = pair.getValue().toString();
            }
            if (pair.getKey().toString().contains("author")) {
                author = pair.getValue().toString();
            }

        }

        try {
            stmt = conn.createStatement();

            DatabaseMetaData dbm = conn.getMetaData();
            {
                stmt.execute("INSERT INTO " + TABLE_NAME + "(file_name,created_date,modified_date,author,path,document_type) "
                        + "VALUES("+name+","+created_date+","+modified_date+","+author+","+path+","+type+")");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " --- setupDatabase");
        } finally {
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }

    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        } finally {
        }
    }
}
