package model;

import java.util.ArrayList;

public class CrossedOff {
    private ArrayList<Item> crossedOff;

    // EFFECTS: instantiates new array list
    public CrossedOff() {
        crossedOff = new ArrayList<>();
    }

    // EFFECTS: returns array list of items on crossedOff list
    public ArrayList<Item> getCrossedOff() {
        return crossedOff;
    }

    // EFFECTS: Returns true is crossedOffList contains task and false otherwise
    public boolean crossedOffListContains(String name) {
        for (int i = 0; i < this.crossedOff.size();i++) {
            Item task = this.crossedOff.get(i);
            if (task.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: return the size of the crossedOff list
    public int crossedOffSize() {
        return crossedOff.size();
    }
}
