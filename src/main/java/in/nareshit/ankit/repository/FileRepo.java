package in.nareshit.ankit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.ankit.entity.FilesEntity;

public interface FileRepo extends JpaRepository<FilesEntity,Long> {

}
