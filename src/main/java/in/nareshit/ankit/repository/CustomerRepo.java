package in.nareshit.ankit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.ankit.entity.Customer;

public interface CustomerRepo  extends JpaRepository<Customer,Long>{

}
