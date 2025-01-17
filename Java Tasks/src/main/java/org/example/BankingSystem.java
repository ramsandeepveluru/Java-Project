package org.example;

import java.util.Scanner;

public class BankingSystem {
        public static void main(String[] args) {
            Bank bank = new Bank();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\nWelcome to the Banking System");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Check Balance");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                try {
                    switch (choice) {
                        case 1:
                            System.out.print("Enter account number: ");
                            String accNumber = scanner.nextLine();
                            System.out.print("Enter account holder name: ");
                            String accHolderName = scanner.nextLine();
                            System.out.print("Enter initial deposit: ");
                            double deposit = scanner.nextDouble();
                            bank.createAccount(accNumber, accHolderName, deposit);
                            System.out.println("Account created successfully.");
                            break;

                        case 2:
                            System.out.print("Enter account number: ");
                            accNumber = scanner.nextLine();
                            System.out.print("Enter deposit amount: ");
                            deposit = scanner.nextDouble();
                            bank.deposit(accNumber, deposit);
                            System.out.println("Deposit successful.");
                            break;

                        case 3:
                            System.out.print("Enter account number: ");
                            accNumber = scanner.nextLine();
                            System.out.print("Enter withdrawal amount: ");
                            double withdrawal = scanner.nextDouble();
                            bank.withdraw(accNumber, withdrawal);
                            System.out.println("Withdrawal successful.");
                            break;

                        case 4:
                            System.out.print("Enter source account number: ");
                            String fromAcc = scanner.nextLine();
                            System.out.print("Enter destination account number: ");
                            String toAcc = scanner.nextLine();
                            System.out.print("Enter transfer amount: ");
                            double transferAmount = scanner.nextDouble();
                            bank.transfer(fromAcc, toAcc, transferAmount);
                            System.out.println("Transfer successful.");
                            break;

                        case 5:
                            System.out.print("Enter account number: ");
                            accNumber = scanner.nextLine();
                            Account account = bank.getAccount(accNumber);
                            if (account != null) {
                                System.out.println("Account Holder: " + account.getAccountHolderName());
                                System.out.println("Balance: " + account.getBalance());
                            } else {
                                System.out.println("Account not found.");
                            }
                            break;

                        case 6:
                            System.out.println("Thank you for using the banking system!");
                            return;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }


}
