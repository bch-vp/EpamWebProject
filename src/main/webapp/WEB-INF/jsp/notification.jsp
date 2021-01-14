<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${not empty language}"> <fmt:setLocale value="${language}"/></c:when>
    <c:when test="${empty language}"> <fmt:setLocale value="en"/></c:when>
</c:choose>

<fmt:setBundle basename="content.language"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<body>
<div id="app"/>
<script>
    let text_page = {
        notification_component: {
            title: "<fmt:message key="notification_component.notification_component.title"/>",
            text: "<fmt:message key="notification_component.notification_component.text"/>",
            button: "<fmt:message key="notification_component.notification_component.button"/>",
        }
    }
</script>
<script src="http://localhost:8081/notification.js"></script>
</body>
</html>