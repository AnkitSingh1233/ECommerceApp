package in.nareshit.ankit.service;

import java.util.List;

import in.nareshit.ankit.entity.Book;
import in.nareshit.ankit.entity.UserRegister;
import in.nareshit.ankit.model.BookRequestDto;

public interface BookRegisterService {

 public Book insertBook(BookRequestDto bookRequestDto);

 public  List<Book> getAllBook();
 
    public Book    getBookById(Long id);

}
