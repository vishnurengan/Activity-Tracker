package model;

public class SchoolItem extends Item {

    public SchoolItem(String itemName) {
        super(itemName);
        type = "School Task";
    }

    @Override
    public void printMessage() {
        System.out.println("Perfect! Your school task was added to the list");
    }
    




}
