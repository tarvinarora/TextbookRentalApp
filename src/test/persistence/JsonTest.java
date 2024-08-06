package persistence;

import model.Textbook;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTextbook(Textbook textbook, String title, String author, String subject, String rentalPrice,
            String condition) {
        assertEquals(title, textbook.getTitle());
        assertEquals(author, textbook.getAuthor());
        assertEquals(subject, textbook.getSubject());
        assertEquals(rentalPrice, textbook.getRentalPrice());
        assertEquals(condition, textbook.getCondition());
    }
}
