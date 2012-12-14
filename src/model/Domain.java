package model;

import io.Writer;

public class Domain {
	//	Population Samples
	public BinaryMatrix sickPopulation;
	public BinaryMatrix healthyPopulation;
	public Writer writer;
	
	//	Population statistics
	public double sickPopulationSize;
	public double healthyPopulationSize;
	public double populationSize;
	public double sampleSize;
	public double chiSquaredFactor;
	
	public Domain (BinaryMatrix sickPopulation, BinaryMatrix healthyPopulation){
		this.healthyPopulation = healthyPopulation;
		this.sickPopulation = sickPopulation;
		
		this.sickPopulationSize = (double)sickPopulation.height;
		this.healthyPopulationSize = (double)healthyPopulation.height;
		
		this.populationSize = this.sickPopulationSize + this.healthyPopulationSize;
		this.sampleSize = healthyPopulation.width;
		this.chiSquaredFactor = populationSize / (sickPopulationSize * healthyPopulationSize); 
	}	
}
