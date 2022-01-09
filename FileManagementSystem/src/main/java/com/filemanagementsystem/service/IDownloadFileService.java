package com.filemanagementsystem.service;

import javax.servlet.http.HttpServletResponse;

import com.filemanagementsystem.entity.File;

public interface IDownloadFileService {
	
	public File findById(Long id);
	
	public void downloadFile(Long id, HttpServletResponse response);
	
}
