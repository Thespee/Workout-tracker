package model;
//TODO add representation for exercise name, primary & secondary muscles worked

import java.util.ArrayList;

public class Exercise {
    String name;
    ArrayList<String> primary;
    ArrayList<String> secondary;

    public Exercise(String n, ArrayList<String> p, ArrayList<String> s) {
        this.name = n;
        this.primary = p;
        this.secondary = s;
    }

    public Exercise(String n) {
        this.name = n;
        this.primary = new ArrayList<>();
        this.primary.clear();
        this.secondary = new ArrayList<>();
        this.secondary.clear();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPrimary() {
        return primary;
    }

    public ArrayList<String> getSecondary() {
        return secondary;
    }

    //REQUIRES muscle name to be the same as it was recorded in primary
    //EFFECTS returns true if exercise primarily works given muscle, false otherwise
    public boolean worksPrimary(String muscle) {
        return primary.contains(muscle);
    }

    //REQUIRES muscle name to be the same as it was recorded in secondary
    //EFFECTS returns true if exercise works this muscle secondarily, false otherwise
    public boolean worksSecondary(String muscle) {
        return secondary.contains(muscle);
    }
}
