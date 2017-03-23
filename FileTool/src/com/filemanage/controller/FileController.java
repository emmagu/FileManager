package com.filemanage.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filemanage.service.FileService;
import com.filemanage.entry.FileEntry;

@RestController
public class FileController {
	private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
	@Autowired
    FileService fileService;
	
	//------------------- Update a FileEntry --------------------------------------------------------
	@RequestMapping(value = "/upload", produces = "application/json")
    public FileEntry uploadFile(@RequestParam MultipartFile file, @RequestParam String userId){
        FileEntry metaData = new FileEntry();
        metaData.setUploadTime(new Date());
        metaData.setUserId(userId);
        LOG.info(metaData.toString());
        return fileService.updateFile(file,metaData);
    }
	
	//------------------- Get FileEntries --------------------------------------------------------
	@RequestMapping(value = "/file/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FileEntry> findMeta(@RequestParam String keyword){
        LOG.info("Searching by keyword: " + keyword);
        return fileService.searchFile(keyword);
    }
	
	//------------------- Get a File --------------------------------------------------------
	@RequestMapping(value = "/file/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public FileEntry getFile(@PathVariable("id") long id) {
		LOG.info("Fetching File with id " + id);
		FileEntry f = fileService.findById(id);
		if (f == null) {
			LOG.info("File with id " + id + " not found");
		}
		return fileService.findById(id);
	}
}
