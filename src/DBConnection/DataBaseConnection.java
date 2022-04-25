package DBConnection;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseConnection {
    private static final String url="jdbc:postgresql://localhost:5432/libdb";
    private static final String username="user01";
    private static final String password="user01";
    private static Connection con;
    public static Connection getConnection() {
        con=null;
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
            if(constraint.getKey().equalsIgnoreCase("check")||constraint.getKey().equalsIgnoreCase("primary key")||constraint.getKey().equalsIgnoreCase("unique"))
                request+= "constraint cst_"+tableName +"_"+ constraint.getValue().split(",")[0]+" " +constraint.getKey()+" ("+constraint.getValue()+"),";
            else //case foreignkey
                request+="constraint cst_"+tableName +"_"+ constraint.getValue().split("\\(")[0]  +" foreign key ("+ constraint.getKey()+ ") references "+ constraint.getValue()+",";
        }

        request=request.substring(0, request.length()-1);
        request+=");";
        System.out.println("the request is :\n"+request);
       con= getConnection();
       PreparedStatement st=con.prepareStatement(request);
       st.executeUpdate();
        System.out.println("Table "+tableName+" has been successfully created.");
    }

    public static void closeConnection() throws SQLException {
        con.close();
    }
   public static void insertImage(String path,String tableName, int idOwner)
   {
       try {
           File f1=new File(path);
           FileInputStream fs=new FileInputStream(f1);
           String request="update "+tableName+ " set photo =? where id =?";
           con=getConnection();
           PreparedStatement st=con.prepareStatement(request);
           st.setBinaryStream(1,fs,f1.length());
           st.setInt(2,idOwner);
           st.executeUpdate();
           st.close();
           closeConnection();
       }
       catch (Exception e) {e.printStackTrace();}
   }
   public static void retreiveImage(int id)
   {

   }
}
