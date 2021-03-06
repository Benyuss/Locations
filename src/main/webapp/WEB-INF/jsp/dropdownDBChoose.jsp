<%@page import="java.util.ArrayList"%>
<%@page import="locations.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Select a record</title>
</head>
<body>
<sql:setDataSource var="ds" driver="org.h2.Driver" url="jdbc:h2:mem:locationdb" user="sa" password=""/>
<sql:query dataSource="${ds}" var="geoItemsList"> SELECT * FROM LOCATIONDB; </sql:query>
	<table align="center">
		<tr>
<form:form method="POST" action="/geohash" modelAttribute="formdata" name="formdata">
			<td>
				<form:select path="geohash">
	    			<c:forEach var="geoItem" items="${geoItemsList.rows}">
	    				<form:option value="${geoItem.geohash}">
	    					${geoItem.geoHash}
	    				 </form:option>
	   				 </c:forEach>
	   			 </form:select>
	   			 
   			</td>
		<th> <input type="submit" value="Choose" style="height:30px; width:100px"> </th>
</form:form>
		</tr>
		<tr>
		<form:form action="/geohash">
		<th> <input type="submit" name="index" value="Back to Index" style="height:30px; width:100px"> </th>
		</form:form>
		</tr>

	</table>
</body>
</html>