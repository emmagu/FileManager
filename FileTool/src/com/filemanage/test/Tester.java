
package com.filemanage.test;

import java.io.File;

import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class Tester {
	@Test
	public void test(){
	    String url = "http://127.0.0.1:8080/file";
	    String filePath = "/Users/Jennifer/Desktop/Resume_Emma Gu.doc";  
	    RestTemplate rest = new RestTemplate();  
	 
	    FileSystemResource resource = new FileSystemResource(new File(filePath));  
	    MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();  
	    map.add("file", resource);  
	    map.add("userId", "testtest");
	  
	    String string = rest.postForObject(url, map, String.class);  
	    System.out.println(string);  
	}
}