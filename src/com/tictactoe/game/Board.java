package com.tictactoe.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board {

	private List<Cell> emptyCell;
	private Scanner scanner;
	private Player[][] board;
	private List<Cell> rootValues; // calculated minmax value from algo

	public Board() {
		initialize();
	}

	private void initialize() {
		this.rootValues = new ArrayList<>();
		this.scanner = new Scanner(System.in);
		this.board = new Player[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
	}

	/**
	 * Check if either computer or user has won the game or not.If no one has
	 * won or game is not yet draw then game is running.
	 * 
	 * @return
	 */
	public boolean isRunning() {
		if (isWinning(Player.COMPUTER))
			return false;
		if (isWinning(Player.USER))
			return false;
		if (getEmptyCell().isEmpty())
			return false;
		return true;
	}

	/**
	 * This method checks whether the User or the Computer has won the game based on winning conditions 
	 * 
	 * @param player
	 * @return true if either of computer or user has won the game else return false.
	 */
	public boolean isWinning(Player player) {

		// check diagonals
		if (board[0][0] == player && board[1][1] == player
				&& board[2][2] == player) {
			return true;
		}

		if (board[0][2] == player && board[1][1] == player
				&& board[2][0] == player) {
			return true;
		}
		// check rows and cols
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {

			if (board[i][0] == player && board[i][1] == player
					&& board[i][2] == player) {
				return true;
			}

			if (board[0][i] == player && board[1][i] == player
					&& board[2][i] == player) {
				return true;
			}
		}
		return false;
	}

	public List<Cell> getEmptyCell() {

		emptyCell = new ArrayList<>();
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			for (int j = 0; j < Constants.BOARD_SIZE; j++) {
				if (board[i][j] == Player.NONE) {
					emptyCell.add(new Cell(i, j));
				}
			}
		}
		return emptyCell;
	}

	public void move(Cell cell, Player player) {
		this.board[cell.getX()][cell.getY()] = player;

	}

	public Cell getBestMove() {

		int max = Integer.MIN_VALUE;
		int best = Integer.MIN_VALUE;

		for (int i = 0; i < rootValues.size(); i++) {
			if (max < rootValues.get(i).getMinmaxValue()) {
				max = rootValues.get(i).getMinmaxValue();
				best = i;
			}
		}
		return rootValues.get(best);
	}

	public void makeUserInput() {
		System.out.println(" user's  move");
		int x = scanner.nextInt();
		int y = scanner.nextInt();
		Cell cell = new Cell(x, y);
		move(cell, Player.USER);
	}

	// This method is used to display the tic tac toe game board
	public void displayBoard() {
		System.out.println();
		for (int i = 0; i < Constants.BOARD_SIZE; i++) {
			for (int j = 0; j < Constants.BOARD_SIZE; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public int returnMin(List<Integer> list) {

		int min = Integer.MAX_VALUE;
		int index = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) < min) {
				min = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}

	public int returnMax(List<Integer> list) {

		int max = Integer.MIN_VALUE;
		int index = Integer.MIN_VALUE;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) > max) {
				max = list.get(i);
				index = i;
			}
		}
		return list.get(index);
	}

	/**
	 * Invokes the minmax algorithm to find the optimal position to be used by computer.
	 * 
	 * @param depth
	 * @param player
	 */
	public void callMinMax(int depth, Player player) {
		rootValues.clear();
		minmax(depth, player);

	}

	private int minmax(int depth, Player player) {

		if(isWinning(Player.COMPUTER)) return +1; // we want the results for the computer.Good for computer.AI
		if(isWinning(Player.USER)) return -1; // -1 for the bad choice, user is winning.
		
		List<Cell> availableCells = getEmptyCell(); // Get the avaliable empty cell where move can be done.
		if(availableCells.isEmpty()) return 0; //if there is no cell left then its draw.
		
		List<Integer> scores =new ArrayList<>();  // list for storing +1 -1 or 0 values.
		
		for(int i=0;i<availableCells.size();i++){
			Cell point = availableCells.get(i);
			if(player == Player.COMPUTER){
				move(point,Player.COMPUTER);
				int currentScore = minmax(depth+1,Player.USER);
				scores.add(currentScore);
				
				if(depth == 0){
					point.setMinmaxValue(currentScore);
					rootValues.add(point);
				}
			}
			else if(player==Player.USER){
				move(point,Player.USER);
				scores.add(minmax(depth+1, Player.COMPUTER));
			}
			// reset the point
			board[point.getX()][point.getY()]=Player.NONE;
		}
		if(player== Player.COMPUTER){
			return  returnMax(scores);
		}
		return returnMin(scores);
		
	}

	public void setupBoard() {
		for(int i=0;i<Constants.BOARD_SIZE;i++){
			for(int j=0;j<Constants.BOARD_SIZE;j++){
				board[i][j]=Player.NONE;
			}
		}
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public Player[][] getBoard() {
		return board;
	}

	public void setBoard(Player[][] board) {
		this.board = board;
	}

	public List<Cell> getRootValues() {
		return rootValues;
	}

	public void setRootValues(List<Cell> rootValues) {
		this.rootValues = rootValues;
	}

	public void setEmptyCell(List<Cell> emptyCell) {
		this.emptyCell = emptyCell;
	}
	
}
