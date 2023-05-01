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
<body>


<div class="content">

    <div class="container">
        <div class="row align-items-stretch justify-content-center no-gutters">
            <div class="col-md-7">
                <div class="form h-100 contact-wrap p-5">
                    <a href="/authors">Back</a>  <h3 class="text-center">Add Author</h3>
                    <form class="mb-5" action="/createAuthor" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-6 form-group mb-3">
                                <label for="name" class="col-form-label">Name</label>
                                <input type="text" class="form-control" name="name" id="name" placeholder="Your Name">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="surname" class="col-form-label">Surname</label>
                                <input type="text" class="form-control" name="surname" id="surname" placeholder="Your Surname">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="email" class="col-form-label">Email</label>
                                <input type="email" class="form-control" name="email" id="email"
                                       placeholder="Your email">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="age" class="col-form-label">Age</label>
                                <input type="number" class="form-control" name="age" id="age"
                                       placeholder="Your Age">
                            </div>
                            <div class="col-md-6 form-group mb-3">
                                <label for="pic_name" class="col-form-label">Image</label>
                                <input type="file" class="form-control" name="profilePic" id="pic_name">
                            </div>
                        </div>
                        <div class="row justify-content-center">
                            <div class="col-md-5 form-group text-center">
                                <input type="submit" value="Add Author"
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