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
                case 1 -> viewMonthReport(true); //month to date
                case 2 -> viewMonthReport(false); //previous month
                case 3 -> viewYearReport(true); //year to date
                case 4 -> viewYearReport(false); //previous year
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

    // view reports for one month
    //either the current month (true) or the previous month (false)

    private static void viewMonthReport(boolean thisMonth) {
        StringBuilder monthTransactions = new StringBuilder();
        for(int i = Main.transactionCollection.size() - 1; i >= 0; i--) {
            String[] splitDate = Main.transactionCollection.get(i).getDate().split("-");
            int month = Integer.parseInt(splitDate[1]);
            int year = Integer.parseInt(splitDate[0]);
            if(thisMonth) {
                if (month == currentMonth && year == currentYear) {
                    monthTransactions.append(Main.transactionCollection.get(i)).append("\n");
                }
            }
            if(!thisMonth) {
                if((month == currentMonth - 1 && year == currentYear) || (currentMonth == 1 && month == 12 && year == currentYear - 1)) {
                    monthTransactions.append(Main.transactionCollection.get(i)).append("\n");
                }
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

    //view transactions for one year
    //either the current year (true) or the previous year (false)

    private static void viewYearReport(boolean thisYear) {
        StringBuilder yearTransactions = new StringBuilder();
        for(int i = Main.transactionCollection.size() - 1; i >= 0; i--) {
            String[] splitDate = Main.transactionCollection.get(i).getDate().split("-");
            int year = Integer.parseInt(splitDate[0]);
            if(thisYear) {
                if (year == currentYear) {
                    yearTransactions.append(Main.transactionCollection.get(i)).append("\n");
                }
            }
            if(!thisYear) {
                if(year == currentYear - 1) {
                    yearTransactions.append(Main.transactionCollection.get(i)).append("\n");
                }
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
}