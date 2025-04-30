package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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

    // reads the current transactions and stores them in a buffer (String) then prompts the user for their transaction information
    //once input is received the new transaction is tagged automatically with date and time and all transactions are written to the filePath

    public static void newTransaction(boolean isDeposit) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            String date = LocalDate.now().toString();
            String time = LocalTime.now().format(formatter);
            String description = Utils.messageAndResponse("Transaction description: ").trim();
            String vendor = Utils.messageAndResponse("Vendor: ").trim();
            double amount = Double.parseDouble(Utils.messageAndResponse("Amount: $"));
            if(!isDeposit) {
                amount *= (-1);
            }
            Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
            Main.transactionCollection.add(newTransaction);
    }

    //region getters
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
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
