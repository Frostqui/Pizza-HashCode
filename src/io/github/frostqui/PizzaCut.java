package io.github.frostqui;

import java.util.ArrayList;

public class PizzaCut {

	private Pizza pizza;

	private ArrayList<Slice> slices;
	private ArrayList<Slice> slicesInCell;
	private static ArrayList<Slice> bestSlices;

	int count = 0;

	int tomato_count = 0;
	int msroom_count = 0;

	static int count_aux = 0;

	public PizzaCut(Pizza pizza) {
		this.pizza = pizza;
		this.slices = new ArrayList<Slice>();
		this.slicesInCell = new ArrayList<Slice>();
	}

	public void solution() {

		for (int i = 0; i < pizza.getRows(); i++) {
			for (int j = 0; j < pizza.getColumns(); j++) {
				createSlice(i, j);

			}
		}
		
		

		for (int i = 2; i < 12; i++) {
			
			combination(this.slicesInCell, i);

		}

		System.out.println("");
		System.out.println("Solution: ");
		System.out.println("");
		printSlices(bestSlices);

	}

	public void createSlice(int row, int column) {

		for (int i = row; i < pizza.getCells_per_slice(); i++) {
			for (int j = column; j < pizza.getCells_per_slice(); j++) {

				int x = row + i;
				int y = column + j;

				if (checkValidSlice(row, column, x, y)) {

					checkCount(row, column, x, y);
				}

			}

		}

	}

	/**
	 * 
	 * Check if the coordinates are inside the pizza
	 * 
	 * @param startRow
	 * @param startColumn
	 * @param endRow
	 * @param endColumn
	 * @return
	 */
	public boolean checkValidSlice(int startRow, int startColumn, int endRow, int endColumn) {

		if ((endRow - startRow + 1) * (endColumn - startColumn + 1) <= pizza.getCells_per_slice()
				&& endRow < pizza.getRows() && endColumn < pizza.getColumns()) {

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check if the coordinates meet minimum ingredients for a slice
	 * 
	 * @param startRow
	 * @param startColumn
	 * @param endRow
	 * @param endColumn
	 */

	public void checkCount(int startRow, int startColumn, int endRow, int endColumn) {

		tomato_count = 0;
		msroom_count = 0;

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

		// System.out.println("Tomato: " + tomato_count + " Msroom: " + msroom_count);

		// System.out.println("-------------------------");

		if (tomato_count >= pizza.getIng_per_slice() && msroom_count >= pizza.getIng_per_slice()) {

			slicesInCell.add(new Slice(startRow, startColumn, endRow, endColumn));

		}

		tomato_count = 0;
		msroom_count = 0;

	}

	/**
	 * Print slices to screen
	 * 
	 * @param values
	 */

	public void printSlices(ArrayList<Slice> values) {
		System.out.println(values.size());
		for (int i = 0; i < values.size(); i++) {
			Slice s = values.get(i);
			System.out.println(s.getStarting_row() + "  " + s.getStarting_column() + "  " + s.getEnd_row() + "  "
					+ s.getEnd_column());
		}
	}

	/**
	 * Print specified slice
	 * 
	 * @param value
	 */

	public static void printSlice(Slice value) {

		System.out.println(value.getStarting_row() + "  " + value.getStarting_column() + "  " + value.getEnd_row()
				+ "  " + value.getEnd_column());

	}

	/**
	 * Put in bestSlices array the combination of the best slices
	 * 
	 * @param count
	 * @param combination
	 * @param slices
	 */
	public void bestSlices(int count, int[] combination, ArrayList<Slice> slices) {

		if (count > count_aux && checkIfValid(combination, slices)) {

			bestSlices = new ArrayList<Slice>();
			for (int i = 0; i < combination.length; i++) {
				bestSlices.add(slices.get(combination[i]));
			}

			count_aux = count;
		}

	}

	/**
	 * All posible combinations of element of a specified size
	 * 
	 * @param elements
	 * @param K
	 */
	public void combination(ArrayList<Slice> elements, int K) {

		// get the length of the array
		// e.g. for {'A','B','C','D'} => N = 4

		int N = elements.size();

		// System.out.println(N);

		if (K > N) {
			System.out.println("Invalid input, K > N");
			return;
		}
		// calculate the possible combinations
		// e.g. c(4,2)
		c(N, K);

		// get the combination by index
		// e.g. 01 --> AB , 23 --> CD
		int combination[] = new int[K];

		// position of current index
		// if (r = 1) r*
		// index ==> 0 | 1 | 2
		// element ==> A | B | C
		int r = 0;
		int index = 0;

		while (r >= 0) {
			// possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
			// possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"

			// for r = 0 ==> index < (4+ (0 - 2)) = 2
			if (index <= (N + (r - K))) {
				combination[r] = index;

				// if we are at the last position print and increase the index
				if (r == K - 1) {

					// do something with the combination e.g. add to list or print
					// print(combination, elements);
					// print(combination, elements);
					// printSlices(slices);
					// System.out.println(checkIfValid(combination, elements));
					if (checkIfValid(combination, elements)) {
						// System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeee");
						bestSlices(calculateCount(combination, elements), combination, elements);

					}

					index++;
				} else {
					// select index for next position
					index = combination[r] + 1;
					r++;
				}
			} else {
				r--;
				if (r > 0)
					index = combination[r] + 1;
				else
					index = combination[0] + 1;
			}
		}
	}

	public static int c(int n, int r) {

		int nf = fact(n);
		int rf = fact(r);
		int nrf = fact(n - r);

		int npr = 3;
		int ncr = 3;

		if (nrf != 0 && rf != 0) {
			npr = nf / nrf;
			ncr = npr / rf;
		}

		return ncr;
	}

	public static int fact(int n) {
		if (n == 0)
			return 1;
		else
			return n * fact(n - 1);
	}

	public static void print(int[] combination, ArrayList<Slice> slices) {

		for (int z = 0; z < combination.length; z++) {
			printSlice(slices.get(combination[z]));
		}

	}

	/**
	 * Check if the slice combination is valid (all slices for a combination can not
	 * overlap
	 * 
	 * @param combination
	 * @param slices
	 * @return
	 */

	public boolean checkIfValid(int[] combination, ArrayList<Slice> slices) {
		Slice s;
		Slice s2;

		boolean valid = true;

		for (int i = 0; i < combination.length; i++) {
			for (int j = i + 1; j < combination.length; j++) {
				if (i == j) {
					return false;
				}

				s = slices.get(combination[i]);
				s2 = slices.get(combination[j]);

				if (s.getStarting_row() > s2.getEnd_row() || s.getEnd_row() < s2.getStarting_row()
						|| s.getEnd_column() < s2.getStarting_column() || s.getStarting_column() > s2.getEnd_column()) {

					valid = valid && true;

				} else {
					valid = valid && false;

				}
			}
		}

		return valid;

	}

	/**
	 * Calculate score for a combination of slices
	 * 
	 * @param combination
	 * @param slices
	 * @return
	 */

	public static int calculateCount(int[] combination, ArrayList<Slice> slices) {
		Slice s;
		int count = 0;
		for (int z = 0; z < combination.length; z++) {

			s = slices.get(combination[z]);
			count += ((s.getEnd_row() - s.getStarting_row()) + 1) * ((s.getEnd_column() - s.getStarting_column()) + 1);

		}
		return count;

	}

	public ArrayList<Slice> getSlices() {
		return slices;
	}

	public void setSlices(ArrayList<Slice> slices) {
		this.slices = slices;
	}

}
