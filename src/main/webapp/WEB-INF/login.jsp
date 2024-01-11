<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <style>
        .container {
            max-width: 400px;
            margin-top: 170px;
        }
        .color{
        	color: "white";
        }
        .card{
        	border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>

<div class="container">
    <div class="card">
        <div class="card-header bg-success text-white text-center">
            <h2 class="card-title">Login</h2>
        </div>
        <div class="card-body">
                <form action='login' method='post'>
                    <div class="form-group">
                        <label for='username'>Username 👤</label>
                        <input type='text' name='username' class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for='password'>Password 🔑</label>
                        <input type='password' name='password' class="form-control" required>
                    </div>
                    <button type='submit' class="btn bg-success btn-block" style="color: white;">Login</button>
                </form>
       </div>
       <%
		    if (request.getAttribute("loginFailed") != null) {
		%>
		    <p class="text-danger" style="text-align: center;">Invalid username or password.</p>
		<%
		    }
		%>
    </div>
</div>

<!-- Include Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>