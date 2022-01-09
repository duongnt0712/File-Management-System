package com.filemanagementsystem.utils;

import org.springframework.web.multipart.MultipartFile;

import com.filemanagementsystem.entity.File;
import com.filemanagementsystem.entity.Setting;

public class FileUtils {
	
	public static boolean validateIsEmptyFile(MultipartFile multipartFile) {
		return multipartFile.isEmpty();
	}
	
	public static boolean validateFileSize(MultipartFile multipartFile, File file, Setting setting) {
		boolean checkFileSize = false;
		if (multipartFile.getSize() <= setting.getMaxFileSize()) {
			checkFileSize = true;
			file.setFileSize(multipartFile.getSize());
		} 
		return checkFileSize;
	}
	
	public static boolean validateMimeType(MultipartFile multipartFile, File file, Setting setting) {
		String mimeTypeAllowed = setting.getMimeTypeAllowed();
		boolean checkMime = false;
		
		if (mimeTypeAllowed.equalsIgnoreCase("Image")) {
			checkMime = validateImageFile(multipartFile);
		} else if (mimeTypeAllowed.equalsIgnoreCase("PDF")) {
			checkMime = validatePDFFile(multipartFile);
		} else if (mimeTypeAllowed.equalsIgnoreCase("MP3")) {
			checkMime = validateMP3File(multipartFile);
		} else { // Mime type is "--All--"
			checkMime = true;
		}
		
		if (checkMime) {
			file.setMime(multipartFile.getContentType());
		}
		return checkMime;		
	}
	
	public static boolean validateMP3File(MultipartFile multipartFile) {
		return multipartFile.getContentType().equalsIgnoreCase("audio/mpeg");
	}
	public static boolean validateImageFile(MultipartFile multipartFile) {
		return multipartFile.getContentType().equalsIgnoreCase("image/jpeg") || multipartFile.getContentType().equalsIgnoreCase("image/png");
	}
	public static boolean validatePDFFile(MultipartFile multipartFile) {
		return multipartFile.getContentType().equalsIgnoreCase("application/pdf");
	}
}
