package com.pluralsight;

import java.util.ArrayList;

public class Main {

    public static ArrayList<Transaction> transactionCollection = Ledger.loadLedger();

    public static void main(String[] args) {

        // Main menu

        boolean keepMainMenuRunning = true;
        while (keepMainMenuRunning) {
            char homeScreenSelection = getHomeScreenSelection();
            switch (homeScreenSelection) {
                case 'D' -> Ledger.addTransaction(true); // adds a deposit to the account
                case 'P' -> Ledger.addTransaction(false); // adds a payment to the account
                case 'L' -> Ledger.ledgerMenu(); // redirects to the ledger menu
                case 'X' -> keepMainMenuRunning = false; //exits the program
                default -> System.out.println("Please select a valid option.");
            }
        }
        Ledger.writeLedger();
    }

    private static char getHomeScreenSelection() {
        System.out.println("\n---HOME---\nD) Add Deposit\nP) Make Payment (Debit)\nL) Ledger\nX) Exit");
        try {
            return Utils.messageAndResponse("Select: ").toUpperCase().charAt(0);
        } catch (Exception e) {
            System.out.println("\nSomething went wrong! " + e.getMessage());
        }
        return ' ';
    }
}
