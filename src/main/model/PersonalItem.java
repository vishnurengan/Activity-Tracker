package model;

public class PersonalItem extends Item {
    public PersonalItem(String itemName) {
        super(itemName);
        type = "Personal Task";
    }

    // EFFECTS: prints message onto console
    @Override
    public void printMessage() {
        System.out.println("Perfect! You're personal task was added to the list");
    }

}
