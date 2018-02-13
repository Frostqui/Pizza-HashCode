package io.github.frostqui;

import java.rmi.server.SocketSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
			for (int j = 0; j < pizza.getColumns(); j++) {
				// System.out.println(i + " " + j);
				createSlice(i, j);

			}
		}

		for(int i = 2; i<pizza.getCells_per_slice(); i++) {
			combination(this.slicesInCell,i);

		}
		//printSlices(slicesInCell);

	}

	public void createSlice(int row, int column) {
		// System.out.println(column);
		for (int i = row; i < pizza.getCells_per_slice(); i++) {
			for (int j = column; j < pizza.getCells_per_slice(); j++) {

				int x = row + i;
				int y = column + j;
				// System.out.println(row + " " + column + " x " + x + " " + y);

				if (checkValidSlice(row, column, x, y)) {

					// System.out.println(i + " " + x + " x " + j + " " + y);
					checkCount(row, column, x, y);
				}

			}

		}

	}

	public boolean checkValidSlice(int startRow, int startColumn, int endRow, int endColumn) {
		// System.out.println(((endRow - startRow) + 1 ) * ((endColumn - startColumn) +
		// 1));
		// System.out.println(pizza.getCells_per_slice());
		if ((endRow - startRow + 1) * (endColumn - startColumn + 1) <= pizza.getCells_per_slice()
				&& endRow < pizza.getRows() && endColumn < pizza.getColumns()) {

			return true;
		} else {
			return false;
		}
	}

	public void checkCount(int startRow, int startColumn, int endRow, int endColumn) {

		tomato_count = 0;
		msroom_count = 0;

		System.out.println("Row " + startRow + " to " + endRow);
		System.out.println("Column " + startColumn + " to " + endColumn);

		if (startRow != endRow && startColumn != endColumn) {

			for (int i = startRow; i <= endRow; i++) {
				for (int j = startColumn; j <= endColumn; j++) {

					if (pizza.getCells()[i][j].charAt(0) == 'T') {
						tomato_count++;
					} else {

						msroom_count++;
					}
				}

			}
		}
		if (startRow == endRow && startColumn == endColumn) {

			if (pizza.getCells()[startRow][startColumn].charAt(0) == 'T') {
				tomato_count++;
			} else {

				msroom_count++;
			}
		}

		if (startRow == endRow && startColumn != endColumn) {

			for (int j = startColumn; j <= endColumn; j++) {

				if (pizza.getCells()[startRow][j].charAt(0) == 'T') {
					// System.out.println(tomato_count);
					tomato_count++;
				} else {
					msroom_count++;
				}
			}

		}

		if (startColumn == endColumn && startRow != endRow) {
			for (int i = startRow; i <= endRow; i++) {

				if (pizza.getCells()[i][startColumn].charAt(0) == 'T') {
					tomato_count++;
				} else {
					msroom_count++;
				}
			}

		}

		System.out.println("Tomato: " + tomato_count + " Msroom: " + msroom_count);

		System.out.println("-------------------------");

		if (tomato_count >= pizza.getIng_per_slice() && msroom_count >= pizza.getIng_per_slice()) {
			// System.out.println("eeeeeeeee");
			slicesInCell.add(new Slice(startRow, startColumn, endRow, endColumn));
			// slices.add(new Slice(startRow,startColumn, endRow, endColumn));

		}

		tomato_count = 0;
		msroom_count = 0;

		
	
		// slices.add(new Slice(startRow,startColumn, endRow, endColumn));

	}

	public ArrayList<Slice> getSlices() {
		return slices;
	}

	public void setSlices(ArrayList<Slice> slices) {
		this.slices = slices;
	}

	public void printSlices(ArrayList<Slice> values) {
		System.out.println(values.size());
		for (int i = 0; i < values.size(); i++) {
			Slice s = values.get(i);
			System.out.println(s.getStarting_row() + "  " + s.getStarting_column() + "  " + s.getEnd_row() + "  "
					+ s.getEnd_column());
		}
	}
	
	public static void printSlice(Slice value) {
		
		
			
			System.out.println(value.getStarting_row() + "  " + value.getStarting_column() + "  " + value.getEnd_row() + "  "
					+ value.getEnd_column());
		
	}


	public static boolean isMultiple(int n1, int n2) {
		if (n1 % n2 == 0)
			return true;
		else
			return false;
	}

	
	public void bestSlices() {
		// System.out.println(slicesInCell.size());
		for (int i = 0; i < slicesInCell.size(); i++) {

			Slice s = slicesInCell.get(i);
			int count = ((s.getEnd_row() - s.getStarting_row()) + 1)
					* ((s.getEnd_column() - s.getStarting_column()) + 1);
			System.out.println(count + "   " + count2);

			if (count > count2) {
				// System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				slices.add(s);
				count2 = count;
			}

			// System.out.println(count + " & " + count2);

		}
		count2 = 0;
		slicesInCell.removeAll(slicesInCell);
	}
	public static void combination(ArrayList<Slice>  elements, int K){

		// get the length of the array
		// e.g. for {'A','B','C','D'} => N = 4 
		int N = elements.size();
		
		if(K > N){
			System.out.println("Invalid input, K > N");
			return;
		}
		// calculate the possible combinations
		// e.g. c(4,2)
		c(N,K);
		
		// get the combination by index 
		// e.g. 01 --> AB , 23 --> CD
		int combination[] = new int[K];
		
		// position of current index
		//  if (r = 1)				r*
		//	index ==>		0	|	1	|	2
		//	element ==>		A	|	B	|	C
		int r = 0;		
		int index = 0;
		
		while(r >= 0){
			// possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
			// possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"
			
			// for r = 0 ==> index < (4+ (0 - 2)) = 2
			if(index <= (N + (r - K))){
					combination[r] = index;
					
				// if we are at the last position print and increase the index
				if(r == K-1){

					//do something with the combination e.g. add to list or print
					print(combination, elements);
					calculateCount(combination, elements);
					index++;				
				}
				else{
					// select index for next position
					index = combination[r]+1;
					r++;										
				}
			}
			else{
				r--;
				if(r > 0)
					index = combination[r]+1;
				else
					index = combination[0]+1;	
			}			
		}
	}
	

	
	public static int c(int n, int r){
		int nf=fact(n);
		int rf=fact(r);
		int nrf=fact(n-r);
		int npr=nf/nrf;
		int ncr=npr/rf; 
		
	//	System.out.println("C("+n+","+r+") = "+ ncr);

		return ncr;
	}
	
	public static int fact(int n)
	{
		if(n == 0)
			return 1;
		else
			return n * fact(n-1);
	}
	

	public static void print(int[] combination, ArrayList<Slice> slices){

	
		for(int z = 0 ; z < combination.length;z++){
			printSlice(slices.get(combination[z]));
		}
		
		
		
	}
	
	public static void calculateCount(int[] combination, ArrayList<Slice> slices) {
		Slice s;
		int count = 0;
		for(int z = 0 ; z < combination.length;z++){
			
			s = slices.get(combination[z]);
			count += ((s.getEnd_row() - s.getStarting_row()) + 1)
					* ((s.getEnd_column() - s.getStarting_column()) + 1);
			
		}
		System.out.println(count);
		System.out.println();
	}
}

