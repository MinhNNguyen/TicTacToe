package robert.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {

	// Constants for game board
	public static final int ROWS = 3;
	public static final int COLS = 3;
	public static final String TITLE = "Tic Tac Toe";
	
	// Constants for size using for graphics drawing
	public static final int CELL_SIZE = 100;
	public static final int CANVAS_WIDTH = CELL_SIZE * ROWS;
	public static final int CANVAS_HEIGHT = CELL_SIZE * COLS;
	public static final int GRID_WIDTH = 8;
	public static final int HALF_GRID_WIDTH = GRID_WIDTH / 2;
	
	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2;
	public static final int SYMBOL_STROKE_WIDTH = 8;
	
	private Board board;
	private GameState currentState;
	private Filler player;
	private JLabel message;
	
	
	
	
	public Game() {
		board = new Board();
		setupMessage();
		init();
		this.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int colClicked = e.getX() / CELL_SIZE;
				int rowClicked = e.getY() / CELL_SIZE;
				
				if (currentState == GameState.PLAYING) {
					if ( board.getCell(rowClicked, colClicked).getValue() == Filler.EMPTY ) {
						board.getCell(rowClicked, colClicked).setValue(player);
						update(rowClicked, colClicked);
					}
				}
				else {
					init();
				}
				repaint();
			}
		});
	}
	
	public void setupMessage() {
		message = new JLabel("      ");
		message.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 12));
		message.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));
		message.setOpaque(true);
		message.setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		add(message, BorderLayout.PAGE_END);
	}
	
	public void init() {
		board.reset();
		currentState = GameState.PLAYING;
		player =  Filler.CROSS;
		message.setText("It is " + player + "'s turn.");
	}
	
	public void update(int row, int col) {
		if (board.isWon(player, row, col)) {
			currentState = player == Filler.CROSS ? GameState.CROSS_WON : GameState.NOUGHT_WON;
			message.setText( player + " won. Click to play again!" );
		}
		else if (board.isFull()) {
			currentState = GameState.DRAW;
			message.setText("Draw Game. Click to play again!");
		}
		else {
			player = player == Filler.CROSS ? Filler.NOUGHT : Filler.CROSS;
			message.setText("It is " + player + "'s turn.");
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.WHITE);
		board.paint(g);
	}
	
	public static void main(String[] args) {
	      javax.swing.SwingUtilities.invokeLater(new Runnable() {
	          public void run() {
	             JFrame frame = new JFrame(TITLE);
	             frame.setContentPane(new Game());
	             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	             frame.setPreferredSize(new Dimension(CANVAS_WIDTH + 15, CANVAS_HEIGHT + 50));
	             frame.pack();
	             frame.setVisible(true);  
	          }
	       });
	}
	
	
	
	
}
