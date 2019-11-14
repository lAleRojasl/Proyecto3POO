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

    //---METHODS---
    public boolean updatePetNeeds(Integer hour){
        boolean hasNeeds = false;
        //It's either feeding time or a subsequent time
        if(feedingTimes.contains(hour) || !isFed) {
            hasNeeds = true;
            this.isFed = false;
            this.hunger++;
            this.happiness--;
            this.health--;
        }
        if(cleaningTimes.contains(hour) || !isClean){
            hasNeeds = true;
            this.isClean = false;
            this.filth++;
            this.health--;
        }
        if(pettingTimes.contains(hour) || !isPetted){
            hasNeeds = true;
            this.isPetted = false;
            this.happiness--;
        }
        if(healingTimes.contains(hour) || !isHealthy){
            hasNeeds = true;
            isHealthy = false;
            this.health--;
        }
        normalizeStats();
        return hasNeeds;
    }

    public void normalizeStats(){
        if(this.hunger > 5) this.hunger = 5;
        if(this.filth > 5) this.filth = 5;
        if(this.happiness > 5) this.happiness = 5;
        if(this.hunger > 5) this.hunger = 5;

        if(this.hunger < 0) this.hunger = 0;
        if(this.filth < 0) this.filth = 0;
        if(this.happiness < 0) this.happiness = 0;
        if(this.health < 0) this.health = 0;
    }

    public void feedPet(){
        this.isFed = true;
        this.hunger--;
        this.happiness++;
        this.health++;
        normalizeStats();
    }

    public void cleanPet(){
        this.isClean = true;
        this.filth--;
        this.health++;
        normalizeStats();
    }

    public void petPet(){
        this.isPetted = true;
        this.happiness += 2; //extra happiness!
        normalizeStats();
    }

    public void healPet(){
        this.isHealthy = true;
        this.health += 2; //extra healthy!
        normalizeStats();
    }

    public Double getPetHealth(){ return health * 0.2d; }

    public Double getPetFilth(){  return filth * 0.2d;  }

    public Double getPetJHappiness(){  return happiness * 0.2d;  }

    public Double getPetHunger(){  return hunger * 0.2d;  }

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
