package org.example.model;

public class Deposit {
    private Long id;
    private String customer;
    private Integer initialBalance;
    private Integer upperBound;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Integer getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Integer initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Integer getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(Integer upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", initialBalance=" + initialBalance +
                ", upperBound=" + upperBound +
                '}';
    }
}
