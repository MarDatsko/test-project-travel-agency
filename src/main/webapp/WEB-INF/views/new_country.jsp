<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>New Country</title>
</head>
<body>
<div align="center">
    <h2>New Country</h2>
    <form:form action="save" method="post" modelAttribute="country">
        <table border="0" cellpadding="5">
            <tr>
                <td>Country: </td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Visa: </td>
                <td><form:input path="visa.name" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
