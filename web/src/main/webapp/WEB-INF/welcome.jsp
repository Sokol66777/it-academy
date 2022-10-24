<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>

</head>
<body>

    <p>Welcome,
       <c:out value="${username}"/></p>
    <button onclick = "location.href='logout'"> logout </button>
    <button onclick = "location.href='update?updateUsername=${username}'">Update</button>

    <c:if test="${role=='admin'}">
       <button onclick = "location.href='allUsers?adminName=${username}'">All users</button>
    </c:if>


</body>
</html>
