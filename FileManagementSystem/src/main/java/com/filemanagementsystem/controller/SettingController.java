package com.filemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.filemanagementsystem.entity.Setting;
import com.filemanagementsystem.service.impl.SettingService;

@Controller
public class SettingController {

	@Autowired
	private SettingService settingService;

	@PostMapping("/setting")
	public String createSetting(@ModelAttribute("setting") Setting setting, RedirectAttributes rA) {
		String message = settingService.saveSetting(setting);
		rA.addFlashAttribute("message", message);
		return "redirect:/";
	}
}
