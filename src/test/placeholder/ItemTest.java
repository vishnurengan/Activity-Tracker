package placeholder;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {
    private Item item;

    @BeforeEach
    public void runBefore() {
        item = new Item("Buy Apples");
    }

    @Test
    public void testItemConstructor() {
        assertEquals("Buy Apples", item.getName());
        assertFalse(item.getStatus());
    }

    @Test
    public void testSetStatus() {
        item.setStatus(true);
        assertTrue(item.getStatus());

        item.setStatus(false);
        assertFalse(item.getStatus());

    }

    @Test
    public void testGetStatus() {
        assertFalse(item.getStatus());
    }

    @Test
    public void testGetName() {
        assertEquals("Buy Apples", item.getName());
    }



}
