package com.fanfodder.manager.common.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.zip.CRC32;

import org.apache.commons.io.FileUtils;

public class StringUtils {

	public static String stripHTML(String html) {
		if (StringUtils.isEmptyOrNull(html)) {
			throw new IllegalArgumentException("html string is empty or null");
		}
		
		return html.replaceAll("\\<.*?\\>","");
	}
	
	
	public static String truncate(String value, int len) {
	   String newValue = StringUtils.getNonNullValue(value);
	   
	   if ( newValue.length() > len) {
		   newValue = newValue.substring(0, len);
	   }
	   
	   return newValue;
	}
	
	
	public static String getNonNullValue(String value) {
		return (value == null) ? "" : value;
	}
	
	
    public static long getCheckSum(String contents) {
        long sum = 0L;
        
        if (contents != null) {
            CRC32 crc = new CRC32();
            crc.update(contents.getBytes());
            sum = crc.getValue();
        }
        
        return sum;
    }
    
    public static String getFileAsString(File f) 
    throws IOException {
        String contents = null;
        
        // using platform default encoding:  could be a problem
        contents = FileUtils.readFileToString(f, null);
        
        return contents;
    }
    
    public static boolean isEmptyOrNull(String s) {
        boolean result = false;

        if (s == null) {
            result = true;
        } else {
            s = s.trim();
            if (s.length() == 0) {
                result = true;
            }
        }
        return result;
    }
    
	/**
	 * create a guid (ripped off from saturn)
	 *
	 * @return String guid
	 */
	public static String getGuid()	{
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("" + System.currentTimeMillis());
		sbuf.append("-");
		Random rand = new Random();
		sbuf.append("" + rand.nextInt());
		sbuf.append("-");
		sbuf.append("" + getNextInt());
		return sbuf.toString();
	}

	private static int nextInt = 0;
	
	private static synchronized int getNextInt()
	{
		int temp = nextInt;
		nextInt++;
		return temp;
	}
	
	
	/**
	* Gets the exception stack trace as a string.
    *
	* @param exception
	* @return
	*/
	public static String getStackTraceAsString(Exception exception)	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		pw.println(" [ " + exception.getClass().getName() + " ] ");		
		exception.printStackTrace(pw);
		return sw.toString();
	}
	
	public static String escapeXML(String str) {
		if ( str != null ) {
			return str.replaceAll("&","&amp;").
			       replaceAll("<","&lt;").
			       replaceAll(">","&gt;").
			       replaceAll("'","&apos;");
		}
		
		return str;
	}
	
	public static String unescapeSql( String s ) {
	  return s;
	}
}
