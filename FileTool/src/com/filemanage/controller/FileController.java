package com.filemanage.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PathVariable;

import com.filemanage.service.FileService;
import com.filemanage.entry.FileEntry;

@RestController
@RequestMapping(value="/file")
public class FileController {
	private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
	@Autowired
    FileService fileService;
	
	//------------------- Upload a FileEntry --------------------------------------------------------
	@RequestMapping(value = "", method=RequestMethod.POST, produces = "application/json")
    public FileEntry uploadFile(@RequestParam MultipartFile file, @RequestParam String userId){
        FileEntry metaData = new FileEntry();
        metaData.setUploadTime(new Date());
        metaData.setUserId(userId);
        metaData.setFileName(file.getOriginalFilename());
        metaData.setDocId(file.getOriginalFilename() + System.currentTimeMillis());
        LOG.info(metaData.toString());
        return fileService.uploadFile(file,metaData);
    }
	
	//------------------- Get FileEntries --------------------------------------------------------
	@RequestMapping(value = "/{keyword}", method = RequestMethod.GET, produces = "application/json")
    public List<FileEntry> findMeta(@PathVariable String keyword){
        LOG.info("Searching by keyword: " + keyword);
        return fileService.searchFile(keyword);
    }
	
	//------------------- Get a File --------------------------------------------------------
	/*
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public FileEntry getFile(@PathVariable("id") long id) {
		LOG.info("Fetching File with id " + id);
		FileEntry f = fileService.findById(id);
		if (f == null) {
			LOG.info("File with id " + id + " not found");
		}
		return fileService.findById(id);
	}
	*/
}
