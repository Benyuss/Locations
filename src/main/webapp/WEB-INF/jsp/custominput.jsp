<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Geohash calculator - Custom Location </title>
</head>
<body>
<br><br><br><br><br><br><br><br><br><br><br>

<table align="center" class="center-div" cellspacing="10">

	<form:form action="/custom-input" name="custom">
	
	<tr> <th align="left" width="20%">Latitude:</th> 
							<td  width="35%"><form:input type="number" step="any" path="latitude" size="35"/></td> 
	</tr>
	
	<tr> <th align="left" width="20%">Longitude:</th> 
							<td><form:input type="number" step="any" path="longitude"/></td></tr>
	
	<tr> <th align="left" width="20%"> Radius:</th> 
							<td width="35%"><form:input type="number" step="any" path="radius" size="35"/></td>
						    <td> <input type="submit" name="submit" value="Add record to the database" style="height:25px; width:100%"/> </td>
	</tr>
	
	</form:form>
	<tr>
		<form:form action="/geohash">
			<th> <input type="submit" name="index" value="Back to Index" style="height:25px; width:100%"> </th>
		</form:form>
	</tr>
</table>
</body>
</html>