package in.nareshit.ankit.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.multipart.MultipartFile;

import in.nareshit.ankit.entity.FilesEntity;
import in.nareshit.ankit.entity.UserRegister;
import in.nareshit.ankit.model.UserRequestDto;
import in.nareshit.ankit.repository.FileRepo;
import in.nareshit.ankit.repository.UserRegisterRepo;
import in.nareshit.ankit.serviceImpl.UserRegisterServiceImpl;

@SpringBootTest
public class UserRegisterServiceTest {

	@MockBean
	private UserRegisterRepo userRegisterRepo;

	@Autowired
	private  UserRegisterServiceImpl userRegisterServiceImpl;
	
	   @Autowired
	    private CacheManager cacheManager;
	   
	 @MockBean
		private FileRepo fileRepo;

@Test
public void	testInsertUserRegister() {
	
	

    //  Step 1: Create Input Data (like from Postman or frontend)
    UserRequestDto input = new UserRequestDto();
    input.setFirstName("Kamal");
    input.setLastName("Kalyan");
    input.setEmail("kamal@gmail.com");
    input.setPassword("pass@123");

    //  Step 2: Create Fake DB Output (as if user saved in DB)
    UserRegister savedUser = new UserRegister();
    savedUser.setId(1L);
    savedUser.setFirstName("Srinu");
    savedUser.setLastName("Lateesha");
    savedUser.setEmail("gopi@gmail.com");
    savedUser.setPassword(Base64.getEncoder().encodeToString("pass@123".getBytes()));
    
    //  Step 3: When save() called, return fake user
    when(userRegisterRepo.save(any(UserRegister.class))).thenReturn(savedUser);
    
    //  Step 4: Call actual service method
    UserRegister result = userRegisterServiceImpl.insertUserRegister(input);
    
//  Step 5: Check (Verify) output
    assertNotNull(result); // result should not be null
    assertEquals("Kamal", result.getFirstName());
    assertEquals("kamal@gmail.com", result.getEmail());
     // save() called only once
    verify(userRegisterRepo, times(1))
   .save(any(UserRegister.class));
    
 }
@Test
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public void testAllUserRecord_Cacheable() {
    // Step 1: Prepare mock data
    UserRegister u1 = new UserRegister(1L, "Ankit", "Singh", "ankit@gmail.com", "abc123", 0);
    UserRegister u2 = new UserRegister(2L, "Kamal", "Kalyan", "kamal@gmail.com", "xyz789", 0);

    List<UserRegister> mockList = Arrays.asList(u1, u2);

    // Step 2: Tell repo what to return
    when(userRegisterRepo.findAll()).thenReturn(mockList);

    // Step 3: First call — should hit repository
    System.out.println("---- FIRST CALL ----");
    List<UserRegister> result1 = userRegisterServiceImpl.allUserRecord();
    System.out.println("Result 1: " + result1);
    verify(userRegisterRepo, times(1)).findAll();

    // Step 4: Second call — should use cache (repo not called again)
    System.out.println("---- SECOND CALL ----");
    List<UserRegister> result2 = userRegisterServiceImpl.allUserRecord();
    System.out.println("Result 2: " + result2);
    verify(userRegisterRepo, times(1)).findAll(); // still 1 call → cache worked ✅

    
}

@Test
public void testUploadMultipleRegister() throws Exception {
    // Step 1️⃣: Prepare Input DTO
    UserRequestDto dto = new UserRequestDto();
    dto.setFirstName("Ravi");
    dto.setLastName("Teja");
    dto.setEmail("ravi@gmail.com");
    dto.setPassword("pass123");
    dto.setContactId(123L);

    // Step 2️⃣: Mock Multipart Files
    MultipartFile file1 = new MockMultipartFile("file1", "test1.txt", "text/plain", "Hello1".getBytes());
    MultipartFile file2 = new MockMultipartFile("file2", "test2.txt", "text/plain", "Hello2".getBytes());
    MultipartFile[] files = { file1, file2 };

    // Step 3️⃣: Mock repository save() calls
    UserRegister savedUser = new UserRegister();
    savedUser.setId(10L);
    savedUser.setFirstName(dto.getFirstName());
    savedUser.setLastName(dto.getLastName());
    savedUser.setEmail(dto.getEmail());
    savedUser.setPassword(Base64.getEncoder().encodeToString(dto.getPassword().getBytes()));
    savedUser.setContactId(dto.getContactId());
    


    when(userRegisterRepo.save(any(UserRegister.class))).thenReturn(savedUser);
    when(fileRepo.save(any(FilesEntity.class))).thenReturn(new FilesEntity());

    // Step 4️⃣: Call service method
    UserRegister result = userRegisterServiceImpl.uploadMultipleRegister(dto, files);

    // Step 5️⃣: Assertions
    assertNotNull(result);
    assertEquals("Ravi", result.getFirstName());
    assertEquals("ravi@gmail.com", result.getEmail());
    assertEquals(Base64.getEncoder().encodeToString("pass123".getBytes()), result.getPassword());

    // Step 6️⃣: Verify repo interactions
    verify(userRegisterRepo, times(1)).save(any(UserRegister.class));
    verify(fileRepo, times(2)).save(any(FilesEntity.class)); // because 2 files

    // Step 7️⃣: Print outputs for visibility
    System.out.println("Saved User: " + result);
    System.out.println("Encoded Password: " + result.getPassword());
}
}

	
	

