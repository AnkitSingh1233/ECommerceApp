package in.nareshit.ankit.service;

import java.util.List;

import in.nareshit.ankit.entity.Ratings;
import in.nareshit.ankit.model.RatingsDto;

public interface RatingService {
	

	public Ratings createRatingRivews(RatingsDto ratingsDto);

	public List<Ratings> getByAllReview();


}
