package databaseworkshop;

import java.util.Random;
import java.util.Scanner;
/*The database used:
Schema: banking
table account
col1: accNumber (int)
col2: accName (varchar45)
col3: accBalance (Double)
*/
public class DatabaseWorkshop {

    public static void main(String[] args) {
        int selection = 0, accountNumber;
        String accountName;
        Account account = null;
        Bank bank = new Bank("abc");
        while (selection != 6) {
            System.out.println("Main Menu");
            System.out.println("1. Display All Accounts");
            System.out.println("2. Open New Account");
            System.out.println("3. Close Existing Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit\n");

            System.out.print("Enter your selection:");
            Scanner scan = new Scanner(System.in);
            selection = scan.nextInt();
            scan.nextLine();
            
            
            switch(selection){
                case 1: 
                    bank.listAccount();
                    break;
                case 2:
                    accountNumber = generateAccNumber();
                    System.out.print("Enter Account Name: ");
                    accountName = scan.nextLine();
                    bank.openAcc(accountNumber, accountName);
                    System.out.println("Your account id: " + accountNumber);
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    bank.closeAcc(accountNumber);
                    break;
                case 4:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter The Amount: ");
                    double amount = scan.nextDouble();
                    scan.nextLine();
                    bank.deposit(accountNumber, amount);
                    break;
                case 5:
                    System.out.print("Enter Account Number: ");
                    accountNumber = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter The Amount: ");
                    amount = scan.nextDouble();
                    scan.nextLine();
                    bank.withdraw(accountNumber, amount);
                    break;
                case 6:break;
                default: System.out.println("Invalid request");;
            }
        }
    }
    
    public static int generateAccNumber(){
        Random rand = new Random();
        int accNumber = 100000 + rand.nextInt(900000);
        return accNumber;
    }

}
