<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <p2>Your posts</p2>
    </head>
    <body>
        <c:forEach var="post" items = "${posts}">
            <p><c:out value = "${post.name}"/></p>
            <p><c:out value = "${post.text}"/></p>
        </c:forEach>
        <button onclick = "location.href='welcome'">Welcome page</button>
    </body>
</html>