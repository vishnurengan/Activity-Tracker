package placeholder;

import model.WorkItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkItemTest {
    private WorkItem workItem;

    @BeforeEach
    public void runBefore() {
        workItem = new WorkItem("Complete Project");
    }

    @Test
    public void testConstructor() {
        assertEquals(workItem.getName(),"Complete Project");
        assertFalse(workItem.getStatus());
        assertEquals(workItem.getType(),"Work Task");
    }

    @Test
    public void testSetStatus() {
        workItem.setStatus(true);
        assertTrue(workItem.getStatus());

        workItem.setStatus(false);
        assertFalse(workItem.getStatus());
    }

    @Test
    public void testGetStatus() {
        assertFalse(workItem.getStatus());
    }

    @Test
    public void testGetName() {
        assertEquals(workItem.getName(), "Complete Project");
    }

    @Test
    public void testGetType() {
        assertEquals(workItem.getType(), "Work Task");
    }
}
