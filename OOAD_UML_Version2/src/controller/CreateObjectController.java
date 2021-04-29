package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import model.BasicObject;

public class CreateObjectController extends IController {
	
	/** determine create an usecase or a class **/
	private Mode mode;
	// private model factory 
	private static final Logger LOGGER = Logger.getLogger(CreateObjectController.class.getName());
	
	public CreateObjectController(Mode mode) {
		this.mode = mode;
	}
	
	/** create an usecase or a class **/
	public void mousePressed(MouseEvent e) {
		// create a new object
		int depth = canvas.getNextDepth();
		Point location = e.getPoint();
		BasicObject object = factory.getObject(mode, location, depth);
		object.setName(mode.toString());
		shapeDao.add(object);
		
		// render it on canvas
		object.moveTo(location);
		canvas.add(object.getUiObject(), depth);
		
		LOGGER.info("depth "+depth);
	}
}
