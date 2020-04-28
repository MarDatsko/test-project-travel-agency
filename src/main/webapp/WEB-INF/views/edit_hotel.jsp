<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
    function get_action() {
        var url = '/saveHotelEditing/' + ${country_id};
        return url;
    }
</script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit Hotel</title>
</head>
<body>
<div align="center">
    <h2>Edit Hotel</h2>
    <form:form action="" method="post" modelAttribute="hotel" onsubmit="this.action=get_action();">
        <table border="0" cellpadding="5">
            <tr>
                <td>ID: </td>
                <td>${hotel.id}
                    <form:hidden path="id"/>
                </td>
            </tr>
            <tr>
                <td>Name: </td>
                <td><form:input path="name" /></td>
            </tr>

                <td colspan="2"><input type="submit" value="Save"></td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
