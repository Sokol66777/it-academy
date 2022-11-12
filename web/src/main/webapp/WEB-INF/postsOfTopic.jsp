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
                    <td><button onclick = "location.href='deletePost?idPost=${post.ID}'">Delete post</button><td>
                    <td><button onclick = "location.href='updatePost?idPost=${post.ID}'">Update post</button><td>
                </tr>
                <tr>
                    <td><c:out value = "${post.text}"/></td>
                </tr>
            </c:forEach>
        </table>
        <p><button onclick = "location.href='welcome'">Welcome page</button>
        <button onclick = "location.href='addPost?idTopic=${idTopic}'">Add post</button>
    </body>
</html>
