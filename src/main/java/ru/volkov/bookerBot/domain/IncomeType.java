package ru.volkov.bookerBot.domain;

public enum IncomeType implements Type {
    SALARY("Зарплата"), NOSALARY("Подработка"), OTHER("Прочее");

    String incomeType;

    IncomeType(String incomeType) {
        this.incomeType = incomeType;
    }
}
