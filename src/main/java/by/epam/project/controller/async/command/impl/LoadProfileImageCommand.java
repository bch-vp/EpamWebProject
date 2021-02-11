package by.epam.project.controller.async.command.impl;

import by.epam.project.controller.async.command.AsyncCommand;
import by.epam.project.model.connection.ConnectionPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

public class LoadProfileImageAsyncCommand implements AsyncCommand {
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_LENGTH = "Content-Length";

    public static final String FFF = "SELECT avatar FROM users WHERE login = ?";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isUpdated;
        byte[] bytes = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FFF)) {
            statement.setString(1, "ilya");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                bytes = resultSet.getBytes("avatar");
            }

        } catch (SQLException exp) {
            System.out.println(exp);
        }

        response.getOutputStream().write(bytes);
    }
}
