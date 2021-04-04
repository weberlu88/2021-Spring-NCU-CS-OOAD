package ViewModel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import UI.Canvas;

/** Strategy Pattern: Each state performs different behavior for mouse events. 
 * ref: https://www.tutorialspoint.com/design_pattern/state_pattern.htm 
 * ref: https://www.tutorialspoint.com/design_pattern/strategy_pattern.htm**/
public abstract class State implements MouseListener, MouseMotionListener {
	protected Canvas canvas = Canvas.getInstance();   // Canvas is singleton 
	protected ViewModel vm = ViewModel.getInstance(); // ViewModel is singleton
	
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
}
