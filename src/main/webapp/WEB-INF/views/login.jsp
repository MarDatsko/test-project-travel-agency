<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Academy.com</title>
</head>
<body>

<h1>Travel Agency</h1>
<h4>Login Form</h4>

<script>
    function ValidateEmail() {
        var email = document.form.username.value;

        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
            return true;
        }
        alert("You have entered an invalid email address!")
        return false;
    }
</script>

<form name="form" action='<spring:url value="/login"/>' method="post" onsubmit="return ValidateEmail()">
    <table>
        <tr>
            <td>Email</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password"></td>
        </tr>

        <tr>
            <td>
                <button type="submit">Login</button>
            </td>
            <td>
            <button onclick="location.href='/register'" type="button">
                Register</button>
            </td>
        </tr>


    </table>
</form>
<br/>
</body>
</html>