package de.home_skrobanek.browser.history;

import de.home_skrobanek.browser.Browser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *  Represents the history of this browser. There are a few simple functions like
 *  saving, reading and deleting content from/in the database where the history is
 *  stored.
 */
public class History {

    public History(){
        Browser.sql.update("CREATE TABLE IF NOT EXISTS history(name varchar(1000),site varchar(1000), " +
                             "time varchar(1000));");

    }

    public void addNewEntry(String title, String site){
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        Date date = new Date();

        Browser.sql.update("INSERT INTO history(name, site, time) VALUES " +
                "('"+title+"','"+site+"','"+formatter.format(date) + "');");
    }

    public ArrayList<HistoryData> getHistory(){
        try{
            ResultSet rs = Browser.sql.query("SELECT * FROM history;");
            ArrayList<HistoryData> data = new ArrayList<>();

            while(rs.next()){
                data.add(new HistoryData(rs.getString(1), rs.getString(2), rs.getString(3)));
            }

            return data;
        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    /*
        Clears the history
     */
    public void clearHistory(){
        Browser.sql.update("DELETE FROM history;");
    }
}
