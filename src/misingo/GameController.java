package misingo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    //Pet Animation
    @FXML private ImageView petView;
    private int imageIndex = 0 ;
    private final int frameTime = 60 ; // milliseconds

    //JSON file pet loader
    private PetLoader petLoader;

    //TIME
    @FXML private Label clockLabel;
    @FXML private Label dayLabel;
    private int realSecondCounter = 0;
    private String[] fakeDays = new String[]{"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
    private int fakeDayIndex = 0;
    private int fakeHour = 12;

    //Available Pets
    @FXML private ComboBox<String> petSelector;

    //Pet Stats Bars
    @FXML private ProgressBar healthBar;
    @FXML private ProgressBar dirtBar;
    @FXML private ProgressBar happinessBar;
    @FXML private ProgressBar hungerBar;

    /* ------ METHODS ------- */

    //Buttons Controller
    @FXML protected void handleHealButtonAction(ActionEvent event) {
        if(healthBar.getProgress() >= 1) healthBar.setProgress(0.2);
        else {
            healthBar.setProgress(healthBar.getProgress() + 0.2);
        }
    }

    @FXML protected void handleCleanButtonAction(ActionEvent event) {
        if(dirtBar.getProgress() <= 0.1) dirtBar.setProgress(1);
        else {
            dirtBar.setProgress(dirtBar.getProgress() - 0.2d);
        }
    }

    @FXML protected void handlePetButtonAction(ActionEvent event) {
        if(happinessBar.getProgress() >= 1) happinessBar.setProgress(0.2d);
        else {
            happinessBar.setProgress(happinessBar.getProgress() + 0.2);
        }
    }

    @FXML protected void handleFeedButtonAction(ActionEvent event) {
        if(hungerBar.getProgress() <= 0.1) hungerBar.setProgress(1);
        else {
            hungerBar.setProgress(hungerBar.getProgress() - 0.2d);
        }
    }

    // Pet Selection Controller
    public void petSelectionChanged(ActionEvent actionEvent) {

    }

    //Initializer
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Load Pets
        petLoader = new PetLoader();
        petSelector.setItems(petLoader.getPetsFromJSON());
        if(petSelector.getItems().size() > 0){
            petSelector.getSelectionModel().select(0);
        }

        //Start timer
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            realSecondCounter += 1;

            //Counted 5 seconds, lets increase fake time by 1 hour
            if(realSecondCounter % 5 == 0) {
                fakeHour += 1;
                //passed 24 hours, back to 0.
                if(fakeHour == 25) fakeHour = 0;
            }

            //Counted 120 seconds, a day has passed
            if(realSecondCounter == 120){
                //reset counter
                realSecondCounter = 0;
                fakeDayIndex += 1;
                //Counted to 7, back to Monday
                if(fakeDayIndex == 7) fakeDayIndex = 0;
            }
            //Set UI Labels with fake times
            dayLabel.setText(fakeDays[fakeDayIndex] );
            clockLabel.setText(fakeHour + ":00");
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();


        //petView = new ImageView();
        List<Image> images = new ArrayList<>();
        // populate images...

        for (int i = 1; i < 11; i++) {
            images.add(new Image("/resources/cats/Idle"+i+".png"));
        }

        Timeline petAnimation = new Timeline( new KeyFrame(Duration.millis(frameTime), e -> {
            if(imageIndex == 9) imageIndex = 0;
            else imageIndex ++;
            petView.setImage(images.get(imageIndex));
        }));

        petAnimation.setCycleCount(Animation.INDEFINITE);
        petAnimation.play();
    }
}
