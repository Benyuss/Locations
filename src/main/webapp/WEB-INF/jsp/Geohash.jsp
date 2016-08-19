<%@page import="java.util.ArrayList"%>
<%@page import="locations.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Geohash Calculator</title>
</head>
<body>
	<table style="width: 100%" border="1">
	<tr>
	<th> Latitude </th> <th> Longitude </th> <th> Radius </th> <th> Geohash </th> <th> Contains </th> <th> Distance </th>
	</tr>
	<c:forEach var="geoItem" begin="1" end= "${listSize}" items="${geoItemList}" >
		<tr>
			<td> <c:out value="${geoItem.firstCoordinate}"/> </td>
			<td> <c:out value="${geoItem.secondCoordinate}"/> </td>
			<td> <c:out value="${geoItem.radius}"/> </td>
			<td> <c:out value="${geoItem.geoHash}"/> </td>
			<td> <c:out value="${geoItem.contains}"/> </td>
			<td> <c:out value="${geoItem.distance}"/> </td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>