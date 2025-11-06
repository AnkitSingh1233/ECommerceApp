package in.nareshit.ankit.cntrollertest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.nareshit.ankit.controller.UserRegisterController;
import in.nareshit.ankit.entity.UserRegister;
import in.nareshit.ankit.model.UserRequestDto;
import in.nareshit.ankit.service.UserRegisterService;

@WebMvcTest(UserRegisterController.class)
public class UserRegisterControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	UserRegisterService userRegisterService;

	@Test
	public void testUserRegistration() throws JsonProcessingException, Exception {
		UserRequestDto userRequest = new UserRequestDto();
		userRequest.setEmail("test@gmail.com");
		userRequest.setPassword("pass@123");

		UserRegister mockResponse = new UserRegister();
		mockResponse.setId(1L);
		mockResponse.setEmail("test@gmail.com");
		when(userRegisterService.insertUserRegister(userRequest)).thenReturn(mockResponse);
		mockMvc.perform(post("/userregisters").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userRequest)))
				.andDo(print()).andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	public void getAllUserTest() throws JsonProcessingException, Exception {

		// input request
		UserRegister list1 = new UserRegister();
		list1.setFirstName("Sai");
		list1.setLastName("Kamal");
		list1.setEmail("abc@gmail.com");

		// Mock Response
		UserRegister list2 = new UserRegister();
		list2.setLastName("hello");
		list2.setEmail("xyz@gmail.com");
		List<UserRegister> asList = Arrays.asList(list1, list2);

		when(userRegisterService.allUserRecord()).thenReturn(asList);
		mockMvc.perform(get("/allUser").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json(new ObjectMapper().writeValueAsString(asList)));

	}

	@Test
	public void testGetAllUserPagination() throws Exception {
		// 🧩 1. Create dummy users
		UserRegister user1 = new UserRegister();
		user1.setId(1L);
		user1.setFirstName("Ankit");
		user1.setEmail("ankit@gmail.com");

		UserRegister user2 = new UserRegister();
		user2.setId(2L);
		user2.setFirstName("Ravi");
		user2.setEmail("ravi@gmail.com");

		List<UserRegister> users = Arrays.asList(user1, user2);

		// 🧩 2. Create a mock Page object
		Page<UserRegister> mockPage = new PageImpl<>(users);

		// 🧩 3. Mock the service call
		when(userRegisterService.getAllUser(0, 2, "id", "asc")).thenReturn(mockPage);

		// 🧩 4. Perform GET request with query params
		mockMvc.perform(get("/userPagenation").param("page", "0").param("size", "2").param("sortBy", "id")
				.param("sortDir", "asc").accept(MediaType.APPLICATION_JSON)).andDo(print()) // print request & response
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.users").isArray()).andExpect(jsonPath("$.users[0].firstName").value("Ankit"))
				.andExpect(jsonPath("$.users[1].email").value("ravi@gmail.com"))
				.andExpect(jsonPath("$.currentPage").value(0)).andExpect(jsonPath("$.totalItems").value(users.size()))
				.andExpect(jsonPath("$.totalPages").value(1));
	}
	
	
	

}
