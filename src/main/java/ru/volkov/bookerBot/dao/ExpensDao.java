package ru.volkov.bookerBot.dao;

import ru.volkov.bookerBot.domain.Expens;
import ru.volkov.bookerBot.domain.ExpensType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class ExpensDao {
    public static final String INSERT_EXPENS = "INSERT INTO user_expens (" +
            " ex_us_id, ex_sum, ex_date, ex_type, ex_description, ex_cash)" +
            "VALUES ( ?, ?, ?, ?, ?, ?)";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public void saveExpens(Expens expens) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EXPENS)) {
            try{
                connection.setAutoCommit(false);

                statement.setLong(1, expens.getUser_id());
                statement.setDouble(2, expens.getSum());
                statement.setDate(3, java.sql.Date.valueOf(expens.getDate()));
                statement.setString(4, expens.getExpensType().getExpensType());
                statement.setString(5, expens.getDescription());
                statement.setBoolean(6, expens.getCash());

                statement.addBatch();
                statement.executeBatch();
                connection.commit();
            }catch (SQLException e){
                connection.rollback();
                throw e;
            }
        }
    }

    private void deleteExpens(Connection connection, Expens expens) {

    }

    private void fixExpens(Connection connection, Expens expens) {

    }
}
