package ru.volkov.bookerBot.dao;

import ru.volkov.bookerBot.domain.Expens;
import ru.volkov.bookerBot.domain.ExpensType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilterExpensRequestDao {
    private final String EXPENS_BY_TYPE = "";
    private final String EXPENS_BY_DATE = "";
    private final String EXPENS_BY_DATE_AND_TYPE = "";

    public List<Expens> filterByType(ExpensType expensType){
        List<Expens> filteredByType = new ArrayList<>();

        return filteredByType;
    }

    public List<Expens> filterByDate(LocalDate startDate, LocalDate endDate){
        List<Expens> filteredByType = new ArrayList<>();

        return filteredByType;
    }

    public List<Expens> filterByDate(ExpensType expensType, LocalDate startDate, LocalDate endDate){
        List<Expens> filteredByType = new ArrayList<>();

        return filteredByType;
    }
}
