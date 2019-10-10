package model;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class PersonalItem extends Item {
    public PersonalItem(String itemName) {
        super(itemName);
        type = "Personal Task";
    }

    @Override
    public void printMessage() {
        System.out.println("Perfect! You're personal task was added to the list");
    }

}
