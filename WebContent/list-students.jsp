<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Tracker App</title>

<link type="text/css" rel="stylesheet" href="css/style.css">

</head>

<body>


<div id="wrapper">
	<div id="header">
	<h2>All Students</h2>	
	</div>
</div>

<div id="container">

	<div id="content">
	
	
	<!-- add in the new button. CHANGE THE INLINE ONCLICK TO EXTERNAL JAVASCRIPT -->
	
	<input type="button" value="Add Student" 
	       onclick="window.location.href='add-student-form.jsp'; return false;"
		   class="add-student-button"
	/>
	
	
		<table border=1>
		
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action</th>
			</tr>
		
		<c:forEach var="tempStudent" items="${STUDENT_LIST}"> <!-- JSTL using request.getAttribute(STUDENT_LIST) -->
		
			<!-- set up an UPDATE link for each student in the STUDENT_LIST, add the LOAD command and the unique ID to the URL -->
			<c:url var="tempLink" value="StudentControllerServlet">
				<c:param name="command" value="LOAD"/>
				<c:param name="studentId" value="${tempStudent.id}"/>
			</c:url>
			<!-- Add a link for the DELETE -->
				<c:url var="deleteLink" value="StudentControllerServlet">
				<c:param name="command" value="DELETE"/>
				<c:param name="studentId" value="${tempStudent.id}"/>
			</c:url>
			
	
					<tr>
						<td> ${tempStudent.firstName} </td> <!--JSTL using tempStudent.getFirstName() getter method  -->
						<td> ${tempStudent.lastName}</td>
						<td> ${tempStudent.email} </td>
						<td><a href="${tempLink}">Update</a>
							| 
							<a href="${deleteLink}" onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
						</td>
					</tr>
		
		</c:forEach>
		
		</table>	
		
	</div>

</div>



</body>
</html>