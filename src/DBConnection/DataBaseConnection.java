package DBConnection;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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
        String request="drop table "+tableName+";create table "+ tableName+"(";
       // Iterator<String> it=columns.keySet().iterator();
        for(Map.Entry<String,String> column : columns.entrySet())
        {
            request+=column.getKey()+" "+column.getValue()+",";

        }
        for(Map.Entry<String,String> constraint : constraints.entrySet())
        {
            if(constraint.getKey().equalsIgnoreCase("check")||constraint.getKey().equalsIgnoreCase("primary key")||constraint.getKey().equalsIgnoreCase("unique"))
                request+= "constraint cst_"+constraintNumber++ +" " +constraint.getKey()+" ("+constraint.getValue()+"),";
            else //case foreignkey
                request+="constraint cst_"+constraintNumber++  +" foreign key "+ constraint.getKey()+ " references "+ constraint.getValue()+",";
        }

        request=request.substring(0, request.length()-1);
        request+=");";
        System.out.println("the request is :\n"+request);
       Connection con= getConnection();
       PreparedStatement st=con.prepareStatement(request);
       st.executeUpdate();
    }
}
