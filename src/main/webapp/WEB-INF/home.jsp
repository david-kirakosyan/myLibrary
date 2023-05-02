<%@ page import="com.example.mylibrary.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="../register/home/style.css">

</head>
<body>
<!-- partial:index.partial.html -->
<div class="back"></div>
<% User user = (User) session.getAttribute("user"); %>
Welcome <%=user.getName()%> <%=user.getSurname()%>
<nav>
    <a href="/users">My Books</a>
    <a href="/authors">Authors</a>
    <a href="/books">Books</a>
    <a href="/logout">Logout</a>
</nav>

</body>
</html>
