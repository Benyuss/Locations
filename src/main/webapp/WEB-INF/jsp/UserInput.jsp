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
<br><br><br><br><br><br><br><br><br><br><br>

<table align="center" class="center-div" cellspacing="10">

	<form:form action="/geohash" enctype="multipart/form-data">
	
	<tr> <th align="left" width="20%">Latitude:</th> 
							<td  width="35%"><form:input type="number" step="any" path="latitude" size="35"/></td> 
							<td> <input type="submit" name="SubmitWithDefault" value="Default Values" 
							style="height:25px; width:100px"> </td>
	</tr>
	
	<tr> <th align="left" width="20%">Longitude:</th> 
							<td><form:input type="number" step="any" path="longitude"/></td></tr>
	
	<tr> <th align="left" width="20%"> Radius:</th> 
							<td width="35%"><form:input type="number" step="any" path="radius" size="35"/></td>
						    <td> <input type="submit" name="Submit" value="Submit" style="height:25px; width:100px"/> </td>
	</tr>
	
	<tr><td width="30%"> <br> </td> </tr>
	
	<tr>  
	
		 <th align="left" width="20%"> Upload a CSV file</th>
		 					<td width="30%"> <input type="file" name="file" size="35" accept=".csv"> </td>
	</tr>
	
	</form:form>
</table>
</body>
</html>