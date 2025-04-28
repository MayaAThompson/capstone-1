package com.pluralsight;

public class Main {

    public static void main(String[] args) {

        boolean keepMainMenuRunning = true;
        while (keepMainMenuRunning) {
            char homeScreenSelection = getHomeScreenSelection();
            switch (homeScreenSelection) {
                case 'D' -> Transaction.newCredit();
                case 'P' -> Transaction.newDebit();
                case 'L' -> Ledger.ledgerMenu();
                case 'X' -> keepMainMenuRunning = false;
                default -> System.out.println("Please select a valid option.");
            }
        }
    }

    private static char getHomeScreenSelection() {
        System.out.println("--HOME--\nD) Add Deposit\nP) Make Payment (Debit)\nL) Ledger\nX) Exit");
        return Utils.messageAndResponse("Select: ").toUpperCase().charAt(0);
    }
}
