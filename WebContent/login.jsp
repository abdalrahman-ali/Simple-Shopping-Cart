<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("form").submit(function(ev) {
			//ev.preventDefault();
			var $username =$(this).find("[name=username]");
		    var $password =$(this).find("[name=password]");
			var ip = {
				"username" : $username.val(),
				"password" : $password.val()
			};
			var valid = true;
			$username.css("border-color","");
			$password.css("border-color","");
			$("#username-error").hide();
			$("#password-error").hide();
			if (ip.username === "") {
				valid = false;
				$username.css("border-color","red");
				$("#username-error").show();
				console.log("username: antesh");
			}
			if (ip.password === "") {
				valid = false;
				$password.css("border-color","red");
				console.log("password : antesh");
				$("#password-error").show();
			}
			return valid;
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>
	<center>
	<form method="POST" action="Controller">
		<label for="username"> User name : <input
			placeholder="User Name" type="text" name="username" /> <span id="username-error" style="color:red;display:none" >please enter a username</span>
		</label><br /> <label for="password"> Password : <input
			placeholder="Password" type="password" name="password" /> <span id="password-error" style="color:red;display:none" >please enter a password</span>
		</label><br /> <input type="submit" value="login" />

	</form>
	
	<c:choose>
    <c:when test="${empty user}">
       
    </c:when>
    <c:otherwise>
       <span style="color:red" >wrong username or password </span>
    </c:otherwise>
</c:choose>


</center>
</body>
</html>