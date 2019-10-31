package model;

import java.util.Objects;

public class UrgentItem {
    private String name;
    private String type;

    private PriorityList priorityList;

    public UrgentItem(String itemName) {
        name = itemName;
        this.type = "Urgent";
        priorityList = new PriorityList();
    }

//    public void addToPriorityList ( PriorityList priorityList) {
//        if (!pl.urgentItems.contains(this)){
//            pl.urgentItems.add(this);
//            priorityList.addUrgentItem(this);
//        }
//    }

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

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//
//        UrgentItem that = (UrgentItem) o;
//
//        if (!name.equals(that.name)) {
//            return false;
//        }
//        return type.equals(that.type);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = name.hashCode();
//        result = 31 * result + type.hashCode();
//        return result;
//    }
}