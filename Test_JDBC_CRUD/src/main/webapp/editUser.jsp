<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 29.12.2017
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<form method="post" action="/userAction" name="formEdit">
    User id: <input type="text" readonly=readonly name="id" value="<c:out value="${user.id}"/>">
    UFirst name: <input type="text" name="firstName" value="<c:out value="${user.firstName}"/>">
    Last name: <input type="text" name="lastName" value="<c:out value="${user.lastName}"/>">
    Email: <input type="text" name="email" value="<c:out value="${user.email}"/>">
    Date of Birth: <input type="text" name="dob"
                    value="<fmt:formatDate pattern="dd/mm/yyyy" value="${user.dob}"/>">
    <input type="submit" value="Submit">
</form>

</body>
</html>
