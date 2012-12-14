package main;

import error.ArgumentException;
import io.Reader;
import io.Writer;
import model.Domain;
import process.Processor;

public class Runner {
	public static void main(String[] args) {
		try	{
			if(args.length > 0){
				String command = args[0];
				if(command.compareTo("--hap") == 0){
					try{
						String input = args[1];
						int br = Integer.parseInt(args[2]);
						int width = Integer.parseInt(args[3]);
						processHaplotype(input, br, width);
					} catch (ArrayIndexOutOfBoundsException e){
						throw new ArgumentException();
					}
				} else {
					if(command.compareTo("--help") == 0){
						printHelp();
					}
				}
			} else {
				printHelp();
			}
		}	catch(Exception e){
			System.out.println(e.getMessage());
			printHelp();
		}
	}
	
	private static void printHelp(){
		System.out.println("Binary Exhaustive Haplotype Fragment Association Search.");
		System.out.println("Lior Galanti lg8@sanger.ac.uk, 2006.\n");
		System.out.println("--hap <input> <break> <width>");
		System.out.println("	input: (String) <input file location>");
		System.out.println("	break: (Integer) <case/control break>");
		System.out.println("	width: (Integer) <number of SNP columns>");
		System.out.println("	-- Any line not contaning <width> number of {0,1} charecters will be ignored (spaces are removed).");
		System.out.println("	-- First <break> lines will be considered cases, the rest controls.");
	}
	
	private static void processHaplotype(String input, int breakPoint, int width) throws Exception{
		Reader reader = new Reader(width);
		Domain domain = reader.read(input, breakPoint);
		reader= null;
		
		Processor processor = new Processor(domain, new Writer(input, true));
		processor.execute();
	}
}
