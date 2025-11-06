package in.nareshit.ankit.controller;
import java.net.HttpURLConnection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import in.nareshit.ankit.entity.Customer;
import in.nareshit.ankit.entity.CustomerMango;
import in.nareshit.ankit.entity.UserRegister;
import in.nareshit.ankit.model.ResponseMessage;
import in.nareshit.ankit.model.UserRequestDto;
import in.nareshit.ankit.service.CustomerService;
import in.nareshit.ankit.util.Constants;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customesave")
	public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody Customer customer) {
		try {
			if (customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getCustName() == null
					|| customer.getCustName().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and passowrd cannot be empty"));
			}
			Customer saveCustomer = customerService.saveCustomer(customer);

			if (saveCustomer != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(
						HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer save successfully", saveCustomer));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
								"Customer Register Failed", saveCustomer));

			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

	@PostMapping("/updatecustomer")
	public ResponseEntity<ResponseMessage> updateCustomer(@RequestBody Customer customer) {
		try {
			if (customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getCustName() == null
					|| customer.getCustName().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and passowrd cannot be empty"));
			}

			if (customer.getId() == null) {

				Customer saveCustomer = customerService.saveCustomer(customer);
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(
						HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer save successfully", saveCustomer));

			} else {
				Customer updateCustomer = customerService.updateCustomer(customer);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"Customer update successfully", updateCustomer));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

	@PostMapping("/createupdatecustomer")
	public ResponseEntity<ResponseMessage> createUpdateCustomer(@RequestBody Customer customer) {
		try {
			if (customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getCustName() == null
					|| customer.getCustName().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and passowrd cannot be empty"));
			}

			if (customer.getId() == null) {

				Customer saveCustomer = customerService.createUpdateCus(customer);
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(
						HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer save successfully", saveCustomer));

			} else {
				Customer saveCustomer = customerService.createUpdateCus(customer);
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"Customer update successfully", saveCustomer));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<ResponseMessage> getCustomer(@PathVariable("id") Long id) {
		Customer byCustomer = customerService.getByCustomer(id);
		try {
			if (byCustomer != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Customer id getting Successfully", byCustomer));
			} else {

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Customer id getting Failed", byCustomer));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}

	}
	
	

	@GetMapping("/getAllCustomer")
	public ResponseEntity<ResponseMessage> getAllCustomer() {
		List<Customer> customer = customerService.getAllCust();
		try {
			if (!customer.isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Customer detail is Successfully Find", customer));
			} else {

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"no Customer is Register", customer));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}
	
	@PostMapping("/customesavemango")
	public ResponseEntity<ResponseMessage> createUserRegisterWithMango(@RequestBody CustomerMango customerMango) {
		try {
			if (customerMango.getEmail() == null || customerMango.getEmail().isEmpty() || customerMango.getCustName() == null
					|| customerMango.getCustName().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and passowrd cannot be empty"));
			}
			 CustomerMango saveCustomerWithMango = customerService.saveCustomerWithMango(customerMango);

			if (saveCustomerWithMango != null) {
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(
						HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer save successfully", saveCustomerWithMango));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
								"Customer Register Failed", saveCustomerWithMango));

			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}
	
	
	
	

}
