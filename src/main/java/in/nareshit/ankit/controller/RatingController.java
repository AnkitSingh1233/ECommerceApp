package in.nareshit.ankit.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.nareshit.ankit.entity.Ratings;
import in.nareshit.ankit.model.RatingsDto;
import in.nareshit.ankit.model.ResponseMessage;
import in.nareshit.ankit.service.RatingService;
import in.nareshit.ankit.util.Constants;

@RestController
@RequestMapping("/api")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping("/ratingbooks")
	public ResponseEntity<ResponseMessage> rateBooks(@RequestBody RatingsDto ratingsDto) {
		try {
		Ratings ratingRivews = ratingService.createRatingRivews(ratingsDto);
		if(ratingRivews!=null) {
	       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "rating save successfully", ratingRivews));
		} else {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "rating  save Failed", ratingRivews));
 
	       }

		 }catch (Exception e) {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

	
	
	      @GetMapping("/getAllreviews")
 		public ResponseEntity<ResponseMessage> getAllreview() {
 		   
 			List<Ratings> byAllReview = ratingService.getByAllReview();
 			if(byAllReview!=null) {
 		       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "custmer  getting all reviews successfully", byAllReview));
 			
 			 }else {
 				 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "custmer  getting all reviews Failed", byAllReview));

 			 }
 		}  

}
