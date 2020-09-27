package ru.volkov.bookerBot.domain;

public enum IncomeType {
    SALARY("Зарплата"), NOSALARY("Подработка"), OTHER("Прочее");

    String incomeType;

    IncomeType(String incomeType) {
        this.incomeType = incomeType;
    }
}
