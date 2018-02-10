package io.github.frostqui;

public class Slice {
	
	private int starting_row;
	private int starting_column;
	
	private int end_row;
	private int end_column;
	
	public Slice(int starting_row, int starting_column, int end_row, int end_column) {
		super();
		this.starting_row = starting_row;
		this.starting_column = starting_column;
		this.end_row = end_row;
		this.end_column = end_column;
	}
	public int getStarting_row() {
		return starting_row;
	}
	public void setStarting_row(int starting_row) {
		this.starting_row = starting_row;
	}
	public int getStarting_column() {
		return starting_column;
	}
	public void setStarting_column(int starting_column) {
		this.starting_column = starting_column;
	}
	public int getEnd_row() {
		return end_row;
	}
	public void setEnd_row(int end_row) {
		this.end_row = end_row;
	}
	public int getEnd_column() {
		return end_column;
	}
	public void setEnd_column(int end_column) {
		this.end_column = end_column;
	}

}
