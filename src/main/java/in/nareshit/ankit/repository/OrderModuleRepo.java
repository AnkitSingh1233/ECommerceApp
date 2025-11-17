package in.nareshit.ankit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.nareshit.ankit.entity.Book;
import in.nareshit.ankit.entity.Orders;

public interface OrderModuleRepo extends JpaRepository<Orders,Long> {
	
	  //Custom query to find all orders placed by a specific customer within the last 7 days.
    // This is useful for restricting non-prime users from placing  more than one order per week.
	  @Query(value = "SELECT * FROM orders o WHERE o.custmer_id = :customerId AND o.created_date > CURDATE() - INTERVAL 7 DAY", nativeQuery = true)
	  public List<Orders> findAnyLastweekPlaced(@Param("customerId")Long customerId);

	  
	    //whether a book with the given title actually exists or not.
	  @Query(value = "SELECT b FROM Book b WHERE b.title = :title")
	  public Book findByBookName(@Param("title")String title);

}
