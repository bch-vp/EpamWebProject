package by.epam.project.controller.sync;

import by.epam.project.controller.constant.RequestParameterKey;
import by.epam.project.controller.sync.command.Command;
import by.epam.project.controller.sync.command.CommandProvider;
import by.epam.project.model.connection.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command = CommandProvider.provideCommand(request.getParameter(RequestParameterKey.COMMAND));
        Router router = command.execute(request);

        String currentPage = router.getCurrentPage();

        if (router.getCurrentType().equals(Router.Type.FORWARD)) {
            request.getRequestDispatcher(currentPage).forward(request, response);
        } else {
            response.sendRedirect(currentPage);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().destroyPool();
    }
}

