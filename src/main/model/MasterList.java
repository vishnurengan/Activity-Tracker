package model;

import java.util.ArrayList;

public class MasterList {
    private ArrayList<Item> masterList;

    // EFFECTS: instantiates new array list
    MasterList() {
        masterList = new ArrayList<>();
    }

    // EFFECTS: returns arraylist of items on masterList
    public ArrayList<Item> getMasterList() {
        return masterList;
    }

    // EFFECTS: Returns true is masterList contains task and false otherwise
    public boolean masterListContains(String name) {
        for (int i = 0; i < this.masterList.size();i++) {
            Item task = this.masterList.get(i);
            if (task.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns status of item in masterList
    public boolean masterListStatus(String name) {
        for (int i = 0; i < this.masterList.size();i++) {
            Item task = this.masterList.get(i);
            if (task.getName().equals(name)) {
                return task.getStatus();
            }
        }
        return false;
    }

    // EFFECTS: returns the size of the masterList
    public int masterListSize() {
        return masterList.size();
    }

}
