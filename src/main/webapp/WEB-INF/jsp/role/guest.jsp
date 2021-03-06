<%--
  Created by IntelliJ IDEA.
  User: ilyamurin
  Date: 1/3/11
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
    <title>Guest</title>
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
        language: "${language}",
        notification_component: {
            title: "<fmt:message key="notification_component.notification_component.title"/>",
            text: "<fmt:message key="notification_component.notification_component.text"/>",
            button: "<fmt:message key="notification_component.notification_component.button"/>",
        },
        guest_home_component: {
            greeting: {
                title: "<fmt:message key="guest_home_component.greeting.title"/>",
                text: "<fmt:message key="guest_home_component.greeting.text"/>",
                button: {
                    submit: "<fmt:message key="guest_home_component.greeting.button.submit"/>",
                }
            }
        },
        page_info: {
            pages: "<fmt:message key="page_info.pages"/>",
            products: "<fmt:message key="page_info.products"/>",
            total_price: "<fmt:message key="page_info.total_price"/>"
        },
        form_component: {
            input: {
                login: {
                    name: "<fmt:message key="form_component.input.login"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.login.error.required"/>",
                        valid_characters: "<fmt:message key="form_component.input.login.error.valid_characters"/>",
                        min_length: "<fmt:message key="form_component.input.login.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.login.error.max_length"/>",
                        spaces_prohibited: "<fmt:message key="form_component.input.login.error.spaces_prohibited"/>",
                    }
                },
                password: {
                    name: "<fmt:message key="form_component.input.password"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.password.error.required"/>",
                        valid_characters: "<fmt:message key="form_component.input.password.error.valid_characters"/>",
                        min_length: "<fmt:message key="form_component.input.password.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.password.error.max_length"/>",
                        spaces_prohibited: "<fmt:message key="form_component.input.password.error.spaces_prohibited"/>",
                        one_digit: "<fmt:message key="form_component.input.password.error.one_digit"/>",
                        one_lower_case_letter: "<fmt:message key="form_component.input.password.error.one_lower_case_letter"/>",
                        one_upper_case_letter: "<fmt:message key="form_component.input.password.error.one_upper_case_letter"/>",
                    }
                },
                old_password: {
                    name: "<fmt:message key="form_component.input.old_password"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.old_password.error.required"/>",
                        valid_characters: "<fmt:message key="form_component.input.old_password.error.valid_characters"/>",
                        min_length: "<fmt:message key="form_component.input.old_password.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.old_password.error.max_length"/>",
                        spaces_prohibited: "<fmt:message key="form_component.input.old_password.error.spaces_prohibited"/>",
                        one_digit: "<fmt:message key="form_component.input.old_password.error.one_digit"/>",
                        one_lower_case_letter: "<fmt:message key="form_component.input.old_password.error.one_lower_case_letter"/>",
                        one_upper_case_letter: "<fmt:message key="form_component.input.old_password.error.one_upper_case_letter"/>",
                    }
                },
                new_password: {
                    name: "<fmt:message key="form_component.input.new_password"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.new_password.error.required"/>",
                        valid_characters: "<fmt:message key="form_component.input.new_password.error.valid_characters"/>",
                        min_length: "<fmt:message key="form_component.input.new_password.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.new_password.error.max_length"/>",
                        spaces_prohibited: "<fmt:message key="form_component.input.new_password.error.spaces_prohibited"/>",
                        one_digit: "<fmt:message key="form_component.input.new_password.error.one_digit"/>",
                        one_lower_case_letter: "<fmt:message key="form_component.input.new_password.error.one_lower_case_letter"/>",
                        one_upper_case_letter: "<fmt:message key="form_component.input.new_password.error.one_upper_case_letter"/>",
                    }
                },
                password_repeat: {
                    name: "<fmt:message key="form_component.input.password_repeat"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.password_repeat.error.required"/>",
                        not_equal: "<fmt:message key="form_component.input.password_repeat.error.not_equal"/>"
                    }
                },
                name: {
                    name: "<fmt:message key="form_component.input.name"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.name.error.required"/>",
                        min_length: "<fmt:message key="form_component.input.name.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.name.error.max_length"/>",
                        pattern: "<fmt:message key="form_component.input.name.error.pattern"/>",
                    }
                },
                address: {
                    name: "<fmt:message key="form_component.input.address"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.address.error.required"/>",
                        min_length: "<fmt:message key="form_component.input.address.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.address.error.max_length"/>",
                        pattern: "<fmt:message key="form_component.input.address.error.pattern"/>",
                    }
                },
                price: {
                    name: "<fmt:message key="form_component.input.price"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.price.error.required"/>",
                        min_length: "<fmt:message key="form_component.input.price.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.price.error.max_length"/>",
                        pattern: "<fmt:message key="form_component.input.price.error.pattern"/>",
                    }
                },
                info: {
                    name: "<fmt:message key="form_component.input.info"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.info.error.required"/>",
                        min_length: "<fmt:message key="form_component.input.info.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.info.error.max_length"/>",
                        pattern: "<fmt:message key="form_component.input.info.error.pattern"/>"
                    }
                },
                comment: {
                    name: "<fmt:message key="form_component.input.comment"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.comment.error.required"/>",
                        min_length: "<fmt:message key="form_component.input.comment.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.comment.error.max_length"/>",
                        pattern: "<fmt:message key="form_component.input.comment.error.pattern"/>"
                    }
                },
                first_name: {
                    name: "<fmt:message key="form_component.input.first_name"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.first_name.error.required"/>",
                        min_length: "<fmt:message key="form_component.input.first_name.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.first_name.error.max_length"/>",
                        spaces_prohibited: "<fmt:message key="form_component.input.first_name.error.spaces_prohibited"/>",
                        only_letters: "<fmt:message key="form_component.input.first_name.error.only_letters"/>",
                    }
                },
                last_name: {
                    name: "<fmt:message key="form_component.input.last_name"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.last_name.error.required"/>",
                        min_length: "<fmt:message key="form_component.input.last_name.error.min_length"/>",
                        max_length: "<fmt:message key="form_component.input.last_name.error.max_length"/>",
                        spaces_prohibited: "<fmt:message key="form_component.input.last_name.error.spaces_prohibited"/>",
                        only_letters: "<fmt:message key="form_component.input.last_name.error.only_letters"/>",
                    }
                },
                telephone_number: {
                    name: "<fmt:message key="form_component.input.telephone_number"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.telephone_number.error.required"/>",
                        spaces_prohibited:
                            "<fmt:message key="form_component.input.telephone_number.error.spaces_prohibited"/>",
                        pattern: "<fmt:message key="form_component.input.telephone_number.error.pattern"/>",
                    }
                },
                email: {
                    name: "<fmt:message key="form_component.input.email"/>",
                    error: {
                        required: "<fmt:message key="form_component.input.email.error.required"/>",
                        max_length: "<fmt:message key="form_component.input.email.error.max_length"/>",
                        spaces_prohibited: "<fmt:message key="form_component.input.email.error.spaces_prohibited"/>",
                        pattern: "<fmt:message key="form_component.input.email.error.pattern"/>",
                    }
                },
                unique_key: {
                    name: "<fmt:message key="form_component.input.unique_key"/>",
                    error: {
                        not_empty: "<fmt:message key="form_component.input.unique_key.error.not_empty"/>",
                        length: "<fmt:message key="form_component.input.unique_key.error.length"/>",
                        only_digits: "<fmt:message key="form_component.input.unique_key.error.only_digits"/>"
                    }
                }
            },
            title: {
                sign_in: "<fmt:message key="form_component.title.sign_in"/>",
                sign_up: "<fmt:message key="form_component.title.sign_up"/>",
                change_password: "<fmt:message key="form_component.title.change_password"/>",
            },
            info: {
                success: "<fmt:message key="form_component.info.success"/>",
                check_email: "<fmt:message key="form_component.info.check_email.unique_code"/>",
            },
            error: {
                old_password_not_equal_login_password:
                    "<fmt:message key="form_component.error.old_password_not_equal_login_password"/>",
                not_found: "<fmt:message key="form_component.error.not_found"/>",
                time_expired: "<fmt:message key="form_component.error.time_expired"/>",
                email_incorrect: "<fmt:message key="form_component.error.email_incorrect"/>",
                notification: "<fmt:message key="form_component.error.notification"/>",
                need_reload_page: "<fmt:message key="form_component.error.need_reload_page"/>",
                unique_key_incorrect: "<fmt:message key="form_component.error.unique_key_incorrect"/>",
            },
            button: {
                submit: "<fmt:message key="form_component.button.submit"/>",
                exit: "<fmt:message key="form_component.button.exit"/>",
                choose_category: "<fmt:message key="form_component.button.choose_category"/>",
                order: "<fmt:message key="form_component.button.order"/>",
                orders: "<fmt:message key="form_component.button.orders"/>",
                choose_status: "<fmt:message key="form_component.button.choose_status"/>",
                show_products: "<fmt:message key="form_component.button.show_products"/>",
                search: "<fmt:message key="form_component.button.search"/>",
                info: "<fmt:message key="form_component.button.info"/>",
                date_created: "<fmt:message key="form_component.button.date_created"/>",
                sort_by: "<fmt:message key="form_component.button.sort_by"/>",
                adds: "<fmt:message key="form_component.button.adds"/>",
                confirmed: "<fmt:message key="form_component.button.confirmed"/>",
                not_confirmed: "<fmt:message key="form_component.button.not_confirmed"/>",
                choose_status_for_ordering: "<fmt:message key="form_component.button.choose_status_for_ordering"/>",
                change_password: "<fmt:message key="form_component.button.change_password"/>",
                delete_avatar: "<fmt:message key="form_component.button.delete_avatar"/>",
                edit_profile_info: "<fmt:message key="form_component.button.edit_profile_info"/>",
                go_to_component: {
                    sign_up: "<fmt:message key="form_component.button.go_to_component.sign_up"/>",
                    sign_in: "<fmt:message key="form_component.button.go_to_component.sign_in"/>",
                    change_password_by_email:
                        "<fmt:message key="form_component.button.go_to_component.change_password_by_email"/>",
                }
            }
        }
    }
</script>
<c:choose>
    <c:when test="${is_dev_mode}">
        <script src="http://localhost:8081/guest.js"></script>
    </c:when>
    <c:otherwise>
        <script src="/js/guest.js"></script>
    </c:otherwise>
</c:choose>
</body>
</html>
