package in.nareshit.ankit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.ankit.entity.CustomerExcelFile;

public interface CustomerExcelFileRepo  extends JpaRepository<CustomerExcelFile,Long>{

}
