package model;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class PersonalItem extends Item {
    public PersonalItem(String itemName) {
        super(itemName);
        type = "Personal Task";
    }
//    private String name;
//    private boolean isComplete;
//    private String type;
//
//    // EFFECTS: sets name and status
//    public PersonalItem(String itemName) {
//        name = itemName;
//        isComplete = false;
//        type = "Personal Task";
//    }
//
//    // MODIFIES: this
//    // EFFECTS: sets status
//    public void setStatus(boolean status) {
//        if (status == true) {
//            isComplete = true;
//        } else {
//            isComplete = false;
//        }
//    }
//
//    // EFFECTS: returns status of  item
//    public boolean getStatus() {
//        return isComplete;
//    }
//
//    // EFFECTS: returns name of item
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public String getType() {
//        return type;
//    }
}
