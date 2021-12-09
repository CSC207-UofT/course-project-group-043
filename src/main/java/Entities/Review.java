package Entities;

/**
 * Represents a review of a particular location
 */
public class Review {

    private String location;
    private String reviewBody;
    private Integer rating;

    /**
     * Create a new Review.
     *
     * @param location The name of the location being reviewed
     * @param reviewBody The comment left by the user creating the review
     * @param rating The rating of the location, out of five
     * @throws IllegalArgumentException
     */
    public Review(String location, String reviewBody, Integer rating) throws IllegalArgumentException {
        this.location = location;
        this.reviewBody = reviewBody;
        if((rating > 5) || (rating < 0)){
            throw new IllegalArgumentException("Rating "+ rating +" is not valid, rating must be between 0 and 5.");
        }
        this.rating = rating;
    }

    public Review() {
        // setting some default values
        location = "";
        reviewBody = "";
        rating = 0;
    }

    public void setLocation(String Location) { this.location = Location; }

    public void setReviewBody(String reviewBody) { this.reviewBody = reviewBody; }

    public void setRating(Integer rating) throws IllegalArgumentException {
        if((rating > 5) || (rating < 0)){
            throw new IllegalArgumentException("Rating "+ rating +" is not valid, rating must be between 0 and 5.");
        }
        this.rating = rating; }

    public String getLocation() { return this.location; }

    public String getReviewBody() { return this.reviewBody; }

    public Integer getRating() { return this. rating; }

    /**
     * Create and return a String with all of the information stored in this Review.
     *
     * @return a String with the location being reviewed, the rating, and the review itself
     */
    public String getFullReview() { return (location + ", " + rating + "/5." + "  " + reviewBody); }
}