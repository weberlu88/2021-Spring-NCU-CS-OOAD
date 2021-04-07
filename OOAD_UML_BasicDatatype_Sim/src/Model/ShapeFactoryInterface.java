package Model;

import java.awt.Point;

import UI.animation.Item;

/** Factory Pattern: creation logic. 
 * ref: https://www.tutorialspoint.com/design_pattern/factory_pattern.htm**/
public interface ShapeFactoryInterface {
	public BasicObject getObject(String objectType, Point location);
	public Line getLine(String lineType, Port start, Port end);
	public Line getLine(Port start, Port end);
	public Line getLine(Item src, int srcPort, Item dest, int destPort);
}
