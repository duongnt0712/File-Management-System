package com.filemanagementsystem.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.filemanagementsystem.entity.File;
import com.filemanagementsystem.entity.Setting;

public interface IUploadFileService {
	
	public String uploadFiles(MultipartFile[] multipartFile, Setting setting) throws IOException;
	
	public String uploadFile(MultipartFile multipartFile, Setting setting) throws IOException;

}
