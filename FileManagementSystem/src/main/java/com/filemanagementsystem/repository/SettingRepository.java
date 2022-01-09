package com.filemanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.filemanagementsystem.entity.Setting;

@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer>{
	
	@Query(value = "SELECT MAX(id) FROM `file-management`.setting", nativeQuery = true)
	public Integer getMaxId();
}
