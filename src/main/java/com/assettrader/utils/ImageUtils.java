package com.assettrader.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;


public class ImageUtils {
	
	
	public void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream inStream = url.openStream();
		OutputStream outStream = new FileOutputStream(destinationFile);
		
		byte[] b = new byte[2048];
		int length;
		
		while ((length = inStream.read(b)) != -1) {
			outStream.write(b, 0, length);
		}
		
		inStream.close();
		outStream.close();		
	}

}
