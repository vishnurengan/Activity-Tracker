package ui;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class UserInterface implements Loadable, Saveable {
    protected ArrayList<Item> masterList;
    protected ArrayList<Item> crossedOff;
    protected Scanner scanner;

    // EFFECTS: sets are empty and initializes scanner
    public UserInterface() {
        masterList = new ArrayList<>();
        crossedOff = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // MODIFIES: this, Item
    // EFFECTS: gets user selection.
    // If selection is 1, add items to list
    // If selection is 2, removes items from list
    // If selection is 3, displays all items and status
    // If selection is 4, program quits
    public void run() throws IOException {
        while (true) {
            int selection = this.userSelection();
            if (selection == 1) {
                this.enterItem();
            } else if (selection == 2) {
                this.removeItem();
                this.crossedOffPrinter();
            } else if (selection == 3) {
                this.masterListPrinter();
            } else if (selection == 4) {
                this.fileNameEntryLD();
            } else if (selection == 5) {
                this.fileNameEntrySD();
                break;
            }
        }
    }

    // EFFECTS: used chooses filename
    private void fileNameEntryLD() throws IOException {
        System.out.println("Please enter the filename:");
        scanner.nextLine();
        String filename = scanner.nextLine();
        loadData(filename);
    }

    //protected abstract void loadData(String filename) throws IOException;

    // EFFECTS: used chooses filename
    private void fileNameEntrySD() throws IOException {
        System.out.println("Please enter the filename:");
        scanner.nextLine();
        String filename = scanner.nextLine();
        saveData(filename);
    }

    //protected abstract void saveData(String filename) throws IOException;


    // REQUIRES: User must enter 1,2,3, or 4,or 5
    // EFFECTS: Returns user selection
    private int userSelection() {
        System.out.println("Please enter what you would like to do: [1] add a to do list item "
                + "[2] cross off an item [3] show all items [4] load data [5] save and quit.");
        int selection = scanner.nextInt();
        System.out.println("You selected: " + selection);
        return selection;
    }

    // MODIFIES: this, task
    // EFFECTS: user inputs new item. New Item is constructed and added to masterlist.
    private void enterItem() {
        scanner.nextLine();
        System.out.println("What would you like to enter: [1] Personal Task [2] School Task?");
        int selection = scanner.nextInt();
        System.out.println("You selected: " + selection);
        if (selection == 1) {
            scanner.nextLine();
            System.out.println("Please enter your personal task.");
            String item = scanner.nextLine();
            Item task = new PersonalItem(item);
            this.addItem(task);
        } else if (selection == 2) {
            System.out.println("Please enter your school task.");
            scanner.nextLine();
            String item = scanner.nextLine();
            Item task = new SchoolItem(item);
            this.addItem(task);
        }

    }

    protected abstract void addItem(Item task);

    // REQUIRES: selection inputted by user must be on displayed toDoList
    // MODIFIES: this, task
    // EFFECTS: asks user which item should be crossed off and crosses it off
    private void removeItem() {
        this.toDoListPrinter();
        System.out.println("Which item would you like to cross off?");
        int selection = scanner.nextInt();
        this.crossOff(selection);
        System.out.println("Here is your updated to do list:");
        this.toDoListPrinter();

    }

    protected abstract void crossOff(int selection);

    // EFFECTS: prints out items in masterList with not complete status
    private void toDoListPrinter() {
        int counter = 0;
        for (int i = 0; i < this.masterList.size();i++) {
            Item task = this.masterList.get(i);
            if (task.getStatus() == false) {
                counter++;
                System.out.println(counter + ". " + task.getName());
            }
        }
    }

    // EFFECTS: prints out item in crossedOff list
    private void crossedOffPrinter() {
        System.out.println("The following items were crossed off:");
        for (int i = 0; i < this.crossedOff.size(); i++) {
            System.out.println(this.crossedOff.get(i).getName());
        }
    }

    // EFFECTS: prints out all items in masterList with their status'
    private void masterListPrinter() {
        for (int i = 0; i < this.masterList.size(); i++) {
            Item task = masterList.get(i);
            if (task.getStatus() == false) {
                System.out.printf("%d. %-20s %-30s %-20s%n", i + 1, task.getName(),
                        "Status: Not Complete", "Type: " + task.getType());
            } else if (task.getStatus() == true) {
                System.out.printf("%d. %-20s %-30s %-20s%n", i + 1, task.getName(),
                        "Status: Complete", "Type: " + task.getType());
            }
        }
    }
}
