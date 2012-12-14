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
