package internal.bankproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransferFundsController {

    @FXML
    private TextField accountNumberField;

    @FXML
    private TextField amountField;

    @FXML
    private ComboBox<String> paymentChannelBox;

    private DefaultController mainController;

    public void setMainController(DefaultController mainController) {
        this.mainController = mainController;
    }

    public void initialize() {
        ObservableList<String> paymentChannels = FXCollections.observableArrayList("Bank Transfer", "PayPal", "GCash", "Maya");
        paymentChannelBox.setItems(paymentChannels);
    }

    @FXML
    public void handleTransferAction() {
        String targetAccount = accountNumberField.getText();
        String amountString = amountField.getText();
        String selectedChannel = paymentChannelBox.getSelectionModel().getSelectedItem();

        if (targetAccount.trim().isEmpty() || amountString.trim().isEmpty() || selectedChannel == null) {
            showAlert("Error", "Please fill in all fields and select a payment channel.", AlertType.ERROR);
            return;
        }

        try {
            double amount = Double.parseDouble(amountString);
            mainController.transferAmount(targetAccount, amount);

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid amount entered.", AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
