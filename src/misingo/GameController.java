package misingo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javax.print.DocFlavor;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private Text actiontarget;

    @FXML
    private ImageView roombg;

    @FXML protected void handleHealButtonAction(ActionEvent event) {
        actiontarget.setText("Heal button pressed");
    }

    @FXML protected void handleCleanButtonAction(ActionEvent event) {
        actiontarget.setText("Clean button pressed");
    }

    @FXML protected void handlePetButtonAction(ActionEvent event) {
        actiontarget.setText("Pet button pressed");
    }

    @FXML protected void handleFeedButtonAction(ActionEvent event) {
        actiontarget.setText("Feed button pressed");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.roombg = new ImageView("/resources/room_bg.jpg");

    }
}
