package com.filemanage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.filemanage.model.FileData;

@Repository
public interface FileRepo extends JpaRepository<FileData,Long>{
	@Query("SELECT D from FileData D where D.fileName like %:keyword% or D.userId like %:keyword%")
    List<FileData> findByFileNameOrUserId(@Param("keyword") String keyword);
    List<FileData> findByFileNameIgnoreCaseContaining(String keyword);
}
