<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language = "java" contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <p2>Add post page</p2>
    </head>
    <body>
        <form action="addPost" method="post">
        	<table>
    			<tr>
        			<td>Name Post</td>
            		<td><input type = "text" name="postName"  required="required"/></td>
    			</tr>
                <tr>
                    <td>Text</td>
                    <td><textarea name = "topicText" rows = "7" cols ="100" required="required"></textarea></td>
                </tr>
                <input type = "hidden" name = "idTopic" value = "<c:out value = '${idTopic}'/>"/>
            	<tr>
            		<td><input type = "submit" value="Add"/></td>
    			</tr>
    		</table>
    	</form>
        <p><button onclick = "location.href='welcome'">Welcome page</button></p>
    </body>
</html>