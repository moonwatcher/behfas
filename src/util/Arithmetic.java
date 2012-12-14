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
 
package util;

public final class Arithmetic {
	public static double max(double a, double b){
		return (a > b ? a : b);
	}
	
	public static double max(double a, double b, double c){
		return (a > b ?(a > c ? a : c) : (b > c ? b : c)); 	
	}
	
	public static double square(double a){
		return a*a;
	}
}
