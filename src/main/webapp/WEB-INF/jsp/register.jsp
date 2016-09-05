<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Geohash calculator - Register a new account</title>
</head>
<body>
<br><br><br><br><br><br><br><br><br><br><br>

<table align="center" class="center-div" cellspacing="10">

	<form:form action="/registration" name="register">
	
	<tr> <th align="left" width="20%">First name:</th> 
							<td><form:input type="text" path="firstName"/></td></tr>
	
	<tr> <th align="left" width="20%">Last name:</th> 
						    <td> <form:input type="text" path="lastName"/></td>
	</tr>
	
	<tr> <th align="left" width="20%">email:</th> 
							<td width="35%"><form:input type="text" path="email"/></td>
	</tr>
	
	<tr> <th align="left" width="20%">Nickname:</th> 
							<td  width="35%"><form:input type="text" path="nickname"/></td> 
	</tr>
	
	<tr> <th align="left" width="20%">Password:</th> 
							<td width="35%"><form:input type="text" path="passwordValidator"/></td>
	</tr>
	
	<tr> <th align="left" width="20%">Repeat password:</th> 
							<td width="35%"><form:input type="text" path="password"/></td>
	</tr>
	<tr>					    
		 <td> <input type="submit" name="createAcc" value="I've checked everything. Create my account!" style="height:25px; width:100%"/> </td>
	</tr>
	
	</form:form>
</table>
</body>
</html>