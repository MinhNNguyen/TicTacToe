package robert.com;

import java.awt.*;

public class Board {
	private Cell[][] board;
	
	public Board() {
		board = new Cell[Game.ROWS][Game.COLS];
		for (int row = 0; row < Game.ROWS; row++) {
			for (int col = 0; col < Game.COLS; col++) {
				board[row][col] = new Cell(row, col);
			}
		}
	}
	
	public Cell getCell(int x, int y) {
		return board[x][y];
	}
	
	public void reset() {
		for (int row = 0; row < Game.ROWS; row++) {
			for (int col = 0; col < Game.COLS; col++) {
				board[row][col].reset();
			}
		}
	}
	
	public boolean isWon( Filler fill, int row, int col) {
		return ( board[row][0].getValue() == fill && board[row][1].getValue() == fill && board[row][2].getValue() == fill)
               || ( board[0][col].getValue() == fill && board[1][col].getValue() == fill && board[2][col].getValue() == fill) 
               || (row == col && board[0][0].getValue() == fill && board[1][1].getValue() == fill && board[2][2].getValue() == fill)
               || (row + col == 2 && board[0][2].getValue() == fill && board[1][1].getValue() == fill && board[2][0].getValue() == fill);
	}
	
	public boolean isFull() {
		for (int row = 0; row < Game.ROWS; row++) {
			for (int col = 0; col < Game.COLS; col++) {
				if (board[row][col].getValue() == Filler.EMPTY)
					return false;
			}
		}
		return true;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		for (int row = 0; row < Game.ROWS; row++) {
		     g.fillRoundRect(0, Game.CELL_SIZE * row - Game.HALF_GRID_WIDTH,
		           Game.CANVAS_WIDTH - 1, Game.GRID_WIDTH,
		           Game.GRID_WIDTH, Game.GRID_WIDTH);
		}
		for (int col = 0; col < Game.COLS; col++) {
		     g.fillRoundRect(Game.CELL_SIZE * col - Game.HALF_GRID_WIDTH, 0,
		           Game.GRID_WIDTH, Game.CANVAS_HEIGHT - 1,
		           Game.GRID_WIDTH, Game.GRID_WIDTH);
		}
		
		
		for (int row = 0; row < Game.ROWS; row++) {
			for (int col = 0; col < Game.COLS; col++) {
				board[row][col].paint(g);
			}
		}
		
	}
		
}
