package misingo;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
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

    //Pet Exclamation mark
    @FXML private ImageView pet1Mark;
    @FXML private ImageView pet2Mark;
    @FXML private ImageView pet3Mark;

    //Pet Stats Bars
    @FXML private ProgressBar healthBar;
    @FXML private ProgressBar filthBar;
    @FXML private ProgressBar happinessBar;
    @FXML private ProgressBar hungerBar;

    //List of Pets
    private ArrayList<Pet> petList = new ArrayList<>();
    private Integer selectedPetIndex = 0;

    public void changeSelectedPet(Integer petIndex){
        this.selectedPetIndex = petIndex;
        if(selectedPetIndex == 0) pet1Mark.setVisible(false);
        if(selectedPetIndex == 1) pet2Mark.setVisible(false);
        if(selectedPetIndex == 2) pet3Mark.setVisible(false);
        updatePetStats();
    }

    public void addPet(Pet pet){
        this.petList.add(pet);
    }

    public void feedSelectedPet(){
        this.petList.get(selectedPetIndex).feedPet();
        updatePetStats();
    }

    public void cleanSelectedPet(){
        this.petList.get(selectedPetIndex).cleanPet();
        updatePetStats();
    }

    public void petSelectedPet(){
        this.petList.get(selectedPetIndex).petPet();
        updatePetStats();
    }

    public void healSelectedPet(){
        this.petList.get(selectedPetIndex).healPet();
        updatePetStats();
    }

    public void updatePetNeeds(Integer newHour){
        for (int i = 0; i < petList.size(); i++) {
            boolean hasNeeds = petList.get(i).updatePetNeeds(newHour);
            if(hasNeeds) {
                if(i == 0) pet1Mark.setVisible(true);
                if(i == 1) pet2Mark.setVisible(true);
                if(i == 2) pet3Mark.setVisible(true);
            }
        }
        //Update progress bars for selected pet
        updatePetStats();

    }

    public void updatePetStats(){
        Pet selectedPet = petList.get(this.selectedPetIndex);
        changeHealth(selectedPet.getPetHealth());
        changeFilth(selectedPet.getPetFilth());
        changeHappiness(selectedPet.getPetJHappiness());
        changeHunger(selectedPet.getPetHunger());
    }

    public void changeHealth(Double newValue){
        healthBar.setProgress(newValue);
    }

    public void changeFilth(Double newValue){
        filthBar.setProgress(newValue);
    }

    public void changeHappiness(Double newValue){
        happinessBar.setProgress(newValue);
    }

    public void changeHunger(Double newValue){
        hungerBar.setProgress(newValue);
    }

    public PetThread(ImageView pet1ViewRef, ImageView pet2ViewRef, ImageView pet3ViewRef,
                     ImageView pet1MarkRef, ImageView pet2MarkRef, ImageView pet3MarkRef,
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

        //Pet Exclamation Mark UI Reference
        this.pet1Mark = pet1MarkRef;
        this.pet2Mark = pet2MarkRef;
        this.pet3Mark = pet3MarkRef;

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
