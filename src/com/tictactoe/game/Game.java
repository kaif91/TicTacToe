package com.tictactoe.game;

import java.util.Random;

public class Game {

	private Board board;
	private Random random;

	public Game() {
		initializeGame();
		displayBoard();
		makeFirstMove();
		playGame();
		checkStatus();
	}


	/**
	 * Infinite loop to make the game continue running until none of the player wins or game gets draw.
	 */
	private void playGame() {
		while(this.board.isRunning()){
			System.out.println("User move: ");
			Cell userCell = new Cell(board.getScanner().nextInt(),board.getScanner().nextInt());
			this.board.move(userCell,Player.USER);
			displayBoard();
			
			if(!this.board.isRunning()){
				break;
			}
			
			this.board.callMinMax(0, Player.COMPUTER);
			for(Cell cell:this.board.getRootValues()){
				System.out.println("cell values "+cell+"min max value"+cell.getMinmaxValue());
			}
			this.board.move(board.getBestMove(),Player.COMPUTER);
			displayBoard();
				
		}

	}

	private void makeFirstMove() {
		System.out.println("WHO starts? 1: computer 2:User");
		int choice = board.getScanner().nextInt();
		if(choice == 1){
			Cell cell=new Cell(random.nextInt(Constants.BOARD_SIZE),random.nextInt(Constants.BOARD_SIZE));
			this.board.move(cell, Player.COMPUTER);
			displayBoard();
		}
	}

	private void displayBoard() {
		this.board.displayBoard();

	}

	private void initializeGame() {
		this.board = new Board();
		this.random = new Random();
		this.board.setupBoard();
	}

	private void checkStatus() {
		if(board.isWinning(Player.COMPUTER)){
			System.out.println("Computer has won");
		}
		else if(board.isWinning(Player.USER)){
			System.out.println(" User has won");
		}
		else{
			System.out.println("its draw");
		}
		
	}

}
