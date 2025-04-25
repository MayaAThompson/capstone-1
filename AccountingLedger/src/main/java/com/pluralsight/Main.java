package com.pluralsight;

public class Main {

    public static void main(String[] args) {

        while (true) {
            char homeScreenSelection = getHomeScreenSelection();
            if (homeScreenSelection == 'D') {
               Transaction.credit();
//               Utils.pauseReturn();
            }
            if (homeScreenSelection == 'P') {
                Transaction.debit();
//                Utils.pauseReturn();
            }
            if (homeScreenSelection =='L') {
                Ledger.ledgerMenu();
//                Utils.pauseReturn();
            }
            if (homeScreenSelection == 'X') {
                break;
            }
            else {
                System.out.println("Please enter a valid selection.");
//                Utils.pauseReturn();
            }
        }
    }

    private static char getHomeScreenSelection() {
        System.out.println("--HOME--\nD) Add Deposit\nP) Make Payment (Debit)\nL) Ledger\nX) Exit");
        return Utils.messageAndResponse("Select: ").toUpperCase().charAt(0);
    }
}
