package misingo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GameInitializer implements Initializable {

    @FXML private Label clockLabel;
    @FXML private Label dayLabel;

    //Pet Animation
    private PetThread petThread;
    @FXML private ImageView pet1View;
    @FXML private ImageView pet2View;
    @FXML private ImageView pet3View;

    //Pet Mark Images
    @FXML private ImageView pet1Mark;
    @FXML private ImageView pet2Mark;
    @FXML private ImageView pet3Mark;

    //Pet Stats Bars
    @FXML private ProgressBar healthBar;
    @FXML private ProgressBar filthBar;
    @FXML private ProgressBar happinessBar;
    @FXML private ProgressBar hungerBar;

    /* ------ METHODS ------- */

    //Buttons Controller

    @FXML protected void handleFeedButtonAction() {
        petThread.feedSelectedPet();
    }

    @FXML protected void handleCleanButtonAction() {
        petThread.cleanSelectedPet();
    }

    @FXML protected void handlePetButtonAction() {
        petThread.petSelectedPet();
    }

    @FXML protected void handleHealButtonAction() {
        petThread.healSelectedPet();
    }

    // Pet Selection Controller
    public void petSelectionChanged(MouseEvent actionEvent) {
        String petId = ((ImageView)actionEvent.getSource()).getId();
        Integer petIndex = 0;
        switch (petId){
            case "pet1":
                petIndex = 0;
                break;
            case "pet2":
                petIndex = 1;
                break;
            case "pet3":
                petIndex = 2;
                break;
        }
        petThread.changeSelectedPet(petIndex);
    }

    //Initializer
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Initialize Pet Animation Thread
        petThread = new PetThread(pet1View, pet2View, pet3View,
                                  pet1Mark, pet2Mark, pet3Mark,
                                  healthBar, filthBar, happinessBar, hungerBar);

        //Load Pet Data from JSON and send PetThread reference
        new PetLoader(petThread);

        //Initialize Timer Thread and send PetThread reference
        new TimeThread(petThread, clockLabel, dayLabel);
    }
}
