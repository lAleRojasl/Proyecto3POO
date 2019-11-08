package misingo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PetLoader {

    private static String jsonFilePath = "";

    public ObservableList<String> getPetsFromJSON(){
        //load from jsonFilePath
        return FXCollections.observableArrayList("Misingo","Bigotes","Garfield","Pelusa");
        //return new ObservableList<String>("");
    }

}
