package de.home_skrobanek.browser.utils.sql;

import java.sql.*;


public class SQLite {

    private String DATABASE = "";

    private boolean reconnect = true;
    private boolean mute = false;

    private Connection con;

    /**
     @param DATABASE
     Legt die Datenbank fest, die angesprochen werden soll.

     */
    public SQLite(String DATABASE) {
        this.DATABASE = DATABASE;

        if(!mute) {
            System.out.println("Connecting to Database");
        }

        connect();
    }

    public SQLite(String DATABASE, boolean autoConnect, boolean thread) {
        this.DATABASE = DATABASE;

        if(!mute) {
            System.out.println("[Information]: Connecting to Database");
        }

        if(autoConnect && thread) {
            new Thread( new Runnable(){
                @Override
                public void run() {
                    connect();

                }}).start();
        }
        else if(autoConnect && !thread) {
            connect();
        }
    }

    /**
     * Stellt eine Verbindung zur Datenbank her.
     */
    public DatabaseMetaData connect() {
        try {
            con = DriverManager.getConnection("jdbc:sqlite:resources/sqlite/" + DATABASE);

            if(!mute) {
                System.out.println("===========================");
                System.out.println("Connected to MySQL database");
                System.out.println("===========================");
            }

            return con.getMetaData();
        }catch(SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * Beendet eine bestehende Verbindung
     */
    public void cancelConnection() {
        try {
            if(con != null) {
                con.close();
            }
            else {
                if(!mute) {
                    System.out.println("[Information]: Can't disconnect if no connection available");
                }
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param query
     *  Ermöglicht es Befehle an die Datenbank zu schicken. Hier können zbs
     *  Tabellen erstellt und Daten eingetragen werden. Im Parameter wird
     *  SQL-Code erwartet.
     */
    public void update(String query) {
        try {
            java.sql.Statement st = con.createStatement();
            st.execute(query);
            st.close();

        }catch(SQLException ex) {
            connect();
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param query
     *  Mit dieser Methode können Daten aus der Datenbank ausgelesen werden.
     *  Der Parameter nimmt SQL-Code entgegen.
     *
     * @return
     * Die Daten werden als @ResultSet zurückgegeben und müssen
     * dementsprechend noch in das richtige Format gecasted werden.
     */
    public ResultSet query(String query) {
        ResultSet rs = null;

        try {
            java.sql.Statement st = con.createStatement();
            rs = st.executeQuery(query);
        }catch(SQLException ex) {
            connect();
            ex.printStackTrace();
        }

        return rs;
    }

    /**
     *
     * @param query
     *  Mit dieser Methode können Daten aus der Datenbank ausgelesen werden.
     *  Der Parameter nimmt SQL-Code entgegen.
     *
     * @return
     * Die Daten werden als @Object zurückgegeben und müssen
     * dementsprechend noch in das richtige Format gecasted werden.
     */
    public Object queryObject(String query) {
        ResultSet rs = null;

        try {
            java.sql.Statement st = con.createStatement();
            rs = st.executeQuery(query);
            return rs.getObject(1);
        }catch(SQLException ex) {
            connect();
            ex.printStackTrace();
        }
        return null;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }
}
