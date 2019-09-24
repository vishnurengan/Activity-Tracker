package model;

import java.util.ArrayList;
import java.util.Scanner;
// Using scanner as provided in the LittleLoggingCalculator

public class Lists {
    private ArrayList<Item> masterList;
    private ArrayList<Item> crossedOff;
    private Scanner scanner;

    // EFFECTS: sets are empty and initializes scanner
    public Lists() {
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
    public void run() {
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
                break;
            }
        }

    }

    // REQUIRES: User must enter 1,2,3, or 4
    // EFFECTS: Returns user selection
    private int userSelection() {
        System.out.println("Please enter what you would like to do: "
                + "[1] add a to do list item [2] cross off an item [3] show all items [4] quit the program.");
        int selection = scanner.nextInt();
        System.out.println("You selected: " + selection);
        return selection;
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

    // MODIFIES: this,Item
    // EFFECTS: user inputs new item. New Item is constructed and added to masterlist.
    private void enterItem() {
        scanner.nextLine();
        System.out.println("Enter the item text");
        String item = scanner.nextLine();
        this.addItem(item);
    }

    // MODIFIES: this, Item
    // EFFECTS: New Item is constructed and added to masterList.
    public void addItem(String task) {
        Item newTask = new Item(task);
        this.masterList.add(newTask);
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

    // REQUIRES: selection inputted by user must be on displayed toDoList
    // MODIFIES: this, Item
    // EFFECTS: asks user which item should be crossed off and crosses it off
    private void removeItem() {
        this.toDoListPrinter();
        System.out.println("Which item would you like to cross off?");
        int selection = scanner.nextInt();
        this.crossOff(selection);
        System.out.println("Here is your updated to do list:");
        this.toDoListPrinter();

    }

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
                System.out.printf("%d. %-20s %-30s%n", i + 1, task.getName(), "Status: Not Complete");
            } else if (task.getStatus() == true) {
                System.out.printf("%d. %-20s %-30s%n", i + 1, task.getName(), "Status: Complete");
            }
        }
    }


}