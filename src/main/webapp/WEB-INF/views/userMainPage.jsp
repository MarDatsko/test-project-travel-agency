<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<script >
    function goToPage()
    {
        var url = document.getElementById('nameId');
        document.location.href = '/freeHotel/' + url.value;
    }
</script>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>"Sun"</title>
</head>
<body>
<div>
    <h2>Travel Agency "Sun"</h2>
    <form:form action="/freeHotel" method="post" modelAttribute="dateAndCountryDto">

            <p>We work with such countries, you can see the list of these countries --->
                <a href="/countries">here</a>
            </p>
            <p>By selecting a country and date, you can see a list of hotels with available rooms for a certain day</p>
            <tr>
                <td><form:label path = "name" >Country</form:label></td>
                <td>
                    <form:select path="name" id="nameId">
                        <form:option value="NONE" label="--- Select ---" />
                        <form:options items="${countryList}" itemValue="name" itemLabel="name" />
                    </form:select>
                </td>
            </tr>


        <label>Start Booking:</label>
        <td><form:input path="firstDate" type="date"/></td>
        <label>End Booking:</label>
        <td><form:input path="secondDate" type="date"/></td>

        <a href="javascript: goToPage();">Find</a>
    </form:form>
</div>
</body>
</html>