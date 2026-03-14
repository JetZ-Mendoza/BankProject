package internal.bankproject;

public class TransactionRecord {
    private static TransactionRecord instance = null;
    private String message;

    private TransactionRecord(String message) {
        this.message = message;
    }

    public static TransactionRecord getInstance(String message) {
        if (instance == null) {
            instance = new TransactionRecord(message);
        }
        return instance;
    }

    public String getMessage() {
        return message;
    }

    public static TransactionRecord createCashInTransaction(double cashInAmount) {
        String transactionMessage = "Cash In: " + String.valueOf(cashInAmount);
        return getInstance(transactionMessage);
    }

    public static TransactionRecord createWithdrawTransaction(double withdrawAmount) {
        String transactionMessage = "Withdrawn: " + String.valueOf(withdrawAmount);
        return getInstance(transactionMessage);
    }

    public static TransactionRecord createTransferTransaction(double transferAmount, String targetAccountName) {
        String transactionMessage = "Transferred " + String.valueOf(transferAmount) + " to " + targetAccountName;
        return getInstance(transactionMessage);
    }
}
