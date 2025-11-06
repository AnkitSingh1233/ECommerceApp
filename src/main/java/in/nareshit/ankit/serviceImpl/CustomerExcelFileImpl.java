package in.nareshit.ankit.serviceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.nareshit.ankit.entity.Customer;
import in.nareshit.ankit.entity.CustomerExcelFile;
import in.nareshit.ankit.repository.CustomerExcelFileRepo;
import in.nareshit.ankit.service.CustomerExcelFileService;
import in.nareshit.ankit.util.ExcelHelper;

@Service
public class CustomerExcelFileImpl implements CustomerExcelFileService {

	@Autowired
	CustomerExcelFileRepo customerExcelFileRepo; 
	
	@Override
	public void uploadExcelIntoDb(MultipartFile file) throws IOException{ 
	List<CustomerExcelFile> excelToCustomerList = ExcelHelper.excelToCustomerList(file.getInputStream());
		customerExcelFileRepo.saveAll(excelToCustomerList);
	}

	@Override
	public List<CustomerExcelFile> getAllCust() {
		List<CustomerExcelFile> allCustomer = customerExcelFileRepo.findAll();
		return allCustomer;
	}
	
	  
	
}
