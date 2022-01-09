package com.filemanagementsystem.utils;

import com.filemanagementsystem.entity.Setting;

public class SettingUtils {
	
	public static boolean validateMaxFileSize(Setting setting) {
		return setting.getMaxFileSize() >= 0;
	}
	
	public static boolean validateItemPerPage(Setting setting) {
		int itemPerPage = setting.getItemPerPage();
		return (itemPerPage > 0) && (itemPerPage == (int) itemPerPage);
	}
}
