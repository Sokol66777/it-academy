<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <h2>Update page</h2>
</head>
<body>
    <form action="updatePost" method="post">
    	<table>
			<tr>
    			<td>Post name</td>
        		<td><input type = "text" name="newPostName"  required="required"  value="<c:out value='${updatePost.name}'/>"/></td>
        		<input type = "hidden" name = "idPost" value = "<c:out value = '${updatePost.ID}'/>"/>
			</tr>
                <tr>
                    <td>Text</td>
                    <td><textarea name = "newPostText" rows = "7" cols ="100" required="required"><c:out value='${updatePost.text}'/></textarea></td>
                </tr>
        	<tr>
        		<td><input type = "submit" value="Update"/></td>
			</tr>
		</table>
	</form>
	<button onclick ="location.href='welcome'" >welcome page</button>
	<button onclick = "location.href='logout'"> logout </button>
</body>
</html>
