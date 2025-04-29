package com.pluralsight;

public class Main {

    public static void main(String[] args) {

        // Main menu
        // a program that can performs operations for an accounting ledger
        // display transactions
        // add new transactions
        // display reports for different ranges of transactions

        boolean keepMainMenuRunning = true;
        while (keepMainMenuRunning) {
            char homeScreenSelection = getHomeScreenSelection();
            switch (homeScreenSelection) {
                case 'D' -> Transaction.newTransaction(true); // adds a deposit to the account
                case 'P' -> Transaction.newTransaction(false); // adds a payment to the account
                case 'L' -> Ledger.ledgerMenu(); // redirects to the ledger menu
                case 'X' -> keepMainMenuRunning = false; //exits the program
                default -> System.out.println("Please select a valid option.");
            }
        }
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
