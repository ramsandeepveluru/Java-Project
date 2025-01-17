package org.example;

public class Account {

        private String accountNumber;
        private String accountHolderName;
        private double balance;

        public Account(String accountNumber, String accountHolderName, double initialDeposit) {
            this.accountNumber = accountNumber;
            this.accountHolderName = accountHolderName;
            this.balance = initialDeposit;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getAccountHolderName() {
            return accountHolderName;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
            } else {
                throw new IllegalArgumentException("Deposit amount must be positive.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
            } else {
                throw new IllegalArgumentException("Invalid withdrawal amount.");
            }
        }


}
