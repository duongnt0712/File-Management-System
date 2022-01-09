package com.filemanagementsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="setting")
public class Setting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="max_file_size")
	private long maxFileSize;
	
	@Column(name="item_per_page")
	private int itemPerPage;
	
	@Column(name="mime_type_allowed")
	private String mimeTypeAllowed;
	
	@Column(name="last_update_time")
	private Date lastUpdateTime;
	
	public Setting() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getMaxFileSize() {
		return maxFileSize;
	}

	public void setMaxFileSize(long maxFileSize) {
		this.maxFileSize = maxFileSize;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public String getMimeTypeAllowed() {
		return mimeTypeAllowed;
	}

	public void setMimeTypeAllowed(String mimeTypeAllowed) {
		this.mimeTypeAllowed = mimeTypeAllowed;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
