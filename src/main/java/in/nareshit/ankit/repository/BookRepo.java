package in.nareshit.ankit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.ankit.entity.Book;

public interface BookRepo  extends JpaRepository<Book,Long>{

}
