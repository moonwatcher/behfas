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
 
package model;

import java.util.ArrayList;

import org.apache.solr.util.OpenBitSet;

public class BinaryMatrix {
	private ArrayList<OpenBitSet> transpose;
	private ArrayList<OpenBitSet> invertedTranspose;

	public int width;
	public int height;
	
	public OpenBitSet getColumn(int index) throws Exception{
		return (OpenBitSet)transpose.get(index).clone();
	}
	
	public OpenBitSet getInvertedColumn(int index) throws Exception{
		return (OpenBitSet)invertedTranspose.get(index).clone();
	}
	
	public BinaryMatrix(ArrayList<OpenBitSet> matrix, int width) {
		this.width = width;
		this.height = matrix.size();
		transpose = new ArrayList<OpenBitSet>(width);
		invertedTranspose = new ArrayList<OpenBitSet>(width);
		populateTranspose(matrix);
		populateInvertedTranspose();
	}
	
	private void populateInvertedTranspose(){
		for(OpenBitSet column : transpose){
			OpenBitSet inverted = (OpenBitSet)column.clone();
			inverted.flip(0,height);
			invertedTranspose.add(inverted);
		}
	}		
	
	private void populateTranspose(ArrayList<OpenBitSet> matrix){
		for(int j=0; j<width; j++){
			OpenBitSet column = new OpenBitSet(height);
			for(int i=0; i<height; i++){
				if(matrix.get(i).fastGet(j)){column.fastSet(i);}
			}	transpose.add(column);
		}
	}
}
