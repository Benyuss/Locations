<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Geohash calculator - Log in</title>
</head>
<body>
<br><br><br><br><br><br><br><br><br><br><br>
	<table>
		<form:form action="/login" name="formdata">
		<tr>
			<th align="left" width="20%">Nickname:</th> 
							<td><form:input type="text" path="nickname"/></td>
		</tr>
		<tr>					
			<th align="left" width="20%">Password:</th> 
							<td><form:input type="text" path="password"/></td>
		</tr>		
		<tr>		
			<th><input type="submit" name="login" value="Log in"> </th>
		</tr>
		</form:form>	
		
		<form:form action="/login">
		<tr>
			<th><input type="submit" name="register" value="Register a new account"> </th>
		</tr>	
		</form:form>
	</table>

</body>
</html>