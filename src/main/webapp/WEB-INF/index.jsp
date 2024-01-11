<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            max-width: 600px;
        }
        .container2{
        	margin-top: 100px;
        }
        .card {
            border: none;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            background-color: #ffffff;
            margin-bottom: 20px;
        }
        .card-header {
            border-bottom: none;
            text-align: center;
            background-color: #007bff;
            color: #ffffff;
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
            padding: 20px;
        }
        .card-body {
            padding: 40px;
            font-weight: bold;
            font-size: 1.2rem;
            color: #333;
            line-height: 1.4;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            color: #007bff;
            font-weight: bold;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            border-radius: 8px;
            padding: 10px 20px;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .logout-link {
            color: black;
            text-decoration: none;
            font-weight: bold;
            float: right;
        }
        .logout-link:hover {
            text-decoration: underline;
        }
        .danger{
        	margin-top: 20px;
        	margin-bottom: -30px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-dark bg-success">
    <a class="navbar-brand" href="bank">Virtual Bank 🏦</a>
    <a href="bank?action=logout" class="logout-link">Logout 🚪</a>
</nav>

<div class="container mt-4">
    <div class="container2">
    	<div class="card">
	        <div class="card-body">
	            💶Your credit is: <span class="text-success">${solde}€</span>
	            <br>
	        </div>
	    </div>
	    <div class="card">
	        <div class="card-body">
	            <form action='bank' method='post'>
	                <div class="form-group">
	                    <label for='amount' style="color: black;">Amount:</label>
	                    <input type='number' name='amount' class="form-control" required placeholder="1.00" step="0.1" min="0">
	                </div>
	                <div class="form-group">
	                    <label for='operation' style="color: black;">Operation:</label>
	                    <select name="operation" class="form-control">
	                    	<option value="verser">Deposit</option>
	                    	<option value="retirer">Withdraw</option>
	                        <option value="conversion">Conversion</option>
	                    </select>
	                </div>
	                <button type='submit' class="btn bg-success btn-block" style="color: white;">Submit</button>
	                <%
					    if (request.getAttribute("soldeInsuffisant") != null) {
					%>
					    <p class="text-danger danger" style="text-align: center;">
					    	Withdrawal amount exceeds your balance. 🚫
					    </p>
					<%
					    }
					%>
					<%
					    if (request.getAttribute("conversion") != null) {
					%>
					    <p class="danger" style="text-align: center;">
					    	The amount in Euro: <span class="text-success">${soldeBefore}</span> €, <br>
					    	The amount in Dinar: <span class="text-success">${soldeAfter}</span> TND
					    </p>
					<%
					    }
					%>
	            </form>
	            <br>
	        </div>
	    </div>
    </div>
</div>

<!-- Include Bootstrap JS and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>