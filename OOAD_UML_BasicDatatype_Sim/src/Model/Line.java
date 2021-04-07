package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import UI.ToolBar.State;
import UI.animation.Item;

public class Line {
//	private int x1, y1, x2, y2;
	protected Port src, dest; 
	protected String state;
	private int arrowW = 10, arrowH = 10; // 三角
	private int diamondW = 10, diamondH = 10; // 菱形
	
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
	
	public Line(Item src, int srcPort, Item dest, int destPort, String state) {
		this.src = new Port(src, srcPort);
		this.dest = new Port(dest, destPort);
		this.state = state;

		System.out.print("state:"+this.state);
	}

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
		
		
		if (state == "GENERAL") {
			// 三角形的點, 考慮線條角度
			int x1 = (int) src.getLocation().getX(), y1 = (int) src.getLocation().getY();
			int x2 = (int) dest.getLocation().getX(), y2 = (int) dest.getLocation().getY();
			int dx = x2 - x1, dy = y2 - y1;
			double D = Math.sqrt(dx*dx + dy*dy);
			double xm = D - arrowW, xn = xm, ym = arrowH, yn = -arrowH, x;
			double sin = dy/D, cos = dx/D;
			
			x = xm*cos - ym*sin + x1;
	        ym = xm*sin + ym*cos + y1;
	        xm = x;

	        x = xn*cos - yn*sin + x1;
	        yn = xn*sin + yn*cos + y1;
	        xn = x;

	        int[] xpoints = {x2, (int) xm, (int) xn};
	        int[] ypoints = {y2, (int) ym, (int) yn};

	        g.fillPolygon(xpoints, ypoints, 3);
		}
		else if (state == "COMPOSITE") {
			// 三角形的點, 考慮線條角度
			int x1 = (int) src.getLocation().getX(), y1 = (int) src.getLocation().getY();
			int x2 = (int) dest.getLocation().getX(), y2 = (int) dest.getLocation().getY();
			int dx = x2 - x1, dy = y2 - y1;
			double D = Math.sqrt(dx*dx + dy*dy);
			double xm = D - diamondW, xn = xm, ym = diamondH, yn = -diamondH, x;
			double sin = dy/D, cos = dx/D;
			
			x = xm*cos - ym*sin + x1;
	        ym = xm*sin + ym*cos + y1;
	        xm = x;

	        x = xn*cos - yn*sin + x1;
	        yn = xn*sin + yn*cos + y1;
	        xn = x;
	        
	        // 分點公式算出線上的點, 三角形的三個點與這個點連線即為一個菱形
	        double xq = (diamondH*2/D)*x1 + ((D-diamondH*2)/D)*x2;
	        double yq = (diamondH*2/D)*y1 + ((D-diamondH*2)/D)*y2;
	   
	        int[] xpoints = {x2, (int) xm, (int) xq, (int) xn};
	        int[] ypoints = {y2, (int) ym, (int) yq, (int) yn};

	        g.fillPolygon(xpoints, ypoints, 4);
		}
	}
}
