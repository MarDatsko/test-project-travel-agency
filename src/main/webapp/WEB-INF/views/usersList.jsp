<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Users</title>
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
        <c:forEach var="user" items="${listUserDto}" >
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.userRole}</td>
                <sec:authorize access="hasRole('ROLE_MANAGER')">
                    <td>
                        <a href="/edit?id=${user.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=${user.id}">Delete</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="/userStatistic/${user.id}">Statistic</a>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>