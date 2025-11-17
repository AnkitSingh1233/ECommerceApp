package in.nareshit.ankit.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.ankit.entity.Book;
import in.nareshit.ankit.entity.Customer;
import in.nareshit.ankit.entity.Ratings;
import in.nareshit.ankit.exception.BookNotFoundException;
import in.nareshit.ankit.exception.CustomerIdNotFoundException;
import in.nareshit.ankit.exception.RatingsRepo;
import in.nareshit.ankit.model.RatingsDto;
import in.nareshit.ankit.repository.BookRepo;
import in.nareshit.ankit.repository.CustomerRepo;
import in.nareshit.ankit.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {
	

	@Autowired 
	private  CustomerRepo custmerRepoo;
	@Autowired 
	private BookRepo booksModuleRepo;
	@Autowired 
	private RatingsRepo ratingsRepo;

	@Override
	public Ratings createRatingRivews(RatingsDto ratingsDto) {
		
		 //  Step 1: Check if customer exists in DB using ID
	    // If not found, throw custom exception "Customer Id Not Found"
	    Customer customer = custmerRepoo.findById(ratingsDto.getCusmerId())
	        .orElseThrow(() -> new CustomerIdNotFoundException("Custmer Id Not found"));
	    
	    
	    //  Step 2: Check if book exists in DB using ID
	    // If not found, throw custom exception "Book Id not Found"
	    Book booksModule = booksModuleRepo.findById(ratingsDto.getBookId())
	        .orElseThrow(() -> new BookNotFoundException("Book Id not Found"));
	    
	    Ratings rrr = new Ratings();
	    rrr.setBooksModule(booksModule);
	    rrr.setCustomer(customer);
	    rrr.setRate(ratingsDto.getRate());
	    rrr.setReviewText(ratingsDto.getReviewText());
	    ratingsRepo.save(rrr);
		return rrr;
	}

	@Override
	public List<Ratings> getByAllReview() {
		
		return ratingsRepo.findAll();
	}
	

}
