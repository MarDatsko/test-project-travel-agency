<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2>Country Manager</h2>
    <form method="get" action="search">
        <input type="text" name="keyword" /> &nbsp;
        <input type="submit" value="Search" />
    </form>
    <h3><a href="/new_country">New Country</a></h3>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Country</th>
            <th>Visa</th>
            <th>Action</th>
        </tr>
        <c:forEach var="country" items="${listCountries}" >
            <tr>
                <td>${country.id}</td>
                <td>${country.name}</td>
                <td>${country.visa}</td>

                <td>
                    <a href="/edit?id=${customer.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=${customer.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
