package internal.bankproject;

import javafx.scene.layout.AnchorPane;

public class UIManager {
    private static AnchorPane sharedAnchorPane;

    public static AnchorPane getSharedAnchorPane() {
        return sharedAnchorPane;
    }

    public static void setSharedAnchorPane(AnchorPane anchorPane) {
        sharedAnchorPane = anchorPane;
    }
}
