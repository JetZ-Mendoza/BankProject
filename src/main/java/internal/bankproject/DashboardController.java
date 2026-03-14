package internal.bankproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.List;

public class DashboardController {

    @FXML
    private Label getName;

    @FXML
    private Label balance;

    @FXML
    private Label transactionHistoryLabel;

    private BankingService bankingService;

    @FXML
    public void initialize() {
        Controller controller = new Controller();
        String name = controller.getName();
        this.bankingService = BankingService.getInstance();
        updateBalanceDisplay();
        updateTransactionDisplay();
        getName.setText(name);
    }


    private void updateBalanceDisplay() {
        double currentBalance = bankingService.getBalance();
        balance.setText(String.format("₱%.2f", currentBalance));
    }

    private void updateTransactionDisplay() {
        List<String> transactionHistory = TransactionManager.getInstance().getTransactionHistory();
        int maxHistorySize = 4;

        if (!transactionHistory.isEmpty()) {
            StringBuilder historyText = new StringBuilder();

            int startIndex = Math.max(transactionHistory.size() - maxHistorySize, 0);

            for (int i = startIndex; i < transactionHistory.size(); i++) {
                String transaction = transactionHistory.get(i);
                historyText.append(transaction).append("\n");
            }

            transactionHistoryLabel.setText(historyText.toString());
        } else {
            transactionHistoryLabel.setText("No recent transactions.");
        }
    }
}

