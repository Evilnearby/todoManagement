package com.in28minutes.todo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import org.springframework.stereotype.Service;

import com.mysql.cj.jdbc.PreparedStatement;

@Service
public class MySQLConnector {
	static Connection myConn = null;
	
	static String dbURL = "jdbc:mysql://localhost:3306/TodoDB";
	static String user = "shilun";
	static String pwd = "password";
	private static int todoCount = 3;
	
	public List<Todo> retrieveTodos(String userName) throws SQLException {
		if (myConn == null || myConn.isValid(4)) {
			myConn = DriverManager.getConnection(dbURL, user, pwd);
		}
		Statement myStmt = myConn.createStatement();	
		List<Todo> res = new ArrayList<>();
		ResultSet myRs = myStmt.executeQuery("select * from todos where user = '" + userName + "'");
		while (myRs.next()) {
			res.add(new Todo(myRs.getInt("id"), myRs.getString("user"), 
					myRs.getString("todoDesc"), myRs.getDate("targetDate"), myRs.getInt("isDone") == 0 ? false : true));
		}
		return res;
	}
	
	public void deleteTodo(int todoID) throws SQLException {
		if (myConn == null || myConn.isValid(4)) {
			myConn = DriverManager.getConnection(dbURL, user, pwd);
		}
		Statement myStmt = myConn.createStatement();	
		int rowsAffected = myStmt.executeUpdate("delete from todos where id = " + todoID);
	}
	
	public void addTodo(String name, String desc, Date targetDate, boolean isDone) throws SQLException {
		if (myConn == null || myConn.isValid(4)) {
			myConn = DriverManager.getConnection(dbURL, user, pwd);
		}
		java.sql.PreparedStatement myStmt = myConn.prepareStatement("INSERT INTO TODOS VALUES (?, ?, ?, ?, ?)");
		
		int done = isDone?1 : 0;
		myStmt.setString(1, Integer.toString(++todoCount));
		myStmt.setString(2, name);
		myStmt.setString(3, desc);
		myStmt.setString(5, "" + done);
		java.sql.Date sqlDate = new java.sql.Date(targetDate.getTime());
		myStmt.setDate(4, sqlDate);
		
		myStmt.executeUpdate();
	}
	
	public Todo retrieveTodo(int id) throws SQLException {
		if (myConn == null || myConn.isValid(4)) {
			myConn = DriverManager.getConnection(dbURL, user, pwd);
		}
		
		Statement myStmt = myConn.createStatement();
		ResultSet myRs = myStmt.executeQuery("select * from todos where id = " + id);
		if (myRs.next()) {
			return new Todo(myRs.getInt("id"), myRs.getString("user"), 
					myRs.getString("todoDesc"), myRs.getDate("targetDate"), myRs.getInt("isDone") == 0 ? false : true);
		}
		return null;
	}
	
	public void updateTodo(Todo todo) throws SQLException {
		if (myConn == null || myConn.isValid(4)) {
			myConn = DriverManager.getConnection(dbURL, user, pwd);
		}
		Statement myStmt = myConn.createStatement();
		myStmt.executeUpdate("delete from todos where id = " + todo.getId());
		addTodo(todo.getUser(), todo.getDesc(), todo.getTargetDate(), todo.isDone());
	}
}
