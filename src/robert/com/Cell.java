package robert.com;

import java.awt.*;

public class Cell {
	private Filler value;
	int row, col;
	
	public Cell(int row, int col) {
		this.row = row;
		this.col = col;
		reset();
	}
	
	public void reset() {
		value = Filler.EMPTY;
	}
	
	public Filler getValue()
	{
		return value;
	}
	
	public void setValue(Filler fill) {
		value = fill;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
	      g2d.setStroke(new BasicStroke(Game.SYMBOL_STROKE_WIDTH,
	              BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); // Graphics2D only
	        // Draw the Seed if it is not empty
	        int x1 = col * Game.CELL_SIZE + Game.CELL_PADDING;
	        int y1 = row * Game.CELL_SIZE + Game.CELL_PADDING;
	        if (value == Filler.CROSS) {
	           g2d.setColor(Color.RED);
	           int x2 = (col + 1) * Game.CELL_SIZE - Game.CELL_PADDING;
	           int y2 = (row + 1) * Game.CELL_SIZE - Game.CELL_PADDING;
	           g2d.drawLine(x1, y1, x2, y2);
	           g2d.drawLine(x2, y1, x1, y2);
	        } else if (value == Filler.NOUGHT) {
	           g2d.setColor(Color.BLUE);
	           g2d.drawOval(x1, y1, Game.SYMBOL_SIZE, Game.SYMBOL_SIZE);
	        }
	}
	
}
