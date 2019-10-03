package placeholder;

import model.Item;
import model.PersonalItem;
import model.SchoolItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchoolItemTest {
    private SchoolItem schoolItem;

    @BeforeEach
    public void runBefore() {
        schoolItem = new SchoolItem("Buy Supplies");
    }

    @Test
    public void testConstructor() {
        assertEquals(schoolItem.getName(),"Buy Supplies");
        assertFalse(schoolItem.getStatus());
        assertEquals(schoolItem.getType(),"School Task");
    }

    @Test
    public void testSetStatus() {
        schoolItem.setStatus(true);
        assertTrue(schoolItem.getStatus());

        schoolItem.setStatus(false);
        assertFalse(schoolItem.getStatus());
    }

    @Test
    public void testGetStatus() {
        assertFalse(schoolItem.getStatus());
    }

    @Test
    public void testGetName() {
        assertEquals(schoolItem.getName(), "Buy Supplies");
    }

    @Test
    public void testGetType() {
        assertEquals(schoolItem.getType(), "School Task");
    }
}
