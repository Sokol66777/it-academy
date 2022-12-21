<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
    <p>Add topic page</p>
    <p><c:out value="${errorMassage}"/></p>
    <c:if test="${user.role=='admin'}">
        <form action="add" method="post">
    	    <table>
			    <tr>
    			    <td>Topic Name</td>
        		    <td><input type = "text" name="name" required="required"/></td>
	    		</tr>
            	<tr>
        	    	<td><input type = "submit" value="Add topic"/></td>
	    		</tr>
		    </table>
	    </form>
	</c:if>
	<c:if test = "${user.role=='user'}">
	    <form action="add" method="post">
	        <table>
	            <tr>
	                <td>Select topic which you want add</td>
	                <td>
	                    <select name="idTopic">
	                        <c:forEach var = "topic" items ="${allTopics}">
	                            <option value="<c:out value='${topic.id}'/>">
	                                <c:out value="${topic.name}"/>
	                            </option>
	                        </c:forEach>
	                    </select>
	                </td>
	            </tr>
	            <tr>
	                <td><input type = "submit" value="Add topic"/></td>
	            </tr>
	        </table>
	    </form>
	</c:if>
    <button onclick = "location.href='${pageContext.request.contextPath}/user/welcome'">welcome</button>
</body>
</html>