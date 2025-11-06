package in.nareshit.ankit.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.nareshit.ankit.entity.CartModule;
import in.nareshit.ankit.entity.Customer;
import in.nareshit.ankit.model.ResponseMessage;
import in.nareshit.ankit.service.CartModuleService;
import in.nareshit.ankit.util.Constants;

@RestController
public class CartController {
	
	
	@Autowired
    private CartModuleService cartModuleService;

	@PostMapping("/addtocart")
	public ResponseEntity<ResponseMessage> addedToCarts(@RequestParam("custmerId") Long custmerId,
			                                             @RequestParam("bookId") Long bookId,
			                                             @RequestParam("quantity") int quantity) {
		
		try {
     		CartModule cartBooks = cartModuleService.addToCartBooks(custmerId,bookId,quantity);
		if(cartBooks!=null) {
			
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Added the cart into books successfully ", cartBooks));
	    }else { 
	    	return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Added the cartare failure", cartBooks));
	    	
	    }}catch (Exception e) {
		
	    	return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_GATEWAY, Constants.FAILURE, "Added the cart Books getting failed", e.getMessage()));
		}
		
	}
	
	
	@GetMapping("/getAllCartDetail")
	public ResponseEntity<ResponseMessage> getAllCartDeatils() {
		 List<CartModule> allCartDetail = cartModuleService.getAllCartDetail();
		try {
			if (!allCartDetail.isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Cart Detail is Successfully Find", allCartDetail));
			} else {

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"no Cart Detail is find", allCartDetail));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}
	
	
	@GetMapping("/getCart/{id}")
	public ResponseEntity<ResponseMessage> getCartDetailById(@PathVariable("id") Long id) {
		CartModule byCustomer = cartModuleService.geCartById(id);
		try {
			if (byCustomer != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Card id getting Successfully", byCustomer));
			} else {

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Card id getting Failed", byCustomer));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}

	}



	
	
	
	
}
