package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ledger {

    static String filePath = "src/main/resources/transactions.csv";
    private static String fileHeader;

    //Ledger menu option operation and loop

    public static void ledgerMenu() {

        boolean keepLedgerRunning = true;
        while(keepLedgerRunning) {
            char ledgerScreenSelection = getLedgerScreenSelection();
            switch (ledgerScreenSelection) {
                case 'A' -> viewAllTransactions();
                case 'D' -> viewPaymentsDeposits(false); //view deposits
                case 'P' -> viewPaymentsDeposits(true); //view payments
                case 'R' -> Reports.reportsMenu(); //go to reports menu
                case 'H' -> keepLedgerRunning = false; //return to home screen menu
                default -> System.out.println("Please select a valid option.");
            }
        }
    }

    //loads the current contents of filePath to an ArrayList<> transactionCollection

    public static ArrayList<Transaction> loadLedger() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String input;
            fileHeader = reader.readLine();
            while ((input = reader.readLine()) != null) {
                String[] parts = input.split("\\|");
                transactions.add(new Transaction(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to read the file. " + e.getMessage());
        }
        return transactions;
    }

    //writes the contents of transactionCollection to filePath
    public static void writeLedger(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Ledger.filePath))) {
            writer.write(fileHeader + "\n");
            for (Transaction transaction : Main.transactionCollection) {
                writer.write(transaction.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong. " + e.getMessage());
        }
    }

    //add a new transaction to transactionCollection

    public static void addTransaction(boolean isDeposit) {

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

    //prints all transactions in the ledger to the console

    private static void viewAllTransactions() {
        System.out.println(); //only purpose is whitespace in CLI
        for (int i = Main.transactionCollection.size() - 1; i >= 0; i--) {
            String transactions = Main.transactionCollection.get(i).toString();
            System.out.println(transactions + "\n");
        }
        Utils.pauseReturn();
    }

    //Ledger menu printout and option selection

    private static char getLedgerScreenSelection() {
        System.out.println("\n---Ledger---\nA) All\nD) Deposits\nP) Payments\nR) Reports\nH) Home");
        try {
            return Utils.messageAndResponse("Select: ").toUpperCase().charAt(0);
        } catch (Exception e) {
            System.out.println("\nSomething went wrong! " + e.getMessage());
        }
        return ' ';
    }

    //View transactions: all deposits (false) or all payments (true)

    private static void viewPaymentsDeposits(boolean isPayment) {
        StringBuilder transactions = new StringBuilder();
        for(int i = Main.transactionCollection.size() - 1; i >= 0; i--) {
            double paymentDeposit = Main.transactionCollection.get(i).getAmount();
            if(isPayment) {
                if (paymentDeposit < 0) {
                    transactions.append(Main.transactionCollection.get(i)).append("\n");
                }
            }
            if(!isPayment) {
                if(paymentDeposit > 0 ) {
                    transactions.append(Main.transactionCollection.get(i)).append("\n");
                }
            }
        }
        if (transactions.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println("\n" + transactions);
        }
        Utils.pauseReturn();
    }
}
