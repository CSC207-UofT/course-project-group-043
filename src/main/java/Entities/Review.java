package Entities;

public class Review {

    // need to add a location variable in Entities.Event maybe? Otherwise, I'm not sure where reviews should go

    private String Location;
    private String review;
    private Integer rating;

    public Review(String Location, String review, Integer rating){
        this.Location = Location;
        this.review = review;
        if((rating > 5) || (rating < 0)){
            throw new IllegalArgumentException("Rating "+ rating +" is not valid, rating must be between 0 and 5.");
        }
        this.rating = rating;
    }

    public void printReview(){
        System.out.println(Location + ", " + rating + "/5");
        System.out.println(review);
    }
}
