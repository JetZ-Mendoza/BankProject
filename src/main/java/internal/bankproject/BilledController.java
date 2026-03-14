package internal.bankproject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BilledController {

    @FXML
    private Label balance;

    @FXML
    private ComboBox<String> billedDropdown;

    @FXML
    private TextField amountInput;

    private BankingService bankingService;

    @FXML
    public void initialize() {
        billedDropdown.getItems().addAll("Water Services", "Electrical Services", "Internet Services");

        bankingService = BankingService.getInstance();
        updateBalanceDisplay();
    }

    @FXML
    private void handleConfirmButtonAction() {
        String selectedBilled = billedDropdown.getSelectionModel().getSelectedItem();
        String amountStr = amountInput.getText();

        if (selectedBilled == null || amountStr.isEmpty()) {
            showAlert(AlertType.ERROR, "Error", "Please select a billed entity and enter the amount.");
            return;
        }

        try {
            double amount = Double.parseDouble(amountStr);
            if (bankingService.withdraw(amount)) {

                showAlert(AlertType.INFORMATION, "Success", "Payment of ₱" + amountStr + " to " + selectedBilled + " was successful.");
                updateBalanceDisplay();


                String transaction = "- " + amountStr + " " + selectedBilled;
                TransactionManager.getInstance().addTransaction(transaction);
            } else {
                // Insufficient balance or other error
                showAlert(AlertType.ERROR, "Failed", "Failed to make the payment. Ensure you have sufficient balance.");
            }
        } catch (NumberFormatException e) {
            showAlert(AlertType.ERROR, "Error", "Invalid amount entered.");
        }
    }

    @FXML
    private void handleCancelButtonAction() {
        billedDropdown.getSelectionModel().clearSelection();
        amountInput.clear();
    }

    private void updateBalanceDisplay() {
        double currentBalance = bankingService.getBalance();
        balance.setText(String.format("₱%.2f", currentBalance));
    }

    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
