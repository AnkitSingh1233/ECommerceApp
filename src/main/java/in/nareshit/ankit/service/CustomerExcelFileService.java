package in.nareshit.ankit.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.nareshit.ankit.entity.Customer;
import in.nareshit.ankit.entity.CustomerExcelFile;

public interface CustomerExcelFileService {

 public	void uploadExcelIntoDb(MultipartFile file) throws Exception;

public List<CustomerExcelFile> getAllCust();

}
