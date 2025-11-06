package in.nareshit.ankit.controller;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import in.nareshit.ankit.entity.UserRegister;
import in.nareshit.ankit.model.ResponseMessage;
import in.nareshit.ankit.model.UserName;
import in.nareshit.ankit.model.UserRequestDto;
import in.nareshit.ankit.service.UserRegisterService;
import in.nareshit.ankit.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController

@Tag(name = "UserRegisterController", description = "UserRegister Register and Login")
public class UserRegisterController {

	@Autowired
	private UserRegisterService userRegisterService;

	@Operation(summary = "Create User Register", description = "E-commerce online bookstore: register a user")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "user register successfully"),
			@ApiResponse(responseCode = "400", description = "user register failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })

	@PostMapping("/userregisters")
	public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody UserRequestDto userRequestDto) {
		try {
			if (userRequestDto.getEmail() == null || userRequestDto.getEmail().isEmpty()
					|| userRequestDto.getPassword() == null || userRequestDto.getPassword().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email and passowrd cannot be empty"));
			}
			UserRegister exitingUser = userRegisterService.findByEmail(userRequestDto.getEmail());
			if (exitingUser != null) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "email are dublicate"));
			}

			UserRegister userRegister = userRegisterService.insertUserRegister(userRequestDto);
			if (userRegister != null) {
//			       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "online bookstore save successfully", userRegister));
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"online bookstore save successfully", userRegister));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Register Failed", userRegister));

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

	@Operation(summary = "Create User Register", description = "e commerece online books store  register the users")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "user Login successfully"),
			@ApiResponse(responseCode = "400", description = "user Login failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })
	@PostMapping("/userlogin")
	public ResponseEntity<ResponseMessage> checkLogin(@RequestBody UserRequestDto userRequestDto) {

		try {
			if (userRequestDto.getEmail() == null || userRequestDto.getEmail().isEmpty()
					|| userRequestDto.getPassword() == null || userRequestDto.getPassword().isEmpty()) {

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"email and passowrd cannot be empty"));

			}
			UserRegister checkUserDetails = userRegisterService.checkUserDetails(userRequestDto);
			if (checkUserDetails != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Login successfully", checkUserDetails));

			} else {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Invalid creditials...!"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
					"Internal server error"));

		}
	}

	@GetMapping("/users/{id}")
	public UserName fstNameAndLastName(@PathVariable("id") Long id) {
		return userRegisterService.fstNameAndLastName(id);
	}

	@GetMapping("/user/{id}")
	public List<UserName> allEmployee(@PathVariable("id") Long id) {
		return userRegisterService.getAllEmployee(id);
	}
	/*
	 * @PostMapping("/userregistersWithFile") public ResponseEntity<ResponseMessage>
	 * createUserRegisterWithFile(@RequestParam("jsonData") String
	 * jsonData,@RequestParam("files") MultipartFile[]files) { try { UserRequestDto
	 * userRequestDto = new ObjectMapper().readValue(jsonData,UserRequestDto.class);
	 * 
	 * 
	 * UserRegister userRegister =
	 * userRegisterService.uploadMultipleRegister(userRequestDto, files); if
	 * (userRegister != null) { // return ResponseEntity.ok(new
	 * ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
	 * "online bookstore save successfully", userRegister)); return
	 * ResponseEntity.status(HttpStatus.CREATED) .body(new
	 * ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
	 * "online bookstore save successfully", userRegister)); } else { return
	 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
	 * HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Register Failed",
	 * userRegister));
	 * 
	 * } } catch (Exception e) { return
	 * ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new
	 * ResponseMessage( HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,
	 * "Internal server error")); } }
	 * 
	 */

	@PostMapping(value = "/userregistersWithFile", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
			MediaType.APPLICATION_OCTET_STREAM_VALUE })
	public ResponseEntity<ResponseMessage> createUserRegisterWithFile(
			@RequestPart("userRequestDto") UserRequestDto userRequestDto, @RequestPart("files") MultipartFile[] files) {
		try {
			UserRegister userRegister = userRegisterService.uploadMultipleRegister(userRequestDto, files);
			if (userRegister != null) {
//			       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "online bookstore save successfully", userRegister));
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"online bookstore save successfully", userRegister));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "User Register Failed", userRegister));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}

	@PostMapping("/userUpdate/{id}")
	public ResponseEntity<ResponseMessage> updateUser(@PathVariable("id") Long id,
			@RequestBody UserRequestDto userRequestDto) {
		UserRegister updateRegister = userRegisterService.updateRegister(id, userRequestDto);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(new ResponseMessage(HttpURLConnection.HTTP_ACCEPTED, Constants.SUCCESS, "save Suceesfully"));

	}

	@GetMapping("/allUser")
	public List<UserRegister> allUser() {
		return userRegisterService.allUserRecord();
	}

	@GetMapping("/userPagenation")
	public ResponseEntity<Map<String, Object>> getAllUser(@RequestParam(name="page") int page, @RequestParam(name="size") int size,
			@RequestParam(name="sortBy") String sortBy, @RequestParam(name="sortDir") String sortDir) {

		Page<UserRegister> userPage = userRegisterService.getAllUser(page, size, sortBy, sortDir);
		Map<String, Object> response = new HashMap<>();
		response.put("users", userPage.getContent());
		response.put("currentPage", userPage.getNumber());
		response.put("totalItems", userPage.getTotalElements());
		response.put("totalPages", userPage.getTotalPages());

		return ResponseEntity.ok(response);

	}

}