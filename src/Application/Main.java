package Application;

import DBConnection.DataBaseConnection;
import org.postgresql.util.GettableHashMap;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
            //crate Book table
//            HashMap<String, String> columns = new HashMap<>();
//            columns.put("id","serial");
//            columns.put("title","varchar(50) not null");
//            columns.put("releaseDate","date");
//            columns.put("price","real");
//            columns.put("stock","int default 0");
//            columns.put("photo","bytea");
//            columns.put("summary","varchar(200)");
//            columns.put("type","varchar(20)");
//
//            HashMap<String, String> constraints = new HashMap<>();
//            constraints.put("primary key","id");
//            DataBaseConnection.createTable("Book", columns, constraints);
            //create table Member
           // HashMap<String, String>  columns = new HashMap<>();
//            columns.put("id","serial");
//            columns.put("firstName","varchar(50) not null");
//            columns.put("lastName","varchar(50) not null");
//            columns.put("birthdate","date");
//            columns.put("email","varchar(30)");
//            columns.put("photo","bytea");
//            columns.put("phoneNumber","varchar(20)");
//            columns.put("type","varchar(20)");
//            columns.put("endSubscriptionDate","date");
//
//            HashMap<String, String> constraints = new HashMap<>();
//            constraints.put("primary key","id");
//            DataBaseConnection.createTable("Member", columns, constraints);

            //create table Borrowing
            HashMap<String, String>  columns = new HashMap<>();
            columns.put("bookId","int");
            columns.put("memberId","int");
            columns.put("borrowingDate","date not null");
            columns.put("duration","int");
            columns.put("email","varchar(30)");
            columns.put("state","boolean");

            HashMap<String, String> constraints = new HashMap<>();
            constraints.put("primary key","bookId,memberId");
            constraints.put("bookId","Book(id)");
            constraints.put("memberId","Member(id)");

            DataBaseConnection.createTable("Borrowing", columns, constraints);

        }catch(Exception e){e.printStackTrace();}
    }
}
