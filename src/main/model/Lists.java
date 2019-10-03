package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Using scanner as provided in the LittleLoggingCalculator

public class Lists implements Loadable, Saveable {
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

    // EFFECTS: used chooses filename
    private void fileNameEntrySD() throws IOException {
        System.out.println("Please enter the filename:");
        scanner.nextLine();
        String filename = scanner.nextLine();
        saveData(filename);
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


    // REQUIRES: User must enter 1,2,3, or 4
    // EFFECTS: Returns user selection
    private int userSelection() {
        System.out.println("Please enter what you would like to do: [1] add a to do list item "
                + "[2] cross off an item [3] show all items [4] load data [5] save and quit.");
        int selection = scanner.nextInt();
        System.out.println("You selected: " + selection);
        return selection;
    }

    // REQUIRES: number inputted must be in displayed list
    // MODIFIES: this, task
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

    // MODIFIES: this, task
    // EFFECTS: New Item is constructed and added to masterList.
    public void addItem(Item task) {
        this.masterList.add(task);
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