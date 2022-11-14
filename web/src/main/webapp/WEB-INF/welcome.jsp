<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>

</head>
<body>

    <p>Welcome,
       <c:out value="${username}"/></p>

    <c:if test="${role=='admin'}">
        <p2>All Topics</p2>
        <c:forEach var="topic" items = "${allTopics}">
            <p><c:out value="${topic.name}"/></p>
        </c:forEach>
    </c:if>

    <c:if test="${role=='user'}">
        <p2>Your topics</p2>
        <c:forEach var="topic" items = "${userWithTopic.topics}">
            <p><c:out value = "${topic.name}"/>
            <button onclick = "location.href='postsOfTopic?idTopic=${topic.ID}'">Go to</button>
            <button onclick = "location.href='deleteTopic?deleteTopicID=${topic.ID}'">Delete Topic</button></p>
        </c:forEach>
    </c:if>
    <p></p>
    <button onclick = "location.href='logout'"> logout </button>
    <button onclick = "location.href='update?updateUsersID=${ID}'">Update</button>
    <button onclick = "location.href='addTopic'">Add topic</button>
    <c:if test="${role=='admin'}">
       <button onclick = "location.href='allUsers?adminName=${username}'">All users</button>
    </c:if>




</body>
</html>
