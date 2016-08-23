<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="locations.*"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Geohash calculator</title>
</head>
<body>
<table align="center" class="center-div">

	<form:form action="/geohash">
	<tr> <th>Latitude:</th> <td><form:input type="number" step="any" path="firstCoordinate"/></td></tr>
	<tr> <th>Longitude:</th> <td><form:input type="number" step="any" path="secondCoordinate"/></td></tr>
	<tr> <th>Radius:</th> <td><form:input type="number" step="any" path="radius"/></td></tr> 
	<tr> <td> <br> </td> </tr>
	<tr> <td><input type="submit" name="Submit" value="Submit" style="height:25px; width:100px"/> </td> 
		 <td> <input type="submit" name="SubmitWithDefault" value="Default Values" style="height:25px; width:100px"> </td> <tr>
	
	</form:form>
</table>
</body>
</html>