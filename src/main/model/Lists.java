package model;

import exceptions.NoSuchItemExistsException;
import exceptions.NothingToCrossOffException;
import exceptions.TooManyThingsToDoException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Lists extends Subject implements Loadable, Saveable {

    private MasterList masterList;
    private CrossedOff crossedOff;
    Map<String, Item> listMap;

    // EFFECTS: sets are empty and initializes scanner
    public Lists() {
        super();
        masterList = new MasterList();
        crossedOff = new CrossedOff();
        listMap = new HashMap<>();
    }


    // EFFECTS: data is loaded from file
    public void loadData(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./data/" + filename));
        for (int i = 0; i < lines.size(); i = i + 3) {
            Item task;
            if (lines.get(i + 2).equals("School Task")) {
                task = new SchoolItem(lines.get(i));
                task.setStatus(lines.get(i + 1).equals("true"));
            } else if (lines.get(i + 2).equals("Personal Task")) {
                task = new PersonalItem(lines.get(i));
                task.setStatus(lines.get(i + 1).equals("true"));
            } else {
                task = new WorkItem(lines.get(i));
                task.setStatus(lines.get(i + 1).equals("true"));
            }
            masterList.getMasterList().add(task);
            if (task.getStatus() == true) {
                crossedOff.getCrossedOff().add(task);
            }
        }
    }



    // EFFECTS: masterlist is saved into file
    public void saveData(String filename) throws IOException {
        PrintWriter writer = new PrintWriter("./data/" + filename,"UTF-8");
        for (Item task: masterList.getMasterList()) {
            writer.println(task.getName());
            writer.println(task.getStatus());
            writer.println(task.getType());
        }
        writer.close();
    }


    // REQUIRES: number inputted must be in displayed list
    // MODIFIES: this, Item
    // EFFECTS: finds Item to cross off, toggles its status, and copies it to crossedOff list
    public void crossOff(int number) throws NothingToCrossOffException, NoSuchItemExistsException {
        if (masterList.masterListSize() == crossedOff.crossedOffSize()) {
            throw new NothingToCrossOffException();
        }

        int counter = 0;
        for (int i = 0; i < this.masterList.masterListSize();i++) {
            Item task = this.masterList.getMasterList().get(i);
            if (task.getStatus() == false) {
                counter++;
                if (counter == number) {
                    this.crossedOff.getCrossedOff().add(task);
                    task.setStatus(true);
                    return;
                } else if (counter == masterList.masterListSize() - crossedOff.crossedOffSize()) {
                    throw new NoSuchItemExistsException();
                }
            }
        }
    }

    // MODIFIES: this, Item
    // EFFECTS: creates new Item of specified sub class and adds to masterList
    public void addItem(String item, int selection) throws TooManyThingsToDoException {
        if (selection == 1) {
            Item task = new PersonalItem(item);
            listMap.put(item,task);
            addItem(task);
        } else if (selection == 2) {
            Item task = new SchoolItem(item);
            listMap.put(item,task);
            addItem(task);
        } else  {
            Item task = new WorkItem(item);
            listMap.put(item,task);
            addItem(task);
        }
    }

    // MODIFIES: this
    // EFFECTS: Item is added to masterList.
    public void addItem(Item task) throws TooManyThingsToDoException {
        if (masterList.masterListSize() - crossedOff.crossedOffSize() == 5) {
            throw new TooManyThingsToDoException();
        }

        this.masterList.getMasterList().add(task);
        notify(task);

        task.printMessage();
    }

    public Item getItem(String item) {
        Item task = listMap.get(item);
        return task;
    }

    public MasterList getMasterList() {
        return masterList;
    }

    public CrossedOff getCrossedOff() {
        return crossedOff;
    }


}