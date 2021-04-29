package dao;

import java.awt.Point;

import controller.Mode;
import model.BasicObject;
import model.ClassObject;
import model.UsecaseObject;

public class ShapeFactory {

	/** ref: https://www.tutorialspoint.com/design_pattern/factory_pattern.htm **/
	public BasicObject getObject(Mode createMode, Point location, int depth) {
		if (createMode == null) {
		     return null;
		  }		
		  if (createMode == Mode.CLASS) {
			 BasicObject object =  new ClassObject(location, depth);
			 object.setName(createMode.toString());
		     return object;
		     
		  } else if (createMode == Mode.USECASE) {
		     BasicObject object =  new UsecaseObject(location, depth);
			 object.setName(createMode.toString());
		     return object;
		  }
		return null;
	}
	
}
