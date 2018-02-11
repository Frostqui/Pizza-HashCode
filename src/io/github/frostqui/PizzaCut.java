package io.github.frostqui;

import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;

public class PizzaCut {

	private Pizza pizza;

	private ArrayList<Slice> slices;

	int count = 0;

	int tomato_count = 0;
	int msroom_count = 0;

	private int starting_row;
	private int starting_column;

	private int end_row;
	private int end_column;

	private ArrayList<Integer> multiples;

	public PizzaCut(Pizza pizza) {
		this.pizza = pizza;
		this.slices = new ArrayList<Slice>();
		this.multiples = new ArrayList<Integer>();
	}

	public void solution() {

		int multiplo = pizza.getCells_per_slice();

		for (int x = 1; x < multiplo; x++) {
			if (isMultiple(multiplo, x)) {
				multiples.add(x);

			}
		}
		
		int i = 0;
		int j = 0;
		
		int x = 0;
		int y = 0;
		
		
		while (x != pizza.getRows() && y != pizza.getColumns()) {
			
		for (int c = 1; c < pizza.getCells_per_slice(); c++) {
			for (int d = 1; d < pizza.getCells_per_slice(); d++) {

				
				if ((c + 1) * (d + 1) <= pizza.getCells_per_slice()) {

					 x = c;
					 y = d;

					

					System.out.println("////////////////");
					
					System.out.println( x + " x " + y);
					
				
					System.out.println("////////////////");

					
						
						
						while (y <= pizza.getRows() && x <= pizza.getColumns()) {
							
							System.out.println("-------");
						for (int a = i; a <= x; a++) {
							for (int b = j; b <= y; b++) {

								if (a  <= pizza.getRows() && b  <= pizza.getColumns()) {
									checkCount(a, b);
								}

							}

						}

						if (y + d <= pizza.getColumns()) {
							
							j += d + 1;
							y += d + 1;
						} else if (x + c <= pizza.getRows()) {
						
							i += d + 1;
							x += d + 1;
						}

					}
				}
					x = c;
					y = d;

					i = 0;
					j = 0;
				}

			}
		}

	}

	public void checkCount(int i, int j) {

		System.out.println(i + " " + j);

		if (pizza.getCells()[i][j].charAt(0) == 'T') {
			if (tomato_count > pizza.getIng_per_slice()) {
				tomato_count++;
			}
		} else {
			if (msroom_count < pizza.getIng_per_slice()) {
				msroom_count++;
			}
		}

		count++;

		if (count == pizza.getCells_per_slice()) {

			end_row = i;
			end_column = j;
			Slice slice = new Slice(starting_row, starting_column, end_row, end_column);
			slices.add(slice);
			count = 0;

		}

		if (count == 0) {
			starting_row = i;
			starting_column = j;
		}
	}

	public ArrayList<Slice> getSlices() {
		return slices;
	}

	public void setSlices(ArrayList<Slice> slices) {
		this.slices = slices;
	}

	public void printSlices() {
		for (int i = 0; i < slices.size(); i++) {
			Slice s = slices.get(i);
			System.out.println(s.getStarting_row() + "  " + s.getStarting_column() + "  " + s.getEnd_row() + "  "
					+ s.getEnd_column());
		}
	}

	public static boolean isMultiple(int n1, int n2) {
		if (n1 % n2 == 0)
			return true;
		else
			return false;
	}

}
