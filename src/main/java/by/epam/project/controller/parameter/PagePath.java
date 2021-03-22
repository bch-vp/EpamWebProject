package by.epam.project.controller.parameter;

public class PagePath {

    public final static String PASSING_BY_GUEST = "/WEB-INF/jsp/role/guest.jsp";
    public final static String PASSING_BY_CLIENT = "/WEB-INF/jsp/role/client.jsp";
    public final static String PASSING_BY_ADMIN = "/WEB-INF/jsp/role/admin.jsp";

    public final static String ERROR_403 = "/jsp/error403.jsp";
    public final static String ERROR_404 = "/jsp/error404.jsp";
    public final static String ERROR_500 = "/jsp/error500.jsp";

    public final static String NOTIFICATION_SUCCESS = "/jsp/notificationSuccess.jsp";

    private PagePath() {
    }
}
