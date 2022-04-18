package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

public class DataBaseConnection {
    private static final String url="jdbc:postgresql://localhost:5432/libdb";
    private static final String username="user01";
    private static final String password="user01";
    private static int constraintNumber=1;
    public static Connection getConnection() {
        Connection con=null;
        try {
            con= DriverManager.getConnection(url,username,password);
            System.out.println("Connection has been successfully established.");
        }catch(SQLException e) {
           e.printStackTrace();
        }
        return con;
    }
    public static void createTable(String tableName, HashMap<String,String> columns, HashMap<String,String> constraints) throws SQLException
    {
        String request="create table "+ tableName+"(";
       // Iterator<String> it=columns.keySet().iterator();
        for(Map.Entry<String,String> column : columns.entrySet())
        {
            request+=column.getKey()+" "+column.getValue()+",";

        }
        for(Map.Entry<String,String> constraint : constraints.entrySet())
        {
            if(constraint.getKey().equalsIgnoreCase("check")||constraint.getKey().equalsIgnoreCase("primery key")||constraint.getKey().equalsIgnoreCase("unique"))
                request+= "constraint cst_"+constraintNumber++ +constraint.getKey()+" "+constraint.getValue()+",";
            else //case foreignkey
                request+="constraint cst_"+constraintNumber++ +" foreign key "+ constraint.getKey()+ " references "+ constraint.getValue()+",";
        }
        request+=");";

    }
}
