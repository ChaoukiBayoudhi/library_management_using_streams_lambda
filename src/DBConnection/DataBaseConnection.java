package DBConnection;

import BookManagement.AuthorBook;
import BookManagement.Book;
import BorrowingPackage.Borrowing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;


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
   public static void retreiveImage(String tableName, int idOwner)
   {
       try {
           String request="select photo from  "+tableName+ " where id =?";
           con=getConnection();
           PreparedStatement st=con.prepareStatement(request);
           st.setInt(1,idOwner);
           ResultSet rs=st.executeQuery();
           rs.next();
           InputStream inpts=rs.getBinaryStream(1);
           rs.close();
           FileOutputStream fout=new FileOutputStream("images/img"+idOwner+".gif");
           int k;
           while((k=inpts.read()) != -1)
               fout.write(k);
           System.out.println("The photo is uploaded.");
           st.close();
           closeConnection();
       }
       catch (Exception e) {e.printStackTrace();}


   }
   public static ResultSet ExecuteSelect(String request)
   {
       ResultSet rs=null;
       try {
           con=getConnection();
           PreparedStatement st=con.prepareStatement(request);
           rs=st.executeQuery();
           st.close();
           closeConnection();
       }catch(SQLException e)
       {
           e.printStackTrace();
       }
       return rs;
   }
   public static List<Book> getAllBooks()
   {
       ResultSet rs=null;
       List<Book> books=new ArrayList<>();
       try {
           String request="select * from book";
           con=getConnection();
           PreparedStatement st=con.prepareStatement(request);
           rs=st.executeQuery();
            while(rs.next())
            {
                Book b1=new Book();
                b1.setIsbnCode(rs.getInt("id"));
                b1.setTitle(rs.getString("title"));
                b1.setReleaseDate(rs.getObject("releasedate", LocalDate.class));

                b1.setPrice(rs.getBigDecimal("price"));
                b1.setStock(rs.getInt("stock"));
                books.add(b1);
            }
           st.close();

           closeConnection();
       }catch(SQLException e)
       {
           e.printStackTrace();
       }
       return books;
   }
   public static void executeInsertOrUpdateOrDelete(String request)
   {
       try {
           con=getConnection();
           PreparedStatement st=con.prepareStatement(request);
           st.executeUpdate();
           st.close();
           closeConnection();
       }catch(SQLException e)
       {
           e.printStackTrace();
       }
   }

    public static List<AuthorBook> getAuthorBooks(int id) {
        List<AuthorBook> books=new ArrayList<AuthorBook>();
        ResultSet rs=null;
        try {
            String request="select * from book,author,writing_books where book.id=book_id and author.id=author_id";
            con=getConnection();
            PreparedStatement st=con.prepareStatement(request);
            rs=st.executeQuery();
            while(rs.next())
            {
//                Book b1=new Book();
//                b1.setIsbnCode(rs.getInt("id"));
//                b1.setTitle(rs.getString("title"));
//                b1.setReleaseDate(rs.getObject("releasedate", LocalDate.class));
//
//                b1.setPrice(rs.getBigDecimal("price"));
//                b1.setStock(rs.getInt("stock"));
//                books.add(b1);
            }
            st.close();

            closeConnection();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return books;
    }

    public static List<Borrowing> getAllBorrowings() {
        ResultSet rs=null;
        List<Borrowing> borrowings=new ArrayList<>();
        try {
            String request="select * from borrowing";
            con=getConnection();
            PreparedStatement st=con.prepareStatement(request);
            rs=st.executeQuery();
            while(rs.next())
            {
                Borrowing b1=new Borrowing();
                b1.setId(rs.getInt("id"));
                b1.setBorrowingDate(rs.getObject("borrowingdate", LocalDate.class));

                b1.setDuration(rs.getInt("duration"));
                b1.setStatus(rs.getString("status"));
                borrowings.add(b1);
            }
            st.close();
            closeConnection();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return borrowings;
    }
}
