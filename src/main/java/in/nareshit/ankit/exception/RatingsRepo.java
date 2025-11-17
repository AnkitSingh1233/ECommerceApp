 package in.nareshit.ankit.exception;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.ankit.entity.Ratings;

public interface RatingsRepo extends JpaRepository<Ratings,Long> {

}
