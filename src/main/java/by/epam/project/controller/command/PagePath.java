package by.epam.project.controller.command;

public class PagePath {
    public final static String INDEX = "/index.jsp";
    public final static String GUEST = "/WEB-INF/jsp/role/guest.jsp";
    public final static String CLIENT = "/WEB-INF/jsp/role/client.jsp";

    public final static String ERROR_404 = "/WEB-INF/jsp/error404.jsp";
    public final static String ERROR_500 = "/WEB-INF/jsp/error500.jsp";

    public final static String NOTIFICATION = "/WEB-INF/jsp/notification.jsp";
    public static final String EMAIL_ACTIVATION_LINK = "http://localhost:8080/HotelWeb?command=confirm_sign_up&login=";

    private PagePath(){}
}
