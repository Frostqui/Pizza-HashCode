package io.github.frostqui;

import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;

public class PizzaCut {

	private Pizza pizza;

	private ArrayList<Slice> slices;
	private ArrayList<Slice> slicesInCell;

	int count = 0;

	int tomato_count = 0;
	int msroom_count = 0;

	private int starting_row;
	private int starting_column;

	private int row_size;
	private int column_size;

	private int row;
	private int column;

	private int r;
	private int c;

	int count2 = 0;

	private ArrayList<Integer> multiples;

	public PizzaCut(Pizza pizza) {
		this.pizza = pizza;
		this.slices = new ArrayList<Slice>();
		this.slicesInCell = new ArrayList<Slice>();
		this.multiples = new ArrayList<Integer>();
	}

	public void solution() {

		
		for (int i = 0; i < pizza.getRows(); i++) {
			for (int j = 0; j <= pizza.getColumns(); j++) {
				//System.out.println(j);
				createSlice(i, j);
				
			}
		}

	}

	public void createSlice(int row, int column) {
		//System.out.println(column);
		for (int i = row; i < pizza.getCells_per_slice(); i++) {
			for (int j = column; j < pizza.getCells_per_slice(); j++) {
				
				if (checkValidSlice(i, j, row + i, column + j)) {
					
					int x = row + i;
					int y = column + j;
					int sizex = x - row; 
					int sizey = y - column;
					
					
					checkCount(i, j, x, y);
				}
			}
		}

		for (int i = 0; i < slicesInCell.size(); i++) {

			Slice s = slicesInCell.get(i);
			int count = (s.getEnd_row() - s.getStarting_row() + 1) * (s.getEnd_column() - s.getStarting_column() + 1);
			if (count > count2) {
				slices.add(s);
				count2 = count;
			}

			// System.out.println(count + " & " + count2);

		}
		slicesInCell.removeAll(slicesInCell);
	}

	public boolean checkValidSlice(int startRow, int startColumn, int endRow, int endColumn) {
		if ((endRow - startRow) * (endColumn - startColumn) <= pizza.getCells_per_slice()
				&& endRow <= pizza.getRows()  - 1 && endColumn <= pizza.getColumns()) {
			return true;
		} else {
			return false;
		}
	}

	public void checkCount(int startRow, int startColumn, int endRow, int endColumn) {
		
		
		//System.out.println(startRow - endRow);
		for (int i = startRow; i < endRow; i++) {
			for (int j = startColumn; j < endColumn; j++) {
				//System.out.println(i + " " + j);
				System.out.println(pizza.getCells()[i][j].charAt(0));
				if (pizza.getCells()[i][j].charAt(0) == 'T') {

					tomato_count++;

				} else {

					msroom_count++;

				}
			}
		

		//System.out.println("Tomato: " + tomato_count + " Msroom: " + msroom_count);

		if (tomato_count > pizza.getIng_per_slice() && msroom_count > pizza.getIng_per_slice()) {
			slicesInCell.add(new Slice(startRow, startColumn, endRow, endColumn));
		}

		tomato_count = 0;
		msroom_count = 0;
		
		}

		// slices.add(new Slice(startRow,startColumn, endRow, endColumn));

	}

	public ArrayList<Slice> getSlices() {
		return slices;
	}

	public void setSlices(ArrayList<Slice> slices) {
		this.slices = slices;
	}

	public void printSlices() {
		System.out.println(slices.size());
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
