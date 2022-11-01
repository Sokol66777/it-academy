<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>

</head>
<body>
    <h2><c:out value="${error}"/></h2>
	<button onclick = "location.href='logout'">Login page </button>
</body>
</html>
