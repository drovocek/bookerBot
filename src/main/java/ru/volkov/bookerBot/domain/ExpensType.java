package ru.volkov.bookerBot.domain;

public enum ExpensType implements Type {
    FOOD("Еда"), SPORT("Спорт"), HEALTH("Здоровье"), OTHER("Прочее");

    private String expensTypeString;

    ExpensType(String expensType){
        this.expensTypeString = expensType;
    }

    public String getExpensType(){
        return expensTypeString;
    }

    public static ExpensType expensTypeByString(String expensTypeString){
        ExpensType expensType = null;
        switch(expensTypeString){
            case("Еда"):
                expensType = ExpensType.FOOD;
                break;
            case("Спорт"):
                expensType = ExpensType.SPORT;
                break;
            case("Здоровье"):
                expensType = ExpensType.FOOD;
                break;
            default:
                expensType = ExpensType.OTHER;
        }
        return expensType;
    }
}
