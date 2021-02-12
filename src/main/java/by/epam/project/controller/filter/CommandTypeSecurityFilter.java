package by.epam.project.controller.filter;

import by.epam.project.controller.parameter.PagePath;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandTypeSecurityFilter implements Filter {
    private static final String PATH = "/ajax, /do";

    @Override
    public void init(FilterConfig filterConfig){}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestPath = request.getRequestURI();

        if(!PATH.contains(requestPath)){
            request.getRequestDispatcher(PagePath.ERROR_404).forward(servletRequest, servletResponse);
            return;
        }

        chain.doFilter(servletRequest, servletResponse);
    }
}