package InterfaceAdapters;

import UseCaseClasses.ReviewCreator;
import UseCaseClasses.InformationSaver;
import UseCaseClasses.ReviewList;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ReviewManager {

    InformationSaver saver;
    ReviewCreator creator;
    ReviewList data;

    public ReviewManager() throws IOException {

    }

    /**
     * Calls on UseCaseClasses.ReviewCreator to create a new review.
     *
     * @param Location   The location that the review is for.
     * @param reviewBody The body of the review.
     * @param rating     The score out of 5 given by the reviewer.
     * @return true if the review was successfully created and added
     */

    public boolean createReview(String Location, String reviewBody, Integer rating)
            throws ExecutionException, InterruptedException, IllegalArgumentException {


        boolean result = creator.createReview(Location, reviewBody, rating, data);
        if (result) {
            saver.saveReview(data.getReview(Location)); // adds the user to the firestore database
        }
        return result;
    }
}