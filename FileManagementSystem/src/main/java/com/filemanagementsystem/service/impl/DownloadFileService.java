package com.filemanagementsystem.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filemanagementsystem.entity.File;
import com.filemanagementsystem.repository.FileRepository;
import com.filemanagementsystem.service.IDownloadFileService;

@Service
public class DownloadFileService implements IDownloadFileService {
	
	@Autowired
	private FileRepository fileRepository;
	
	@Override
	public File findById(Long id) {
		return fileRepository.findById(id).get();
	}
	
	@Override
	public void downloadFile(Long id, HttpServletResponse response) {
		File file = findById(id);
		
		java.io.File downloadedFile = new java.io.File(file.getPath() + "\\" + file.getName());
		if(Files.exists(downloadedFile.toPath())) {
			//update download times
			int numberOfDownload = file.getNumberOfDownload();
			file.setNumberOfDownload(numberOfDownload + 1);
			fileRepository.save(file);
		}	
		
		String headerKey = "Content-Disposition";
	    String headerValue = "attachment; filename= " + file.getName();
	    response.setContentType("application/octet-stream");
	    response.setHeader(headerKey, headerValue); 
		
	    //Write file content
		Path path = Paths.get(downloadedFile.getAbsolutePath());
	    ServletOutputStream outputStream;
		try {
			outputStream = response.getOutputStream();
			outputStream.write(Files.readAllBytes(path));
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
