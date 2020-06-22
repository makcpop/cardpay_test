package com.cardpay.test.models;

import java.util.Currency;
import java.util.List;
import java.util.Objects;

/**
 * Validated data
 */
public class ValidatedData {

    private final Long id;
    private final Long amount;
    private final Currency currency;
    private final String comment;
    private final String fileName;
    private final long line;
    private final List<ParsingResult> result;

    public ValidatedData(
            Long id,
            Long amount,
            Currency currency,
            String comment,
            String fileName,
            long line,
            List<ParsingResult> result) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.comment = comment;
        this.fileName = fileName;
        this.line = line;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public Long getAmount() {
        return amount;
    }

    public Currency getCurrency() {
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

    public List<ParsingResult> getResult() {
        return result;
    }

    public static Builder builder() {
        return new Builder();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidatedData that = (ValidatedData) o;
        return line == that.line &&
                Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, currency, comment, fileName, line, result);
    }

    @Override
    public String toString() {
        return "ValidatedData{" +
                "id='" + id + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                ", comment='" + comment + '\'' +
                ", fileName='" + fileName + '\'' +
                ", line='" + line + '\'' +
                ", result=" + result +
                '}';
    }

    public static class Builder {

        private Long id;
        private Long amount;
        private Currency currency;
        private String comment;
        private String fileName;
        private long line;
        private List<ParsingResult> result;

        private Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder amount(Long amount) {
            this.amount = amount;
            return this;
        }

        public Builder currency(Currency currency) {
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

        public Builder line(long line) {
            this.line = line;
            return this;
        }

        public Builder result(List<ParsingResult> result) {
            this.result = result;
            return this;
        }

        public ValidatedData build() {
            return new ValidatedData(id, amount, currency, comment, fileName, line, result);
        }
    }
}
