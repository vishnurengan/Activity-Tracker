package model;

public abstract class Item {
    protected String name;
    protected boolean isComplete;
    protected String type;

    public Item(String itemName) {
        name = itemName;
        isComplete = false;
        type = "";
    }


    public void setStatus(boolean status) {
        if (status == true) {
            isComplete = true;
        } else {
            isComplete = false;
        }
    }

    public boolean getStatus() {
        return isComplete;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract void printMessage();
}
