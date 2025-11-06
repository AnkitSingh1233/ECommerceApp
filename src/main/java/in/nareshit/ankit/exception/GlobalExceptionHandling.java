package in.nareshit.ankit.exception;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springdoc.api.ErrorMessage;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import in.nareshit.ankit.model.ErrorMsg;
import in.nareshit.ankit.model.ResponseMessage;
import in.nareshit.ankit.util.Constants;

@ControllerAdvice
public class GlobalExceptionHandling {
	@ExceptionHandler(CustomerIdNotFoundException.class)
   public ResponseEntity<ErrorMsg> handleException(CustomerIdNotFoundException ex){
		
		List<String> li=new ArrayList<>();
		li.add("Error Detail Customer Not Found");
		li.add("msg "+ex.getLocalizedMessage());
		li.add("TimeStamp "+System.currentTimeMillis());
		ErrorMsg err=new ErrorMsg(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"Customer id is not Found",li);
		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
}
	
	@ExceptionHandler(BookNotFoundException.class)
	   public ResponseEntity<ErrorMsg> handleExceptionBook(BookNotFoundException ex){
			List<String> li=new ArrayList<>();
			li.add("Error Detail Book Not Found");
			li.add("msg "+ex.getLocalizedMessage());
			li.add("TimeStamp "+System.currentTimeMillis());
			ErrorMsg err=new ErrorMsg(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"Book id is not Found",li);
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	} 
	
	
	@ExceptionHandler(CardNotPresentException.class)
	   public ResponseEntity<ErrorMsg> handleExceptionCard(CardNotPresentException ex){
			List<String> li=new ArrayList<>();
			li.add("Error Detail Book Not Found");
			li.add("msg "+ex.getLocalizedMessage());
			li.add("TimeStamp "+System.currentTimeMillis());
			ErrorMsg err=new ErrorMsg(HttpURLConnection.HTTP_BAD_REQUEST,Constants.FAILED,"Card id is not Found",li);
			return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}  
	
	
}
