<pre><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
              pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>Register</title>
<script>

 function ValidateEmail(mail) {
     if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
         return true;
     }
     return false;
 }

 function validate() {
     var firstName = document.form.firstName.value;
     var lastName = document.form.lastName.value;
     var email = document.form.email.value;
     var password = document.form.password.value;
     var conpassword = document.form.conpassword.value;

     if (firstName == null || firstName == "") {
         alert("First name can't be blank");
         return false;
     }else if (lastName == null || lastName == "") {
         alert("Last name can't be blank");
         return false;
     } else if (email == null || email == "" || !ValidateEmail(email)) {
         alert("You have entered an invalid email address!")
         return false;
     } else if (password.length < 6) {
         alert("Password must be at least 6 characters long.");
         return false;
     } else if (password != conpassword) {
         alert("Confirm Password should match with the Password");
         return false;
     }
 }
</script>

</head>
 <body>
 <center><h2>Registration</h2><center>
 <form name="form" action="/register" method="post" onsubmit="return validate()">
  <table align="center">

   <tr>
      <td>First Name</td>
      <td><input type="text" name="firstName"/></td>
   </tr>

   <tr>
      <td>Last Name</td>
      <td><input type="text" name="lastName"/></td>
   </tr>

   <tr>
      <td>Email</td>
      <td><input type="text" name="email"/></td>
   </tr>

   <tr>
      <td>Password</td>
      <td><input type="password" name="password"/></td>
   </tr>

   <tr>
      <td>Confirm Password</td>
      <td><input type="password" name="conpassword"/></td>
   </tr>

 <tr>
 <td></td>
      <td><input type="submit" value="Register"></input><input
              type="reset" value="Reset"></input></td>
   </tr>

</table>
</form>
</body>
</html>