package ViewModel;

import java.awt.event.MouseEvent;

import UI.animation.AnimationController;
import UI.animation.Item;

public class SelectObjectState extends State {
	
	public AnimationController animationController = AnimationController.getInstance();
	
	@Override
	public void mousePressed(MouseEvent e) {
		// click on Item -> dispatchEvent to Canvas
		// only accept events emit from Item components (child)
		System.out.println("[Press] selected source: "+e.getSource());
		if (e.getSource() != canvas) {
			// mark as Selected in ViewModel
			
			// show its group mates
			animationController.addMoveObserversByGroup((Item) e.getSource());
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("[Drag] selected source: "+e.getSource());
		if (e.getSource() != canvas) {
			// moving -> move Item alone with its group mates (X, Y is offset)
			animationController.moveByGroup( e.getX(), e.getY() );//			e.
			
			// change its & group mates' location on in ViewModel
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("[Release] selected source: "+e.getSource());
		if (e.getSource() != canvas)
			System.out.println("[Release] inside selected: ");
			animationController.removeMoveObservers();
	}
}
