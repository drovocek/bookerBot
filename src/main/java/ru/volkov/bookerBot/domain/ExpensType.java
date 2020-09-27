package ru.volkov.bookerBot.domain;

public enum ExpensType {
    FOOD("Еда"), SPORT("Спорт"), HEALTH("Здоровье"), OTHER("Прочее");

    private String expensType;

    ExpensType(String expensType){
        this.expensType = expensType;
    }

    public String getExpensType(){
        return expensType;
    }
}
