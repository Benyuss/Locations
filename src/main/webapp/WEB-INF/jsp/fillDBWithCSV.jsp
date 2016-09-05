<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Geohash calculator - database</title>
</head>
<body>
<br><br><br><br><br><br><br><br><br><br><br>

<table align="center" class="center-div" cellspacing="10">

	<form:form action="/upload" enctype="multipart/form-data">
	
	<tr>  
		 <th align="left"> Upload a CSV file</th>
		 					<td> <input type="file" name="dbfile" size="35" accept=".csv"> </td>
	 <td> <br> </td>
	 <td> <input type="submit" name="upload" value="upload" style="height:25px; width:100px"/> </td>
	 
	</tr>
	
	</form:form>
</table>
</body>
</html>