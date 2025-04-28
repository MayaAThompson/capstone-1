package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ledger {

    static String filePath = "src/main/resources/transactions.csv";

    public static void ledgerMenu() {

        boolean keepLedgerRunning = true;
        while(keepLedgerRunning) {
            char ledgerScreenSelection = getLedgerScreenSelection();
            switch (ledgerScreenSelection) {
                case 'A' -> viewAllTransactions();
                case 'D' -> viewCredits();
                case 'P' -> viewDebits();
                case 'R' -> Reports.reportsMenu();
                case 'H' -> keepLedgerRunning = false;
                default -> System.out.println("Please select a valid option.");
            }
        }
    }

    private static void viewDebits() {
        StringBuilder debits = new StringBuilder();
        for(Transaction transaction : Ledger.loadLedger()) {
            double debit = transaction.getAmount();
            if(debit < 0 ) {
                debits.append(transaction).append("\n");
            }
        }
        if (debits.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println(debits);
        }
    }

    private static void viewCredits() {
        StringBuilder credits = new StringBuilder();
        for(Transaction transaction : Ledger.loadLedger()) {
            double credit = transaction.getAmount();
            if(credit > 0 ) {
                credits.append(transaction).append("\n");
            }
        }
        if (credits.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println(credits);
        }
    }

    private static char getLedgerScreenSelection() {
        System.out.println("--Ledger--\nA) All\nD) Deposits\nP) Payments\nR) Reports\nH) Home");
        return Utils.messageAndResponse("Select: ").toUpperCase().charAt(0);
    }

    public static ArrayList<Transaction> loadLedger() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String input;
            while ((input = reader.readLine()) != null) {
                String[] parts = input.split("\\|");
                if (parts[0].equals("date")){
                    continue;
                }

                transactions.add(new Transaction(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));

            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to read the file. " + e.getMessage());
        }
        return transactions;
    }

    public static void viewAllTransactions() {
        for (int i = loadLedger().size() - 1; i >= 0; i--) {
            String transaction = loadLedger().get(i).toString();
            System.out.println(transaction);
        }
    }

    public static String readExistingLedger() {
        StringBuilder existingLedger = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String input;
            while ((input = reader.readLine()) != null) {
                existingLedger.append(input).append("\n");
            }

        } catch (FileNotFoundException e) {
        System.out.println("File not found. " + e.getMessage());
        } catch (IOException e) {
        System.out.println("Failed to read the file. " + e.getMessage());
        }
        return existingLedger.toString();
    }
}
