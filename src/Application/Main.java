package Application;

import DBConnection.DataBaseConnection;
import org.postgresql.util.GettableHashMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       // try {
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
//            HashMap<String, String>  columns = new HashMap<>();
//            columns.put("bookId","int");
//            columns.put("memberId","int");
//            columns.put("borrowingDate","date not null");
//            columns.put("duration","int");
//            columns.put("email","varchar(30)");
//            columns.put("state","boolean");
//
//            HashMap<String, String> constraints = new HashMap<>();
//            constraints.put("primary key","bookId,memberId");
//            constraints.put("bookId","Book(id)");
//            constraints.put("memberId","Member(id)");
//
//            DataBaseConnection.createTable("Borrowing", columns, constraints);
//            DataBaseConnection.closeConnection();
//            Scanner sc=new Scanner(System.in);
//            System.out.println("Enter a path for book's photo ");
//            String path = sc.next();
//            DataBaseConnection.insertImage(path,"book",1);
//        }catch(Exception e){e.printStackTrace();}
//

        List<Integer> lst= Arrays.asList(5,11,2,3,66);
        //show cube values of lst
        lst.stream()
                .map(x->Math.pow(x,3)) //replace each value x of the stream by x^3
                //.forEach(x-> System.out.println(x)); //or
                .forEach(System.out::println); //method reference

    }
}
