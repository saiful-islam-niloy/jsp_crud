<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
	<form action="/crud/User?type=put" method="post">
		<label for="id">Id:</label><br>
 		<input type="text" id="id" name="id" value="${user.ID }" readonly><br>
		<label for="name">User Name:</label><br>
 		<input type="text" id="name" name="name"  value="${user.name }"><br>
		<label for="email">Email:</label><br>
 		<input type="text" id="email" name="email"  value="${user.email }"><br>
		<label for="location">Location:</label><br>
 		<input type="text" id="location" name="location"  value="${user.location }"><br>
		<input type="submit" value="Update">
	</form>	
</body>
</html>