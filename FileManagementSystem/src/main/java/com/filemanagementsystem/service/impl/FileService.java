package com.filemanagementsystem.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.filemanagementsystem.entity.File;
import com.filemanagementsystem.repository.FileRepository;
import com.filemanagementsystem.service.IFileService;

@Service
public class FileService implements IFileService {

	@Autowired
	private FileRepository fileRepository;

	@Override
	public Page<File> findAllFiles(int pageNum, int pageSize) {
	    Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by("createdDateTime").descending());
		return fileRepository.findAll(pageable);
	}

	@Override
	public File getFile(Long id) {
		File file = fileRepository.getById(id);
		return file;
	}

	@Override
	public void saveFile(File file) {
		fileRepository.save(file);
	}
	
	@Override
	public String deleteFile(Long id) {
		String message = "The file has been deleted successfully!";
		
		// delete file in local storage and database
		Optional<File> dbFile = fileRepository.findById(id);
		if(dbFile.isPresent()) {
			File file = dbFile.get();
			Path fileToDeletePath = Paths.get(file.getPath() + "\\" + file.getName());
			try {
				Files.delete(fileToDeletePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//delete from db
		fileRepository.deleteById(id);
		return message;
	}
	
	@Override
	public void viewFile(File file, HttpServletResponse response) throws IOException {
		java.io.File downloadedFile = new java.io.File(file.getPath() + "\\" + file.getName());
		Path path = Paths.get(downloadedFile.getAbsolutePath());
	    
	    ServletOutputStream outputStream;
		outputStream = response.getOutputStream();
		outputStream.write(Files.readAllBytes(path));
		outputStream.close();
	}
}
