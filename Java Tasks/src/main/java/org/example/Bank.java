package org.example;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Bank {
        private Map<String, Account> accounts = new HashMap<>();
        private final String DATA_FILE = "bank_data.dat";

        public Bank() {
            loadAccounts();
        }

        public void createAccount(String accountNumber, String accountHolderName, double initialDeposit) {
            if (accounts.containsKey(accountNumber)) {
                throw new IllegalArgumentException("Account already exists.");
            }
            Account newAccount = new Account(accountNumber, accountHolderName, initialDeposit);
            accounts.put(accountNumber, newAccount);
            saveAccounts();
        }

        public Account getAccount(String accountNumber) {
            return accounts.get(accountNumber);
        }

        public void deposit(String accountNumber, double amount) {
            Account account = accounts.get(accountNumber);
            if (account == null) {
                throw new IllegalArgumentException("Account not found.");
            }
            account.deposit(amount);
            saveAccounts();
        }

        public void withdraw(String accountNumber, double amount) {
            Account account = accounts.get(accountNumber);
            if (account == null) {
                throw new IllegalArgumentException("Account not found.");
            }
            account.withdraw(amount);
            saveAccounts();
        }

        public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
            Account fromAccount = accounts.get(fromAccountNumber);
            Account toAccount = accounts.get(toAccountNumber);

            if (fromAccount == null || toAccount == null) {
                throw new IllegalArgumentException("One or both accounts not found.");
            }

            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            saveAccounts();
        }

        private void saveAccounts() {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
                oos.writeObject(accounts);
            } catch (IOException e) {
                System.out.println("Error saving account data: " + e.getMessage());
            }
        }

        @SuppressWarnings("unchecked")
        private void loadAccounts() {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                accounts = (Map<String, Account>) ois.readObject();
            } catch (FileNotFoundException e) {
                // No data file yet
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading account data: " + e.getMessage());
            }
        }


}
