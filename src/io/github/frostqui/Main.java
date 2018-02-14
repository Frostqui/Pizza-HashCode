package io.github.frostqui;

public class Main {

		
	public static void main(String args[]){
		
		FileParser p = new FileParser("src/io/github/frostqui/medium.in");
		
		p.read();
		//p.write();
		
		System.out.println("----------------------------------");
		
	
		PizzaCut pc = new PizzaCut(p.getPizza());
		
		pc.solution();
		
			
						
	}
	
	
}
