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
