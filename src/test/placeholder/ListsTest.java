package placeholder;

import model.Item;
import model.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListsTest {
    private Lists lists;

    @BeforeEach
    public void runBefore() {
        lists = new Lists();
    }

    @Test
    public void testMasterListContainsOne() {
       lists.addItem("Buy Apples");
       assertTrue(lists.masterListContains("Buy Apples"));
       assertFalse(lists.masterListContains("Buy Kiwis"));

    }

    @Test
    public void testMasterListContainsMany() {
        lists.addItem("Buy Apples");
        lists.addItem("Buy Kiwis");
        lists.addItem("Buy Bananas");

        assertTrue(lists.masterListContains("Buy Apples"));
        assertTrue(lists.masterListContains("Buy Kiwis"));
        assertTrue(lists.masterListContains("Buy Bananas"));
    }

    @Test
    public void testCrossedOffListContainsOnce() {
        lists.addItem("Buy Apples");
        lists.addItem("Buy Kiwis");
        lists.addItem("Buy Bananas");

        lists.crossOff(1);
        assertTrue(lists.crossedOffListContains("Buy Apples"));
    }

    @Test
    public void testCrossedOffListContainsMultiple() {
        lists.addItem("Buy Apples"); // User Selection 1
        lists.addItem("Buy Kiwis"); // User Selection 2
        lists.addItem("Buy Bananas"); // User Selection 3

        lists.crossOff(1); // Apples removed off toDoList
        lists.crossOff(1); // Buy Kiwis is now user selection 1, Buy Bananas is user selection 2
        assertTrue(lists.crossedOffListContains("Buy Apples"));
        assertTrue(lists.crossedOffListContains("Buy Kiwis"));
        assertFalse(lists.crossedOffListContains("Buy Bananas"));
    }

    @Test
    public void testMasterListStatus() {
        lists.addItem("Buy Apples"); // User Selection 1
        lists.addItem("Buy Kiwis"); // User Selection 2
        lists.addItem("Buy Bananas"); // User Selection 3

        lists.crossOff(1); // Apples removed off toDoList
        lists.crossOff(2); // Buy Kiwis is now user selection 1, Buy Bananas is user selection 2

        assertTrue(lists.masterListStatus("Buy Apples"));
        assertFalse(lists.masterListStatus("Buy Kiwis"));
        assertTrue(lists.masterListStatus("Buy Bananas"));
    }

    @Test
    public void testAddOneItem() {
        lists.addItem("Buy Apples");
        assertTrue(lists.masterListContains("Buy Apples"));
    }

    @Test
    public void testAddMultipleItems() {
        lists.addItem("Buy Bananas");
        lists.addItem("Buy Kiwis");
        lists.addItem("Buy Cherries");

        assertTrue(lists.masterListContains("Buy Bananas"));
        assertTrue(lists.masterListContains("Buy Kiwis"));
        assertTrue(lists.masterListContains("Buy Cherries"));
        assertFalse(lists.masterListContains("Buy Apples"));
    }

    @Test
    public void testCrossOff() {
        lists.addItem("Buy Bananas");
        lists.addItem("Buy Kiwis");
        lists.addItem("Buy Cherries");

        lists.crossOff(1);
        assertTrue(lists.crossedOffListContains("Buy Bananas"));
        assertTrue(lists.masterListStatus("Buy Bananas"));

        assertFalse(lists.crossedOffListContains("Buy Kiwis"));
        assertFalse(lists.masterListStatus("Buy Kiwis"));

    }
}
