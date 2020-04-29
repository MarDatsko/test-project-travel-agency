<%--
  Created by IntelliJ IDEA.
  User: Denys
  Date: 28.04.2020
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
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
    function get_action() {
        var name = document.getElementById('nameId');
        var url = '/roomStatistic/' + name.value;
        return url;
    }
</script>

<script type="text/javascript">
    $(function() {
        $("#startdate").datepicker({ dateFormat: "yy-mm-dd" });
        $("#enddate").datepicker({ dateFormat: "yy-mm-dd" });
    });
</script>

<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <h1>Hotel Statistic</h1>
    <h2>Selected hotel - ${hotel.name}</h2>
    <div>
        <h4>Total number of customers : ${numberOfCustomers} </h4>
        <h4>Average booking time : ${averageReserveTime} days</h4>
    </div>


    <form:form action="" method="post" modelAttribute="room" onsubmit="this.action=get_action();">
        <tr>
            <td><form:label path = "name" >Room: </form:label></td>
            <td>
                <form:select path="id" id="nameId">
                    <form:option value="NONE" label="--- Select ---" />
                    <form:options items="${listRooms}" itemValue="id" itemLabel="name" />
                </form:select>
            </td>
        </tr>
        <td>
            <form:label path="firstDate">Start Date</form:label>
            <form:input path="firstDate" id="startdate"  name="startDate" type="text" autocomplete="off"/>
        </td>
        <td>
            <form:label path="secondDate">End Date</form:label>
            <form:input path="secondDate" id="enddate" name="endDate" type="text" autocomplete="off"/>
        </td>
        <input type="submit" value="Save"></td
    </form:form>

    <div>
        <h3>${logo}</h3>
        <p>${statistic} ${day}</p>
    </div>

</div>
</body>
</html>
