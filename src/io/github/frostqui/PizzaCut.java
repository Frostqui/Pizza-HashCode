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

	public PizzaCut(Pizza pizza) {
		this.pizza = pizza;
		this.slices = new ArrayList<Slice>();
	}

	public void solution() {

		int x = 2;
		int y = 1;

		int i = 0;
		int j = 0;

		while (x < pizza.getRows() && y < pizza.getColumns()) {

			System.out.println("-------");
			for (int a = i; a <= x; a++) {
				for (int b = j; b <= y; b++) {

					checkCount(a, b);

				}

			}

			

			j += 2;
			y += 2;

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

	public static boolean esMultiplo(int n1, int n2) {
		if (n1 % n2 == 0)
			return true;
		else
			return false;
	}

}
