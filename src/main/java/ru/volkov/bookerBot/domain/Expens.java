package ru.volkov.bookerBot.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Expens {
    private Long user_id;
    private Long sum = new Long(0);
    private LocalDateTime date = LocalDateTime.now();
    private ExpensType expensType = ExpensType.OTHER;
    private String description = "Default description";
    private Boolean cash = new Boolean(false);

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        if (sum < 0) sum = new Long(0);
        this.sum = sum;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public ExpensType getExpensType() {
        return expensType;
    }

    public void setExpensType(ExpensType expensType) {
        this.expensType = expensType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCash() {
        return cash;
    }

    public void setCash(Boolean cash) {
        this.cash = cash;
    }

    @Override
    public String toString() {
        return "Expens{" +
                "user_id=" + user_id +
                ", sum=" + sum +
                ", date=" + date +
                ", expensType=" + expensType +
                ", description='" + description + '\'' +
                ", cash=" + cash +
                '}';
    }
}
