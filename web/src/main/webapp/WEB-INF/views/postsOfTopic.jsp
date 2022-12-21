<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <h2>Your posts</h2>
    </head>
    <body>
        <table>
            <c:forEach var="post" items = "${posts}">
                <tr>
                    <td><c:out value = "${post.name}"/></td>
                    <td><button onclick = "location.href='${pageContext.request.contextPath}/post/deletePost?idPost=${post.id}&idTopic=${idTopic}'">Delete post</button><td>
                    <td><button onclick = "location.href='${pageContext.request.contextPath}/post/preUpdate?idPost=${post.id}'">Update post</button><td>
                </tr>
                <tr>
                    <td><c:out value = "${post.text}"/></td>
                </tr>
            </c:forEach>
        </table>
        <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>
        <button onclick = "location.href='${pageContext.request.contextPath}/post/preAdd?idTopic=${idTopic}'">Add post</button>
    </body>
</html>