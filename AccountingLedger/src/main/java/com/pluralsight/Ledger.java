package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ledger {

    static String filePath = "src/main/resources/transactions.csv";

    public static void ledgerMenu() {

        char ledgerScreenSelection = getLedgerScreenSelection();
        while(true) {
            if (ledgerScreenSelection == 'A') {
                viewAllTransactions();
                Utils.pauseReturn();
            }
            if (ledgerScreenSelection == 'D') {
                viewCredits();
                Utils.pauseReturn();
            }
            if (ledgerScreenSelection == 'P') {
                viewDebits();
                Utils.pauseReturn();
            }
            if (ledgerScreenSelection == 'R') {
                reportsMenu();
                Utils.pauseReturn();
            }
            if (ledgerScreenSelection == 'H') {
                break;
            }
            else {
                System.out.println("Please enter a valid selection.");
            }
        }
    }

    private static void reportsMenu() {
    }

    private static void viewDebits() {
    }

    private static void viewCredits() {
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

                transactions.add(new Transaction(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4])));

            }

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
    
    
}
