package internal.bankproject;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {


    @FXML
    private Button dashboardEntity;

    @FXML
    private AnchorPane pane;

    @FXML
    private Button payEntity;

    @FXML
    private Button transferEntity;

    @FXML
    private ImageView transferImage;

    @FXML
    private ImageView payImage;

    @FXML
    private ImageView homeImage;

    @FXML
    private ImageView annoyingBackground;


    @FXML
    public void dashboardButton(ActionEvent event) throws IOException {
        AnchorPane panel = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        pane.getChildren().setAll(panel);
        dashboardEntity.setStyle("-fx-background-color:#ffffff; -fx-text-fill: #006400;");
        payEntity.setStyle("-fx-background-color:#006400; -fx-text-fill:#ffffff;");
        transferEntity.setStyle("-fx-background-color:#006400; -fx-text-fill:#ffffff;");
        annoyingBackground.setVisible(false);


        //Please to other people who use this, Direct the assets file or it will cause errors
        Image dashboard = new Image("file:src/main/resources/internal/bankproject/Assets/blackHome.png");
        homeImage.setImage(dashboard);
        Image pay = new Image("file:src/main/resources/internal/bankproject/Assets/Pay.png");
        payImage.setImage(pay);
        Image transfer = new Image("file:src/main/resources/internal/bankproject/Assets/Funds.png");
        transferImage.setImage(transfer);

    }


    @FXML
    public void payButton(ActionEvent event) throws IOException {
        AnchorPane panel = FXMLLoader.load(getClass().getResource("Pay.fxml"));
        pane.getChildren().setAll(panel);
        dashboardEntity.setStyle("-fx-background-color:#006400; -fx-text-fill:#ffffff;");
        payEntity.setStyle("-fx-background-color:#ffffff; -fx-text-fill: #006400;");
        transferEntity.setStyle("-fx-background-color:#006400; -fx-text-fill:#ffffff;");
        Image dashboard = new Image("file:src/main/resources/internal/bankproject/Assets/home.png");
        homeImage.setImage(dashboard);
        Image pay = new Image("file:src/main/resources/internal/bankproject/Assets/blackDollar.png");
        payImage.setImage(pay);
        Image transfer = new Image("file:src/main/resources/internal/bankproject/Assets/Funds.png");
        transferImage.setImage(transfer);
        annoyingBackground.setVisible(false);
    }


    @FXML
    public void transferButton(ActionEvent event) throws IOException {
        AnchorPane panel = FXMLLoader.load(getClass().getResource("Transfer.fxml"));
        pane.getChildren().setAll(panel);
        dashboardEntity.setStyle("-fx-background-color:#006400; -fx-text-fill:#ffffff;");
        payEntity.setStyle("-fx-background-color:#006400; -fx-text-fill:#ffffff;");
        transferEntity.setStyle("-fx-background-color:#ffffff; -fx-text-fill: #006400;");
        Image dashboard = new Image("file:src/main/resources/internal/bankproject/Assets/home.png");
        homeImage.setImage(dashboard);
        Image pay = new Image("file:src/main/resources/internal/bankproject/Assets/Pay.png");
        payImage.setImage(pay);
        Image transfer = new Image("file:src/main/resources/internal/bankproject/Assets/blackHand.png");
        transferImage.setImage(transfer);
        annoyingBackground.setVisible(false);
    }

    @FXML
    public void logoutButton(ActionEvent event) throws IOException
    {
        AnchorPane sharedAnchorPane = UIManager.getSharedAnchorPane();
        AnchorPane load = FXMLLoader.load(getClass().getResource("Loading.fxml"));
        sharedAnchorPane.getChildren().setAll(load);
        UIManager.setSharedAnchorPane(sharedAnchorPane);

        PauseTransition delay = new PauseTransition(Duration.seconds(4));

        AnchorPane pane = FXMLLoader.load(getClass().getResource("Main.fxml"));

        SequentialTransition sequentialTransition = new SequentialTransition(delay);
        sequentialTransition.setOnFinished(actionEvent ->
        {
            sharedAnchorPane.getChildren().setAll(pane);
        });
        sequentialTransition.play();
    }
}
