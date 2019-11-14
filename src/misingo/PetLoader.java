package misingo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class PetLoader {

    //JSON Path
    private static String jsonFilePath = "/src/resources/json/petData.json";

    //Reference to Pet Thread
    private PetThread petThread;

    public PetLoader(PetThread petThreadRef){
        //Pet Thread reference used to update Pet needs while time runs
        this.petThread = petThreadRef;

        //TODO: load JSON data from jsonFilePath
        JSONParser parser = new JSONParser();

        try {

            String filePath = new File("").getAbsolutePath();
            Object obj = parser.parse(new FileReader(filePath + jsonFilePath));

            JSONObject jsonObject = (JSONObject) obj;

            String playerName = (String) jsonObject.get("player");

            JSONArray petList = (JSONArray) jsonObject.get("pet");

            System.out.println("Player Name: " + playerName);
            System.out.println("\nPet List:");
            Iterator<JSONObject> iterator = petList.iterator();
            while (iterator.hasNext()) {
                JSONObject petData = iterator.next();
                String petName = (String) petData.get("name");
                System.out.println("Pet Name: " + petName);

                //Lists of Integers with the specific times for each need
                ArrayList<Integer> feedingTimes = new ArrayList<>();
                ArrayList<Integer> cleaningTimes = new ArrayList<>();
                ArrayList<Integer> pettingTimes = new ArrayList<>();
                ArrayList<Integer> healingTimes = new ArrayList<>();

                //Lets iterate over each need, and add the values to the corresponding list
                JSONArray feedingTimesList = (JSONArray) petData.get("feeding");
                Iterator<Long> feedingIterator = feedingTimesList.iterator();
                while (feedingIterator.hasNext()) {
                    feedingTimes.add(feedingIterator.next().intValue());
                }

                JSONArray cleaningTimesList = (JSONArray) petData.get("cleaning");
                Iterator<Long> cleaningIterator = cleaningTimesList.iterator();
                while (cleaningIterator.hasNext()) {
                    cleaningTimes.add(cleaningIterator.next().intValue());
                }

                JSONArray pettingTimesList = (JSONArray) petData.get("petting");
                Iterator<Long> pettingIterator = pettingTimesList.iterator();
                while (pettingIterator.hasNext()) {
                    pettingTimes.add(pettingIterator.next().intValue());
                }

                JSONArray healingTimesList = (JSONArray) petData.get("healing");
                Iterator<Long> healingIterator = healingTimesList.iterator();
                while (healingIterator.hasNext()) {
                    healingTimes.add(healingIterator.next().intValue());
                }

                Pet newPet = new Pet(petName, feedingTimes, cleaningTimes, pettingTimes, healingTimes);
                //Add pet to list of pets on Pet Thread
                this.petThread.addPet(newPet);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO: for each Pet on JSON file:
        //      Create a Pet Object



    }

}
