/**
 * 
 */
package Model;

import java.awt.Point;

/**
 * Basic Object Class
 *
 */
public class Shape {
	public Point location;
	
	public Shape() {}
	public Shape(Point location) {
		this.location = location;
	}
	
//	public abstract void draw(Graphics g);
	
	public void move(Point offset) { location.translate(offset.x, offset.y); }
	public void move(int dx, int dy) { location.translate(dx, dy); }
	public void moveTo(Point location) { this.location.setLocation(location); }
	
	// Basic object
//		public Port getPort(int portIndex){
//			return null;
//		}
}
