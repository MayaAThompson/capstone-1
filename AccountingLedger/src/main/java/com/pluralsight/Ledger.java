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
                case 'D' -> viewPaymentsDeposits(false); //view deposits
                case 'P' -> viewPaymentsDeposits(true); //view payments
                case 'R' -> Reports.reportsMenu(); //go to reports menu
                case 'H' -> keepLedgerRunning = false; //return to home screen menu
                default -> System.out.println("Please select a valid option.");
            }
        }
    }

    private static char getLedgerScreenSelection() {
        System.out.println("--Ledger--\nA) All\nD) Deposits\nP) Payments\nR) Reports\nH) Home");
        return Utils.messageAndResponse("Select: ").toUpperCase().charAt(0);
    }

    // false will print to console all deposits true prints all payments to console

    private static void viewPaymentsDeposits(boolean isPayment) {
        StringBuilder transactions = new StringBuilder();
        ArrayList<Transaction> transaction = loadLedger();
        for(int i = loadLedger().size() - 1; i >= 0; i--) {
            double paymentDeposit = transaction.get(i).getAmount();
            if(isPayment) {
                if (paymentDeposit < 0) {
                    transactions.append(transaction.get(i)).append("\n");
                }
            }
            if(!isPayment) {
                if(paymentDeposit > 0 ) {
                    transactions.append(transaction.get(i)).append("\n");
                }
            }
        }
        if (transactions.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println(transactions);
        }
    }

    //loads the current contents of filePath to an ArrayList<>

    public static ArrayList<Transaction> loadLedger() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String input;
            while ((input = reader.readLine()) != null) {
                String[] parts = input.split("\\|");
                if (parts[0].equals("date")){
                    continue;
                }
                transactions.add(new Transaction(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4])));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to read the file. " + e.getMessage());
        }
        return transactions;
    }

    //prints all transactions in the ledger to the console

    public static void viewAllTransactions() {
        for (int i = loadLedger().size() - 1; i >= 0; i--) {
            String transaction = loadLedger().get(i).toString();
            System.out.println(transaction);
        }
    }

    //reads the current ledger and stores it in a string

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
