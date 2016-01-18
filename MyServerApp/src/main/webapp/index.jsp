<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>First App</title>
</head>
<body>
	Hello ${firstname} ${lastname} !
	<br>
	<br> Here are the listed users :
	<br>
	<br>
	<c:forEach var="cur" items="${users}">
		${cur.firstname} - ${cur.lastname} <br>
	</c:forEach>
	<br>
	<form name="register" action="Users" method="post">
		<input name="firstname"> <input name="lastname">
		<button type="submit">Enregistrer</button>
	</form>
</body>
</html>