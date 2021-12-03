package UseCaseClasses;

import Entities.Review;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class ReviewCreator {

    /**
     * Create a new review for a Entities.Review.
     *
     * @param Location The location that the review is for.
     * @param reviewBody The body of the review.
     * @param rating The score out of 5 given by the reviewer.
     * @param data HashMap containing all reviews created thus far, with associated Entities.Review
     * @return true if the review was successfully created and added
     */

    public boolean createReview(String Location, String reviewBody, Integer rating, ReviewList data)
            throws ExecutionException, InterruptedException{

        boolean check = isValidLocation(Location, data);
        Review newReview = new Review();

        if (check) {
            newReview.setLocation(Location);
            newReview.setReviewBody(reviewBody);
            newReview.setRating(rating);
            data.addReview(Location, newReview);
        }

        return check;
    }

    private boolean isValidLocation(String Location, ReviewList data) {
        return !data.containsReview(Location);
    }

}
