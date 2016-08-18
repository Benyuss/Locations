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

<%
	LocationExecute.calculate();
	ArrayList<String> hash = LocationExecute.getList();
	String temp; %>
<!-- 	<tr><td> -->
	<%
    for(int i = 0; i < hash.size(); i++){ 
   	 	temp = hash.get(i); %>
    	
    <% 
        out.println(temp); }
    %>	
<!--     </td></tr> -->
	</table>
</body>
</html>