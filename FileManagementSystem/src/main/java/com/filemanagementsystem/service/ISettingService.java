package com.filemanagementsystem.service;

import com.filemanagementsystem.entity.Setting;

public interface ISettingService {
	
	public Setting getCurrentSetting();
	
	public String saveSetting(Setting setting);
	
}
