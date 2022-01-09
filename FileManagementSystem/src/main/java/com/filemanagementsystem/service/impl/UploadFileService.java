package com.filemanagementsystem.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.filemanagementsystem.entity.File;
import com.filemanagementsystem.entity.Setting;
import com.filemanagementsystem.repository.FileRepository;
import com.filemanagementsystem.service.IUploadFileService;
import com.filemanagementsystem.utils.FileUtils;

@Service
public class UploadFileService implements IUploadFileService {

	@Autowired
	private FileRepository fileRepository;
	
	@Override
	public String uploadFiles(MultipartFile[] multipartFile, Setting setting) throws IOException {
		String message = "";
		for(MultipartFile file : multipartFile) {
			message = uploadFile(file, setting);
		}
		return message;
	}
	
	@Override
	public String uploadFile(MultipartFile multipartFile, Setting setting) throws IOException {
		String message = "";
		
		if(FileUtils.validateIsEmptyFile(multipartFile)) {
			message = "Please choose a file to upload!";
		} else {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			int version = checkVersion(multipartFile, fileName); 	
					
			//create directory to store files
			Path dir = Paths.get("upload-files/version " + version);
			if (!Files.exists(dir)) {
	            Files.createDirectories(dir);
	        }
			String path = dir.toFile().getAbsolutePath();
			if(path.startsWith("../")) {
				path = path.replace("../","");
			}	
			
			File file = new File();
	
			if(!FileUtils.validateFileSize(multipartFile, file, setting)) {
				message = fileName + " is over the allowed size!";
			} else if(!FileUtils.validateMimeType(multipartFile, file, setting)) {
				message = fileName + " is unacceptable! Only " + setting.getMimeTypeAllowed() + " files are allowed!";
			} else {
				file.setName(fileName);
				file.setPath(path);
				file.setCreatedDateTime(new Date());
				file.setNumberOfDownload(0);
				file.setVersion(version);
				file.setStatus(true);
				fileRepository.save(file);
				message = "The file has been uploaded successfully!";
				//save in configured location
				try {			
					Files.copy(multipartFile.getInputStream(), dir.resolve(multipartFile.getOriginalFilename()));
				} catch(Exception e) {
					message = "Could not store the file.";
					throw new RuntimeException(message + "Error: " + e.getMessage());
				}
			}
		}
		return message;
	}
	
	private int checkVersion(MultipartFile multipartFile, String fileName) {
		int version = 1; 	
		String versionFile = fileRepository.findByMaxVersion(fileName);
		
		if(versionFile != null) {
			File dbFile = fileRepository.findByNameAndVersion(fileName, Integer.parseInt(versionFile));
			if(dbFile != null) {
				version = dbFile.getVersion() + 1;
			}
		}
		return version;
	}
}
