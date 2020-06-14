<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page errorPage="../error.jsp" %>
<head>
	<a href="${pageContext.request.contextPath}/" >Home</a>
	<meta charset="ISO-8859-1">
	<title>Users</title>
	<link rel="stylesheet"
		  href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
		  integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
		  crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
			integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
			crossorigin="anonymous"></script>
	<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
			integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
			crossorigin="anonymous"></script>
	<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
			integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
			crossorigin="anonymous"></script>
</head>
<h1>Users List</h1>
<table border="2" width="70%" cellpadding="2">
	<tr>
		<th>User ID</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Phone</th>
		<th>Email</th>
	</tr>
	<c:forEach var="users" items="${users}">
		<tr>
			<td>${users.userId}</td>
			<td>${users.firstName}</td>
			<td>${users.lastName}</td>
			<td>${users.phone}</td>
			<td>${users.email}</td>
			<td><a href="/edit/user/${users.userId}">Edit</a></td>
			<td><a href="/del/user/${users.userId}">Delete</a></td>
		</tr>
	</c:forEach>
</table>
<br/>
<a href="${pageContext.request.contextPath}/user/form">Add New User</a>