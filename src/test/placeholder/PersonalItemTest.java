package placeholder;

import model.Item;
import model.PersonalItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalItemTest {
    private PersonalItem personalItem;

    @BeforeEach
    public void runBefore() {
        personalItem = new PersonalItem("Buy Apples");
    }

    @Test
    public void testConstructor() {
        assertEquals(personalItem.getName(),"Buy Apples");
        assertFalse(personalItem.getStatus());
        assertEquals(personalItem.getType(),"Personal Task");
    }

    @Test
    public void testSetStatus() {
        personalItem.setStatus(true);
        assertTrue(personalItem.getStatus());

        personalItem.setStatus(false);
        assertFalse(personalItem.getStatus());
    }

    @Test
    public void testGetStatus() {
        assertFalse(personalItem.getStatus());
    }

    @Test
    public void testGetName() {
        assertEquals(personalItem.getName(), "Buy Apples");
    }

    @Test
    public void testGetType() {
        assertEquals(personalItem.getType(), "Personal Task");
    }
}
