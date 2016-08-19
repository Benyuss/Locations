<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Geohash calculator</title>
</head>
<body>
	<form:form action="/geohash">
		Latitude: <form:input type="number" step="any" path="firstCoordinate"/><br> 
		Longitude: <form:input type="number" step="any" path="secondCoordinate"/><br>
		Radius: <form:input type="number" step="any" path="radius"/><br> 
		<input type="submit" value="Submit"/>
	</form:form>
</body>
</html>