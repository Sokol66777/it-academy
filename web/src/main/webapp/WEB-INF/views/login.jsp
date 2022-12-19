<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <h2>Login page</h2>
</head>
<body>
    <p><c:out value="${errorMassage}"/></p>
    <form action="login" method="post">
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
        		<td><input type = "submit" value="login"/></td>
			</tr>
		</table>
	</form>
	<button onclick = "location.href='${pageContext.request.contextPath}/'">Start Page </button>
</body>
</html>