<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Countries</title>
</head>
<body>
<div align="center">
    <h2>User List</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Role</th>
            <sec:authorize access="hasRole('ROLE_MANAGER')">
                <th>Action</th>
            </sec:authorize>
        </tr>
        <c:forEach var="country" items="${listCountries}" >
            <tr>
                <td>${country.id}</td>
                <td><a href="/country?id=${country.id}">${country.name}</a></td>
                <td>${country.visa.name}</td>
                <td>${country.visa.name}</td>
                <td>${country.visa.name}</td>
                <sec:authorize access="hasRole('ROLE_MANAGER')">
                    <td>
                        <a href="/edit?id=${country.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=${country.id}">Delete</a>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>