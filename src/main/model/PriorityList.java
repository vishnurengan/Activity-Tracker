package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PriorityList {
    private List<UrgentItem> urgentItems;

    public PriorityList() {
        this.urgentItems = new ArrayList<>();
    }


    public void addUrgentItem(UrgentItem urgentItem) {

        if (!urgentItems.contains(urgentItem)) {
            urgentItems.add(urgentItem);
            urgentItem.setPriorityList(this);
        }
    }

    public void removeUrgentItem(UrgentItem urgentItem) {
        if (urgentItems.contains(urgentItem)) {
            urgentItems.remove(urgentItem);
            urgentItem.removePriorityList(this);
        }
    }

    public List<UrgentItem> getUrgentItems() {
        return urgentItems;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PriorityList that = (PriorityList) o;

        return urgentItems != null ? urgentItems.equals(that.urgentItems) : that.urgentItems == null;
    }

    @Override
    public int hashCode() {
        return urgentItems != null ? urgentItems.hashCode() : 0;
    }
}
