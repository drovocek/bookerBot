package ru.volkov.bookerBot.dao;

import ru.volkov.bookerBot.domain.Expens;
import ru.volkov.bookerBot.domain.ExpensType;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpensDao {
    private static final String INSERT_EXPENS = "" +
            "INSERT INTO user_expens (" +
            " ex_us_id, ex_sum, ex_date, " +
            "ex_type, ex_description, ex_cash)" +
            "VALUES ( ?, ?, ?, ?, ?, ?)";

    private final String EXPENS_BY_DATE = "" +
            "SELECT * FROM user_expens " +
            "WHERE ex_us_id = ? " +
            "AND ex_date <= ? " +
            "AND ex_date >= ? ";

    private final String DELETE_EXPENS_BY_DATE = "" +
            "DELETE FROM user_expens " +
            "WHERE ex_us_id = ? " +
            "AND ex_date = ? ";

    private Connection getConnection() throws SQLException {
        return ConnectionBuilder.getConnection();
    }

    public boolean addExpens(Expens expens) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_EXPENS)) {
            try {
                connection.setAutoCommit(false);

                statement.setLong(1, expens.getUser_id());
                statement.setDouble(2, expens.getSum());
                statement.setTimestamp(3, java.sql.Timestamp.valueOf(expens.getDate()));
                statement.setString(4, expens.getExpensType().getExpensType());
                statement.setString(5, expens.getDescription());
                statement.setBoolean(6, expens.getCash());

                statement.addBatch();
                statement.executeBatch();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }

        return true;
    }

    private void deleteExpens(LocalDateTime dateTime, Long userId) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EXPENS_BY_DATE)) {
            statement.setLong(1, userId);
            statement.setTimestamp(2, Timestamp.valueOf(dateTime));
            ResultSet rs = statement.executeQuery();
        }
    }

    private void updateExpens(LocalDateTime dateTime, Long userId) {

    }

    public Map<ExpensType, List<Expens>> filterByTypeToMap(List<ExpensType> expensType, Long userId) throws SQLException {
        StringBuilder EXPENS_BY_TYPE = new StringBuilder(
                "SELECT * FROM user_expens " +
                        "WHERE ex_us_id = ? AND( ");
        String query = expensType.stream().map(expens -> "ex_type = " + expens.getExpensType()).collect(Collectors.joining(" OR "));
        EXPENS_BY_TYPE.append(query);
        EXPENS_BY_TYPE.append(")");

        List<Expens> filteredByTypeList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(EXPENS_BY_TYPE.toString())) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Expens expens = new Expens();
                expens.setUser_id(userId);
                expens.setSum(rs.getLong("ex_sum"));
                expens.setDate(rs.getTimestamp("ex_date").toLocalDateTime());
                expens.setExpensType(ExpensType.expensTypeByString(rs.getString("ex_type")));
                expens.setDescription(rs.getString("ex_description"));
                expens.setCash(rs.getBoolean("ex_cash"));
                filteredByTypeList.add(expens);
            }
        }
        Map<ExpensType, List<Expens>> filteredByTypeMap = filteredByTypeList.stream()
                .collect(Collectors.groupingBy(expens -> expens.getExpensType(), HashMap::new, Collectors.toList()));
        return filteredByTypeMap;
    }

    public List<Expens> filterByDate(LocalDate startDate, LocalDate endDate, Long userId) throws SQLException {
        List<Expens> filteredByDate = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(EXPENS_BY_DATE)) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Expens expens = new Expens();
                expens.setUser_id(userId);
                expens.setSum(rs.getLong("ex_sum"));
                expens.setDate(rs.getTimestamp("ex_date").toLocalDateTime());
                expens.setExpensType(ExpensType.expensTypeByString(rs.getString("ex_type")));
                expens.setDescription(rs.getString("ex_description"));
                expens.setCash(rs.getBoolean("ex_cash"));
                filteredByDate.add(expens);
            }
        }
        return filteredByDate;
    }

    public Map<ExpensType, List<Expens>> filterByDateAndType(List<ExpensType> expensType, LocalDate startDate, LocalDate endDate, Long userId) throws SQLException {
        StringBuilder EXPENS_BY_DATE_AND_TYPE = new StringBuilder(EXPENS_BY_DATE);
        String query = expensType.stream().map(expens -> "ex_type = " + expens.getExpensType()).collect(Collectors.joining(" OR "));
        EXPENS_BY_DATE_AND_TYPE.append(query);
        EXPENS_BY_DATE_AND_TYPE.append(")");

        List<Expens> filteredByTypeList = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(EXPENS_BY_DATE_AND_TYPE.toString())) {
            statement.setLong(1, userId);
            statement.setDate(2, java.sql.Date.valueOf(startDate));
            statement.setDate(3, java.sql.Date.valueOf(endDate));
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Expens expens = new Expens();
                expens.setUser_id(userId);
                expens.setSum(rs.getLong("ex_sum"));
                expens.setDate(rs.getTimestamp("ex_date").toLocalDateTime());
                expens.setExpensType(ExpensType.expensTypeByString(rs.getString("ex_type")));
                expens.setDescription(rs.getString("ex_description"));
                expens.setCash(rs.getBoolean("ex_cash"));
                filteredByTypeList.add(expens);
            }
        }
        Map<ExpensType, List<Expens>> filteredByDateAndType = filteredByTypeList.stream()
                .collect(Collectors.groupingBy(expens -> expens.getExpensType(), HashMap::new, Collectors.toList()));

        return filteredByDateAndType;
    }
}
