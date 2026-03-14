package internal.bankproject;

import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
    private static TransactionManager instance = new TransactionManager();
    private List<String> transactionHistory = new ArrayList<>();

    private TransactionManager() {}

    public static TransactionManager getInstance() {
        return instance;
    }

    public void addTransaction(String transaction) {
        transactionHistory.add(transaction);
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}
