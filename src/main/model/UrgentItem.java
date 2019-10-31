package model;

import java.util.Objects;

//Followed C7 Practice Problem implementation of Orchestra - Brass Intstrument Relation
public class UrgentItem {
    private String name;
    private String type;

    private PriorityList priorityList;

    public UrgentItem(String itemName) {
        name = itemName;
        this.type = "Urgent";
        priorityList = new PriorityList();
    }


    public void setPriorityList(PriorityList priorityList) {
        if (!this.priorityList.equals(priorityList)) {
            this.priorityList = priorityList;
            priorityList.addUrgentItem(this);
        }
    }

    public void removePriorityList(PriorityList priorityList) {
        if (this.priorityList.equals(priorityList)) {
            this.priorityList = null;
            priorityList.removeUrgentItem(this);
        }
    }

    public PriorityList getPriorityList() {
        return priorityList;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UrgentItem that = (UrgentItem) o;

        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}