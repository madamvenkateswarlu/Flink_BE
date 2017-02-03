package com.niit.flink.imageupload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public class ImageUpload {

	public static boolean UploadMethod(String path,MultipartFile file,String file_name){
		
		if(!file.isEmpty())
		{
		try {
			byte[] byte_image=file.getBytes();
			
			File dir=new File(path);
			if(!dir.exists()){
				
				dir.mkdirs();
			}
			
			File  uploading_file=new File(dir.getAbsolutePath()+File.separator+file_name);
			
			BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(uploading_file));
			bos.write(byte_image);
			bos.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		return false;
		
		
		
	}


}
