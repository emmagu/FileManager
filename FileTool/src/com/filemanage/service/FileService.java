package com.filemanage.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.filemanage.entry.FileEntry;

public interface FileService {
	FileEntry uploadFile(MultipartFile file, FileEntry metaData);

    List<FileEntry> searchFile(String keyword);
    /*
    FileEntry findById(long id);
    */
}
