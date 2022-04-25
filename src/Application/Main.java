package Application;

import DBConnection.DataBaseConnection;
import org.postgresql.util.GettableHashMap;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
            HashMap<String, String> columns = new HashMap<>();
            columns.put("id","serial");
            columns.put("title","varchar(50) not null");
            HashMap<String, String> constraints = new HashMap<>();
            constraints.put("primary key","id");
            DataBaseConnection.createTable("Book", columns, constraints);
        }catch(Exception e){e.printStackTrace();}
    }
}
