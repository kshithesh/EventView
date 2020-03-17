<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>
</head>
<body>

	<form action="addEven">
		id<input type="text" name="eid"> <br> fname<input type="text"
			name="fname"><br> lname<input type="text" name="lname"><br>
		event date<input type="text" name="ed" id="datepicker"> <br> event type<select
			name="etypes">
			<option value="birthday">birthday</option>
			<option value="m anniversary">manniv</option>
			<option value="d anniversary">danniv</option>
		phone</select> <br> <input type="text" name="phone"> <br> email <input
			type="text" name="email"><br>
			<input type="submit"><br>
	</form>
	<form action="getEven">
		enter id <input type="text" name="eid"><br> 
			<input type="submit"><br>
	</form>
</body>
</html>