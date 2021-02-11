package by.epam.project.controller.filter;

import by.epam.project.controller.filter.util.RolePermission;
import by.epam.project.controller.parameter.PagePath;
import by.epam.project.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Set;

import static by.epam.project.controller.parameter.ParameterKey.COMMAND;
import static by.epam.project.controller.parameter.ParameterKey.USER;

public class CommandRoleSecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String commandName = request.getParameter(COMMAND);
        if(commandName == null){
            request.getRequestDispatcher(PagePath.ERROR_404).forward(servletRequest, servletResponse);
            return;
        }

        User.Role role = User.Role.GUEST;
        Set<String> commandsByRole;

        User user = (User) session.getAttribute(USER);
        if (user != null) {
            role = user.getRole();
        }
        switch (role) {
            case CLIENT -> {
                commandsByRole = RolePermission.CLIENT.getCommands();
            }
            case ADMIN -> {
                commandsByRole = RolePermission.ADMIN.getCommands();
            }
            default -> {
                commandsByRole = RolePermission.GUEST.getCommands();
            }
        }

        commandName = commandName.toUpperCase();
        if (!commandsByRole.contains(commandName)) {
            request.getRequestDispatcher(PagePath.ERROR_404).forward(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
