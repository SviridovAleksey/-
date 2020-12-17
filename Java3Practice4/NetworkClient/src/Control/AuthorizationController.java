package Control;

import Network.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthorizationController {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    private Client client;
    private Stage primaryStage;
    private Stage dialogStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    public void chekAuthorization() {
        String login = loginField.getText();
        String password = passwordField.getText();
        if(login.isEmpty()|| password.isEmpty()) {
            showErrorMessage("Запоните оба поля", "Ошибка ввода");
        } else {
            String authErrMessage = client.sendAuthCommand(login, password);
            if (authErrMessage == null) {
                openChat();
            } else {
                showErrorMessage("Ошибка авторизации", authErrMessage);
                loginField.setText("");
                passwordField.setText("");
            }
        }

    }
    public void showErrorMessage(String message, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка подключения");
        alert.setHeaderText(errorMessage);
        alert.setContentText(message);
        alert.showAndWait();
        }


    public void openChat () {
        dialogStage.close();
        primaryStage.setTitle(client.getUsername());
        primaryStage.show();
        client.waitMessage();
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
