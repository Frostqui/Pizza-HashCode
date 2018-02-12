package io.github.frostqui;

public class Main {

	private static int total_slices;
	
	
	public static void main(String args[]){
		
		FileParser p = new FileParser("src/io/github/frostqui/example.in");
		
		p.read();
		p.write();
		
		System.out.println("----------------------------------");
		
	
		PizzaCut pc = new PizzaCut(p.getPizza());
		
		pc.solution();
		
		System.out.println("");
		System.out.println("Solución: ");
		
		pc.printSlices();
		
						
	}
	
	
}
