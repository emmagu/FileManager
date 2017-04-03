package com.filemanage.entry;

import java.util.Date;
//DTO
public class FileEntry {
	private Long fileId;
    private String userId;
    private String fileName;
    private String docId;
    private Date uploadTime;
    
    public FileEntry(){};
    public FileEntry(Long fileId, String userId, String fileName, String docId, Date uploadTime) {
        this.fileId = fileId;
        this.userId = userId;
        this.fileName = fileName;
        this.docId = docId;
        this.uploadTime = uploadTime;
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
    
    @Override
    public String toString() {
        return "FileMetaData{" +
                "fileId='" + fileId + '\'' +
                ", userId='" + userId + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
