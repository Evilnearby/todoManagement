<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Yahoo From JSP</title>
</head>
<body>
<p>
This is my first JSP's passage part. <br>
<!-- the name is: ${name} and the password is ${password}  -->
</p>
<form action = "/login" method = "post">	
<!-- The method here defines it's a post operation, and it's with a parameter submitted with name  "name" -->
<table>
<tr>
	<td>Name: </td> <td><input type = "text" name = "name"/></td>
</tr>
<tr>
	<td>Password:</td> <td><input type = "password" name = "password"/> </td>
</tr>
</table>
<p><font color = "red">${errorMessage}</font></p>
<input type = "submit" value = "Login"/>
</form>
</body>
</html>