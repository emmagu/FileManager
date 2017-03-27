package com.filemanage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.filemanage.entry.FileEntry;
import com.filemanage.exception.FileUploadException;
import com.filemanage.model.FileData;
import com.filemanage.service.FileService;
import com.filemanage.service.FileServiceImpl;

import com.filemanage.dao.FileRepo;
import com.filemanage.dao.FileUpload;

@Service
public class FileServiceImpl implements FileService{
	private static final Logger LOG = LoggerFactory.getLogger(FileServiceImpl.class);
	@Autowired
    private FileRepo fileRepo;
	
	@Autowired
    private FileUpload fileUp;
	
	/*
	private static final AtomicLong counter = new AtomicLong();
	private static List<FileEntry> files;
	static{
		files= populateDummyFiles();
	}
	*/
	
	@Override
	@Transactional
	public FileEntry uploadFile(MultipartFile file, FileEntry metaData) {
		// TODO Auto-generated method stub
		FileData fileData = new FileData(metaData);
		/*
		fileData.setUserId(metaData.getUserId());
        fileData.setFileName(file.getOriginalFilename());
        metaData.setDocId(file.getOriginalFilename() + System.currentTimeMillis());
        fileData.setUploadTime(metaData.getUploadTime());
        FileData savedFileData = fileRepo.save(fileData);
        metaData.setId(savedFileData.getId());
        metaData.setFileName(file.getOriginalFilename());
        metaData.setDocId(fileData.getDocId());
        LOG.info("DocId: " + fileData.getDocId());
        return metaData;
        */
        LOG.info(file + " " + file.getContentType());
        fileRepo.save(fileData);
        try {
			fileUp.saveFile(file.getBytes(), fileData.getDocId());
		} catch (IOException e) {
			throw new FileUploadException(e);
		}
        return metaData;
	}

	/*
	private static List<FileEntry> populateDummyFiles() {
		// TODO Auto-generated method stub
		List<FileEntry> files = new ArrayList<>();
        files.add(new FileEntry(counter.incrementAndGet(), "emma", "FINRA", "FINRAAPP", new Date()));
        files.add(new FileEntry(counter.incrementAndGet(), "Gu", "FINRA2", "FINRAAPP2", new Date()));
		return files;
	}
	*/

	@Override
	@Transactional(readOnly = true)
	public List<FileEntry> searchFile(String keyword) {
		// TODO Auto-generated method stub
		List<FileData> entryList = fileRepo.findByFileNameOrUserId(keyword);
        List<FileEntry> resList = new ArrayList<>();
        for (FileData data : entryList) {
            resList.add(new FileEntry(data.getId(),data.getUserId(),data.getFileName(),data.getDocId(), data.getUploadTime()));
        }
        return resList;
	}
	
	/*
	@Override
	public FileEntry findById(long id) {
		// TODO Auto-generated method stub
		for(FileEntry f : files){
			if(f.getId() == id){
				return f;
			}
		}
		return null;
	}
	*/

}
