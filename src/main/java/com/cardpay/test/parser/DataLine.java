package com.cardpay.test.parser;

import java.util.Objects;

/**
 * Data from one line from file
 */
public class DataLine {
    private String orderId;
    private String amount;
    private String currency;
    private String comment;

    public DataLine() {
    }

    public String getOrderId() {
        return orderId;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getComment() {
        return comment;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataLine dataLine = (DataLine) o;
        return Objects.equals(orderId, dataLine.orderId) &&
                Objects.equals(amount, dataLine.amount) &&
                Objects.equals(currency, dataLine.currency) &&
                Objects.equals(comment, dataLine.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, amount, currency, comment);
    }

    @Override
    public String toString() {
        return "DataLine{" +
                "id='" + orderId + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
