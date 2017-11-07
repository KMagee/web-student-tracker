package com.luv2code.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	//set up ref to the db util
	
	private StudentDbUtil studentDbUtil;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
			
		try {
			
			studentDbUtil = new StudentDbUtil();
			
		}
		
		catch(Exception exc){
		throw new ServletException(exc);
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	
		try {
			
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			//default to show all list
			if (theCommand == null) {
				
				theCommand  = "LIST";
			}
			
			// route to the appropriate method
			
			switch(theCommand) {
			
			
			case "LIST":
				listStudents(request,response);
				break;
				
			case "ADD":
				addStudent(request, response);
				break;
			
			case "LOAD":
				loadStudent(request,response);
				
				
			case "UPDATE":
				updateStudent(request, response);
				break;
				
			default:
				listStudents(request, response);
			
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}
			
		
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read student info from form
		
		int id =Integer.parseInt(request.getParameter("studentId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");

		
		//create a new student object
		Student theStudent = new Student(id, firstName, lastName, email);
	
			
		//peform the update on the database
		studentDbUtil.updateStudent(theStudent);
		
		
		//send the user back to the DB
		listStudents(request,response);
		
		
		
	}

	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read the student form
		
		//studentId from the Update Link
		String theStudentId = request.getParameter("studentId");
		
		//get the student from the db
		Student theStudent = studentDbUtil.getStudent(theStudentId);
		
		//place the student in the request attribute
		request.setAttribute("THE_STUDENT", theStudent);
		
		//send to the jsp page
		RequestDispatcher dispatcher
		  = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
	}

	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read student info from the form data
		//using the Form names:
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		
		//create a new student object
		Student theStudent = new Student(firstName,lastName,email);
		
		
		//add the student to the database
		studentDbUtil.addStudent(theStudent);
		
		//send back to the main page
		
		listStudents(request, response);
		
	}

	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get students from db util
		List<Student> students = studentDbUtil.getStudents();
		
		//add students to the request object
		request.setAttribute("STUDENT_LIST", students);
		
		//send the data to jsp page
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

}
