package com.test.unify;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainClass {

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.washingtonbeerblog.com/wp-content/uploads/2012/10/crash_test1-276x300.jpg");

		URLConnection connection = url.openConnection();
		String path ="/opt/DownloadingFile.jpg";
		File fileThatExists = new File(path); 
		OutputStream output = new FileOutputStream(path, true);
		InputStream input = null;
		connection.setRequestProperty("Range", "bytes=" + fileThatExists.length() + "-");

		connection.connect();

		int lenghtOfFile = connection.getContentLength();
		
		if(lenghtOfFile > 0){
			input = new BufferedInputStream(connection.getInputStream());
			byte data[] = new byte[1024];

			long total = 0;

			int count;
			
			while ((count = input.read(data)) != -1) {
			    total += count;
			    /*if(total > 7000){
			    	break;
			    }*/
			    output.write(data, 0, count);
			}
		}

		
		if(output != null && input != null){
			output.close();
			input.close();
		}

	}

}
