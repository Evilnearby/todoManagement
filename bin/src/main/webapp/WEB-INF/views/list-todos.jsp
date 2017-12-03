<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" rel="stylesheet">
<title>List-todos</title>
</head>
<body>
<div class = "container">
	Hi ${name}<br>
	<table class = "table table-striped">
		<caption>Your todos are:</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Date</th>
				<th>Is Completed?</th>
				<th></th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items = "${todos}" var = "todo">
			<tr>
				<td>${todo.desc}</td>
				<td><fmt:formatDate pattern="MM/dd/yyyy" value="${todo.targetDate}" /></td>
				<td>${todo.done}</td>
				<td><a href = "/update-todo?id=${todo.id}" class = "btn btn-success">Update</a></td>
				<td><a href = "/delete-todo?id=${todo.id}" class = "btn btn-danger">Delete</a></td>
			</tr>
			</c:forEach>
		</tbody>
		
	</table>
	<div>
		<a class = "btn btn-success" href = "/add-todo">Add new Todo</a>
	</div>
</div>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
</body>
</html>