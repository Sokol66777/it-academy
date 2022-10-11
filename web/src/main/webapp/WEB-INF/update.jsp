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
        		<td><input type = "text" name="newUsername"  required="required"/></td>
			</tr>
    		<tr>
    			<td>Old Password</td>
        		<td><input type = "password" name="oldPassword"  required="required"/></td>
			</tr>
			<tr>
                <td>New password</td>
                <td><input type = "password" name="newPassword"  required="required"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type = "email" name="newEmail"  required="required"/></td>
            </tr>
        	<tr>
        		<td><input type = "submit" value="Update"/></td>
			</tr>
		</table>
	</form>
	<button onclick ="location.href='welcome'" >welcome page</button>
	<button onclick = "location.href='logout'"> logout </button>
	 <c:if test="${user.role=='admin'}">
           <form action="allUsers" method="post">
                <p><input type = "submit" value="Your users"/></p>
           </form>
     </c:if>

</body>
</html>
