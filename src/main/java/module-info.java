module internal.bankproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens internal.bankproject to javafx.fxml;
    exports internal.bankproject;
}