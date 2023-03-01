<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Comments</title>
</head>
<body>
<h1>Comments</h1>
<table>
    <thead>
        <tr>
            <th>Comment Text</th>
            <th>User</th>
            <th>Child Comments</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="comment" items="${comments}">
            <tr>
                <td>${comment.text}</td>
                <td>${comment.user}</td>
                <td>${comment.childComments}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>

