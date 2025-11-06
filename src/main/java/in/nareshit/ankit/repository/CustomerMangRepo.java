package in.nareshit.ankit.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.nareshit.ankit.entity.CustomerMango;

public interface CustomerMangRepo  extends MongoRepository<CustomerMango,String>{

}
