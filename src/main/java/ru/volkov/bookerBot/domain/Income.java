package ru.volkov.bookerBot.domain;

import java.time.LocalDate;

public class Income {
    private Long user_id;
    private Long sum = new Long(0);
    private LocalDate date = LocalDate.now();
    private IncomeType incomeType = IncomeType.OTHER;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(IncomeType incomeType) {
        this.incomeType = incomeType;
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
        return "Income{" +
                "user_id=" + user_id +
                ", sum=" + sum +
                ", date=" + date +
                ", incomeType=" + incomeType +
                ", description='" + description + '\'' +
                ", cash=" + cash +
                '}';
    }
}
