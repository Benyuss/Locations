<%@page import="java.util.ArrayList"%>
<%@page import="locations.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World - JSP tutorial</title>
</head>
<body>
	<table style="width: 100%" border="1">
	<tr>
	<th> Latitude </th> <th> Longitude </th> <th> Radius </th> <th> Geohash </th> <th> Contains </th> <th> Distance </th>
	</tr>
<%	
	LocationExecute.calculate();
	ArrayList<Tuple> tupleList = LocationExecute.getTupleList();
	for (int i = 1; i < tupleList.size(); i++) {
		%>
		<tr>
			<td><%out.println(tupleList.get(i).getFirstCoord()); %></td>
			<td><%out.println(tupleList.get(i).getSecondCoord()); %></td>
			<td><%out.println(tupleList.get(i).getRadius()); %></td>
			<td><%out.println(tupleList.get(i).getGeoHash()); %></td>
			<td><%out.println(tupleList.get(i).getContains()); %></td>
			<td><%out.println(tupleList.get(i).getDistance()); %></td>
		</tr>
		<%
	}
%>	
	</table>
</body>
</html>