<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>First App</title>
</head>
<body>
<%! String username = "Meric"; %>
<%!
public class User{
	public String firstname;
	public String lastname;
	
	public User(String firstname, String lastname){
		this.firstname=firstname;
		this.lastname=lastname;
	}
	
	public String getFirstname(){
		return firstname;
	}
	
	public String getLastname(){
		return lastname;
	}
}

%>
<%!
//fonction récupérant un user
List<User> selectAllQuery() throws SQLException {
	//connection a la BDD
	String PASSWORD = "test";
	String USER = "test";
	String DRIVER = "org.postgresql.Driver";
	String URL = "jdbc:postgresql://localhost:5432/myDB";
	try{
	Class.forName(DRIVER);
	}catch(ClassNotFoundException e){
		e.printStackTrace();
	}
	
	List<User> users = new ArrayList<>();
	Statement stmt = null;
	String query = "SELECT * FROM test.USER";
	

	stmt = DriverManager.getConnection(URL, USER, PASSWORD).createStatement();
	ResultSet rs = stmt.executeQuery(query);
	
	while(rs.next()){
		String firstname = rs.getString("firstname");
		String lastname = rs.getString("lastname");
		users.add(new User(firstname,lastname));
	}
	
	return users;
}%>
<%!
//fonction enregistrant un user
void insertQuery(User user) throws SQLException {
	//connection a la BDD
	String PASSWORD = "test";
	String USER = "test";
	String DRIVER = "org.postgresql.Driver";
	String URL = "jdbc:postgresql://localhost:5432/myDB";
	try{
	Class.forName(DRIVER);
	}catch(ClassNotFoundException e){
		e.printStackTrace();
	}
	
	Statement stmt = null;
	String query = "INSERT INTO test.USER (firstname,lastname) VALUES('"
			+ user.firstname 
			+"','"
			+ user.lastname
			+ "')";	

	stmt = DriverManager.getConnection(URL, USER, PASSWORD).createStatement();
	stmt.executeUpdate(query);
}
%>
<%
String firstname = request.getParameter("firstname");
String lastname = request.getParameter("lastname");
if(firstname != null && lastname != null ){
	request.setAttribute("firstname",firstname);
	request.setAttribute("lastname",lastname);
	insertQuery(new User(firstname,lastname));
}
request.setAttribute("users", selectAllQuery());
%>
Hello ${firstname} ${lastname} !<br>
<br>
Here are the listed users :
<br>
<br>
<c:forEach var="cur"  items="${users}" >
${cur.firstname} - ${cur.lastname} <br>
</c:forEach>
<br>
<form name="register" action="index.jsp" method="post">
<input name="firstname">
<input name="lastname">
<button action="submit">Enregistrer</button>
</form>
</body>
</html>