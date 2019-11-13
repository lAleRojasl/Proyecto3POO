package misingo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    //Timer
    private TimeThread timeThread;
    @FXML private Label clockLabel;
    @FXML private Label dayLabel;

    //Pet Animation
    private PetThread petThread;
    @FXML private ImageView pet1View;
    @FXML private ImageView pet2View;
    @FXML private ImageView pet3View;

    //JSON file pet loader
    private PetLoader petLoader;

    //Pet Stats Bars
    @FXML private ProgressBar healthBar;
    @FXML private ProgressBar filthBar;
    @FXML private ProgressBar happinessBar;
    @FXML private ProgressBar hungerBar;

    /* ------ METHODS ------- */

    //Buttons Controller

    @FXML protected void handleFeedButtonAction(ActionEvent event) {
        petThread.changeHunger(false); //decrease hunger
        petThread.changeHappiness(true); //increase happiness
        petThread.changeHealth(true); //increase health
        petThread.feedSelectedPet();
    }

    @FXML protected void handleCleanButtonAction(ActionEvent event) {
        petThread.changeFilth(false);
        petThread.changeHealth(true);
        petThread.cleanSelectedPet();
    }

    @FXML protected void handlePetButtonAction(ActionEvent event) {
        petThread.changeHappiness(true);
        petThread.changeHappiness(true);
        petThread.petSelectedPet();
    }

    @FXML protected void handleHealButtonAction(ActionEvent event) {
        petThread.changeHealth(true);
    }

    // Pet Selection Controller
    public void petSelectionChanged(MouseEvent actionEvent) {
        String petId = ((ImageView)actionEvent.getSource()).getId();
        Integer petIndex = 0;
        switch (petId){
            case "Pet1":
                petIndex = 0;
                break;
            case "Pet2":
                petIndex = 1;
                break;
            case "Pet3":
                petIndex = 2;
                break;
        }
        petThread.changeSelectedPet(petIndex);
    }

    //Initializer
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initialize Pet Animation Thread
        petThread = new PetThread(pet1View, pet2View, pet3View, healthBar, filthBar, happinessBar, hungerBar);

        //Load Pet Data from JSON and send PetThread reference
        petLoader = new PetLoader(petThread);

        //Initialize Timer Thread and send PetThread reference
        timeThread = new TimeThread(petThread, clockLabel, dayLabel);
    }
}
