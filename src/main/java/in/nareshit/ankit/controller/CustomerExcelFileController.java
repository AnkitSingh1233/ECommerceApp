package in.nareshit.ankit.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.nareshit.ankit.entity.Customer;
import in.nareshit.ankit.entity.CustomerExcelFile;
import in.nareshit.ankit.model.ResponseMessage;
import in.nareshit.ankit.service.CustomerExcelFileService;
import in.nareshit.ankit.util.Constants;
import in.nareshit.ankit.util.ExcelHelper;


@RestController
public class CustomerExcelFileController {
	
	@Autowired
	CustomerExcelFileService customerExcelFileService; 

	@PostMapping("/uploadexcel")
	public ResponseEntity<ResponseMessage> exceltoDbSaveCustomerDetail(@RequestParam("file") MultipartFile file) throws Exception{
	
		if(ExcelHelper.hasExcelFormat(file)) {
			customerExcelFileService.uploadExcelIntoDb(file);
			 	return ResponseEntity.ok(new ResponseMessage(
						HttpURLConnection.HTTP_OK, Constants.SUCCESS, "ExcelFile save successfully"));
			} else {	
				
			 	return ResponseEntity.ok(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "ExcelFile not saved"));
		}
		

	
	}
	
	@GetMapping("/getAllCustomerDetail")
	public ResponseEntity<ResponseMessage> getAllCustomerData() {
		List<CustomerExcelFile> customer = customerExcelFileService.getAllCust();
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

	
	
	
	
	
	
}
