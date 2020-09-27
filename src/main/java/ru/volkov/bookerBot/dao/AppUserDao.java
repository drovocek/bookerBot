package ru.volkov.bookerBot.dao;

import ru.volkov.bookerBot.domain.AppUser;
import ru.volkov.bookerBot.domain.Expens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppUserDao {
    public static final String INSERT_EXPENS = "INSERT INTO app_user (" +
            " us_id, us_firstName, us_lastName, us_userName)" +
            "VALUES ( ?, ?, ?, ?)";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public void saveAppUser(AppUser appUser) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EXPENS)) {
            try{
                connection.setAutoCommit(false);

                statement.setLong(1, appUser.getId());
                statement.setString(2, appUser.getFirstName());
                statement.setString(3, appUser.getLastName());
                statement.setString(4, appUser.getUserName());

                statement.addBatch();
                statement.executeBatch();
                connection.commit();
            }catch (SQLException e){
                connection.rollback();
                throw e;
            }
        }
    }

}
