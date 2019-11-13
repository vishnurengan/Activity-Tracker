package model;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<ItemObserver> itemObservers;

    public Subject() {
        itemObservers = new ArrayList<>();
    }

    public void addObserver(ItemObserver itemObserver) {
        itemObservers.add(itemObserver);
    }

    public void notify(Item item) {
        for (ItemObserver io : itemObservers) {
            io.update(item);
        }
    }
}
