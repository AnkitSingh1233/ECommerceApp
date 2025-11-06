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

import in.nareshit.ankit.entity.Book;
import in.nareshit.ankit.entity.Customer;
import in.nareshit.ankit.entity.UserRegister;
import in.nareshit.ankit.model.BookRequestDto;
import in.nareshit.ankit.model.ResponseMessage;
import in.nareshit.ankit.model.UserRequestDto;
import in.nareshit.ankit.service.BookRegisterService;
import in.nareshit.ankit.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class BookController {
	@Autowired
	BookRegisterService bookRegisterService;
	
	@PostMapping("/bookInsert")
@Operation(summary = "Book Register", description = "E-commerce online book: register By user")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "user Book successfully"),
			@ApiResponse(responseCode = "400", description = "user Book failure"),
			@ApiResponse(responseCode = "500", description = "Internal server error") })

	public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody BookRequestDto bookRequestDto) {
		try {
			if (bookRequestDto.getBook() == null || bookRequestDto.getBook().isEmpty()
					|| bookRequestDto.getAuthor() == null || bookRequestDto.getAuthor().isEmpty()
					||bookRequestDto.getPublisherName()==null||bookRequestDto.getPublisherName().isEmpty()) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "book and Author and Publisher is not empty"));
			}
		

		 Book insertBook = bookRegisterService.insertBook(bookRequestDto);
			if (insertBook != null) {
//			       return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "online bookstore save successfully", userRegister));
				return ResponseEntity.status(HttpStatus.CREATED)
						.body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS,
								"book Insert suceessfully", insertBook));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(
						HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Book Insert Failed", insertBook));

			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}


	@GetMapping("/getAllBooks")
	public ResponseEntity<ResponseMessage> getAllBook() {
		List<Book> book = bookRegisterService.getAllBook();
		try {
			if (!book.isEmpty()) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Book detail is Successfully Find", book));
			} else {

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Book Detail is not Found", book));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}
	}
	
	@GetMapping("/getBook/{id}")
	public ResponseEntity<ResponseMessage> getBookById(@PathVariable("id") Long id) {
	 Book bookById = bookRegisterService.getBookById(id);
		try {
			if (bookById != null) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS,
						"Book id getting Successfully", bookById));
			} else {

				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED,
						"Book id getting Failed", bookById));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(
					HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED, "Internal server error"));
		}

	}
	
	
	
	
	
}
