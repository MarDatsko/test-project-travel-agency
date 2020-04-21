<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 21.04.2020
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h2>Country Manager</h2>
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
                <td>${country.visa.name}</td>

                <td>
                    <a href="/edit?id=${country.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=${customer.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
