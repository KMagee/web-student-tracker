package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDbUtil {
		
//METHOD THAT WILL LIST THE STUDENTS
	
	public List<Student> getStudents() throws Exception {
		
		//empty array list for students, used in loop
		List<Student> students = new ArrayList<>();
		
		//jdbc objects
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs  = null;
		
		
		try {
			
			//driver details
			Class.forName("com.mysql.jdbc.Driver");
			
			//get a connection
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_student_tracker?useSSL=false", "webstudent", "webstudent");
			
			
			//create a sql stmt
			String sql = "select * from student order by last_name";
			
			myStmt = myConn.createStatement();
		
			//execute query
			
			myRs = myStmt.executeQuery(sql);
			
			//process result set
			
			while (myRs.next()) {
				
				//retrieve data from results set row
				
				int id = myRs.getInt("id"); //column name from DB Table Student, name on left is the student object name
				String firstName = myRs.getString("first_name");
				String lastName  = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				
				//create new student object
				
				Student tempStudent = new Student(id, firstName, lastName, email);
				
				//add it to the list of students
				students.add(tempStudent);
			}
			
		
			
			return students;
		}
		
		finally {
			
			//close the jdbc objects
			
			close(myConn, myStmt, myRs);
			
			
		}	
	
	}

private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
	// TODO Auto-generated method stub
	
	try {
		
		if (myRs != null) {
			myRs.close();
		}
		
		if (myStmt != null) {
			myStmt.close();
		}
		
		if (myConn != null) {
			myConn.close();
		}
		
	}
	
	catch(Exception exc){
		exc.printStackTrace();
	}
	
}

public void addStudent(Student theStudent) throws Exception{
	
	//jdbc objects
	Connection myConn = null;
	PreparedStatement myStmt = null;
	
	
	try {
		
	///driver details
		Class.forName("com.mysql.jdbc.Driver");
		
		//get a connection
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_student_tracker?useSSL=false", "webstudent", "webstudent");
		
	// create the sql for insert
		String sql = "insert into student (first_name, last_name,email) values (?,?,?)";
	
		myStmt = myConn.prepareStatement(sql);
		
	//set the param values for the student
		myStmt.setString(1, theStudent.getFirstName());
		myStmt.setString(2, theStudent.getLastName());
		myStmt.setString(3, theStudent.getEmail());
		
	
	//execute th sql insert
		
		myStmt.execute();
		
	}
	finally {
	//clean up thejdbc objects
	close(myConn,myStmt,null);
		
		
	}
}


//part of the update, get the student requested
public Student getStudent(String theStudentId) throws Exception {

	Student theStudent = null;
	
	//jdbc objects
	Connection myConn = null;
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	int studentId;
	
	try {
		
		//convert Student id to int
		
		studentId = Integer.parseInt(theStudentId);
		
		///driver details
		Class.forName("com.mysql.jdbc.Driver");
		
		//get a connection
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_student_tracker?useSSL=false", "webstudent", "webstudent");
		
		
		//create sql to get selected student
		
		String sql = "select * from student where id=?";
		
		//create prepared statement
		
		myStmt = myConn.prepareStatement(sql);
		
		//set the parameters
		
		myStmt.setInt(1, studentId);
		
		
		//execute the query
		
		myRs = myStmt.executeQuery();		
		//retrieve the data
		if(myRs.next()) {
			
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			
		//use the studentID in the constructor to create a new student object
		theStudent = new Student(studentId,firstName,lastName,email);		
		} 
		else {
			throw new Exception("Could not find the student ID: " + studentId);
		}
		
		return theStudent;
	}
	
	finally {
		//close off jdbc objects
		close(myConn, myStmt, myRs);
	}
	

	
	
}

public void updateStudent(Student theStudent) throws Exception {
	
	//jdbc objects
	Connection myConn = null;
	PreparedStatement myStmt = null;

	try {
		///driver details
		Class.forName("com.mysql.jdbc.Driver");
				
		//get a connection
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_student_tracker?useSSL=false", "webstudent", "webstudent");
		
		// prepare statement
		String sql = "update student "
					+ "set first_name=?, last_name=?, email=? "
				    + "where id=?";
		
		myStmt = myConn.prepareStatement(sql);
		
		//set params
		myStmt.setString(1, theStudent.getFirstName());
		myStmt.setString(2, theStudent.getLastName());
		myStmt.setString(3, theStudent.getEmail());
		
		myStmt.setInt(4, theStudent.getId());
		
		//execute sql statement 
		myStmt.execute();
		}
	
	finally {
		close(myConn, myStmt, null);
			}
	}	
}

