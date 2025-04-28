package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public static void newCredit() {
        try {
            String existingLedger = Ledger.readExistingLedger();
            BufferedWriter writer = new BufferedWriter(new FileWriter(Ledger.filePath));
            StringBuilder newCredit = new StringBuilder();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            newCredit.append(LocalDate.now()).append("|");
            newCredit.append(LocalTime.now().format(formatter)).append("|");
            newCredit.append(Utils.messageAndResponse("Deposit description: ")).append("|");
            newCredit.append(Utils.messageAndResponse("Vendor: ")).append("|");
            newCredit.append(Utils.messageAndResponse("Amount: $"));

            writer.write(existingLedger);
            writer.write(newCredit.toString());
            writer.close();

        } catch (IOException e) {
            System.out.println("Something went wrong." + e.getMessage());
        }
    }

    public static void newDebit() {
        try {
            String existingLedger = Ledger.readExistingLedger();
            BufferedWriter writer = new BufferedWriter(new FileWriter(Ledger.filePath));
            StringBuilder newCredit = new StringBuilder();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            newCredit.append(LocalDate.now()).append("|");
            newCredit.append(LocalTime.now().format(formatter)).append("|");
            newCredit.append(Utils.messageAndResponse("Payment description: ")).append("|");
            newCredit.append(Utils.messageAndResponse("Vendor: ")).append("|");
            newCredit.append("-").append(Utils.messageAndResponse("Amount: $"));

            writer.write(existingLedger);
            writer.write(newCredit.toString());
            writer.close();

        } catch (IOException e) {
            System.out.println("Something went wrong." + e.getMessage());
        }
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
