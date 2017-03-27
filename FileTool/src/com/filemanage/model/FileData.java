package com.filemanage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.filemanage.entry.FileEntry;

@Entity
@Table(name="file")
public class FileData {
	@Id
    @GeneratedValue
    private Long fileId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String docId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadTime;

    public FileData() {}
    
    public FileData(FileEntry file){
    	this.docId = file.getDocId();
    	this.fileName = file.getFileName();
    	this.uploadTime = file.getUploadTime();
    	this.userId = file.getUserId();
    }
    
    public Long getId() {
        return fileId;
    }
    public void setId(Long fileId) {
        this.fileId = fileId;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public String getDocId() {
        return docId;
    }
    public void setDocId(String docId) {
        this.docId = docId;
    }
    
    public Date getUploadTime() {
        return uploadTime;
    }
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
    
}
