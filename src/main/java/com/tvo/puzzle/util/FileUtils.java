package com.tvo.puzzle.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.List;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FileUtils {

	public static InputStream readFile(String filepath)
			throws FileNotFoundException {
		return new BufferedInputStream(new FileInputStream(filepath));
	}

	public static boolean isExists(String filepath) {
		if (!CommonUtils.isEmpty(filepath)) {
			File f = new File(filepath);
			if (f.exists()) {
				return true;
			}
		}
		return false;
	}

	public static boolean isFile(String filepath) {
		if (isExists(filepath)) {
			File f = new File(filepath);
			if (f.isFile()) {
				return true;
			}
		}
		return false;
	}

	public static String downloadFile(String filename,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		try {
			// Get the MIME type of the image
			String mimeType = request.getServletContext().getMimeType(filename);
			/*
			 * if (mimeType == null) { sc.log("Could not get MIME type of " +
			 * filename);
			 * response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			 * return; }
			 */
			// Set content size
			byte[]file_byte =download(FileUtils.getDataRootDir() + filename);
			response.setContentLength((int) file_byte.length);
			response.setContentType((mimeType != null) ? mimeType
					: "application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ filename + "\"");
			response.setHeader("File-Name", filename);

			// Open the file and output streams
			ByteArrayInputStream in = new ByteArrayInputStream(file_byte);
			OutputStream out = response.getOutputStream();

			// Copy the contents of the file to the output stream
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
			in.close();
			out.close();
			return null;
		} catch (Exception e) {
			return " file requested: " + FileUtils.getDataRootDir() + filename
					+ "\n" + e.toString();
		}
	}

	public static void copyFile(File in, File out) throws Exception {
		FileInputStream fis = new FileInputStream(in);
		FileOutputStream fos = new FileOutputStream(out);
		try {
			byte[] buf = new byte[1024];
			int i = 0;
			while ((i = fis.read(buf)) != -1) {
				fos.write(buf, 0, i);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
		}
	}

	/*
	 * public static String getTomcatPath(HttpServletRequest request) { String
	 * f0 = request.getServletContext().getRealPath("/"); File f = new File(f0);
	 * String f1 = f.getParent(); File f2 = new File(f1); String f3 =
	 * f2.getParent(); return f3; }
	 */

	public static String getDataRootDir() {
		// Get the inputStream
		InputStream inputStream = FileUtils.class.getClassLoader()
				.getResourceAsStream("/META-INF/system-config.properties");

		Properties properties = new Properties();
		// load the inputStream using the Properties
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// get the value of the property
		String propValue = properties.getProperty(Constants.ROOR_DIR);

		return propValue;

	}
	
	/**
	 * convert from file to byte array
	 *
	 * @author MinhTQ
	 *
	 * @param path
	 * @return 
	 *
	 */
	public static byte[] fileTobyte(String path) {
	    File f = new File(getDataRootDir()+ path);
	    FileInputStream fin = null;
	    FileChannel ch = null;
	    byte[] bytes = null;
	    try {
	        fin = new FileInputStream(f);
	        ch = fin.getChannel();
	        int size = (int) ch.size();
	        MappedByteBuffer buf = ch.map(MapMode.READ_ONLY, 0, size);
	        bytes = new byte[size];
	        buf.get(bytes);

	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (fin != null) {
	                fin.close();
	            }
	            if (ch != null) {
	                ch.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return bytes;
	}
    public static byte[] getBytesFromInputStream(InputStream is) throws IOException {
        int len;
        int size = 1024;
        byte[] buf;
        size = is.available();
        if (is instanceof ByteArrayInputStream) 
        {
          buf = new byte[size];
          len = is.read(buf, 0, size);
        } else {
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          buf = new byte[size];
          while ((len = is.read(buf, 0 , size)) != -1) {
            bos.write(buf, 0, len);
            buf = bos.toByteArray();
          }
        }
        return buf;
      }
	public static byte[] download(String filename) {
		byte [] buf = null ;
		try {
			InputStream is = new FileInputStream(new File(filename)); 
			if(null != is){
				buf = FileUtils.getBytesFromInputStream(is);
			}
			return buf;
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return null;
	}
	public static boolean checkFileType(String fileName, String fileType) {
		try {
			List<String> extension = StringUtil.separateString(fileType);
			for (String s : extension) {
				if (fileName.toLowerCase().endsWith(s)) {
					return true;
				}
			}
		} catch (Exception e) {
		}
		return false;
	}

	public  static String getTypeOfImageByUrl(String fileName)
		
		{
			String type = "";
			if(checkFileType(fileName, "jpg"))
			{
				type= "jpg";
			}
			else if(checkFileType(fileName, "png"))
			{
				type= "png";
			}
			else if(checkFileType(fileName, "gif"))
			{
				type= "gif";
			}
			return type;
		}
}
