package model;

import java.awt.Point;

public interface Shape {
	
	public void move(Point offset);
	public void move(int dx, int dy);
	public void moveTo(Point location);

}
