<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>
<h1>Welcome to the Hacker News API</h1>
<p>Use the links below to access the API:</p>
<ul>
    <li><a href="${pageContext.request.contextPath}/top-stories">Top Stories</a></li>
    <li><a href="${pageContext.request.contextPath}/past-stories">Past Stories</a></li>
    <li><a href="${pageContext.request.contextPath}/comments">Comments</a></li>
</ul>
</body>
</html>

