package ui;

import model.*;


import java.io.IOException;
import java.util.Scanner;
// Using scanner as provided in the LittleLoggingCalculator

public class ListInterface {
    private Lists lists;
    private Scanner scanner;

    // EFFECTS: constructs lists and initializes scanner
    public ListInterface() {
        lists = new Lists();
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
            int selection = userSelection();
            if (selection == 1) {
                enterItem();
            } else if (selection == 2) {
                removeItem();
                crossedOffPrinter();
            } else if (selection == 3) {
                masterListPrinter();
            } else if (selection == 4) {
                fileNameEntryLD();
            } else if (selection == 5) {
                fileNameEntrySD();
                break;
            }
        }
    }

    // EFFECTS: user chooses filename
    private void fileNameEntryLD() throws IOException {
        System.out.println("Please enter the filename:");
        scanner.nextLine();
        String filename = scanner.nextLine();
        lists.loadData(filename);
    }


    // EFFECTS: user chooses filename
    private void fileNameEntrySD() throws IOException {
        System.out.println("Please enter the filename:");
        scanner.nextLine();
        String filename = scanner.nextLine();
        lists.saveData(filename);
    }


    // REQUIRES: User must enter 1,2,3, or 4,or 5
    // EFFECTS: Returns user selection
    private int userSelection() {
        System.out.println("Please enter what you would like to do: [1] add a to do list item "
                + "[2] cross off an item [3] show all items [4] load data [5] save and quit.");
        int selection = scanner.nextInt();
        System.out.println("You selected: " + selection);
        return selection;
    }

    // MODIFIES: this, Item
    // EFFECTS: user inputs new item. New Item is constructed and added to masterlist.
    private void enterItem() {
        int selection = taskSelection();

        if (selection == 1) {
            scanner.nextLine();
            String item = scanner.nextLine();
            lists.addItem(item,selection);
        } else if (selection == 2) {
            scanner.nextLine();
            String item = scanner.nextLine();
            lists.addItem(item,selection);
        } else if (selection == 3) {
            scanner.nextLine();
            String item = scanner.nextLine();
            lists.addItem(item,selection);
        }

    }

    // EFFECTS: returns user selection
    public int taskSelection() {
        scanner.nextLine();
        System.out.println("What would you like to enter: [1] Personal Task [2] School Task [3] Work Task ?");
        int selection = scanner.nextInt();
        System.out.println("You selected: " + selection);
        System.out.println("Please enter the task name:");
        return selection;
    }


    // REQUIRES: selection inputted by user must be on displayed toDoList
    // MODIFIES: this, Item
    // EFFECTS: asks user which item should be crossed off and crosses it off
    private void removeItem() {
        toDoListPrinter();
        System.out.println("Which item would you like to cross off?");
        int selection = scanner.nextInt();
        lists.crossOff(selection);
        System.out.println("Here is your updated to do list:");
        toDoListPrinter();

    }


    // EFFECTS: prints out items in masterList with not complete status
    private void toDoListPrinter() {
        int counter = 0;
        for (int i = 0; i < lists.getMasterList().size();i++) {
            Item task = lists.getMasterList().get(i);
            if (task.getStatus() == false) {
                counter++;
                System.out.println(counter + ". " + task.getName());
            }
        }
    }

    // EFFECTS: prints out item in crossedOff list
    private void crossedOffPrinter() {
        System.out.println("The following items were crossed off:");
        for (int i = 0; i < lists.getCrossedOff().size(); i++) {
            System.out.println(lists.getCrossedOff().get(i).getName());
        }
    }

    // EFFECTS: prints out all items in masterList with their status'
    private void masterListPrinter() {
        for (int i = 0; i < lists.getMasterList().size(); i++) {
            Item task = lists.getMasterList().get(i);
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
