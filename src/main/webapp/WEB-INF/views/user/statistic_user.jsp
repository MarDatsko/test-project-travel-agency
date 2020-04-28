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
    <h2>User Statistic</h2>
    <div>

        <h4>List countries where was user : </h4>
        <c:forEach var="listCountries" items="${listCountriesWhereWasUser}" >
            <p>${listCountries}</p>
        </c:forEach>

        <h4>List visas what has user : </h4>
        <c:forEach var="listVisas" items="${listVisasWhichHasUser}" >
            <p>${listVisas}</p>
        </c:forEach>

    </div>
</div>
</body>
</html>