package com.pluralsight;

import java.time.LocalDate;

public class Reports {
    private static final LocalDate date = LocalDate.now();
    private static final int currentYear = date.getYear();
    private static final int currentMonth = date.getMonthValue();

    public static void reportsMenu() {

        boolean keepReportsRunning = true;
        while(keepReportsRunning) {
            int reportsScreenSelection = getReportsScreenSelection();
            switch (reportsScreenSelection) {
                case 1 -> viewMonthReport(currentMonth, currentYear); //month to date
                case 2 -> viewMonthReport(previousMonth, previousMonthYear()); //previous month
                case 3 -> viewYearReport(currentYear); //year to date
                case 4 -> viewYearReport(currentYear - 1); //previous year
                case 5 -> searchByVendor(); //search through records by vendor name
                case 0 -> keepReportsRunning = false; //returns to ledger menu
                default -> System.out.println("Please select a valid option.");
            }
        }
    }

    private static int getReportsScreenSelection() {
        System.out.println("\n---Reports---\n1) Month to Date\n2) Previous Month\n3) Year to Date\n4) Previous Year\n5) Search by Vendor\n0) Back");
        try {
            return Integer.parseInt(Utils.messageAndResponse("Select: "));
        } catch (NumberFormatException e) {
            System.out.println("\nSomething went wrong! " + e.getMessage());
        }
        return 999;
    }

    // view reports for one month based on month and year values passed in

    private static void viewMonthReport(int month, int year) {
        StringBuilder monthTransactions = new StringBuilder();
        for(int i = Main.transactionCollection.size() - 1; i >= 0; i--) {
            String[] splitDate = Main.transactionCollection.get(i).getDate().split("-");
            int transactionMonth = Integer.parseInt(splitDate[1]);
            int transactionYear = Integer.parseInt(splitDate[0]);
            if (transactionMonth == month && transactionYear == year) {
                    monthTransactions.append(Main.transactionCollection.get(i)).append("\n");
            }
        }
        if (monthTransactions.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println("\n" + monthTransactions);
        }
        Utils.pauseReturn();
    }

    //view transactions for one year based on year value passed in

    private static void viewYearReport(int year) {
        StringBuilder yearTransactions = new StringBuilder();
        for(int i = Main.transactionCollection.size() - 1; i >= 0; i--) {
            String[] splitDate = Main.transactionCollection.get(i).getDate().split("-");
            int transactionYear = Integer.parseInt(splitDate[0]);
            if (transactionYear == year) {
                yearTransactions.append(Main.transactionCollection.get(i)).append("\n");
            }
        }
        if (yearTransactions.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println("\n" + yearTransactions);
        }
        Utils.pauseReturn();
    }

    // prompts user for a vendor search term and displays transactions from that vendor (if any)

    private static void searchByVendor() {
        StringBuilder searchTransactions = new StringBuilder();
        String searchTerm = Utils.messageAndResponse("Search: ").trim().toLowerCase();
        String ledgerVendor;
        for(int i = Main.transactionCollection.size() - 1; i >= 0; i--) {
            ledgerVendor = Main.transactionCollection.get(i).getVendor().toLowerCase();
            if(ledgerVendor.contains(searchTerm)) {
                searchTransactions.append(Main.transactionCollection.get(i)).append("\n");
            }
        }
        if (searchTransactions.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println("\n" + searchTransactions);
        }
        Utils.pauseReturn();
    }

    private static int previousMonth = (currentMonth - 2) % 12 + 1;

    private static int previousMonthYear() {
        int previousMonthYear = 0;
        if (previousMonth >= 1 && previousMonth < 12) {
            previousMonthYear = currentYear;
        }
        if (previousMonth == 12) {
            previousMonthYear = currentYear - 1;
        }
        return previousMonthYear;
    }
}