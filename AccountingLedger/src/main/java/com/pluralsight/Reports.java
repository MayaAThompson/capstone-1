package com.pluralsight;

import java.time.LocalDate;

public class Reports {
    private static LocalDate date = LocalDate.now();
    private static int currentYear = date.getYear();
    private static int currentMonth = date.getMonthValue();

    public static void reportsMenu() {

        boolean keepReportsRunning = true;
        while(keepReportsRunning) {
            int reportsScreenSelection = getReportsScreenSelection();
            switch (reportsScreenSelection) {
                case 1 -> viewMonthToDate();
                case 2 -> viewPreviousMonth();
                case 3 -> viewYearToDate();
                case 4 -> viewPreviousYear();
                case 5 -> searchByVendor();
                case 0 -> keepReportsRunning = false;
                default -> System.out.println("Please select a valid option.");
            }
        }
    }

    private static void viewMonthToDate() {
        StringBuilder monthToDate = new StringBuilder();
        for(Transaction transaction : Ledger.loadLedger()) {
            String[] splitDate = transaction.getDate().split("-");
            int month = Integer.parseInt(splitDate[1]);
            if(month == currentMonth) {
                monthToDate.append(transaction).append("\n");
            }
        }
        if (monthToDate.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println(monthToDate);
        }
    }

    private static void viewPreviousMonth() {
        StringBuilder previousMonth = new StringBuilder();
        for(Transaction transaction : Ledger.loadLedger()) {
            String[] splitDate = transaction.getDate().split("-");
            int month = Integer.parseInt(splitDate[1]);
            if(month == currentMonth - 1 || currentMonth == 1 && month == 12) {
                previousMonth.append(transaction).append("\n");
            }
        }
        if (previousMonth.toString().isEmpty()) {
            System.out.println("No records available.");
        }
        else {
            System.out.println(previousMonth);
        }
    }

    private static void viewYearToDate() {
    }

    private static void viewPreviousYear() {
    }

    private static void searchByVendor() {
    }

    private static int getReportsScreenSelection() {
        System.out.println("--Reports--\n1) Month to Date\n2) Previous Month\n3) Year to Date\n4) Previous Year\n5) Search by Vendor\n0) Back");
        return Integer.parseInt(Utils.messageAndResponse("Select: "));
    }
}
