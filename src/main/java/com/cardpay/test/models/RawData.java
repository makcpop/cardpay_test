package com.cardpay.test.models;

import java.util.Objects;

/**
 * Data from file with file name and line
 */
public class RawData {
    private final String id;
    private final String amount;
    private final String currency;
    private final String comment;
    private final String fileName;
    private final long line;

    private RawData(
            String id,
            String amount,
            String currency,
            String comment,
            String fileName,
            long line) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.fileName = fileName;
        this.line = line;
    }

    public String getId() {
        return id;
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

    public String getFileName() {
        return fileName;
    }

    public long getLine() {
        return line;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RawData rawData = (RawData) o;
        return line == rawData.line &&
                Objects.equals(id, rawData.id) &&
                Objects.equals(amount, rawData.amount) &&
                Objects.equals(currency, rawData.currency) &&
                Objects.equals(comment, rawData.comment) &&
                Objects.equals(fileName, rawData.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, comment, fileName, line);
    }

    @Override
    public String toString() {
        return "RawData{" +
                "id='" + id + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", comment='" + comment + '\'' +
                ", fileName='" + fileName + '\'' +
                ", line='" + line + '\'' +
                '}';
    }

    public static class Builder {
        private String id;
        private String amount;
        private String currency;
        private String comment;
        private String fileName;
        private long line;

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder amount(String amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder line(int line) {
            this.line = line;
            return this;
        }

        public RawData build() {
            return new RawData(id, amount, currency, comment, fileName, line);
        }
    }
}
