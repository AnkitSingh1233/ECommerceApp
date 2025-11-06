package in.nareshit.ankit.service;

import java.util.List;

import in.nareshit.ankit.entity.CartModule;
import in.nareshit.ankit.entity.Customer;

public interface CartModuleService {
	public CartModule addToCartBooks(Long custmerId, Long bookId, int quantity);

	public  List<CartModule> getAllCartDetail();

	public CartModule geCartById(Long id);	

}
