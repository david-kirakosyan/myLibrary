<%@ page import="com.example.mylibrary.model.Author" %>
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

    <title>Add Author</title>
</head>
<% Author author = (Author) request.getAttribute("author");
    String msg = (String) request.getAttribute("msg");
%>
<body>


<div class="content">

    <div class="container">
        <div class="row align-items-stretch justify-content-center no-gutters">
            <div class="col-md-7">
                <div class="form h-100 contact-wrap p-5">
                    <a href="/authors">Back</a>  <h3 class="text-center">Edit Author</h3>
                    <% if (msg != null) {%>
                    <samp style="color: red"><%=msg%>
                    </samp>
                    <%}%>
                    <form class="mb-5" action="/editAuthor" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="<%=author.getId()%>">
                        <div class="row">
                            <div class="col-md-6 form-group mb-3">
                                <label for="name" class="col-form-label">Name</label>
                                <input type="text" class="form-control" name="name" id="name" value="<%=author.getName()%>">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="surname" class="col-form-label">Surname</label>
                                <input type="text" class="form-control" name="surname" id="surname" value="<%=author.getSurname()%>">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="email" class="col-form-label">Email</label>
                                <input type="email" class="form-control" name="email" id="email"
                                       value="<%=author.getEmail()%>">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="age" class="col-form-label">Age</label>
                                <input type="number" class="form-control" name="age" id="age"
                                       value="<%=author.getAge()%>">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="pic_name" class="col-form-label">Image</label>
                                <input type="file" class="form-control" name="profilePic" id="pic_name"
                                       value="<%=author.getPicName()%>" alt="">
                            </div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-md-5 form-group text-center">
                                <input type="submit" value="Edit Author"
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