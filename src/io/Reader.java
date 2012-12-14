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
