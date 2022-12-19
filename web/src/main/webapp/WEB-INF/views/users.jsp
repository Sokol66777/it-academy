<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
    <p>Users page</p>
    <c:forEach var="user" items= "${allUsers}">
        <p><c:out value="${user}"/>
        <button onclick = "location.href='delete?deleteUsersID=${user.id}'">delete</button>
        <button onclick = "location.href='update?updateUsersID=${user.id}'">Update</button>

        </p>
    </c:forEach>
    <button onclick = "location.href='${pageContext.request.contextPath}/welcome'">welcome</button>
    <button onclick = "location.href= '${pageContext.request.contextPath}/logout'"> logout </button>

</body>
</html>