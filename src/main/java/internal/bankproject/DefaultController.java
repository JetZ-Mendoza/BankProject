package internal.bankproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;

public class DefaultController {

    @FXML
    private Label balance;

    private BankingService bankingService;



    @FXML
    public void initialize() {
        this.bankingService = BankingService.getInstance();
        updateBalanceDisplay();

    }

   private void updateBalanceDisplay() {
        double currentBalance = bankingService.getBalance();
        balance.setText(String.format("₱%.2f", currentBalance));
    }

    @FXML
    public void handleCashInButtonAction(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("0");
        dialog.setTitle("Cash In");
        dialog.setHeaderText("Enter the amount to cash in:");
        dialog.setContentText("Amount:");

        String result = dialog.showAndWait().orElse(null);

        if(result == null || result.trim().isEmpty()) {
            System.out.println("No input provided or cancelled.");
            return;
        }

        try {
            double cashInAmount = Double.parseDouble(result);
            bankingService.deposit(cashInAmount);
            updateBalanceDisplay();
            String transaction = "+ " + cashInAmount + " Cash In";
            TransactionManager.getInstance().addTransaction(transaction);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    @FXML
    public void handleTransferFundsAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TransferFundsPage.fxml"));
            Parent root = fxmlLoader.load();

            TransferFundsController controller = fxmlLoader.getController();
            controller.setMainController(this);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Transfer Funds");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void transferAmount(String targetAccount, double amount) {
        double currentBalance = bankingService.getBalance();
        if (amount > currentBalance) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Insufficient Funds");
            alert.setContentText("You don't have enough money to complete this transfer.");
            alert.showAndWait();
        } else {
            bankingService.withdraw(amount);
            updateBalanceDisplay();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Transfer Successful");
            alert.setContentText("Funds transferred successfully!");
            alert.showAndWait();
            String transaction = "- " + amount + " Transfer to " + targetAccount;
            TransactionManager.getInstance().addTransaction(transaction);
        }
    }
}
