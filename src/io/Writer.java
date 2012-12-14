/* 
 * BEHFAS. Binary Exhaustive Haplotype Fragment Association Search.
 * 
 * Copyright (c) 2006 Genome Research Ltd.
 * 
 * Author: Lior Galanti <lior.galanti@gmail.com>
 * 
 * This file is part of BEHFAS.
 * BEHFAS is free software; you can redistribute it and/or modify it under the terms of 
 * the GNU General Public License as published by the Free Software Foundation; 
 * either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 */

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
