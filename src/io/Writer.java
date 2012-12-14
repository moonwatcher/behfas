package io;

import java.io.File;
import java.io.FileWriter;
import java.util.GregorianCalendar;

public class Writer {
	//private final static String fileSeparator = System.getProperty("file.separator");
	private final static String lineSeparator = System.getProperty("line.separator");
	private GregorianCalendar calendar;
	
	private File file;
	private FileWriter fileWriter;
	private String filename;
	private boolean echo;
    
	public Writer(String filename, boolean echo){
		this.filename = filename;
		this.echo = echo;
		calendar = new GregorianCalendar();
		open();
	}

	public String now(){
		calendar.setTimeInMillis(System.currentTimeMillis());
		return calendar.getTime().toString();
	}
	
    public void open() {
		try {
			file = new File(filename + ".txt");
			fileWriter = new FileWriter(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    public void close() {
    	try {
    		fileWriter.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
	public void writeLine(String line){
		try {
			String processedLine = "[" + now() + "] " + line;
			fileWriter.write(processedLine + lineSeparator);	
			if(echo){System.out.println(processedLine);}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
