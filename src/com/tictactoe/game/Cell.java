package com.tictactoe.game;

public class Cell {
	
	private int x; // row index
	private int y; // col index
	private int minmaxValue;
	
	public Cell(int x, int y){
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getMinmaxValue() {
		return minmaxValue;
	}

	public void setMinmaxValue(int minmaxValue) {
		this.minmaxValue = minmaxValue;
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", minmaxValue=" + minmaxValue
				+ "]";
	}
	
}
