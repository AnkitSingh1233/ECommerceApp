package in.nareshit.ankit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.ankit.entity.Book;
import in.nareshit.ankit.entity.CartModule;
import in.nareshit.ankit.entity.Customer;

public interface CartModuleRepository extends JpaRepository<CartModule, Long>{

	public CartModule findByCustomerAndBooksModule(Customer customer, Book booksModule);

}
