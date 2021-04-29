package controller;

import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import model.BasicObject;
import view.UIObject;

/** click on Item -> dispatchEvent to Canvas **/
public class SelectCanvasController extends IController {
	
	private static final Logger LOGGER = Logger.getLogger(CreateObjectController.class.getName());

	@Override
	public void mousePressed(MouseEvent e) {
		LOGGER.info("selected source: "+e.getSource()+", on: "+e.getPoint());
		// Select: if click on UIObject
		// only accept events emit from UIObject components (child)
		if (e.getSource() instanceof UIObject) {
			// mark whole group as Selected in dao (?)
			BasicObject selected = ( (UIObject) (e.getSource()) ).getBasicObject();
//			dao.triggerSelect(selected);
			// show its group mates by display ¸s²Õ®Ø®Ø (via GroupGraphic)
//			setGroupAreas(selected);
			// add its group mates to Observer, ready to move
//			animationController.addMoveObserversByGroup((Item) e.getSource());
		}
	}
}
