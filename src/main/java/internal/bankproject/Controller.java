package internal.bankproject;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;


public class Controller {

    @FXML
    private Label label;

    @FXML
    private TextField passwordBox;

    @FXML
    private TextField usernameBox;

    @FXML
    private AnchorPane anchorPane;

    public String name = "Peter";

    @FXML
    public void loginButton(ActionEvent event) throws IOException
    {
        buttonClicked();
    }

    public void buttonClicked() throws IOException
    {
        if (usernameBox.getText().toString().equals(name) && passwordBox.getText().toString().equals("AEXBank"))
        {
            AnchorPane load = FXMLLoader.load(getClass().getResource("Loading.fxml"));
            anchorPane.getChildren().setAll(load);
            UIManager.setSharedAnchorPane(anchorPane);

            PauseTransition delay = new PauseTransition(Duration.seconds(4));

            AnchorPane pane = FXMLLoader.load(getClass().getResource("UserLogin.fxml"));

            SequentialTransition sequentialTransition = new SequentialTransition(delay);
            sequentialTransition.setOnFinished(actionEvent ->
            {
                anchorPane.getChildren().setAll(pane);
            });
            sequentialTransition.play();
            anchorPane.setStyle("-fx-background-color:#ffffff");
        }
        else
        {
            label.setText("Incorrect Password");
        }
    }
    public String getName()
    {
        return name;
    }
}
