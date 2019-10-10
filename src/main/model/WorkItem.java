package model;

public class WorkItem extends Item {

    public WorkItem(String itemName) {
        super(itemName);
        type = "Work Task";
    }

    @Override
    public void printMessage() {
        System.out.println("Perfect! Your work task was added to the list");
    }
}
