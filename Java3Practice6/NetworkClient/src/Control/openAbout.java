package Control;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class openAbout {

    public void startNewWindow () {
        Stage primaryStage = new Stage();
        Label secondLable = new Label("by Sviridov");
        StackPane secondsryLayout = new StackPane();
        secondsryLayout.getChildren().add(secondLable);
        Scene secondScene = new Scene(secondsryLayout, 200,100);
        Stage newWindow = new Stage();
        newWindow.setTitle("About");
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.initOwner(primaryStage);
//        newWindow.setX(primaryStage.getX()+200);
//        newWindow.setY(primaryStage.getY()+200);
        newWindow.setResizable(false);
        newWindow.show();
    }
}
