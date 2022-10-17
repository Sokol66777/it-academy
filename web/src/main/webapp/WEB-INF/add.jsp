<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <h2>Registration page</h2>
</head>
<body>
    <form action="add" method="post">
    	<table>
			<tr>
    			<td>User Name</td>
        		<td><input type = "text" name="username" required="required"/></td>
			</tr>
    		<tr>
    			<td>Password</td>
        		<td><input type = "password" name="password" required="required"/></td>
			</tr>
			<tr>
                <td>Confirm password</td>
                <td><input type = "password" name="confirmedPassword" required="required"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type = "email" name="email" required="required"/></td>
            </tr>
        	<tr>
        		<td><input type = "submit" value="Registration"/></td>
			</tr>
		</table>
	</form>
	<button onclick = "location.href='login'"> login page </button>
</body>
</html>
