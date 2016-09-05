<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Geohash Calculator - Results</title>
</head>
<body>
	<table style="width: 100%" border="1">
	<tr>
	<th> Latitude </th> <th> Longitude </th> <th> Radius </th> <th> Geohash </th> <th> Contains </th> <th> Distance </th>
	</tr>
	<c:forEach var="geoItem" begin="1" end= "${listSize}" items="${geoItemList}" >
		<tr>
			<td width="15%"> <c:out value="${geoItem.loc.latitude}"/> </td>
			<td width="15%"> <c:out value="${geoItem.loc.longitude}"/> </td>
			<td width="10%"> <c:out value="${geoItem.loc.radius}"/> </td>
			<td width="10%"> <c:out value="${geoItem.geoHash}"/> </td>
			<td width="20%"> <c:out value="${geoItem.contains}"/> </td>
			<td> <c:out value="${geoItem.distance}"/> </td>
		</tr>
		</c:forEach>
	</table>
	<h1>Chosen location</h1>
	<table style="width: 100%" border="1">
		<tr>
			<td width="15%"> <c:out value="${chosen.latitude}"/> </td>
			<td width="15%"> <c:out value="${chosen.longitude}"/> </td>
			<td width="10%"> <c:out value="${chosen.radius}"/> </td>
			<td width="10%"> <c:out value="${chosen.geohash}"/> </td>
			<td style='border-left:none;border-bottom:none;border-top:none' width="20%"> </td>
			<td style='border-right:none;border-bottom:none;border-top:none'> </td>
		</tr>
	</table>
	<br><br>
	<table align="center">
		<tr>
		<form:form action="/geohash">
		<th> <input type="submit" name="index" value="Back to Index" style="height:30px; width:100px"> </th>
		</form:form>
		<form:form action="/geohash">
		<th> <input type="submit" name="reset" value="Delete DB and back to Index" style="height:30px"> </th>
		</form:form>
		</tr>
		
	</table>
</body>
</html>