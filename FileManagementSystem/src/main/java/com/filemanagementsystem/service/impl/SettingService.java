package com.filemanagementsystem.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filemanagementsystem.entity.Setting;
import com.filemanagementsystem.repository.SettingRepository;
import com.filemanagementsystem.service.ISettingService;
import com.filemanagementsystem.utils.SettingUtils;

@Service
public class SettingService implements ISettingService {

	@Autowired
	private SettingRepository settingRepository;
	
	@Override
	public Setting getCurrentSetting() {
		Setting setting = findByMaxId();
		if(setting == null) {
			setting = new Setting();
			setting.setItemPerPage(5);
			setting.setMaxFileSize(5242880);
			setting.setLastUpdateTime(new Date());
			setting.setMimeTypeAllowed("--All--");
			settingRepository.save(setting);
		}
		return setting;
	}
	
	@Override
	public String saveSetting(Setting setting) {
		String message = "";
		long maxFileSize = setting.getMaxFileSize();
		setting.setMaxFileSize(maxFileSize * 1024 * 1024);
		if (!SettingUtils.validateMaxFileSize(setting)) {
			message = "Invalid file size setting. Please try again!";
		} else if (!SettingUtils.validateItemPerPage(setting)) {
			message = "Invalid item per page. Please try again!";
		} else {
			setting.setLastUpdateTime(new Date());
			settingRepository.save(setting);
			message = "Updated setting successfully!";
		}
		return message;
	}
	
	public Setting findByMaxId() {
		Setting setting = null;
		Integer id = settingRepository.getMaxId();
		if (id != null) {
			setting = settingRepository.findById(id).get();
		}
		return setting;
	}
}
