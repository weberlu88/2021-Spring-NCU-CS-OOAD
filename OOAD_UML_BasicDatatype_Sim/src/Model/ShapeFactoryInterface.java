package Model;

import java.awt.Point;

/** Factory Pattern: creation logic. 
 * ref: https://www.tutorialspoint.com/design_pattern/factory_pattern.htm**/
public interface ShapeFactoryInterface {
	public BasicObject getObject(String objectType, Point location);
//	public Line createObj(String objectType, Point start, Point end);
}
