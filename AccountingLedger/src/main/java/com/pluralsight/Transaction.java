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

    // reads the current transactions and stores them in a buffer (String) then prompts the user for their transaction information
    //once input is received the new transaction is tagged automatically with date and time and all transactions are written to the filePath

    public static void newTransaction(boolean isDeposit) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Ledger.filePath))) {
            String existingLedger = Ledger.readExistingLedger();
            StringBuilder newCredit = new StringBuilder();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            newCredit.append(LocalDate.now()).append("|");
            newCredit.append(LocalTime.now().format(formatter)).append("|");
            newCredit.append(Utils.messageAndResponse("Transaction description: ").trim()).append("|");
            newCredit.append(Utils.messageAndResponse("Vendor: ").trim()).append("|");

            if(isDeposit) {
                newCredit.append(Utils.messageAndResponse("Amount: $").trim());
            }

            if(!isDeposit) {
                newCredit.append("-").append(Utils.messageAndResponse("Amount: $").trim());
            }
            writer.write(existingLedger);
            writer.write(newCredit.toString());

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
