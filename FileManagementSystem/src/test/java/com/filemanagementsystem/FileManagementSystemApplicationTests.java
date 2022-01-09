package com.filemanagementsystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.filemanagementsystem.entity.File;
import com.filemanagementsystem.repository.FileRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class FileManagementSystemApplicationTests {

	@Autowired
	private FileRepository fileRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	@Rollback(false)
	void testUploadFile() throws IOException {
		java.io.File f = new java.io.File("D:\\Dai Bich.png");
		File file = new File();
		file.setName(f.getName());
		file.setPath(f.getPath());
		long fileSize = Files.readAllBytes(f.toPath()).length;
		file.setFileSize(fileSize);
		file.setCreatedDateTime(new Date());
		
		File savedFile = fileRepository.save(file);
		File existedFile = testEntityManager.find(File.class, savedFile.getId());
		
		assertThat(existedFile.getFileSize()).isEqualTo(fileSize);
	}

}
