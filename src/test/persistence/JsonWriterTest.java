package persistence;

import model.Textbook;
import model.Buyer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Buyer b = new Buyer("Buyer Name");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWishlist() {
        try {
            Buyer b = new Buyer("Buyer Name");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWishlist.json");
            writer.open();
            writer.write(b);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWishlist.json");
            b = reader.read();
            assertEquals("Buyer Name", b.getBuyerName());
            assertEquals(0, b.getWishlist().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWishlist() {
        try {
            Buyer b = new Buyer("Buyer Name");
            b.addToWishlist(new Textbook("Title 1", "Author 1", "Subject 1", "Price 1", "Condition 1"));
            b.addToWishlist(new Textbook("Title 2", "Author 2", "Subject 2", "Price 2", "Condition 2"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWishlist.json");
            writer.open();
            writer.write(b);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWishlist.json");
            b = reader.read();
            assertEquals("Buyer Name", b.getBuyerName());
            List<Textbook> wishlisted = b.getWishlist();
            assertEquals(2, wishlisted.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
