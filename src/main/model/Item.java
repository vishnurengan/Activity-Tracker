package model;

public abstract class Item {
    protected String name;
    protected boolean isComplete;
    protected String type;

    // EFFECTS: constructs new Item object with name, status, and type
    public Item(String itemName) {
        name = itemName;
        isComplete = false;
        type = "";
    }


    // MODIFIES: this
    // EFFECTS: sets status of task
    public void setStatus(boolean status) {
        if (status == true) {
            isComplete = true;
        } else {
            isComplete = false;
        }
    }

    // EFFECTS: returns status of task
    public boolean getStatus() {
        return isComplete;
    }

    // EFFECTS: returns name of task
    public String getName() {
        return name;
    }

    // EFFECTS: returns type of task
    public String getType() {
        return type;
    }

    // EFFECTS: prints message to console
    public abstract void printMessage();
}
