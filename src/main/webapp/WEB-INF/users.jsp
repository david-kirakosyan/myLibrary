<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Book" %>
<%@ page import="com.example.mylibrary.model.User" %>
<%@ page import="com.example.mylibrary.model.UserType" %>
<!DOCTYPE html>
<html lang="en" title="Coding design">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>My Book</title>
    <link rel="stylesheet" href="../register/tables/author.css">
</head>
<% List<Book> books = (List<Book>) request.getAttribute("book");
    User user = (User) session.getAttribute("user");
    String keyword = request.getParameter("keyword") == null || request.getParameter("keyword").equals("null")
            ? "" : request.getParameter("keyword");
%>
%>
<body>
<main class="table">
    <section class="table__header">
        <a href="/home">Back</a>
        <h1>My Books</h1>
        <form action="/users" method="get" id="search-books" class="input-group">
            <input type="search" name="keyword" placeholder="Search Data..." value="<%=keyword%>">
            <img onclick="document.getElementById('search-books').submit()" src="../register/tables/images/search.png"
                 alt="">
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
                <th>Image</th>
                <th>Title</th>
                <th>Description</th>
                <th>Price</th>
                <th>Author name</th>
                <th>User name</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <% if (books != null && !books.isEmpty()) {%>
            <% for (Book book : books) { %>
            <% if (user.getId() == book.getUser().getId()){%>
            <tr>
                <td><%=book.getId()%>
                </td>
                <td>
                    <% if (book.getImage() == null || book.getImage().equalsIgnoreCase("null")) { %>
                    <img src="../register/images/difault-book.png" alt="">
                    <%} else {%>
                    <a href="/getImageBook?picName=<%=book.getImage()%>"><img
                            src="/getImageBook?picName=<%=book.getImage()%>"> </a></td>
                <%}%>
                <td><%=book.getTitle()%>
                </td>
                <td><%=book.getDescription()%>
                </td>
                <td><%=book.getPrice()%>
                </td>
                <td><%=book.getAuthor().getName()%> <%=book.getAuthor().getSurname()%>
                </td>
                <td><%=book.getUser().getName()%> <%=book.getUser().getSurname()%>
                </td>
                <td>
                    <a class="status cancelled" href="/removeBook?id=<%=book.getId()%>">Delete</a>
                    <a class="status delivered" href="/editBook?id=<%=book.getId()%>">Edit</a>
                </td>
            </tr>
            <% } %>
            <% } %>
            <% } %>
            </tbody>
        </table>
    </section>
</main>
</body>

</html>