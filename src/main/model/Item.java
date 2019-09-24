package model;

public class Item {
    private String name;
    private boolean isComplete;

    // EFFECTS: sets name and status
    public Item(String itemName) {
        name = itemName;
        isComplete = false;
    }

    // MODIFIES: this
    // EFFECTS: sets status
    public void setStatus(boolean status) {
        if (status == true) {
            isComplete = true;
        } else {
            isComplete = false;
        }
    }

    // EFFECTS: returns status of  item
    public boolean getStatus() {
        return isComplete;
    }

    // EFFECTS: returns name of item
    public String getName() {
        return name;
    }
}
