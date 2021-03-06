package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.BlogDao;
import com.javaex.dao.UserDao;
import com.javaex.vo.BlogVo;
import com.javaex.vo.UserVo;

@Service
public class BlogService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private BlogDao blogDao;
	
//블로그 유저 가져오기	
   public UserVo getBlogUser(String id) {
	   	return userDao.getBlogUser(id);
   }
   
   public void modify(String id, MultipartFile file, BlogVo blogVo) {
	   
	   //블로그 아이디
	   blogVo.setId(id);
	   
	  //저장경를 변수로
	   String saveDir="C:\\javaStudy\\upload\\";
	  //파일을 하드디스크에 저장(운영내용)
 		//원본파일 이름
 		String orgName = file.getOriginalFilename();
 	
 		//확장자                               //점뒤에 다가져오기                     //점으로 위치 지정
 		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
 		 
 		//저장파일이름                      //현재시간을 숫자로             //랜덤 이름 생성 
 		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;
 
 		
 		//파일패스 생성
 		String filePath = saveDir + saveName; 

 		
 		//파일사이즈
 		long fileSize = file.getSize();

 		
 		//파일 저장(업로드)
 				try {
 					byte[] fileData = file.getBytes();
 					//저장할 이름이랑 경로 까지 같이 입력
 					
 					OutputStream out = new FileOutputStream(filePath);
 					BufferedOutputStream bout = new BufferedOutputStream(out);
 					
 					bout.write(fileData);
 					bout.close();
 					
 				}catch(IOException e) {
 					e.printStackTrace();
 				}
 				blogVo.setLogoFile(saveName);
 				blogDao.update(blogVo);
	 			
	   
   }

}
