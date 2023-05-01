<%@ page import="com.example.mylibrary.model.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.mylibrary.model.Author" %>
<%@ page import="com.example.mylibrary.model.User" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700,900&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="../register/add/fonts/icomoon/style.css">


    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="../register/add/css/bootstrap.min.css">

    <!-- Style -->
    <link rel="stylesheet" href="../register/add/css/style.css">

    <title>Add Book</title>
</head>
<body>
<% List<Author> authors = (List<Author>) request.getAttribute("author");
List<User> users = (List<User>) request.getAttribute("user");
List<Book> books = (List<Book>) request.getAttribute("book");
%>

<div class="content">

    <div class="container">
        <div class="row align-items-stretch justify-content-center no-gutters">
            <div class="col-md-7">
                <div class="form h-100 contact-wrap p-5">
                    <a href="/books">Back</a>  <h3 class="text-center">Add Book</h3>
                    <form class="mb-5" action="/createBook" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-6 form-group mb-3">
                                <label for="title" class="col-form-label">Title</label>
                                <input type="text" class="form-control" name="title" id="title" placeholder="Your Title">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="description" class="col-form-label">Description</label>
                                <input type="text" class="form-control" name="description" id="description" placeholder="Your Description">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="price" class="col-form-label">Price</label>
                                <input type="number" class="form-control" name="price" id="price"
                                       placeholder="Your Price">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="author_id" class="col-form-label">Author name</label>
                                <SELECT name="author_id" class="form-control" id="author_id">
                                    <% for (Author author : authors) {%>
                                    <option value="<%=author.getId()%>"><%=author.getName()%></option>
                                    <%}%>
                                </SELECT>
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                    <%for (User user : users) {%>
                                    <input type="hidden" name="id" value="<%=user.getId()%>">
                                    <%}%>
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="pic_name" class="col-form-label">Image</label>
                                <input type="file" class="form-control" name="profilePic" id="pic_name">
                            </div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-md-5 form-group text-center">
                                <input type="submit" value="Add Book"
                                       class="btn btn-block btn-primary rounded-0 py-2 px-4">
                                <span class="submitting"></span>
                            </div>
                        </div>
                    </form>

                    <div id="form-message-warning mt-4"></div>
                    <div id="form-message-success">
                        Your message was sent, thank you!
                    </div>

                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>