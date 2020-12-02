package Control;
import Network.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpenAuthorization {
    private String PATH_DIALOG_XML = "authorization.fxml";
    private Boolean isWindowClose =false;

    public void startNewWindow (Stage primaryStage, Client network) throws Exception {
        FXMLLoader loaderDialog = new FXMLLoader();
        loaderDialog.setLocation(Main.class.getResource(PATH_DIALOG_XML));
        Parent rootDialog = loaderDialog.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Логинатор");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(rootDialog);
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        AuthorizationController authorizationController = loaderDialog.getController();
        authorizationController.setClient(network);
        authorizationController.setPrimaryStage(primaryStage);
        authorizationController.setDialogStage(dialogStage);
        dialogStage.show();
    }
}
