package com.company;

import java.io.FileWriter;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Statement;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.Writer;


public class Main {
    public static void main(String[] args) {

        int choose;
        int usertype;
        String register_name, register_email, register_password;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Course Management System");
        System.out.println("1. Login for the system");
        System.out.println("2. Register for the system");
        choose = sc.nextInt();
        //choose to login or register to the course

	    switch(choose)
        {
            case 1:
                    //calling login function
                login();
                break;

            case 2:

                System.out.println("Name:");
                register_name = sc.next();
                System.out.println("Email:");
                register_email = sc.next();
                System.out.println("Password:");
                register_password = sc.next();
                System.out.println("User Type: Enter 1 for Student, 2 for Course Administrator, and 3 for Instructor");
                usertype = sc.nextInt();

                //Signup user
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3308/coursemanagementsystem","root","");
                    Statement stmt=con.createStatement();
                    String query = "insert into users (name,email,password,usertype) values('" +register_name+ "', '" +register_email+
                            "', '" +register_password+ "', " +usertype+ ")";
                    // Echo for debugging
//                    System.out.println("The SQL statement is: " + query + "\n");
                    int status = (stmt.executeUpdate(query));

                    if(status==1)
                    {
                        System.out.println("You are registered successfully. Press 1 to Login to the system.");
                        //After pressing 1 he will be redirected to login
                        int press_continue;
                        press_continue = sc.nextInt();
                        if(press_continue==1)
                        {
                            login();
                        }
                        else
                        {
                            //calling thankyou function as user is going to close the application
                            thankyou();
                        }
                    }
                    else
                    {

                    }
                    con.close();
                }catch(Exception e){ System.out.println(e);}
                break;
            default:
                System.out.println("Please choose the valid option. 1 for Login and 2 for Register");
        }
    }


    public static void thankyou()
    {
        System.out.println("Thank your for using the course management system.");
    }

    public static void login()
    {
        Scanner sc = new Scanner(System.in);
        String login_email, login_password;
        System.out.println("Email:");
        login_email = sc.next();
        System.out.println("Password:");
        login_password = sc.next();

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/coursemanagementsystem","root","");
            Statement stmt=con.createStatement();
            String testquery = "SELECT * FROM users Where email='"+login_email+"' AND password='"+login_password+"'";
            ResultSet rs= stmt.executeQuery(testquery);
            while(rs.next())
            {
                String useremail = rs.getString("email");
                String userpassword = rs.getString("password");
                int usertype = rs.getInt("usertype");
                int userid = rs.getInt("id");

                if(useremail.equals(login_email) && userpassword.equals(login_password))
                {
                    switch(usertype)
                    {
                        case 1:

                            System.out.println("You are logged in as Student.");

//                            String testquery1 = "SELECT * FROM courses Where student_id='"+userid+"'";
//                            ResultSet rs1= stmt.executeQuery(testquery1);
//                            while(rs1.next())
//                            {
//                                int level_id = rs1.getInt("level_id");
//                                System.out.println("You are studying in level "+level_id);
//                            }
                            System.out.println("Enter 1 to choose the course");
                            int coo = sc.nextInt();

                            switch(coo)
                            {

                                case 1:

                                    int course_id,semester_id,optional1,optional2;
                                    Scanner opt = new Scanner(System.in);
                                    System.out.println("Enter the course ID ");
                                    course_id = opt.nextInt();
                                    System.out.println("Enter the semester ID");
                                    semester_id = opt.nextInt();
                                    if(semester_id==6) {
                                        System.out.println("Enter the ID of the module for Optional subject1.");
                                        optional1 = opt.nextInt();
                                        System.out.println("Enter the ID of the Module for Optional subject2.");
                                        optional2 = opt.nextInt();
                                        String query = "insert into user_course (course_id,semester_id,optional1,optional2) values('" +course_id+ "', '" +semester_id+
                                                "', '" +optional1+ "', " +optional2+ ")";
                                        // Echo for debugging
//                    System.out.println("The SQL statement is: " + query + "\n");
                                        int status = (stmt.executeUpdate(query));
                                        if(status==1)
                                        {
                                            System.out.println("You are enrolled into the course successfully.");
                                        }
                                    }
                                    else {
                                        String query = "insert into user_course (course_id,semester_id) values('" +course_id+ "', '"+semester_id+
                                                "')";
                                        // Echo for debugging
//                    System.out.println("The SQL statement is: " + query + "\n");
                                        int status = (stmt.executeUpdate(query));
                                        if(status==1)
                                        {
                                            System.out.println("You are enrolled into the course successfully.");
                                        }
                                    }
                                    break;
                                default:
                                {
                                    System.out.println("Please press 1 to choose the course.");
                                }
                            }

                            break;

                        case 2:

                            int option;
                            System.out.println("You are logged in as Course Administrator.");
                            System.out.println("1. Add New Course");
                            System.out.println("2. Add Module to a Course");
                            System.out.println("3. Cancel a Course");
                            System.out.println("4. Delete a Course");
                            System.out.println("5. Generate Report/Result slip");
                            Scanner scr = new Scanner(System.in);
                            option = scr.nextInt();

                            switch(option)
                            {

                                case 1:

                                    String coursename;
                                    System.out.println("Course Name:");
                                    coursename = scr.next();
                                    try{
                                        Statement stmt1=con.createStatement();
                                        String query1 = "insert into courses (name) values('" +coursename+ "')";
                                        // Echo for debugging
//                    System.out.println("The SQL statement is: " + query1 + "\n");
                                        int status = (stmt1.executeUpdate(query1));
                                        if(status==1)
                                        {
                                            System.out.println("Course is added successfully.");
                                        }
                                        con.close();
                                    }catch(Exception e){ System.out.println(e);}
                                break;

                                case 2:

                                    String modulename,course_id,semester_id;
                                    System.out.println("Enter Module Name:");
                                    modulename = scr.next();
                                    System.out.println("Enter Course No (ID):");
                                    course_id = scr.next();
                                    System.out.println("Enter Semester No (ID):");
                                    semester_id= scr.next();
                                    try{
                                        Statement stmt2=con.createStatement();
                                        String query2 = "insert into modules (name,course_id,semester_id) values('" +modulename+ "', '" +course_id+"','"+semester_id+"')";
                                        // Echo for debugging
//                    System.out.println("The SQL statement is: " + query1 + "\n");
                                        int status = (stmt2.executeUpdate(query2));
                                        if(status==1)
                                        {
                                            System.out.println("Module is added successfully.");
                                        }
                                        con.close();
                                    }catch(Exception e){ System.out.println(e);}
                                    break;

                                case 3:

                                    System.out.println("Cancel a course.");
                                    break;

                                case 4:

                                    System.out.println("Do you want to delete course ? ");
                                        try{
                                            System.out.println("The available courses are:");
                                            ResultSet rs3= stmt.executeQuery("SELECT * FROM courses");
                                            while(rs3.next())
                                            {
                                                System.out.println("ID: "+rs3.getInt(1)+" Course Name: "+rs3.getString(2));
                                            }
                                            int n;
                                            System.out.println("Enter the id of the course that you want to delete?");
                                            n = scr.nextInt();
                                            Statement stmt4=con.createStatement();
                                            String query4 = "DELETE FROM courses WHERE id='"+n+"'";
                                            // Echo for debugging
                                            System.out.println("The SQL statement is: " + query4 + "\n");
                                            int status = (stmt4.executeUpdate(query4));
                                            if(status==1)
                                            {
                                                System.out.println("Course is deleted successfully.");
                                            }
                                            con.close();
                                        }catch(Exception e){ System.out.println(e);}
                                    break;

                                case 5:

                                    System.out.println("Enter the student id to generate report/slip.");
                                    System.out.println("The available students are:");
                                    try{
                                        System.out.println("The available courses are:");
                                        ResultSet rs5= stmt.executeQuery("SELECT * FROM users where usertype=1");
                                        while(rs5.next())
                                        {
                                            System.out.println("ID: "+rs5.getInt(1)+" Student Name: "+rs5.getString(2));
                                        }
                                        int user_id;
                                        Scanner scn = new Scanner(System.in);
                                        user_id = scn.nextInt();
                                        String query6="SELECT * FROM resultslip WHERE student_id="+user_id+"";
//                                    System.out.println("The SQL statement is: " + query6 + "\n");
                                        ResultSet rs6 = stmt.executeQuery(query6);
                                        Writer output = new FileWriter("output.txt");
                                        while(rs6.next())
                                        {
                                            String result = "Student ID: "+rs6.getInt(2)+" Module1: "+rs6.getString(3)+" Module2: "+rs6.getString(4)+" Module3: "+rs6.getString(5)+" Module4: "+rs6.getString(6)+"";

                                            // Closes the writer
                                            if(rs6.getInt(3)>=40&&rs6.getInt(4)>=40&&rs6.getInt(5)>=40&&rs6.getInt(6)>=40)
                                            {
                                                String finalresult = result+" \n Decision: This student will progress to the next level of study.";
                                                output.write(finalresult);
                                            }
                                            else
                                            {
                                                String finalresult = result+" \n Decision: This student won't progress to the next level of study.";
                                                output.write(finalresult);
                                            }
                                                output.close();
                                        }
                                        con.close();
                                    }catch(Exception e){ System.out.println(e);}

                                    break;
                                default:
                                    System.out.println("Please choose either 1,2,3 or 4.");
                            }
                            break;

                        case 3:

                            System.out.println("You are logged in as Course Instructor.");
                            System.out.println("1. See your modules");
                            System.out.println("2. Add marks");
                            int choose_part;
                            Scanner scs = new Scanner(System.in);
                            choose_part = scs.nextInt();
                            switch(choose_part)
                            {

                                case 1:

                                    System.out.println("View Modules:");
                                    System.out.println("The available modules are:");
                                    ResultSet rs10= stmt.executeQuery("SELECT * FROM modules");
                                    while(rs10.next())
                                    {
                                        System.out.println("ID: "+rs10.getInt(1)+" Moduile Name: "+rs10.getString(2));
                                    }
                                    break;
                                case 2:

                                    System.out.println("Add Marks:");
                                    int module1_marks, module2_marks, module3_marks, module4_marks, student_id;
                                    System.out.println("The available students are:");
                                    ResultSet rs4= stmt.executeQuery("SELECT * FROM users where usertype=1");
                                    while(rs4.next())
                                    {
                                        System.out.println("ID: "+rs4.getInt(1)+" Student Name: "+rs4.getString(2));
                                    }
                                    System.out.println("Enter the  id of the student:");
                                    student_id = scs.nextInt();
                                    System.out.println("Enter the marks of Module 1");
                                    module1_marks = scs.nextInt();
                                    System.out.println("Enter the marks of Module 2");
                                    module2_marks = scs.nextInt();
                                    System.out.println("Enter the marks of Module 3");
                                    module3_marks = scs.nextInt();
                                    System.out.println("Enter the marks of Module 4");
                                    module4_marks = scs.nextInt();
                                    String query5 = "insert into resultslip (student_id,module1,module2,module3,module4) values('" +student_id+ "', '" +module1_marks+"','"+module2_marks+"','"+module3_marks+"','"+module4_marks+"')";
                                    System.out.println("The SQL statement is: " + query5 + "\n");
                                    int status = (stmt.executeUpdate(query5));
                                    if(status==1)
                                    {
                                        System.out.println("Marks is added successfully to the student id:"+student_id);
                                    }
                            }
                            break;

                        default:
                            System.out.println("You are invalid User.");
                    }
                }
                else
                {
                    System.out.println("Wrong Credentials.");
                    break;
                }
            }

            con.close();

        }catch(Exception e){ System.out.println(e);}
    }
}


