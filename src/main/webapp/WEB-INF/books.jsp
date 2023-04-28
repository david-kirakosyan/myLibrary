<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.User" %>
<%@ page import="com.example.mylibrary.model.Book" %>
<!DOCTYPE html>
<html lang="en" title="Coding design">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Book</title>
    <link rel="stylesheet" href="../register/tables/author.css">
</head>
<% List<Book> books = (List<Book>) request.getAttribute("book");
%>
<body>
<main class="table">
    <section class="table__header">
        <a href="/home">Back</a>
        <h1>Books</h1> <a href="/createBook">Create Book</a>
        <form action="/books" method="get" id="search-books" class="input-group">
            <input type="search" name="title" placeholder="Search Data...">
            <img onclick="document.getElementById('search-books').submit()" src="../register/tables/images/search.png" alt="">
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
                <th>Title</th>
                <th>Description</th>
                <th>Price</th>
                <th>Author name</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% if (books != null && !books.isEmpty()) {%>
            <% for (Book book : books) { %>
            <tr>
                <td><%=book.getId()%></td>
                <td><%=book.getTitle()%></td>
                <td> <%=book.getDescription()%></td>
                <td><%=book.getPrice()%></td>
                <td><%=book.getAuthor().getName()%></td>
                <td>
                    <a class="status cancelled" href="/removeBook?id=<%=book.getId()%>">Delete</a>
                    <a class="status delivered" href="/editBook?id=<%=book.getId()%>">Edit</a>
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