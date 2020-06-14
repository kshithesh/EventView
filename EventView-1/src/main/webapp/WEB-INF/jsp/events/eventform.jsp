<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="../error.jsp" %>
<head>
    <a href="${pageContext.request.contextPath}/">Home</a>
    <meta charset="ISO-8859-1">
    <title>Events</title>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<h1>Add New Event</h1>
<sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                   url="jdbc:mysql://127.0.0.1:3306/crud?user=root"
                   user="root" password="kshithesh"/>

<sql:query dataSource="${db}" var="userresult">
    SELECT user_id,first_name from users;
</sql:query>
<sql:query dataSource="${db}" var="eventtyperesult">
    SELECT event_type_id,event_type from eventtypes;
</sql:query>
<form:form method="post" action="${pageContext.request.contextPath}/save/event">
    <table>
        <tr>
            <td>User: </td>
            <td><label>
                <select  name="user_id" var="users">
                    <option>Select User</option>
                    <c:forEach var="user" items="${userresult.rows}">
                        <option value=${user.user_id}>${user.first_name}</option>
                    </c:forEach>
                </select>
            </label>
            </td>
        </tr>
        <tr>
            <td>EventType :</td>
            <td><label>
                <select class="" name="event_type_id" var="eventtypes">
                    <option>Select EventType</option>
                    <c:forEach var="event" items="${eventtyperesult.rows}">
                        <option value=${event.event_type_id}>${event.event_type}</option>
                    </c:forEach>
                </select>
            </label></td>
        </tr>
        <tr>
            <td>Event Date :</td>
            <td><form:input type="date" path="eventDate"
                            min="1940-01-01" max="2018-12-31"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form:form>