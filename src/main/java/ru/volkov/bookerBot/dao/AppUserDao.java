package ru.volkov.bookerBot.dao;

import ru.volkov.bookerBot.domain.AppUser;
import ru.volkov.bookerBot.domain.Expens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserDao {
    private static final String INSERT_USER = "" +
            "INSERT INTO app_user (" +
            " us_id, us_firstName, us_lastName, us_userName)" +
            " VALUES ( ?, ?, ?, ?)";
    private static final String UPDATE_USER = "" +
            "UPDATE app_user SET " +
            "us_firstName = ?, " +
            "us_lastName = ?, " +
            "us_userName = ? " +
            "WHERE us_id = ?";
    private static final String USER_BY_ID = "" +
            "SELECT * FROM app_user " +
            "WHERE us_id = ?";


    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public void addAppUser(AppUser appUser) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            try {
                connection.setAutoCommit(false);

                statement.setLong(1, appUser.getId());
                statement.setString(2, appUser.getFirstName());
                statement.setString(3, appUser.getLastName());
                statement.setString(4, appUser.getUserName());

                statement.addBatch();
                statement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    public void updateAppUser(AppUser appUser) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            try {
                connection.setAutoCommit(false);

                statement.setString(1, appUser.getFirstName());
                statement.setString(2, appUser.getLastName());
                statement.setString(3, appUser.getUserName());
                statement.setLong(4, appUser.getId());

                statement.addBatch();
                statement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    public AppUser getUserById(Long userId) throws SQLException {
        AppUser appUserById = new AppUser();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(USER_BY_ID)) {
            statement.setLong(1,userId);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                appUserById.setId(userId);
                appUserById.setFirstName(rs.getString("us_firstName"));
                appUserById.setLastName(rs.getString("us_lastName"));
                appUserById.setUserName(rs.getString("us_userName"));
            }
        }
        return appUserById;
    }
}

