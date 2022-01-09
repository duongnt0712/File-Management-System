package com.filemanagementsystem.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import com.filemanagementsystem.entity.File;

public interface IFileService {
	
	public Page<File> findAllFiles(int pageNum, int pageSize);
	
	public File getFile(Long id);
	
	public void saveFile(File file);
	
	public String deleteFile(Long id);
	
	public void viewFile(File file, HttpServletResponse response) throws IOException;
}
