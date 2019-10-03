package model;

public class SchoolItem implements Item {
    private String name;
    private boolean isComplete;
    private String type;

    // EFFECTS: sets name and status
    public SchoolItem(String itemName) {
        name = itemName;
        isComplete = false;
        type = "School Task";

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

    public String getType() {
        return type;
    }
}
