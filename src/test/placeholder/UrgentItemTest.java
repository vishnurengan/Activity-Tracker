//package placeholder;
//
//import model.PriorityList;
//import model.UrgentItem;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UrgentItemTest {
//    UrgentItem urgentItem;
//    PriorityList priorityList;
//
//    @BeforeEach
//    void runBefore(){
//        priorityList = new PriorityList();
//        urgentItem = new UrgentItem("Study");
//    }
//
//    @Test
//    void testConstructor() {
//        assertEquals("Study", urgentItem.getName());
//        assertEquals("Urgent", urgentItem.getType());
//        assertFalse(urgentItem.getPriorityList()==null);
//        assertFalse(urgentItem.getPriorityList() == priorityList);
//    }
//
//    @Test
//    void testAddUrgentItem() {
//        priorityList.addUrgentItem(urgentItem);
//        assertTrue(urgentItem.getPriorityList()==priorityList);
//        assertTrue(priorityList.getUrgentItems().contains(urgentItem));
//    }
//
//    @Test
//    void testRemoveUrgentItem() {
//        priorityList.addUrgentItem(urgentItem);
//        assertTrue(urgentItem.getPriorityList()==priorityList);
//        assertTrue(priorityList.getUrgentItems().contains(urgentItem));
//
//        priorityList.removeUrgentItem(urgentItem);
//        assertTrue(urgentItem.getPriorityList()==null);
//        assertFalse(priorityList.getUrgentItems().contains(urgentItem));
//    }
//
//    @Test
//    void testEqualsOverride() {
//        priorityList.addUrgentItem(urgentItem);
//        assertTrue(priorityList.getUrgentItems().contains(urgentItem));
//
//        UrgentItem urgentItemTest = new UrgentItem("Study");
//        assertTrue(priorityList.getUrgentItems().contains(urgentItemTest));
//
//        UrgentItem urgentItemTest2 = new UrgentItem("study");
//        assertFalse(priorityList.getUrgentItems().contains(urgentItemTest2));
//
//    }
//
//
//}
