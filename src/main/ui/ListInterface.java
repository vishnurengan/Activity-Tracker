package ui;

import exceptions.NoSuchItemExistsException;
import exceptions.NothingToCrossOffException;
import exceptions.TooManyThingsToDoException;
import model.*;
import network.GetLocation;


import java.io.IOException;
import java.util.Scanner;
// Using scanner as provided in the LittleLoggingCalculator
// Loading and Saving were implemented following provided example in project deliverable file

public class ListInterface {
    private Lists lists;
    private Scanner scanner;
    private PriorityList priorityList;

    // EFFECTS: constructs lists and initializes scanner
    public ListInterface() {
        ConcreteItemObserver concreteItemObserver = new ConcreteItemObserver();
        lists = new Lists();
        lists.addObserver(concreteItemObserver);
        scanner = new Scanner(System.in);
        priorityList = new PriorityList();
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
                try {
                    enterItem();
                } catch (TooManyThingsToDoException e) {
                    System.out.println("Sorry! You have to many uncompleted tasks! Task not added!");
                } finally {
                    int noUncompleteTasks;
                    noUncompleteTasks = lists.getMasterList().masterListSize() - lists.getCrossedOff().crossedOffSize();
                    System.out.println("Number of Uncomplete Tasks: " + noUncompleteTasks);
                }
            } else {
                runContinue(selection);
            }
        }
    }

    public void runContinue(int selection) throws IOException {
        if (selection == 2) {
            try {
                removeItem();
                crossedOffPrinter();
            } catch (NothingToCrossOffException e) {
                System.out.println("Oops. There's nothing to cross off.");
            } catch (NoSuchItemExistsException e) {
                System.out.println("Oops. You entered an invalid number");
            }
        } else if (selection == 3) {
            masterListPrinter();
        } else if (selection == 4) {
            fileNameEntryLD();
        } else {
            runContinue2(selection);
        }
    }

    //comment
    private void runContinue2(int selection) throws IOException {
        if (selection == 5) {
            fileNameEntrySD();
            System.exit(0);
        } else if (selection == 6) {
            enterUrgentItem();
        } else if (selection == 7) {
            removeUrgentItem();
        } else if (selection == 8) {
            getItemInfo();
        }
    }

    private void getItemInfo() {
        System.out.println("Please enter the task name:");
        scanner.nextLine();
        String itemName = scanner.nextLine();
        Item task = lists.getItem(itemName);
        System.out.println("Name: " + task.getName());
        if (task.getStatus()) {
            System.out.println("Status: Complete");
        } else {
            System.out.println("Status: Not Complete");
        }
        System.out.println("Type: " + task.getType());

    }

    private void enterUrgentItem() {
        System.out.println("Please enter the task name:");
        scanner.nextLine();
        String urgentTask = scanner.nextLine();
        UrgentItem urgentItem = new UrgentItem(urgentTask);
        priorityList.addUrgentItem(urgentItem);

        System.out.println("Here is your updated Priority List:");
        priorityListPrinter();

    }

    private void removeUrgentItem() {
        priorityListPrinter();
        System.out.println("Please enter the task name:");
        scanner.nextLine();
        String urgentTask = scanner.nextLine();
        UrgentItem urgentItem = new UrgentItem(urgentTask);

        priorityList.removeUrgentItem(urgentItem);
        System.out.println("Here is your updated Priority List:");
        priorityListPrinter();
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
                + "[2] cross off an item [3] show all items [4] load data [5] save and quit"
                + " [6] add to priority list [7] remove from priority list [8] get task info.");
        int selection = scanner.nextInt();
        System.out.println("You selected: " + selection);
        return selection;
    }

    // MODIFIES: this, Item
    // EFFECTS: user inputs new item. New Item is constructed and added to masterlist.
    private void enterItem() throws TooManyThingsToDoException {
        int selection = taskSelection();

        if (selection == 1) {
            useScanner(selection);
        } else if (selection == 2) {
            useScanner(selection);
        } else if (selection == 3) {
            useScanner(selection);
        }

    }

    public void useScanner(int selection) throws TooManyThingsToDoException {
        scanner.nextLine();
        String item = scanner.nextLine();
        lists.addItem(item,selection);
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
    private void removeItem() throws NothingToCrossOffException, NoSuchItemExistsException {
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
        for (int i = 0; i < lists.getMasterList().masterListSize();i++) {
            Item task = lists.getMasterList().getMasterList().get(i);
            if (task.getStatus() == false) {
                counter++;
                System.out.println(counter + ". " + task.getName());
            }
        }
    }

    // EFFECTS: prints out item in crossedOff list
    private void crossedOffPrinter() {
        System.out.println("The following items were crossed off:");
        for (int i = 0; i < lists.getCrossedOff().crossedOffSize(); i++) {
            System.out.println(lists.getCrossedOff().getCrossedOff().get(i).getName());
        }
    }

    // EFFECTS: prints out all items in masterList with their status'
    private void masterListPrinter() {
        for (int i = 0; i < lists.getMasterList().getMasterList().size(); i++) {
            Item task = lists.getMasterList().getMasterList().get(i);
            taskPrinter(task,i);
        }
    }

    private void priorityListPrinter() {
        for (UrgentItem ui: priorityList.getUrgentItems()) {
            System.out.println(ui.getName());
        }
    }

    private void taskPrinter(Item task, int i) {
        System.out.printf("%d. %-20s %-30s %-20s%n", i + 1, task.getName(),
                task.getStatus() ? "Status: Complete" : "Status: Not Complete", "Type: " + task.getType());
    }

}
