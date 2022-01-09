package com.filemanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.filemanagementsystem.entity")
@SpringBootApplication
public class FileManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileManagementSystemApplication.class, args);
	}

}
