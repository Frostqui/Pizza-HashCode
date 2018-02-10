package io.github.frostqui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileParser {

	private File file;
	
	private int rows;
	private int columns;
	private int ing_per_slice;
	private int cells_per_slice;
	
	private String path;
	
	private Pizza pizza;
	
	public FileParser(String path) {
		
		this.file = new File(path);
		this.path = path;
		System.out.println(this.file.getAbsolutePath());
		
		
	}
	
	
	public void read() {
		
         BufferedReader input;
         try {
             input = new BufferedReader(new FileReader(path));
             String line = input.readLine();
             String pieces[] = line.split(" ");
             
             rows = Integer.parseInt(pieces[0]);                
             columns = Integer.parseInt(pieces[1]);
             ing_per_slice = Integer.parseInt(pieces[2]);
             cells_per_slice = Integer.parseInt(pieces[3]);
             
             
             
             pizza = new Pizza(rows, columns, ing_per_slice, cells_per_slice);
            
             for (int i = 0; i < rows; i++) {
                 line = input.readLine();
                 pieces = line.split("");
                 for (int j = 0; j < columns; j++) {
                     pizza.getCells()[i][j] = pieces[j];
                 }
             } 
             //line = input.readLine();
            // String aux[] = line.split(" ");
             
         } catch (IOException e) {
             e.printStackTrace();
         }

	}
	
	
	public void write() {
		System.out.println();
		
		System.out.println("Rows: "+ pizza.getRows());
		System.out.println("Columns: "+ pizza.getColumns());
		System.out.println("Ingredients per slice: "+ pizza.getIng_per_slice());
		System.out.println("Max number of cells per slice: "+ pizza.getCells_per_slice());
		
		System.out.println();
		
		System.out.println("Pizza: ");
		
		System.out.println();
		
		for (int i = 0; i<pizza.getRows(); i++) {
			for (int j = 0; j<pizza.getColumns(); j++) {
				System.out.print(pizza.getCells()[i][j] + "  ");
			}
			
			 System.out.println();
		
		}
	
	}
	
	public Pizza getPizza() {
		return this.pizza;
	}
	
}
