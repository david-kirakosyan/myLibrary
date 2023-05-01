<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.User" %>
<!DOCTYPE html>
<html lang="en" title="Coding design">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Author</title>
    <link rel="stylesheet" href="../register/tables/author.css">
</head>
<% List<Author> authors = (List<Author>) request.getAttribute("authors");
    User user = (User) session.getAttribute("user");
    String keyword = request.getParameter("keyword") == null || request.getParameter("keyword").equals("null")
    ? "" : request.getParameter("keyword");
%>
<body>
<main class="table">
    <section class="table__header">
        <a href="/home">Back</a>
        <h1>Authors</h1> <a href="/createAuthor">Create Author</a>
            <form action="/authors" method="get" id="search-authors" class="input-group">
                <input type="search" name="keyword" placeholder="Search Data..." value="<%=keyword%>">
                <img onclick="document.getElementById('search-authors').submit()"  src="../register/tables/images/search.png" alt="">
            </form>
        <div class="export__file">
            <label for="export-file" class="export__file-btn" title="Export File"></label>
            <input type="checkbox" id="export-file">
        </div>
    </section>
    <section class="table__body">
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>image</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Email</th>
                <th>Age</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% if (authors != null && !authors.isEmpty()) {%>
            <% for (Author author : authors) { %>
            <tr>

                <td><%=author.getId()%></td>
                <td>
                    <% if (author.getPicName() == null || author.getPicName().equalsIgnoreCase("null")) { %>
                    <img src="../register/images/difault.jpg" alt="">
                    <%} else {%>
                    <a href="/getImage?picName=<%=author.getPicName()%>"><img
                            src="/getImage?picName=<%=author.getPicName()%>" width="100"> </a></td>
                <%}%>
                <td><%=author.getName()%></td>
                <td> <%=author.getSurname()%></td>
                <td><%=author.getEmail()%></td>
                <td><%=author.getAge()%></td>
                <td>
                    <a class="status cancelled" href="/removeAuthor?id=<%=author.getId()%>">Delete</a>
                    <a class="status delivered" href="/editAuthor?id=<%=author.getId()%>">Edit</a>
                </td>
            </tr>
            <% } %>
            <% } %>
            </tbody>
        </table>
    </section>
</main>
</body>

</html>