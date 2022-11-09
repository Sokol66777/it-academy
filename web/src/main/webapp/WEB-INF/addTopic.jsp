<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
</head>
<body>
    <p>Add topic page</p>
    <c:if test="${role=='admin'}">
        <form action="addTopic" method="post">
    	    <table>
			    <tr>
    			    <td>Topic Name</td>
        		    <td><input type = "text" name="topicName" required="required"/></td>
	    		</tr>
            	<tr>
        	    	<td><input type = "submit" value="Add topic"/></td>
	    		</tr>
		    </table>
	    </form>
	</c:if>
	<c:if test = "${role=='user'}">
	    <form action="addTopic" method="post">
	        <table>
	            <tr>
	                <td>Select topic which you want add</td>
	                <td>
	                    <select name="idTopic">
	                        <c:forEach var = "topic" items ="${allTopics}">
	                            <option value="<c:out value='${topic.ID}'/>">
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
	<button onclick = "location.href='welcome'"> welcome page </button>

</body>
</html>
