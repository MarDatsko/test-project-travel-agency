<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

<script type="text/javascript">
    $(function() {
        $("#datepicker").datepicker({dateFormat: "yy-mm-dd" }).val()
    });
    $(function() {
        $("#datepicker1").datepicker({ dateFormat: "yy-mm-dd" }).val()
    });
</script>

<script type="text/javascript">
    function get_action() {
        var name = document.getElementById('nameId');
        var url = '/freeHotel/' + name.value;
        return url;
    }
</script>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>"Sun"</title>
</head>
<body>
<div>
    <h2>Travel Agency "Sun"</h2>
    <form:form action="" method="post" modelAttribute="dateAndCountryDto" onsubmit="this.action=get_action();">

            <p>We work with such countries, you can see the list of these countries --->
                <a href="/countries">here</a>
            </p>
            <p>By selecting a country and date, you can see a list of hotels with available rooms for a certain day</p>
            <tr>
                <td><form:label path = "name" >Country</form:label></td>
                <td>
                    <form:select path="id" id="nameId">
                        <form:option value="NONE" label="--- Select ---" />
                        <form:options items="${countryList}" itemValue="id" itemLabel="name" />
                    </form:select>
                </td>
            </tr>

        <td>
            <form:label path="firstDate">Start Date</form:label>
            <form:input path="firstDate" id="datepicker"  name="startDate" type="text" autocomplete="off"/>
        </td>

        <td>
            <form:label path="secondDate">End Date</form:label>
            <form:input path="secondDate" id="datepicker1" name="endDate" type="text" autocomplete="off"/>
        </td>

        <input type="submit" value="Save"></td>

    </form:form>
</div>
</body>

</html>