package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;

public class Reports {
    private static LocalDate date = LocalDate.now();
    private static int currentYear = date.getYear();
    private static int currentMonth = date.getMonthValue();

    public static void reportsMenu() {

        boolean keepReportsRunning = true;
        while(keepReportsRunning) {
            int reportsScreenSelection = getReportsScreenSelection();
            switch (reportsScreenSelection) {
                case 1 -> viewMonthReport(true); //month to date
                case 2 -> viewMonthReport(false); //previous month
                case 3 -> viewYearReport(true); //year to date
                case 4 -> viewYearReport(false); //previous year
                case 5 -> searchByVendor();
                case 0 -> keepReportsRunning = false;
                default -> System.out.println("Please select a valid option.");
            }
        }
    }

    private static void viewMonthReport(boolean thisMonth) {
        StringBuilder monthTransactions = new StringBuilder();
        ArrayList<Transaction> transaction = Ledger.loadLedger();
        for(int i = Ledger.loadLedger().size() - 1; i >= 0; i--) {
            String[] splitDate = transaction.get(i).getDate().split("-");
            int month = Integer.parseInt(splitDate[1]);
            if(thisMonth) {
                if (month == currentMonth) {
                    monthTransactions.append(transaction.get(i)).append("\n");
                }
            }
            if(!thisMonth) {
                if(month == currentMonth - 1 || currentMonth == 1 && month == 12) {
                    monthTransactions.append(transaction.get(i)).append("\n");
                }
            }
        }
        if (monthTransactions.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println(monthTransactions);
        }
    }

    private static void viewYearReport(boolean thisYear) {
        StringBuilder yearTransactions = new StringBuilder();
        ArrayList<Transaction> transaction = Ledger.loadLedger();
        for(int i = Ledger.loadLedger().size() - 1; i >= 0; i--) {
            String[] splitDate = transaction.get(i).getDate().split("-");
            int year = Integer.parseInt(splitDate[0]);
            if(thisYear) {
                if (year == currentYear) {
                    yearTransactions.append(transaction.get(i)).append("\n");
                }
            }
            if(!thisYear) {
                if(year == currentYear - 1) {
                    yearTransactions.append(transaction.get(i)).append("\n");
                }
            }
        }
        if (yearTransactions.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println(yearTransactions);
        }
    }

    private static void searchByVendor() {
        StringBuilder searchTransactions = new StringBuilder();
        ArrayList<Transaction> transaction = Ledger.loadLedger();
        String searchTerm = Utils.messageAndResponse("Search: ").trim();
        String ledgerVendor;
        for(int i = Ledger.loadLedger().size() - 1; i >= 0; i--) {
            ledgerVendor = transaction.get(i).getVendor();
            if(searchTerm.equalsIgnoreCase(ledgerVendor)) {
                searchTransactions.append(transaction.get(i)).append("\n");
            }
        }
        if (searchTransactions.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println(searchTransactions);
        }
    }

    private static int getReportsScreenSelection() {
        System.out.println("--Reports--\n1) Month to Date\n2) Previous Month\n3) Year to Date\n4) Previous Year\n5) Search by Vendor\n0) Back");
        return Integer.parseInt(Utils.messageAndResponse("Select: "));
    }
}
