package in.nareshit.ankit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.nareshit.ankit.entity.UserRegister;
import in.nareshit.ankit.model.UserName;

public interface UserRegisterRepo  extends JpaRepository<UserRegister,Long>{

	@Query( value ="select first_name,last_name from register where id=:id" ,nativeQuery = true)
	public List<UserName> firstNameAndLastName(@Param("id") Long id);
	
	

	public UserRegister findByEmail(String email);

}
