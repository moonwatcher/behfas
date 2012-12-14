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
 
package process;

import io.Writer;
import model.Domain;
import model.Node;
import model.Node.Polarity;
import util.Arithmetic;

public class Processor {
	private double maxChiSquared;
	private Domain domain;
	private long start;
	
	public void execute() throws Exception{
		Node root, node;
		start = System.currentTimeMillis();
		domain.writer.writeLine("Binary Exhaustive Haplotype Fragment Association Search.");
		domain.writer.writeLine("Lior Galanti lg8@sanger.ac.uk, 2006.\n");
		domain.writer.writeLine("Sick samples: "  + domain.sickPopulationSize);
		domain.writer.writeLine("Healthy samples: "  + domain.healthyPopulationSize);
		domain.writer.writeLine("SNPs in samples: "  + domain.sampleSize + "\n");

		
		for(int i=0; i<domain.sampleSize; i++){
			root = new Node(domain, null, i-1, Polarity.root);			
			node = root;			
			while(null != node){node = node.next();}
			this.maxChiSquared = Arithmetic.max(this.maxChiSquared, root.maxChiSquared);
			domain.writer.writeLine("Max starting at " + (i+1) + ": " + root.maxChiSquared);
		}		
		domain.writer.writeLine("Global Max: " + maxChiSquared + "\n");
		domain.writer.writeLine("Proccessing took: " + duration() + "ms");		
		domain.writer.close();
	}
	
	public Processor(Domain domain, Writer writer) {
		this.maxChiSquared = 0.0;
		this.domain = domain;
		this.domain.writer = writer;
	}
	
	public long duration(){
		return (System.currentTimeMillis() - start);
	}	
}
