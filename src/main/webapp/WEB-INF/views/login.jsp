<!-- This is a useless file -->

<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class = "container">
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
</div>

<%@ include file="common/footer.jspf"%>