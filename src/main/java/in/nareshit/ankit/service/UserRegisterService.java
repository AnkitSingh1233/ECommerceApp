package in.nareshit.ankit.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import in.nareshit.ankit.entity.FilesEntity;
import in.nareshit.ankit.entity.UserRegister;
import in.nareshit.ankit.model.UserName;
import in.nareshit.ankit.model.UserRequestDto;

public interface UserRegisterService {
	public UserRegister insertUserRegister(UserRequestDto userRequestDto);
	
	public List<UserName> getAllEmployee(Long id);
	
	public String saveFiles(MultipartFile fs) throws Exception;
public UserRegister checkUserDetails(UserRequestDto userRequestDto);
	
public UserName fstNameAndLastName(Long id);
	
public UserRegister findByEmail(String email);

public UserRegister uploadMultipleRegister(UserRequestDto userRequestDto,MultipartFile[] files);
	
	public UserRegister updateRegister(Long id,UserRequestDto userRequestDto);
	
	public Page<UserRegister> getAllUser(int page, int size, String sortBy, String sortDir);
	
	

	public List<UserRegister> allUserRecord(); 

}
