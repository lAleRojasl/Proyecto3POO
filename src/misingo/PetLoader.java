package misingo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PetLoader {

    //JSON Path
    private static String jsonFilePath = "/resources/json/petData.json";

    //Reference to Pet Thread
    private PetThread petThread;

    public PetLoader(PetThread petThreadRef){
        //Pet Thread reference used to update Pet needs while time runs
        this.petThread = petThreadRef;

        //TODO: load JSON data from jsonFilePath


        //TODO: for each Pet on JSON file:
        //      Create a Pet Object
        //For example:
        String petName = "Misingo";
        ArrayList<Integer> feedingTimes = new ArrayList<>();
        feedingTimes.add(8);
        feedingTimes.add(18);
        ArrayList<Integer> cleaningTimes = new ArrayList<>();
        cleaningTimes.add(19);
        ArrayList<Integer> pettingTimes = new ArrayList<>();
        pettingTimes.add(9);
        pettingTimes.add(11);
        pettingTimes.add(15);
        pettingTimes.add(19);
        ArrayList<Integer> healingTimes = new ArrayList<>();

        Pet newPet = new Pet(petName, feedingTimes, cleaningTimes, pettingTimes, healingTimes);

        //Add pet to list of pets on Pet Thread
        this.petThread.addPet(newPet);
    }

}
