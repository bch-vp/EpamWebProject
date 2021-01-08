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
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@4.x/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900|Material+Icons" rel="stylesheet">
<%--    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@4.x/css/materialdesignicons.min.css" rel="stylesheet">--%>
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
            language: "<fmt:message key="header.language"/>"
        },
        sign_in_component: {
            name: "<fmt:message key="sign_in_component"/>",
            submit: "<fmt:message key="sign_in_component.submit"/>",
            login: {
                name: "<fmt:message key="sign_in_component.login"/>",
                error: {
                    required: "<fmt:message key="sign_in_component.login.error.required"/>",
                    min_length: "<fmt:message key="sign_in_component.login.error.min_length"/>",
                    max_length: "<fmt:message key="sign_in_component.login.error.max_length"/>",
                    spaces_prohibited: "<fmt:message key="sign_in_component.login.error.spaces_prohibited"/>",
                }
            },
            password: {
                name: "<fmt:message key="sign_in_component.password"/>",
                error: {
                    required: "<fmt:message key="sign_in_component.password.error.required"/>",
                    min_length: "<fmt:message key="sign_in_component.password.error.min_length"/>",
                    max_length: "<fmt:message key="sign_in_component.password.error.max_length"/>",
                    spaces_prohibited: "<fmt:message key="sign_in_component.password.error.spaces_prohibited"/>",
                    one_digit: "<fmt:message key="sign_in_component.password.error.one_digit"/>",
                    one_lower_case_letter: "<fmt:message key="sign_in_component.password.error.one_lower_case_letter"/>",
                    one_upper_case_letter: "<fmt:message key="sign_in_component.password.error.one_upper_case_letter"/>",
                }
            }
        },
        sign_up_component: {
            name: "<fmt:message key="sign_up_component"/>",
            submit: "<fmt:message key="sign_up_component.submit"/>",
            login: {
                name: "<fmt:message key="sign_up_component.login"/>",
                error: {
                    required: "<fmt:message key="sign_up_component.login.error.required"/>",
                    min_length: "<fmt:message key="sign_up_component.login.error.min_length"/>",
                    max_length: "<fmt:message key="sign_up_component.login.error.max_length"/>",
                    spaces_prohibited: "<fmt:message key="sign_up_component.login.error.spaces_prohibited"/>",
                }
            },
            password: {
                name: "<fmt:message key="sign_up_component.password"/>",
                error: {
                    required: "<fmt:message key="sign_up_component.password.error.required"/>",
                    min_length: "<fmt:message key="sign_up_component.password.error.min_length"/>",
                    max_length: "<fmt:message key="sign_up_component.password.error.max_length"/>",
                    spaces_prohibited: "<fmt:message key="sign_up_component.password.error.spaces_prohibited"/>",
                    one_digit: "<fmt:message key="sign_up_component.password.error.one_digit"/>",
                    one_lower_case_letter: "<fmt:message key="sign_up_component.password.error.one_lower_case_letter"/>",
                    one_upper_case_letter: "<fmt:message key="sign_up_component.password.error.one_upper_case_letter"/>",
                }
            },
            first_name: {
                name: "<fmt:message key="sign_up_component.first_name"/>",
                error: {
                    required: "<fmt:message key="sign_up_component.name.error.required"/>",
                    min_length: "<fmt:message key="sign_up_component.name.error.min_length"/>",
                    max_length: "<fmt:message key="sign_up_component.name.error.max_length"/>",
                    spaces_prohibited: "<fmt:message key="sign_up_component.name.error.spaces_prohibited"/>",
                    only_letters: "<fmt:message key="sign_up_component.name.error.only_letters"/>",
                }
            },
            last_name: {
                name: "<fmt:message key="sign_up_component.last_name"/>",
                error: {
                    required: "<fmt:message key="sign_up_component.surname.error.required"/>",
                    min_length: "<fmt:message key="sign_up_component.surname.error.min_length"/>",
                    max_length: "<fmt:message key="sign_up_component.surname.error.max_length"/>",
                    spaces_prohibited: "<fmt:message key="sign_up_component.surname.error.spaces_prohibited"/>",
                    only_letters: "<fmt:message key="sign_up_component.surname.error.only_letters"/>",
                }
            },
            telephone_number: {
                name: "<fmt:message key="sign_up_component.telephone_number"/>",
                error: {
                    required: "<fmt:message key="sign_up_component.telephone_number.error.required"/>",
                    max_length: "<fmt:message key="sign_up_component.telephone_number.error.max_length"/>",
                    spaces_prohibited: "<fmt:message key="sign_up_component.telephone_number.error.spaces_prohibited"/>",
                    pattern: "<fmt:message key="sign_up_component.telephone_number.error.pattern"/>",
                }
            },
            email: {
                name: "<fmt:message key="sign_up_component.email"/>",
                error: {
                    required: "<fmt:message key="sign_up_component.email.error.required"/>",
                    max_length: "<fmt:message key="sign_up_component.email.error.max_length"/>",
                    spaces_prohibited: "<fmt:message key="sign_up_component.email.error.spaces_prohibited"/>",
                    pattern: "<fmt:message key="sign_up_component.email.error.pattern"/>",
                }
            }
        }
    }
    let language = "${language}"
    let signInErrorMessage = "${signInErrorMessage}"
</script>
<script src="http://localhost:8081/home.js"></script>
</body>
</html>
