package model;

import java.util.ArrayList;

public class CrossedOff {
    private ArrayList<Item> crossedOff;

    public CrossedOff() {
        crossedOff = new ArrayList<>();
    }

    // EFFECTS: returns crossedOff List
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

    public int crossedOffSize() {
        return crossedOff.size();
    }
}
