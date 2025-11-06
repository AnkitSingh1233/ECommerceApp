package in.nareshit.ankit.serviceImpl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.ankit.entity.Book;
import in.nareshit.ankit.entity.CartModule;
import in.nareshit.ankit.entity.Customer;
import in.nareshit.ankit.exception.BookNotFoundException;
import in.nareshit.ankit.exception.CardNotPresentException;
import in.nareshit.ankit.exception.CustomerIdNotFoundException;
import in.nareshit.ankit.repository.BookRepo;
import in.nareshit.ankit.repository.CartModuleRepository;
import in.nareshit.ankit.repository.CustomerRepo;
import in.nareshit.ankit.service.CartModuleService;
import io.swagger.v3.oas.annotations.servers.Server;

@Service
public class CartModuleServiceImpl  implements CartModuleService{

	@Autowired
	private CustomerRepo  customerRepo;
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private CartModuleRepository cartModuleRepo;
	
	
	@Override
	public CartModule addToCartBooks(Long custmerId, Long bookId, int quantity) {
		// TODO Auto-generated method stub
		
		   Customer customer = customerRepo.findById(custmerId)
			        .orElseThrow(()-> new CustomerIdNotFoundException("Custmer Id Not found"));
		

		    Book booksModule = bookRepo.findById(bookId)
		        .orElseThrow(()->new BookNotFoundException("Book Id not Found"));
		    
		    CartModule cartItem = cartModuleRepo.findByCustomerAndBooksModule(customer, booksModule);
		    
		    if(cartItem!=null) {
		    	 cartItem.setQuantity(cartItem.getQuantity() + quantity);
		    }
		    else {
		    	  cartItem = new CartModule(quantity, booksModule, customer);
		    }
		    
		    cartItem.setTotalPrice(cartItem.getQuantity() * booksModule.getPrice());
		    
		     return cartModuleRepo.save(cartItem);
	}
	@Override
	public List<CartModule> getAllCartDetail() {
		// TODO Auto-generated method stub
		return cartModuleRepo.findAll();
		
	}
	
	@Override
	public CartModule geCartById(Long id) {
	 
		Optional<CartModule> findById = cartModuleRepo.findById(id);
		if(!findById.isPresent()) {
			throw new CardNotPresentException("Card id is not found");
		}
		return findById.get();
		 
	   
	}
	
}
