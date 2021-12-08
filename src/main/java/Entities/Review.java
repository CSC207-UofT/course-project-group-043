package Entities;

import java.util.ArrayList;

public class Review {

    // need to add a location variable in Event maybe? Otherwise, I'm not sure where reviews should go

    private String Location;
    private String reviewBody;
    private Integer rating;

    public Review() {
    }

    public Review(String Location, String reviewBody, Integer rating) throws IllegalArgumentException {
        this.Location = Location;
        this.reviewBody = reviewBody;
        if((rating > 5) || (rating < 0)){
            throw new IllegalArgumentException("Rating "+ rating +" is not valid, rating must be between 0 and 5.");
        }
        this.rating = rating;
    }

    public void setLocation(String Location) { this.Location = Location; }

    public void setReviewBody(String reviewBody) { this.reviewBody = reviewBody; }

    public void setRating(Integer rating) throws IllegalArgumentException {
        if((rating > 5) || (rating < 0)){
            throw new IllegalArgumentException("Rating "+ rating +" is not valid, rating must be between 0 and 5.");
        }
        this.rating = rating; }

    public String getLocation() { return this.Location; }

    public String getReviewBody() { return this.reviewBody; }

    public Integer getRating() { return this. rating; }

    public String getFullReview() { return (Location + ", " + rating + "/5." + "  " + reviewBody); }
}