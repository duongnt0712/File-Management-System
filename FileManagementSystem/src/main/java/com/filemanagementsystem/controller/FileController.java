package com.filemanagementsystem.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filemanagementsystem.entity.File;
import com.filemanagementsystem.entity.Setting;
import com.filemanagementsystem.service.impl.DownloadFileService;
import com.filemanagementsystem.service.impl.FileService;
import com.filemanagementsystem.service.impl.SettingService;
import com.filemanagementsystem.service.impl.UploadFileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private DownloadFileService downloadFileService;
	
	@Autowired
	private SettingService settingService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {		    
		return paginationTable(model, 1);
	}
	
	@GetMapping("/page/{pageNum}") 
	public String paginationTable(Model model, @PathVariable int pageNum) {
		Setting setting = settingService.getCurrentSetting();
		Page<File> page = fileService.findAllFiles(pageNum, setting.getItemPerPage());
	    List<File> listAllFiles = page.getContent();
	    
	    model.addAttribute("currentPage", pageNum);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("listAllFiles", listAllFiles);
	    model.addAttribute("setting", setting);
	    
	    List<String> mimeTypeList = Arrays.asList("--All--", "Image", "PDF", "MP3");
	    model.addAttribute("mimeTypeList", mimeTypeList);
	     
		return "index";
	}
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile[] multipartFile, RedirectAttributes rA) throws IOException {	
		Setting setting = settingService.findByMaxId();
		String message = uploadFileService.uploadFiles(multipartFile, setting);		 
		
		rA.addFlashAttribute("message", message);
		return "redirect:/";
	}
	
	@GetMapping("/download/{id}")
	public void downloadFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
		downloadFileService.downloadFile(id, response);
	}
	
	@GetMapping("/view/{id}")
	public void viewFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
		File file = downloadFileService.findById(id);
	    fileService.viewFile(file, response);
	}
	
	@GetMapping("/delete/{id}")
	public String deleteFile(@PathVariable Long id, RedirectAttributes rA) {
		String message = fileService.deleteFile(id);
		rA.addFlashAttribute("message", message);
		return "redirect:/";
	}
}
