package misingo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TimeThread {

    //Pet Thread reference
    private PetThread petThread;

    //TIME
    @FXML private Label clockLabel;
    @FXML private Label dayLabel;
    private int realSecondCounter = 0;
    private String[] fakeDays = new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    private int fakeDayIndex = 0;
    private int fakeHour = 0;

    public TimeThread(PetThread petThreadRef, Label clockLabelRef, Label dayLabelRef) {
        //Set reference to Pet Thread
        this.petThread = petThreadRef;

        //Set references to UI Time Labels
        this.clockLabel = clockLabelRef;
        this.dayLabel = dayLabelRef;

        //Start clock simulation
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            realSecondCounter += 1;

            //Counted 5 seconds, lets increase fake time by 1 hour
            if(realSecondCounter % 5 == 0) {
                fakeHour += 1;
                //passed 24 hours, back to 0.
                if(fakeHour == 25) fakeHour = 0;
                //An hour has passed, update Pet needs
                this.petThread.updatePetNeeds(fakeHour);
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
            this.dayLabel.setText(fakeDays[fakeDayIndex] );
            this.clockLabel.setText(fakeHour + ":00");
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
