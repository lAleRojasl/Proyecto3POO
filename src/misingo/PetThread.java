package misingo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class PetThread {

    //Pet Animation
    @FXML private ImageView pet1View;
    @FXML private ImageView pet2View;
    @FXML private ImageView pet3View;
    private int imageIndex = 0 ;
    private final int frameTime = 60 ; // milliseconds

    //Pet Stats Bars
    @FXML private ProgressBar healthBar;
    @FXML private ProgressBar filthBar;
    @FXML private ProgressBar happinessBar;
    @FXML private ProgressBar hungerBar;

    //List of Pets
    private ArrayList<Pet> petList = new ArrayList<>();
    private Integer selectedPet = 0;

    public void changeSelectedPet(Integer petIndex){
        this.selectedPet = petIndex;
    }

    public void addPet(Pet pet){
        this.petList.add(pet);
    }

    public void feedSelectedPet(){
        this.petList.get(selectedPet).feedPet();
    }

    public void cleanSelectedPet(){
        this.petList.get(selectedPet).cleanPet();
    }

    public void petSelectedPet(){
        this.petList.get(selectedPet).petPet();
    }

    public void healSelectedPet(){
        this.petList.get(selectedPet).healPet();
    }

    public void updatePetNeeds(Integer newHour){
        for (Pet pet: petList) {
            System.out.println("Checking for Pet: "+ pet.getName());
            if(pet.isFeedingTime(newHour)){
                System.out.println("IS FEEDING TIME");
                changeHealth(false);
                changeHappiness(false);
                changeHunger(true);
            }
            if(pet.isCleaningTime(newHour)){
                System.out.println("IS CLEANING TIME");
                changeFilth(true);
                changeHealth(false);
            }
            if(pet.isPettingTime(newHour)){
                System.out.println("IS PETTING TIME");
                changeHappiness(false);
            }
            if(pet.isHealingTime(newHour)){
                System.out.println("IS HEALING TIME");
                changeHealth(false);
            }
        }
    }

    public void changeHealth(boolean increase){
        if(increase){
            healthBar.setProgress(healthBar.getProgress() + 0.2);
            if(healthBar.getProgress() >= 1) healthBar.setProgress(1);
        }
        else{
            healthBar.setProgress(healthBar.getProgress() - 0.2d);
            if(healthBar.getProgress() <= 0.1) healthBar.setProgress(0);
        }
    }

    public void changeFilth(boolean increase){
        if(increase){
            filthBar.setProgress(filthBar.getProgress() + 0.2);
            if(filthBar.getProgress() >= 1) filthBar.setProgress(1);
        }
        else{
            filthBar.setProgress(filthBar.getProgress() - 0.2d);
            if(filthBar.getProgress() <= 0.1) filthBar.setProgress(0);
        }
    }

    public void changeHappiness(boolean increase){
        if(increase){
            happinessBar.setProgress(happinessBar.getProgress() + 0.2);
            if(happinessBar.getProgress() >= 1) happinessBar.setProgress(1);
        }
        else{
            happinessBar.setProgress(happinessBar.getProgress() - 0.2d);
            if(happinessBar.getProgress() <= 0.1) happinessBar.setProgress(0);
        }
    }

    public void changeHunger(boolean increase){
        if(increase){
            hungerBar.setProgress(hungerBar.getProgress() + 0.2);
            if(hungerBar.getProgress() >= 1) hungerBar.setProgress(1);
        }
        else{
            hungerBar.setProgress(hungerBar.getProgress() - 0.2d);
            if(hungerBar.getProgress() <= 0.1) hungerBar.setProgress(0);
        }
    }

    public PetThread(ImageView pet1ViewRef, ImageView pet2ViewRef, ImageView pet3ViewRef,
                     ProgressBar healthBarRef, ProgressBar filthBarRef, ProgressBar happinessBarRef, ProgressBar hungerBarRef) {

        //Progress Bar UI Reference
        this.healthBar = healthBarRef;
        this.filthBar = filthBarRef;
        this.happinessBar = happinessBarRef;
        this.hungerBar = hungerBarRef;

        //Pet Image UI Reference
        this.pet1View = pet1ViewRef;
        this.pet2View = pet2ViewRef;
        this.pet3View = pet3ViewRef;

        List<Image> pet1Images = new ArrayList<>();
        List<Image> pet2Images = new ArrayList<>();
        List<Image> pet3Images = new ArrayList<>();
        // populate images
        for (int i = 1; i < 11; i++) {
            pet1Images.add(new Image("/resources/cats/v1/Idle"+i+".png"));
            pet2Images.add(new Image("/resources/cats/v2/Idle"+i+".png"));
            pet3Images.add(new Image("/resources/cats/v3/Idle"+i+".png"));
        }

        //Start Thread to animated the cats
        Timeline petAnimation = new Timeline( new KeyFrame(Duration.millis(frameTime), e -> {
            if(imageIndex == 9) imageIndex = 0;
            else imageIndex ++;
            pet1View.setImage(pet1Images.get(imageIndex));
            pet2View.setImage(pet2Images.get(imageIndex));
            pet3View.setImage(pet3Images.get(imageIndex));
        }));

        petAnimation.setCycleCount(Animation.INDEFINITE);
        petAnimation.play();
    }
}
