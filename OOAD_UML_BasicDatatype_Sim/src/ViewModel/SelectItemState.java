package ViewModel;

import java.awt.Point;
import java.awt.event.MouseEvent;

import UI.animation.Item;

public class SelectItemState extends State {
	
	Item item;

	@Override
	public void mousePressed(MouseEvent e) {
		item = (Item) (e.getSource());
		item.mousePosition = e.getComponent().getParent().getMousePosition();
		
		// pass event to parent
//		System.out.println("[Press] item's location: " + item.location);
		e.translatePoint((int)item.location.getX(), (int)item.location.getY()); // point on canvas
		e.getComponent().getParent().dispatchEvent(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// The Dragged item will moved by itself
		item = (Item) (e.getSource());
		Point newPosition = e.getComponent().getParent().getMousePosition();
		double offsetX = newPosition.getX() - item.mousePosition.getX();
		double offsetY = newPosition.getY() - item.mousePosition.getY();
		item.mousePosition = newPosition;
		// Reset X, Y as offset, a stupid method
		// Let canvas handle its group mates to move
		e.translatePoint(-e.getX(), -e.getY()); // reset to (0, 0)
		e.translatePoint((int)offsetX, (int)offsetY); // offset
		// pass event to parent
		e.getComponent().getParent().dispatchEvent(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// pass event to canvas
		item = (Item) (e.getSource());
		e.translatePoint((int)item.location.getX(), (int)item.location.getY());
		e.getComponent().getParent().dispatchEvent(e);
	}
}
