package ViewModel;

import java.awt.event.MouseEvent;

import UI.animation.AnimationController;
import UI.animation.Item;

/** click on Item -> dispatchEvent to Canvas **/
public class SelectObjectState extends State {
	
	public AnimationController animationController = AnimationController.getInstance();
	
	@Override
	public void mousePressed(MouseEvent e) {
		// Select: if click on UI Item
		// only accept events emit from Item components (child)
		System.out.println("[Press] selected source: "+e.getSource()+", on: "+e.getPoint());
//		System.out.println( ((Item)e.getSource()).getModelReference().location );
		if (e.getSource() != canvas) {
			// mark as Selected in ViewModel
			vm.triggerSelect((Item) e.getSource());
			// show its group mates
			animationController.addMoveObserversByGroup((Item) e.getSource());
		}
		// De-select: if click on canvas
		else {
			vm.removeSelect();
			animationController.removeMoveObservers();
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
//		System.out.println("[Drag] selected source: "+e.getSource());
		if (e.getSource() != canvas) {
			// moving -> move Item alone with its group mates (X, Y is offset)
			animationController.moveByGroup( e.getX(), e.getY() );
			
			// change its & group mates' location on in ViewModel
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("[Release] selected source: "+e.getSource()+", on: "+e.getPoint());
		//System.out.println( ((Item)e.getSource()).getModelReference().location );
		if (e.getSource() != canvas)
//			System.out.println("[Release] inside selected: ");
			animationController.removeMoveObservers();
	}
}
