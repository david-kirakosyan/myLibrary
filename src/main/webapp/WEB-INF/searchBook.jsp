<%@ page import="java.util.List" %>
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
        <a href="/">Back</a>
        <h1>Books</h1> <a href="/searchBook">Search Book</a>
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
            </tr>
            <% } %>
            <% } %>
            </tbody>
        </table>
    </section>
</main>
</body>

</html>