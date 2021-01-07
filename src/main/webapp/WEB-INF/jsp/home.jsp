<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="en"/></c:when>
</c:choose>

<fmt:setBundle basename="pagecontent.language"/>

<html>
<head>
    <title>Home</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
</head>
<body>

<div id="app"/>

<script>
    let language = "${language}"

    let text_page = {
        name_hotel : "<fmt:message key="header.name_hotel"/>",
        sing_in : "<fmt:message key="header.sign_in"/>",
        sing_up : "<fmt:message key="header.sign_up"/>",
        language : "<fmt:message key="header.language"/>"
    }

    let sign_in_component = {
        name : "<fmt:message key="sign_in_component.name"/>",
        login : {
            name : "<fmt:message key="sign_in_component.login.name"/>",
            errors : {
                required : "<fmt:message key="sign_in_component.login.errors.required"/>",
                min_length : "<fmt:message key="sign_in_component.login.errors.min_length"/>",
                max_length : "<fmt:message key="sign_in_component.login.errors.max_length"/>",
                spaces_prohibited : "<fmt:message key="sign_in_component.login.errors.spaces_prohibited"/>",
            }
        }
    }

    let signInErrorMessage = "${signInErrorMessage}"
</script>
<script src="http://localhost:8081/home.js"></script>
</body>
</html>
