package in.nareshit.ankit.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springdoc.api.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.ankit.entity.Customer;
import in.nareshit.ankit.entity.CustomerMango;
import in.nareshit.ankit.exception.CustomerIdNotFoundException;
import in.nareshit.ankit.repository.CustomerMangRepo;
import in.nareshit.ankit.repository.CustomerRepo;
import in.nareshit.ankit.service.CustomerService;
import io.swagger.v3.oas.annotations.servers.Server;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private CustomerMangRepo customerMangRepo;  

	@Override
	public Customer saveCustomer(Customer cust) {
		return customerRepo.save(cust);
	}
	
@Override
public CustomerMango saveCustomerWithMango(CustomerMango cm) {
	// TODO Auto-generated method stub
	return customerMangRepo.save(cm);
}
	
	

	@Override
	public Customer updateCustomer(Customer cust) {

		return customerRepo.save(cust);
	}

	@Override
	public Customer createUpdateCus(Customer cust) {
		if (cust.getId() == null) {
	          customerRepo.save(cust);
		} else {
			Optional<Customer> opt = customerRepo.findById(cust.getId());
			if (opt.isPresent()) {
				Customer existData = opt.get();
				existData.setCustName(cust.getCustName());
				existData.setEmail(cust.getEmail());
				return customerRepo.save(existData);
			} else {
				throw new RuntimeException("Customer not found");
			}
		}
		return cust;

	}
@Override
public Customer getByCustomer(Long id)  {
	Optional<Customer> opt = customerRepo.findById(id);
	if(!opt.isPresent()) {
     throw new CustomerIdNotFoundException("Customer not Found");
	}
   return opt.get();

}
@Override
public List<Customer> getAllCust() {
	return customerRepo.findAll();
}
	
	
}
