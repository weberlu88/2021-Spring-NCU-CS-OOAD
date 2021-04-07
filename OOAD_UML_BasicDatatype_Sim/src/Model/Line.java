package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import UI.animation.Item;

public class Line {
//	private int x1, y1, x2, y2;
	protected Port src, dest; 
	
	public Line(Port src, Port dest) {
		this.src = src;
		this.dest = dest;
	}
	
	public Line(Item src, int srcPort, Item dest, int destPort) {
		this.src = new Port(src, srcPort);
		this.dest = new Port(dest, destPort);
	}
	
//	private void resetLocation() {
//		x1 = (int) this.src.getLocation().getX();
//		y1 = (int) this.src.getLocation().getY();
//		x2 = (int) this.dest.getLocation().getX();
//		y2 = (int) this.dest.getLocation().getY();
//	}
	
	public void resetStartPoint(Port p) {
		src = p;
	}
	
	public void resetEndPoint(Port p) {
		dest = p;
	}
	
	public Port getSrc() {
		return src;
	}

	public void setSrc(Port src) {
		this.src = src;
	}

	public Port getDest() {
		return dest;
	}

	public void setDest(Port dest) {
		this.dest = dest;
	}

	public void draw(Graphics g) {
//		System.out.println("line draw: "+ src.getLocation()+", "+dest.getLocation());
		g.setColor(Color.green);
		g.drawLine((int) src.getLocation().getX() // x1
				, (int) src.getLocation().getY()         // y1
				, (int) dest.getLocation().getX()       // x2
				, (int) dest.getLocation().getY());     // y2
	}
}
