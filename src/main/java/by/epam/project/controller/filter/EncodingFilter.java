package by.epam.project.controller.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * The type Encoding filter.
 */
public class EncodingFilter implements Filter {
    private String encoding;
    private static final String ENCODING_PARAMETER_NAME = "encoding";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(ENCODING_PARAMETER_NAME);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();
        if (encoding != null && !encoding.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        encoding = null;
    }
}
