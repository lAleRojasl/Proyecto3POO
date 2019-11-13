package misingo;

import java.util.ArrayList;

public class Pet {

    //Pet Name
    private String name;
    //Pet Needs
    private ArrayList<Integer> feedingTimes;
    private ArrayList<Integer> cleaningTimes;
    private ArrayList<Integer> pettingTimes;
    private ArrayList<Integer> healingTimes;
    //Pet Stats
    private Integer health = 5;
    private Integer filth = 0;
    private Integer happiness = 5;
    private Integer hunger = 0;
    //Pet Status
    private boolean isFed = true;
    private boolean isClean = true;
    private boolean isPetted = true;
    private boolean isHealthy = true;

    //---Feeding---
    public boolean isFeedingTime(Integer hour){
        //It's either feeding time or a subsequent time
        if(feedingTimes.contains(hour) || !isFed) {
            this.isFed = false;
            this.hunger++;
            this.happiness--;
            this.health--;
            return true;
        }
        else return false;
    }

    public void feedPet(){
        this.isFed = true;
        this.hunger--;
        this.happiness++;
        this.health++;
    }

    //---Cleaning---
    public boolean isCleaningTime(Integer hour){
        if(cleaningTimes.contains(hour) || !isClean){
            this.isClean = false;
            this.filth++;
            this.health--;
            return true;
        }
        else return false;
    }

    public void cleanPet(){
        this.isClean = true;
        this.filth--;
        this.health++;
    }

    public boolean isPettingTime(Integer hour){
        if(pettingTimes.contains(hour) || !isPetted){
            this.happiness--;
            return true;
        }
        else return false;
    }

    public void petPet(){
        this.isPetted = true;
        this.happiness += 2; //extra happiness!
        if(this.happiness > 5) this.happiness = 5;
    }

    public boolean isHealingTime(Integer hour){
        if(healingTimes.contains(hour) || !isHealthy){
            isHealthy = false;
            this.health--;
            return true;
        }
        else return false;
    }

    public void healPet(){
        this.isHealthy = true;
        this.health += 2; //extra healthy!
        if(this.health > 5) this.health = 5;
    }

    public String getName(){
        return this.name;
    }

    public Pet(String name,
               ArrayList<Integer> feedingTimes,
               ArrayList<Integer> cleaningTimes,
               ArrayList<Integer> pettingTimes,
               ArrayList<Integer> healingTimes){
        this.name = name;
        this.feedingTimes = feedingTimes;
        this.cleaningTimes = cleaningTimes;
        this.pettingTimes = pettingTimes;
        this.healingTimes = healingTimes;
    }
}
