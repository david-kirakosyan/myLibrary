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
    Book book = (Book) request.getAttribute("books");
    String msg = (String) request.getAttribute("msg");
%>

<div class="content">

    <div class="container">
        <div class="row align-items-stretch justify-content-center no-gutters">
            <div class="col-md-7">
                <div class="form h-100 contact-wrap p-5">
                    <a href="/books">Back</a>   <h3 class="text-center">Edit Book</h3>
                    <% if (msg != null) {%>
                    <samp style="color: red"><%=msg%>
                    </samp>
                    <%}%>
                    <form class="mb-5" action="/editBook" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="<%=book.getId()%>">
                        <div class="row">
                            <div class="col-md-6 form-group mb-3">
                                <label class="col-form-label">Title</label>
                                <input type="text" class="form-control" name="title" value="<%=book.getTitle()%>">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label class="col-form-label">Description</label>
                                <input type="text" class="form-control" name="description" value="<%=book.getDescription()%>">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label class="col-form-label">Price</label>
                                <input type="number" class="form-control" name="price"
                                       value="<%=book.getPrice()%>">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label class="col-form-label">Author name</label>
                                <SELECT name="author_id" class="form-control">
                                    <% for (Author author : authors) {%>
                                    <option value="<%=author.getId()%>"><%=author.getName()%></option>
                                    <%}%>
                                </SELECT>
                            </div>
                            <div class="col-md-6 float-right form-group mb-3">
                                <% if (book.getImage() == null || book.getImage().equalsIgnoreCase("null")) { %>
                                <img style="width: 100px" src="../register/images/difault-book.png" alt="">
                                <%} else {%>
                                <img style="width: 100px" src="/getImageBook?picName=<%=book.getImage()%>">
                                <%}%>
                                <img src="" alt="" class="img-responsive">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="pic_name" class="col-form-label">Image</label>
                                <input type="file" class="form-control" name="picName" id="pic_name"
                                       value="<%=book.getImage()%>" alt="">
                            </div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-md-5 form-group text-center">
                                <input type="submit" value="Edit Book"
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