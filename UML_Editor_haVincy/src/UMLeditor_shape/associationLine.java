package UMLeditor_shape;

import java.awt.Graphics;

public class associationLine extends Line{
	
	public associationLine(int x1, int y1, int x2, int y2) {		
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void draw(Graphics g) {
		g.drawLine(x1, y1, x2, y2);
	}

	
}
