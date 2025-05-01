package com.pluralsight;

public class Transaction {

    private final String date;
    private final String time;
    private final String description;
    private final String vendor;
    private final double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    //region getters
    public String getDate() {
        return date;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }
    //endregion

    public String toString() {
        return String.format("%s|%s|%s|%s|%.2f", this.date, this.time, this.description, this.vendor, this.amount);

    }
}
