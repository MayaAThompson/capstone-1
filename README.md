# capstone-1
## Accounting Ledger

This CLI application is a basic editor for a .csv file that stores a transaction ledger.  
For demonstration there is a file called transactions.csv that is formatted like this:  

date|time|description|vendor|amount  

formats for each section are as follows:  

date: yyyy:MM:dd  
time: HH:mm:ss  
description: description of the transaction  
vendor: who the transaction was made with  
amount: amount for the transaction positive for deposits and negative(-) for payments  

### running the program

after the program starts you will see this screen:  
![Home Screen](./AccountingLedger/images/home_screen.png)  

which prompts the user to type in one of the available options:  
if the user chooses add deposit or make payment they will be asked to give the details of their transaction.  
if the user chooses ledger it will go to the ledger menu:  
![Ledger Menu](./AccountingLedger/images/ledger_screen.png)   




the user once again has some options available.  
All will print to console all transactions that are currently in the ledger
Deposits will print to console all transactions that are deposits (positive)  
Payments will print to console all transactions that are payments (negative)  
Reports will take you to the reports menu
Home returns you to the home screen

The reports menu:  
![Reports Menu](./AccountingLedger/images/reports_screen.png)  

The reports menu has a few different report types the user can choose to view.  
Month to date, previous month, year to date, previous year.  
The search option allows the user to type in a search term and will display transactions that the vendor field contains the search term (if any)

Here is an example of what one of the report printouts looks like:  
![example report printout](./AccountingLedger/images/report_printout.png)  

Here is one piece of code in this project I'm proud of:

```java  
public static void pauseReturn() {
    System.out.print("Press Enter to Continue");
    if (scanner.hasNextLine()) {
        scanner.nextLine();
    }
    scanner.nextLine();
}
```

This method is in Utils.java class  
It is used in multiple places throughout the project.  

when this method is called it will clear the buffer of any potential leftover line feeds then waits for the user to press enter to continue the program.
