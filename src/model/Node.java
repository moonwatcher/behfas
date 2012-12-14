package model;

import org.apache.solr.util.OpenBitSet;

import util.Arithmetic;

public class Node {
	public enum Polarity {origin, mutated, root};
	
	//	Domain
	public Domain domain;
	
	//	Tree Structure
	public Node origin;
	public Node mutated;
	public Node back;
	public int depth;
	public Polarity polarity;
	
	//	Current match
	public OpenBitSet matchSick;
	public OpenBitSet matchHealthy;
	
	//	Statistics
	public double sick;
	public double healthy;
	public double chiSquared;	
	public double maxChiSquared;
	
	public Node(Domain domain, Node back, int depth, Polarity polarity) throws Exception{
		this.domain = domain;
		this.depth = depth;
		this.polarity = polarity;
		this.back = back;
		this.origin = null;
		this.mutated = null;

		if(Polarity.origin == polarity) {
			matchSick = domain.sickPopulation.getColumn(depth);
			matchHealthy = domain.healthyPopulation.getColumn(depth);
			calculateStatisticValues();
			
		} else { if(Polarity.mutated == polarity) {
				matchSick = domain.sickPopulation.getInvertedColumn(depth);
				matchHealthy = domain.healthyPopulation.getInvertedColumn(depth);
				calculateStatisticValues();
			}		
		}
	}

	public Node next() throws Exception	{
		if(stop()) {
			maxChiSquared = chiSquared;
			return back;
			
		} else { 
			
			if(origin == null){
				origin = new Node(domain, this, depth + 1, Polarity.origin);
				return origin;
			}

			if(mutated == null){
				mutated = new Node(domain, this, depth + 1, Polarity.mutated);
				return mutated;
			}
			
			//	Clear the branch and fold back
			maxChiSquared = Arithmetic.max(chiSquared, origin.maxChiSquared, mutated.maxChiSquared);			
			origin = null;
			mutated = null;
			domain.writer.writeLine(this.depth + "," + maxChiSquared);
			return back;
		}
	}

	private boolean stop(){
		return (Polarity.root != polarity) && ((depth == domain.sampleSize-1) || (healthy  <= 0 || sick <= 0));
	}
	
	private void calculateStatisticValues(){
		if(Polarity.root != back.polarity){
			matchSick.and(back.matchSick);
			matchHealthy.and(back.matchHealthy);
		}
		sick = (double)matchSick.cardinality();
		healthy = (double)matchHealthy.cardinality();
		chiSquared = calculateChiSquared();
	}
	
	private double calculateChiSquared(){
		double result = 0.0;
		if(healthy > 0 || sick > 0){
			double match = sick + healthy;
			result = Arithmetic.square(sick * domain.healthyPopulationSize - healthy * domain.sickPopulationSize);
			if(result != 0){result /= (match *(domain.populationSize - match));}
		}	return result * domain.chiSquaredFactor;
	}	
}
