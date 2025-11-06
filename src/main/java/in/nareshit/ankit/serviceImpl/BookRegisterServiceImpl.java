package in.nareshit.ankit.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import in.nareshit.ankit.entity.Book;
import in.nareshit.ankit.entity.UserRegister;
import in.nareshit.ankit.exception.BookNotFoundException;
import in.nareshit.ankit.model.BookRequestDto;
import in.nareshit.ankit.repository.BookRepo;
import in.nareshit.ankit.service.BookRegisterService;

@Service
public class BookRegisterServiceImpl  implements BookRegisterService{
	  @Autowired
	  BookRepo bookRepo;
	

	public Book insertBook(BookRequestDto bookRequestDto) {
		Book book=new Book();
		         book.setBook(bookRequestDto.getBook());
		         book.setAuthor(bookRequestDto.getAuthor());
		         book.setPublisherName(bookRequestDto.getPublisherName());
		         book.setPrice(bookRequestDto.getPrice());
		         return bookRepo.save(book);
	}
	@Override
	@Cacheable(value = "book")
	public List<Book> getAllBook() {
		System.out.println("Catcheable Data");
		return bookRepo.findAll() ;
	}
	@Override
	public Book getBookById(Long id) {
		   Optional<Book> findById = bookRepo.findById(id);
		   if(!findById.isPresent()) {
			    throw new BookNotFoundException("Book not found");
		   }
		return findById.get();
	}

}
