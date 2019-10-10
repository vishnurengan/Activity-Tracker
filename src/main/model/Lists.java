package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Lists implements Loadable, Saveable {
    private ArrayList<Item> masterList;
    private ArrayList<Item> crossedOff;

    // EFFECTS: sets are empty and initializes scanner
    public Lists() {
        masterList = new ArrayList<>();
        crossedOff = new ArrayList<>();
    }

    // EFFECTS: returns masterList
    public ArrayList<Item> getMasterList() {
        return masterList;
    }

    // EFFECTS: returns crossedOff List
    public ArrayList<Item> getCrossedOff() {
        return crossedOff;
    }


    // EFFECTS: data is loaded from file
    public void loadData(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/" + filename));
        for (int i = 0; i < lines.size(); i = i + 3) {
            Item task;
            if (lines.get(i + 1).equals("true") && lines.get(i + 2).equals("School Task")) {
                task = new SchoolItem(lines.get(i));
                task.setStatus(true);
                this.crossedOff.add(task);
            } else if (lines.get(i + 1).equals("false") && lines.get(i + 2).equals("School Task")) {
                task = new SchoolItem(lines.get(i));
            } else if (lines.get(i + 1).equals("true") && lines.get(i + 2).equals("Personal Task")) {
                task = new PersonalItem(lines.get(i));
                task.setStatus(true);
                this.crossedOff.add(task);
            } else {
                task = new PersonalItem(lines.get(i));
            }
            masterList.add(task);
        }
    }

    // EFFECTS: masterlist is saved into file
    public void saveData(String filename) throws IOException {
        PrintWriter writer = new PrintWriter("./data/" + filename,"UTF-8");
        for (Item task: masterList) {
            writer.println(task.getName());
            writer.println(task.getStatus());
            writer.println(task.getType());
        }
        writer.close();
    }


    // REQUIRES: number inputted must be in displayed list
    // MODIFIES: this, Item
    // EFFECTS: finds Item to cross off, toggles its status, and copies it to crossedOff list
    public void crossOff(int number) {
        int counter = 0;
        for (int i = 0; i < this.masterList.size();i++) {
            Item task = this.masterList.get(i);
            if (task.getStatus() == false) {
                counter++;
                if (counter == number) {
                    this.crossedOff.add(task);
                    task.setStatus(true);
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Item is added to masterList.
    public void addItem(Item task) {
        this.masterList.add(task);
        task.printMessage();
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


}