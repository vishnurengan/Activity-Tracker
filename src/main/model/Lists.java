package model;

import exceptions.NoSuchItemExistsException;
import exceptions.NothingToCrossOffException;
import exceptions.TooManyThingsToDoException;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Lists implements Loadable, Saveable {

    private MasterList masterList;
    private CrossedOff crossedOff;

    // EFFECTS: instantiates new MasterList and CrossedOffList
    public Lists() {
        masterList = new MasterList();
        crossedOff = new CrossedOff();
    }

    // MODIFIES: this
    // EFFECTS: data is loaded from file and added to masterList
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



    // MODIFIES: this, Item
    // EFFECTS: finds Item to cross off, toggles its status, and copies it to crossedOff list
    // throws NothingToCrossOffException is there is nothing to cross off
    // throws NoSuchItemExistsException if user inputs invalid selection

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
    // throws TooManyThingsToDoException if there are more than 5 uncompleted task
    public void addItem(String item, int selection) throws TooManyThingsToDoException {
        if (selection == 1) {
            Item task = new PersonalItem(item);
            addItem(task);
        } else if (selection == 2) {
            Item task = new SchoolItem(item);
            addItem(task);
        } else  {
            Item task = new WorkItem(item);
            addItem(task);
        }
    }

    // MODIFIES: this
    // EFFECTS: Item is added to masterList and message is printed to console.
    // throws TooManyThingsToDoException if there are more than 5 uncompleted task
    public void addItem(Item task) throws TooManyThingsToDoException {
        if (masterList.masterListSize() - crossedOff.crossedOffSize() >= 5) {
            throw new TooManyThingsToDoException();
        }

        this.masterList.getMasterList().add(task);

        task.printMessage();
    }


    // EFFECTS: returns masterlist associated with list
    public MasterList getMasterList() {
        return masterList;
    }

    // EFFECTS : returns crossedOff list associated with list
    public CrossedOff getCrossedOff() {
        return crossedOff;
    }


}