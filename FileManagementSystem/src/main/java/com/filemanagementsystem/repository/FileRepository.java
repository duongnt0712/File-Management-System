package com.filemanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.filemanagementsystem.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long>, PagingAndSortingRepository<File,Long> {
	
	@Query(value = "SELECT MAX(version) FROM `file-management`.file AS fm WHERE fm.name = ?1", nativeQuery = true)
	public String findByMaxVersion(String fileName);
	
	@Query(value = "SELECT * FROM `file-management`.file AS fm WHERE fm.name = ?1 AND fm.version = ?2",
			nativeQuery = true)
	public File findByNameAndVersion(String fileName, int version);
}
