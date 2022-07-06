<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {background-color: #f2f2f2;}

.button {
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 16px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
}

.homebutton {
  background-color: white; 
  color: black; 
  border: 2px solid #4CAF50;
}

.homebutton:hover {
  background-color: #4CAF50;
  color: white;
}
</style>
</head>
<body>
	<a href="http://localhost:8080/crud/"><button class="button homebutton">Go to Home</button></a>
	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Location</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.ID }</td>
				<td>${user.name }</td>
				<td>${user.email }</td>
				<td>${user.location }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>