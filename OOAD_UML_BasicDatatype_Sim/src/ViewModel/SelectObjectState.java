package ViewModel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import UI.GroupGraphic;
import UI.animation.AnimationController;
import UI.animation.Item;

/** click on Item -> dispatchEvent to Canvas **/
public class SelectObjectState extends State {
	
	public Point areaStart = null; // record selected area's location
	public Point areaEnd = null;
	GroupGraphic groupPainter = GroupGraphic.getInstance();
	/** observer pattern: perform group movement **/
	public AnimationController animationController = AnimationController.getInstance();
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("[Press] selected source: "+e.getSource()+", on: "+e.getPoint());
//		System.out.println( ((Item)e.getSource()).getModelReference().location );
		// Select: if click on UI Item
		// only accept events emit from Item components (child)
		if (e.getSource() instanceof Item) {
			// mark as Selected in ViewModel
			vm.triggerSelect((Item) e.getSource());
			// show its group mates
			animationController.addMoveObserversByGroup((Item) e.getSource());
		}
		// De-select: if click on canvas
		// Record areaStart, preparing to paint the selected area
		else if (e.getSource() == canvas) {
			vm.removeSelect();
			vm.clearGroupSelect();
			animationController.removeMoveObservers();
			areaStart = e.getPoint();
//			canvas.repaint();
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
//		System.out.println("[Drag] selected source: "+e.getSource());
		if (e.getSource() instanceof Item) {
			// moving -> move Item alone with its group mates (X, Y is offset)
			animationController.moveByGroup( e.getX(), e.getY() );
			
			// change its & group mates' location on in ViewModel
			// I edit update() method, but UI movies 2x faster..... WTF
			// and vm's location changes itself...
		}
		else if (e.getSource() == canvas) {
			// display selected area
			areaEnd = e.getPoint();
			groupPainter.setSelectedArea(areaStart, areaEnd);
			canvas.repaint();
			
			// perform grouping
			vm.selectItemsInArea(groupPainter);
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
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == canvas) {
			groupPainter.clearSelectedArea();
			vm.clearGroupSelect();
			canvas.repaint();
		}
	}
	
}
