package in.nareshit.ankit.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.nareshit.ankit.entity.FilesEntity;
import in.nareshit.ankit.repository.FileRepo;
import in.nareshit.ankit.service.UserRegisterService;

@RestController
public class FileController {

	@Autowired
	private FileRepo fileRepo;
	@Autowired
	private UserRegisterService userRegisterService;

	@PostMapping("/upload")
	public ResponseEntity<String> saveFile(@RequestParam("file") MultipartFile file) throws Exception {
              String saveFiles = userRegisterService.saveFiles(file);
		return ResponseEntity.ok(saveFiles);
	}

	@PostMapping("/uploadmultifiles")
	public ResponseEntity<List<Object>> uploadFileMulti(@RequestParam MultipartFile[] files) throws IOException {
		List<Object> response = Arrays.stream(files).map((s) -> {
			try {
				return saveFile(s);
			} catch (Exception e) {
				return "files Upload file " + e.getLocalizedMessage();
			}
		}).collect(Collectors.toList());
		return ResponseEntity.ok(response);

	}

}
