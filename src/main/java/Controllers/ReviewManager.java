package Controllers;

import Entities.Review;
import UseCaseClasses.InformationSaver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class ReviewManager {

    private HashMap<String, Review> reviews;

    public ReviewManager() {
        reviews = new HashMap<>();
    }

    /**
     *
     * @param Location The location that the review is for.
     * @param reviewBody The body of the review.
     * @param rating The score out of 5 given by the reviewer.
     * @return true if the review was successfully created and added
     */

    public boolean createReview(String Location, String reviewBody, Integer rating)
            throws IOException, ExecutionException, InterruptedException, IllegalArgumentException{

        InformationSaver saver = new InformationSaver();
        boolean result = false;
        if((rating > 5) || (rating < 0)){
            result = false;
        }
        else {
            Review review = new Review(Location, reviewBody, rating);
            reviews.put(Location, review); // adds the review to the hashmap of reviews
            saver.saveReview(reviews.get(Location)); // adds the review to the firestore database
            result = true;
        }
        return result;
    }


    /**
     * Retrieves all users from database and adds their names as keys in users, and the Entities.Person object as values.
     */

    public void populateReviews() throws ExecutionException, InterruptedException, IOException, IllegalArgumentException {
        InformationSaver saver = new InformationSaver();

        ArrayList<Review> allReviews = saver.retrieveReviews();
        for (Review a : allReviews) {
            this.reviews.put(a.getLocation(), a);
        }
    }

    public HashMap<String, Review> getReviews() {
        return reviews;
    }
}