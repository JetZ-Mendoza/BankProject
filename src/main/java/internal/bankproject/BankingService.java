package internal.bankproject;

public class BankingService {
    private static BankingService instance = null;
    private double balance;
    private TransactionRecord lastTransaction;

    private BankingService(double initialBalance) {
        this.balance = initialBalance;
    }

    public static synchronized BankingService getInstance() {
        if(instance == null) {
            instance = new BankingService(0);
        }
        return instance;
    }

    public TransactionRecord getLastTransaction() {
        return lastTransaction;
    }

    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
            lastTransaction = TransactionRecord.createCashInTransaction(amount);
        }
    }

    public boolean withdraw(double amount) {
        if(amount > 0 && balance >= amount) {
            balance -= amount;
            lastTransaction = TransactionRecord.createWithdrawTransaction(amount);
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public boolean transfer(BankingService targetAccount, double amount) {
        if(this.withdraw(amount)) {
            targetAccount.deposit(amount);
            this.lastTransaction = TransactionRecord.createTransferTransaction(amount, "Target Account");

            return true;
        }
        return false;
    }
}
