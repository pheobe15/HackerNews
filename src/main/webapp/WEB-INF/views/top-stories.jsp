<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Top Stories</title>
</head>
<body>
<h1>Top Stories</h1>
<table>
    <thead>
        <tr>
            <th>Title</th>
            <th>URL</th>
            <th>Score</th>
            <th>Submission Time</th>
            <th>User</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="story" items="${topStories}">
            <tr>
                <td><a href="${story.url}">${story.title}</a></td>
                <td>${story.url}</td>
                <td>${story.score}</td>
                <td>${story.time}</td>
                <td>${story.user}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>
