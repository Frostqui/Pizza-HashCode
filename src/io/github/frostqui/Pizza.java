package io.github.frostqui;

public class Pizza {

	private int rows;
	private int columns;
	private int ing_per_slice;
	private int cells_per_slice;
	
	private String[][] cells;
	
	public Pizza(int rows, int columns, int ing_per_slice, int cells_per_slice) {
		super();
		this.rows = rows;
		this.columns = columns;
		this.ing_per_slice = ing_per_slice;
		this.cells_per_slice = cells_per_slice;
		this.cells = new String[rows][columns];
	}
	

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public int getIng_per_slice() {
		return ing_per_slice;
	}

	public void setIng_per_slice(int ing_per_slice) {
		this.ing_per_slice = ing_per_slice;
	}

	public int getCells_per_slice() {
		return cells_per_slice;
	}

	public void setCells_per_slice(int cells_per_slice) {
		this.cells_per_slice = cells_per_slice;
	}

	public String[][] getCells() {
		return cells;
	}

	public void setCells(String[][] cells) {
		this.cells = cells;
	}

	
	
}
