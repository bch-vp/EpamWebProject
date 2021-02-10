package by.epam.project.controller.async.command.impl.client;

import by.epam.project.controller.async.command.Command;
import by.epam.project.exception.DaoException;
import by.epam.project.model.connection.ConnectionPool;
import by.epam.project.model.dao.SqlQuery;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;

public class LoadFileCommand implements Command {
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
//            throw new DaoException(exp);
            System.out.println(exp);
        }

//        response.setHeader(CONTENT_TYPE, "image/jpeg");
        System.out.println(response.getContentType());
        response.getOutputStream().write(bytes);
//        response.setHeader(CONTENT_LENGTH, St
//        ring.valueOf(Files.size(path)));
    }
}
