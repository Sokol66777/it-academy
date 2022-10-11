<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>

</head>
<body>

    <p>Welcome,
       <c:out value="${user.username}"/></p>
    <button onclick = "location.href='logout'"> logout </button>
    <button onclick = "location.href='update?updateUsername=${user.username}'">Update</button>

    <c:if test="${user.role=='admin'}">
       <form action="allUsers" method="post">
            <p><input type = "submit" value="Your users"/></p>
       </form>
    </c:if>


</body>
</html>
