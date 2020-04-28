<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Country</title>
</head>
<body>
<div align="center">
    <h2>Country  - ${country.name}</h2>
    <sec:authorize access="hasRole('ROLE_MANAGER')">
        <h3><a href="/new_hotel/country/${country.id}">New Hotel</a></h3>
    </sec:authorize>
    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>Hotel</th>
        <sec:authorize access="hasRole('ROLE_MANAGER')">
            <th>Actions</th>
        </sec:authorize>
        </tr>
        <c:forEach var="hotel" items="${listHotels}" >
            <tr>
                <td>${hotel.id}</td>
                <td><a href="/reserveRoom/${hotel.id}">${hotel.name}</a></td>
                <sec:authorize access="hasRole('ROLE_MANAGER')">
                <td>
                    <a href="/editHotel/${hotel.id}/country/${country.id}">Edit</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/deleteHotel/${hotel.id}/${country.id}">Delete</a>
                    &nbsp;&nbsp;&nbsp;
                    <a href="/hotelStatistic/${hotel.id}">Statistic</a>
                </td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </table>

</div>
</body>
</html>
