import Entities.Review;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ReviewTest {

    @Test(timeout = 100)
    public void Test1() {
        Review review = new Review("Bahen Centre", "Great place for studying, and meeting new people.", 4);
        String actual = review.printReview();
        String expected = "Bahen Centre, 4/5.  Great place for studying, and meeting new people.";
        assertEquals(expected, actual);
    }

    @Test(timeout = 100)
    public void TestInvalidRating() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class, () -> {
                    Review review = new Review("Bahen Centre", "Great place for studying, and meeting new people.", 6);
                });
        assertEquals("Rating 6 is not valid, rating must be between 0 and 5.", exception.getMessage());
    }
}