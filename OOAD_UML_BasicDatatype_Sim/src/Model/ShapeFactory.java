package Model;

import java.awt.Point;

public class ShapeFactory implements ShapeFactoryInterface {

	/** ref: https://www.tutorialspoint.com/design_pattern/factory_pattern.htm **/
	@Override
	public BasicObject getObject(String objectType, Point location) {
		if (objectType == null) {
		     return null;
		  }		
		  if (objectType.equalsIgnoreCase("CLASS")) {
		     return new ClassObject(location);
		     
		  } else if (objectType.equalsIgnoreCase("USECASE")) {
		     return new UsecaseObject(location);
		  }
		return null;
	}

}
