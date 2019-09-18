package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Lists {
    private ArrayList<Item> masterList;
    private ArrayList<Item> crossedOff;
    private Scanner scanner;

    public Lists() {
        masterList = new ArrayList<>();
        crossedOff = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public int userSelection() {
        System.out.println("Please enter what you would like to do: "
                + "[1] add a to do list item [2] cross off an item [3] show all items [4] quit the program.");
        int selection = scanner.nextInt();
        System.out.println("You selected: " + selection);
        return selection;
    }

    public void addItem(String task) {
        Item newTask = new Item(task);
        this.masterList.add(newTask);
    }

    public void crossOff(int number) {
        int counter = 0;
        for (int i = 0; i < this.masterList.size();i++) {
            Item task = this.masterList.get(i);
            if (task.isComplete == false) {
                counter++;
                if (counter == number) {
                    this.crossedOff.add(task);
                    task.isComplete = true;
                }
            }
        }
    }


    public void enterItem() {
        scanner.nextLine();
        System.out.println("Enter the item text");
        String item = scanner.nextLine();
        this.addItem(item);
    }

    public void removeItem() {
        this.toDoListPrinter();
        System.out.println("Which item would you like to cross off?");
        int selection = scanner.nextInt();
        this.crossOff(selection);
        System.out.println("Here is your updated to do list:");
        this.toDoListPrinter();

    }

    public void toDoListPrinter() {
        int counter = 0;
        for (int i = 0; i < this.masterList.size();i++) {
            Item task = this.masterList.get(i);
            if (task.isComplete == false) {
                counter++;
                System.out.println(counter + ". " + task.name);
            }
        }
    }

    public void crossedOffPrinter() {
        System.out.println("The following items were crossed off:");
        for (int i = 0; i < this.crossedOff.size(); i++) {
            System.out.println(this.crossedOff.get(i).name);
        }
    }

    public void masterListPrinter() {
        for (int i = 0; i < this.masterList.size(); i++) {
            Item task = masterList.get(i);
            if (task.isComplete == false) {
                System.out.printf("%d. %-20s %-30s%n", i + 1, task.name, "Status: Not Complete");
            } else if (task.isComplete == true) {
                System.out.printf("%d. %-20s %-30s%n", i + 1, task.name, "Status: Complete");
            }
        }
    }

    public static void main(String[] args) {
        Lists lists = new Lists();

        while (true) {
            int selection = lists.userSelection();
            if (selection == 1) {
                lists.enterItem();
            } else if (selection == 2) {
                lists.removeItem();
                lists.crossedOffPrinter();
            } else if (selection == 3) {
                lists.masterListPrinter();
            } else if (selection == 4) {
                break;
            }
        }

    }

}