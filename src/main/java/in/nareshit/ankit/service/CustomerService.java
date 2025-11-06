package in.nareshit.ankit.service;

import java.util.List;

import in.nareshit.ankit.entity.Customer;
import in.nareshit.ankit.entity.CustomerMango;
import in.nareshit.ankit.entity.UserRegister;

public interface CustomerService {

	public Customer saveCustomer(Customer cust);

	public Customer updateCustomer(Customer cust);
	
	public Customer createUpdateCus(Customer cust);
	
	public Customer getByCustomer(Long id);

	public List<Customer> getAllCust();
	
	public CustomerMango saveCustomerWithMango(CustomerMango cm);
		

}
