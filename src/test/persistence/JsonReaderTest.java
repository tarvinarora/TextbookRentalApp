package persistence;

import model.Textbook;
import model.Buyer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Buyer b = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWishlist() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWishlist.json");
        try {
            Buyer b = reader.read();
            assertEquals("Buyer Name", b.getBuyerName());
            assertEquals(0, b.getWishlist().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWishlist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWishlist.json");
        try {
            Buyer b = reader.read();
            assertEquals("Buyer Name", b.getBuyerName());
            List<Textbook> wishlisted = b.getWishlist();
            assertEquals(2, wishlisted.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
