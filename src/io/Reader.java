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

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import model.BinaryMatrix;
import model.Domain;

import org.apache.solr.util.OpenBitSet;

public class Reader {
	private Pattern pattern;
	private int width;
	
	public Reader(int width){
		this.width = width;
		pattern = Pattern.compile("[01\n]{" + width + "}");
	}
	
	private OpenBitSet convertToBinaryVector(String line, int width){
		OpenBitSet openBitSet = new OpenBitSet(width);
		for(int i=0; i<width; i++){
			if('1' == line.charAt(i)){openBitSet.set(i);}
		} return openBitSet;
	}

	private boolean validate(String line){
		boolean match = pattern.matcher(line).matches();
		return match;
	}
	
	public Domain read(String filename, int breakPosition) throws Exception{
		ArrayList<OpenBitSet> sick = new ArrayList<OpenBitSet>();
		ArrayList<OpenBitSet> healthy = new ArrayList<OpenBitSet>();
		int count = 0;
		
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line = reader.readLine().replace(" ", "");
		while(null != line){
			if(validate(line)){			
				if(count < breakPosition){
					sick.add(convertToBinaryVector(line, width));
					count++;
				} else {
					healthy.add(convertToBinaryVector(line, width));
					count++;
				}			
			}
			line = reader.readLine();
		}
		reader.close();
		return new Domain(new BinaryMatrix(healthy, width), new BinaryMatrix(sick, width));
	}
}
