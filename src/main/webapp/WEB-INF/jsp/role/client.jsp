<%--
  Created by IntelliJ IDEA.
  User: ilyamurin
  Date: 1/3/21
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="en"/></c:when>
</c:choose>

<fmt:setBundle basename="content.language"/>

<html>
<head>
    <title>Client</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Monoton&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
</head>
<body>
<div id="app"/>
<script>
    let text_page = {
        header: {
            hotel: "<fmt:message key="header.hotel"/>",
            sing_in: "<fmt:message key="header.sign_in"/>",
            sing_up: "<fmt:message key="header.sign_up"/>",
        },
        language:  "<c:out value="${language}"/>",
        profile_component:{
            login: "<c:out value="${user.login}"/>",
            first_name: "<c:out value="${user.firstName}"/>",
            last_name: "<c:out value="${user.lastName}"/>",
            telephone_number: "<c:out value="${user.telephoneNumber}"/>",
            email: "<c:out value="${user.email}"/>"
        }
    }
</script>
<script src="http://localhost:8081/client.js"></script>
</body>
</html>
