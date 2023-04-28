<%@ page import="com.example.mylibrary.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <link rel="alternate" href="../home.css">
</head>
<body>
<% User user = (User) session.getAttribute("user"); %>
Welcome <%=user.getName()%> <%=user.getSurname()%>
<nav>
    <a href="/authors">AUTHORS</a>
    <a href="/books">Books</a>
    <a href="/logout">Logout</a>
    <div id="indicator"></div>
</nav>
</body>
</html>
