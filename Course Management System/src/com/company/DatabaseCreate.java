package com.company;
import javax.xml.transform.Result;
import java.sql.*;

//creating database
public class DatabaseCreate {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3308/";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";

    //Constructor OOP Feature
    DatabaseCreate() {
        Connection conn = null;
        Statement stmt = null;

        //Creating database and table code goes here...
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Configuring database and tables...");
            stmt = conn.createStatement();
            //Query that creates a database named testjava

                    //test code
            //finding database exist or not
            ResultSet rs = stmt.executeQuery("select schema_name from information_schema.schemata where schema_name = 'testjava'");
            while(rs.next()) {
                String db_name = rs.getString("schema_name");
                if(db_name.equals("testjava")) {
                    System.out.println("Database already exist...");
                }
                else
                {
                    String sql = "CREATE DATABASE testjava";
                    stmt.executeUpdate(sql);
                    System.out.println("Database created successfully...");
                }
            }

            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/testjava","root","");
            Statement stmt1=con.createStatement();


            //1
            java.sql.DatabaseMetaData dbm1 = con.getMetaData();
            rs = dbm1.getTables(null, null, "courses", null);
            if (rs.next()) {
                System.out.println("Courses Table already Exists !");
            }else{
                String sql1 = "CREATE TABLE courses " +
                        "(id INTEGER not NULL auto_increment, " +
                        " name VARCHAR(255), " +
                        " status integer(255) default 1, " +
                        " PRIMARY KEY ( id ))";
                stmt1.executeUpdate(sql1);
            }

            //2
            java.sql.DatabaseMetaData dbm2 = con.getMetaData();
            rs = dbm2.getTables(null, null, "modules", null);
            if (rs.next()) {
                System.out.println("Modules Table already Exists !");
            }else{
                String sql2 = "CREATE TABLE modules " +
                        "(id INTEGER not NULL auto_increment, " +
                        " name VARCHAR(255), " +
                        " course_id INTEGER, " +
                        " semester_id INTEGER, " +
                        " status integer(255) default 1, " +
                        " PRIMARY KEY ( id ))";
                stmt1.executeUpdate(sql2);
            }

            //3
            java.sql.DatabaseMetaData dbm3 = con.getMetaData();
            rs = dbm3.getTables(null, null, "resultslip", null);
            if (rs.next()) {
                System.out.println("Resultslip Table already Exists !");
            }else{
                String sql3 = "CREATE TABLE resultslip " +
                        "(id INTEGER not NULL auto_increment, " +
                        " student_id INTEGER, " +
                        " module1 INTEGER, " +
                        " module2 INTEGER, " +
                        " module3 INTEGER, " +
                        " module4 INTEGER, " +
                        " status integer(255) default 1, " +
                        " PRIMARY KEY ( id ))";
                stmt1.executeUpdate(sql3);
            }

            //4
            java.sql.DatabaseMetaData dbm4 = con.getMetaData();
            rs = dbm4.getTables(null, null, "semesters", null);
            if (rs.next()) {
                System.out.println("Semesters Table already Exists !");
            }else{
                String sql4 = "CREATE TABLE semesters " +
                        "(id INTEGER not NULL auto_increment, " +
                        " course_id INTEGER, " +
                        " name VARCHAR(255), " +
                        " status integer(255) default 1, " +
                        " PRIMARY KEY ( id ))";
                stmt1.executeUpdate(sql4);
            }

            //5
            java.sql.DatabaseMetaData dbm5 = con.getMetaData();
            rs = dbm5.getTables(null, null, "users", null);
            if (rs.next()) {
                System.out.println("Users Table already Exists !");
            }else{
                String sql5 = "CREATE TABLE users " +
                        "(id INTEGER not NULL auto_increment, " +
                        " name VARCHAR(255), " +
                        " email VARCHAR(255), " +
                        " password VARCHAR(255), " +
                        " user_type INTEGER, " +
                        " status integer(255) default 1, " +
                        " PRIMARY KEY ( id ))";
                stmt1.executeUpdate(sql5);
            }

            //6
            java.sql.DatabaseMetaData dbm6 = con.getMetaData();
            rs = dbm6.getTables(null, null, "user_course", null);
            if (rs.next()) {
                System.out.println("User_Course Table already Exists !");
            }else{
                String sql6 = "CREATE TABLE user_course " +
                        "(id INTEGER not NULL auto_increment, " +
                        " student_id INTEGER, " +
                        " course_id INTEGER, " +
                        " semester_id INTEGER, " +
                        " module1 INTEGER, " +
                        " module2 INTEGER, " +
                        " module3 INTEGER, " +
                        " module4 INTEGER, " +
                        " optional1 INTEGER, " +
                        " optional2 INTEGER, " +
                        " status integer(255) default 1, " +
                        " PRIMARY KEY ( id ))";
                stmt1.executeUpdate(sql6);
            }
            System.out.println("Database Configuration is done successfully...");

        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try



    }//end main
}


