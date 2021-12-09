package UseCaseClasses;

import Entities.Review;

import java.util.HashMap;

/**
 * A class for storing all the Reviews in the system.
 */
public class ReviewList {

    private HashMap<String, Review> reviews;

    public HashMap<String, Review> getReviews() {
        return reviews;
    }

    public void addReview(String Location, Review review) {
        reviews.put(Location, review);
    }

    public Review getReview(String Location) {
        return reviews.get(Location);
    }

    public boolean containsReview(String Location) {
        return reviews.containsKey(Location);
    }

}