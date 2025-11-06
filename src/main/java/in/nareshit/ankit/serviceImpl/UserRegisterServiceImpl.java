package in.nareshit.ankit.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.nareshit.ankit.entity.FilesEntity;
import in.nareshit.ankit.entity.UserRegister;
import in.nareshit.ankit.model.UserName;
import in.nareshit.ankit.model.UserRequestDto;
import in.nareshit.ankit.repository.FileRepo;
import in.nareshit.ankit.repository.UserRegisterRepo;
import in.nareshit.ankit.service.UserRegisterService;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	private UserRegisterRepo userRegisterRepo;
	@Autowired
	private FileRepo fileRepo;

	public UserRegister insertUserRegister(UserRequestDto userRequestDto) {
		UserRegister user = new UserRegister();
		try {
			user.setFirstName(userRequestDto.getFirstName());
			user.setLastName(userRequestDto.getLastName());
			user.setEmail(userRequestDto.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
			user.setContactId(userRequestDto.getContactId());
			userRegisterRepo.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<UserName> getAllEmployee(Long id) {
		return userRegisterRepo.firstNameAndLastName(id);
	}

	public String saveFiles(MultipartFile file) throws IOException {
		FilesEntity fss = new FilesEntity();
		fss.setFileName(file.getOriginalFilename());
		fss.setFileType(file.getContentType());
		fss.setData(file.getBytes());
		fileRepo.save(fss);
		return " file Save Successfull";

	}

	@Override
	public UserRegister checkUserDetails(UserRequestDto userRequestDto) {
		/*
		 * UserRegister findByEmail =
		 * userRegisterRepo.findByEmail(userRequestDto.getEmail());
		 * 
		 * if (findByEmail != null) { String decode = new
		 * String(Base64.getDecoder().decode(findByEmail.getPassword())); if
		 * (decode.equals(userRequestDto.getPassword())) { return findByEmail; } else {
		 * return null; }
		 * 
		 * } return null;
		 */

		return Optional.ofNullable(userRegisterRepo.findByEmail(userRequestDto.getEmail()))
				.filter((user) -> new String(Base64.getDecoder().decode(user.getPassword()))
						.equals(userRequestDto.getPassword()))
				.orElse(null);

	}

	@Override
	public UserName fstNameAndLastName(Long id) {

		Optional<UserRegister> findById = userRegisterRepo.findById(id);
		UserRegister userRegister = findById.get();
		return new UserName(userRegister.getFirstName(), userRegister.getLastName());

	}

	@Override
	public UserRegister findByEmail(String email) {
		return userRegisterRepo.findByEmail(email);
	}

	public UserRegister uploadMultipleRegister(UserRequestDto userRequestDto, MultipartFile[] files) {
		UserRegister user = new UserRegister();
		try {
			user.setFirstName(userRequestDto.getFirstName());
			user.setLastName(userRequestDto.getLastName());
			user.setEmail(userRequestDto.getEmail());
			user.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
			user.setContactId(userRequestDto.getContactId());
			userRegisterRepo.save(user);
			if (files != null && files.length > 0) {
				for (MultipartFile multipartFile : files) {
					FilesEntity fss = new FilesEntity();
					fss.setFileName(multipartFile.getOriginalFilename());
					fss.setFileType(multipartFile.getContentType());
					fss.setData(multipartFile.getBytes());
					fileRepo.save(fss);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public UserRegister updateRegister(Long id, UserRequestDto userRequestDto) {
		Optional<UserRegister> opt = userRegisterRepo.findById(id);
		UserRegister userRegister = opt.get();
		userRegister.setFirstName(userRequestDto.getFirstName());
		userRegister.setLastName(userRequestDto.getLastName());
		userRegister.setEmail(userRequestDto.getEmail());
		userRegister.setPassword(Base64.getEncoder().encodeToString(userRequestDto.getPassword().getBytes()));
		userRegister.setContactId(userRequestDto.getContactId());
		return userRegisterRepo.save(userRegister);

	}

	@Cacheable(value = "users")
	public List<UserRegister> allUserRecord() {
		System.out.println("Catching data");
		return userRegisterRepo.findAll();
	}
	public Page<UserRegister> getAllUser(int page, int size, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(page, size, sort);
		return userRegisterRepo.findAll(pageable);

	}

}
