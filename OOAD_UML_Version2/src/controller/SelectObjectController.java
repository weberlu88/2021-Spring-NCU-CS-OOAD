package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import model.BasicObject;
import view.UIObject;

/** Listener to the source UIObject, 
 * we pass the event the canvas so as to deal with GROUP operations. **/
public class SelectObjectController extends IController {
	
	/** selected UIObject **/
	private BasicObject item;
	private static final Logger LOGGER = Logger.getLogger(CreateObjectController.class.getName());
	
	@Override
	public void mousePressed(MouseEvent e) {
		item = ( (UIObject) (e.getSource()) ).getBasicObject();
		item.setMousePosition(e.getComponent().getParent().getMousePosition());
		// pass event to parent
//		LOGGER.info("item's location: " + item.getLocation());
		e.translatePoint(item.getLocation().x, item.getLocation().y); // convert to point on canvas
		e.getComponent().getParent().dispatchEvent(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// Calculate the movement offset of the Dragged item
		item = ( (UIObject) (e.getSource()) ).getBasicObject();
		Point newPosition = e.getComponent().getParent().getMousePosition();
		int offsetX = newPosition.x - item.getMousePosition().x;
		int offsetY = newPosition.y - item.getMousePosition().y;
		item.setMousePosition(newPosition);
		// Reset X, Y as offset, a stupid method
		// Let canvas handle its group mates to move
		e.translatePoint(-e.getX(), -e.getY()); // reset to (0, 0)
		e.translatePoint(offsetX, offsetY); // offset
		// pass event to parent
		e.getComponent().getParent().dispatchEvent(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// pass event to canvas
		item = ( (UIObject) (e.getSource()) ).getBasicObject();
		e.translatePoint(item.getMousePosition().x, item.getMousePosition().y);
		e.getComponent().getParent().dispatchEvent(e);
	}

}
