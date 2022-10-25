<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <h2>Update page</h2>
</head>
<body>
    <form action="update" method="post">
    	<table>
			<tr>
    			<td>User Name</td>
        		<td><input type = "text" name="newUsername"  required="required"  value="<c:out value='${updateUsersUsername}'/>"/></td>
			</tr>
    		<tr>
    		    <c:if test="${username==updateUsersUsername}">
    			    <td>Password</td>
        		    <td><input type = "password" name="password"  required="required" value="<c:out value='${updateUsersPassword}'/>"/></td>
			    </c:if>
			</tr>
            <tr>
                <td>Email</td>
                <td><input type = "email" name="newEmail"  required="required" value="<c:out value='${updateUsersEmail}'/>"/></td>
            </tr>
        	<tr>
        		<td><input type = "submit" value="Update"/></td>
			</tr>
		</table>
	</form>
	<button onclick ="location.href='welcome'" >welcome page</button>
	<button onclick = "location.href='logout'"> logout </button>
	 <c:if test="${role=='admin'}">
        <button onclick = "location.href='allUsers?adminName=${username}'">All users</button>
     </c:if>

</body>
</html>
