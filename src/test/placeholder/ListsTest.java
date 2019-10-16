package placeholder;

import exceptions.ListException;
import exceptions.NothingToCrossOffException;
import exceptions.TooManyThingsToDoException;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ListsTest {
    private Lists lists;

    @BeforeEach
    public void runBefore() {
        lists = new Lists();
    }

    @Test
    public void testGetMasterList() {
        Item task1 = new PersonalItem("Buy Apples");
        try {
            lists.addItem(task1);
        } catch (ListException e) {
            fail();
        }

        Item task2 = new PersonalItem("Buy Kiwis");
        try {
            lists.addItem(task2);
        } catch (ListException e) {
            fail();
        }

        Item task3 = new SchoolItem("Buy Supplies");
        try {
            lists.addItem(task3);
        } catch (ListException e) {
            fail();
        }

        ArrayList<Item> listsTest = new ArrayList<>();
        listsTest.add(task1);
        listsTest.add(task2);
        listsTest.add(task3);

        assertEquals(listsTest, lists.getMasterList());
    }

    @Test
    public void testGetCrossedOff() {
        Item task1 = new PersonalItem("Buy Apples");
        try {
            lists.addItem(task1);
        } catch (ListException e) {
            fail();
        }

        Item task2 = new PersonalItem("Buy Kiwis");
        try {
            lists.addItem(task2);
        } catch (ListException e) {
            fail();
        }

        Item task3 = new SchoolItem("Buy Supplies");
        try {
            lists.addItem(task3);
        } catch (ListException e) {
            fail();
        }

        try {
            lists.crossOff(1);
        } catch (ListException e) {
            fail();
        }
        try {
            lists.crossOff(1);
        } catch (ListException e) {
            fail();
        }
        try {
            lists.crossOff(1);
        } catch (ListException e) {
            fail();
        }

        ArrayList<Item> listsTest = new ArrayList<>();
        listsTest.add(task1);
        listsTest.add(task2);
        listsTest.add(task3);

        assertEquals(listsTest, lists.getCrossedOff());
    }

    @Test
    public void testMasterListContainsOne() {
        Item task = new PersonalItem("Buy Apples");
        try {
            lists.addItem(task);
        } catch (ListException e) {
            fail();
        }
        assertTrue(lists.masterListContains("Buy Apples"));
        assertFalse(lists.masterListContains("Buy Kiwis"));

    }

    @Test
    public void testMasterListContainsMany() {

        Item task1 = new PersonalItem("Buy Apples");
        try {
            lists.addItem(task1);
        } catch (ListException e) {
            fail();
        }

        Item task2 = new PersonalItem("Buy Kiwis");
        try {
            lists.addItem(task2);
        } catch (ListException e) {
            fail();
        }

        Item task3 = new SchoolItem("Buy Supplies");
        try {
            lists.addItem(task3);
        } catch (ListException e) {
            fail();
        }

//        lists.addItem(task1);
//        lists.addItem(task2);
//        lists.addItem(task3);

        assertTrue(lists.masterListContains("Buy Apples"));
        assertTrue(lists.masterListContains("Buy Kiwis"));
        assertTrue(lists.masterListContains("Buy Supplies"));
    }

    @Test
    public void testCrossedOffListContainsOnce() {
        Item task1 = new PersonalItem("Buy Apples");
        try {
            lists.addItem(task1);
        } catch (ListException e) {
            fail();
        }

        Item task2 = new PersonalItem("Buy Kiwis");
        try {
            lists.addItem(task2);
        } catch (ListException e) {
            fail();
        }

        Item task3 = new SchoolItem("Buy Supplies");
        try {
            lists.addItem(task3);
        } catch (ListException e) {
            fail();
        }

//        lists.addItem(task1);
//        lists.addItem(task2);
//        lists.addItem(task3);

        try {
            lists.crossOff(1);
        } catch (ListException e) {
            fail();
        }
        assertTrue(lists.crossedOffListContains("Buy Apples"));
    }

    @Test
    public void testCrossedOffListContainsMultiple() {
        Item task1 = new PersonalItem("Buy Apples");
        try {
            lists.addItem(task1);
        } catch (ListException e) {
            fail();
        }

        Item task2 = new PersonalItem("Buy Kiwis");
        try {
            lists.addItem(task2);
        } catch (ListException e) {
            fail();
        }

        Item task3 = new SchoolItem("Buy Supplies");
        try {
            lists.addItem(task3);
        } catch (ListException e) {
            fail();
        }

        try {
            lists.crossOff(1); // Apples removed off toDoList
        } catch (ListException e) {
            fail();
        }
        try {
            lists.crossOff(1); // Buy Kiwis is now user selection 1, Buy Bananas is user selection 2
        } catch (ListException e) {
            fail();
        }
        assertTrue(lists.crossedOffListContains("Buy Apples"));
        assertTrue(lists.crossedOffListContains("Buy Kiwis"));
        assertFalse(lists.crossedOffListContains("Buy Supplies"));
    }

    @Test
    public void testMasterListStatus() {
        Item task1 = new PersonalItem("Buy Apples");
        try {
            lists.addItem(task1);
        } catch (ListException e) {
            fail();
        }

        Item task2 = new PersonalItem("Buy Kiwis");
        try {
            lists.addItem(task2);
        } catch (ListException e) {
            fail();
        }

        Item task3 = new SchoolItem("Buy Supplies");
        try {
            lists.addItem(task3);
        } catch (ListException e) {
            fail();
        }

        try {
            lists.crossOff(1); // Apples removed off toDoList
        } catch (ListException e) {
            fail();
        }
        try {
            lists.crossOff(2); // Buy Kiwis is now user selection 1, Buy Bananas is user selection 2
        } catch (ListException e) {
            fail();
        }

        assertTrue(lists.masterListStatus("Buy Apples"));
        assertFalse(lists.masterListStatus("Buy Kiwis"));
        assertTrue(lists.masterListStatus("Buy Supplies"));

        assertFalse(lists.masterListStatus("Buy Cherries"));
    }

    @Test
    public void testAddOneItem() {
        Item task3 = new SchoolItem("Buy Supplies");
        try {
            lists.addItem(task3);
        } catch (ListException e) {
            fail();
        }

        assertTrue(lists.masterListContains("Buy Supplies"));
    }

    @Test
    public void testAddMultipleItems() {
        Item task1 = new PersonalItem("Buy Apples");
        try {
            lists.addItem(task1);
        } catch (ListException e) {
            fail();
        }

        Item task2 = new PersonalItem("Buy Kiwis");
        try {
            lists.addItem(task2);
        } catch (ListException e) {
            fail();
        }

        Item task3 = new SchoolItem("Buy Supplies");
        try {
            lists.addItem(task3);
        } catch (ListException e) {
            fail();
        }


        assertTrue(lists.masterListContains("Buy Apples"));
        assertTrue(lists.masterListContains("Buy Kiwis"));
        assertTrue(lists.masterListContains("Buy Supplies"));
        assertFalse(lists.masterListContains("Buy Cherries"));
    }

    @Test
    public void testCrossOff() {
        Item task1 = new PersonalItem("Buy Apples");
        try {
            lists.addItem(task1);
        } catch (ListException e) {
            fail();
        }

        Item task2 = new PersonalItem("Buy Kiwis");
        try {
            lists.addItem(task2);
        } catch (ListException e) {
            fail();
        }

        Item task3 = new SchoolItem("Buy Supplies");
        try {
            lists.addItem(task3);
        } catch (ListException e) {
            fail();
        }

        try {
            lists.crossOff(1);
        } catch (ListException e) {
            fail();
        }
        assertTrue(lists.crossedOffListContains("Buy Apples"));
        assertTrue(lists.masterListStatus("Buy Apples"));

        assertFalse(lists.crossedOffListContains("Buy Kiwis"));
        assertFalse(lists.masterListStatus("Buy Kiwis"));

    }
    @Test
    public void testLoadData() throws IOException {
        lists.loadData("loadTestFile.txt");
        assertTrue(lists.masterListContains("Buy Apples"));
        assertTrue(lists.masterListContains("Study"));
        assertTrue(lists.masterListContains("Go to Gym"));
        assertTrue(lists.masterListContains("Buy Supplies"));
        assertTrue(lists.masterListContains("Set Meeting"));
        assertTrue(lists.masterListContains("Complete Project"));

        assertTrue(lists.masterListStatus("Buy Apples"));
        assertFalse(lists.masterListStatus("Go to Gym"));
        assertTrue(lists.masterListStatus("Buy Supplies"));
        assertFalse(lists.masterListStatus("Study"));
        assertTrue(lists.masterListStatus("Set Meeting"));
        assertFalse(lists.masterListStatus("Complete Project"));


        assertTrue(lists.crossedOffListContains("Set Meeting"));
        assertTrue(lists.crossedOffListContains("Buy Apples"));
        assertFalse(lists.crossedOffListContains("Go to Gym"));
    }

    @Test
    public void testSaveData() throws IOException {
        Item task1 = new PersonalItem("Buy Kiwis");
        try {
            lists.addItem(task1);
        } catch (ListException e) {
            fail();
        }

        Item task2 = new PersonalItem("Buy Grapes");
        try {
            lists.addItem(task2);
        } catch (ListException e) {
            fail();
        }


//        lists.addItem(task1);
//        lists.addItem(task2);
        try {
            lists.crossOff(1); // Crossing of Buy Kiwis
        } catch (ListException e) {
            fail();
        }

        saveDataTester(lists);

        lists.loadData("saveDataTestFile.txt");
        assertTrue(lists.masterListContains("Buy Kiwis"));
        assertTrue(lists.masterListContains("Buy Grapes"));
        assertFalse(lists.masterListContains("Buy Apples"));
        assertTrue(lists.masterListStatus("Buy Kiwis"));
        assertFalse(lists.masterListStatus("Buy Grapes"));

    }

    public void saveDataTester(Saveable saveable) throws IOException {
        saveable.saveData("saveDataTestFile.txt");
    }

    @Test
    public void testAddItem() {
        try {
            lists.addItem("Go to Gym",1);
        } catch (ListException e) {
            fail();
        }
        try {
            lists.addItem("Study",2);
        } catch (ListException e) {
            fail();
        }
        try {
            lists.addItem("Complete Project", 3);
        } catch (ListException e) {
            fail();
        }

        assertTrue(lists.masterListContains("Go to Gym"));
        assertTrue(lists.masterListContains("Study"));
        assertTrue(lists.masterListContains("Complete Project"));
    }

}
