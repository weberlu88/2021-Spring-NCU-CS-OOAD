package UI.animation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Model.BasicObject;
import Model.ClassObject;
import Model.UsecaseObject;
import UI.Canvas;
import ViewModel.State;
import ViewModel.ViewModel;

@SuppressWarnings("serial")
public class Item extends JLayeredPane implements IObserver {
	public int id;
	public Point location; // 左上角
	public Point mousePosition; // 滑鼠按在canvas上的點，not 左上角
	public int width, height;
	private EventListener listener = null;
	public BasicObject modelReference; // 判斷是哪種物件和其reference
	public JLabel label;
	public JLabel text;
	
//	public AnimationController animationController = AnimationController.getInstance();
//	public ViewModel vm = ViewModel.getInstance();
	
	public Item(BasicObject modelReference, Point location, String text) {
		// 用instanceof判斷
		this.modelReference = modelReference;
		this.location = location;
		this.text = new JLabel(text, JLabel.CENTER);
		ImageIcon icon;
		if (modelReference instanceof ClassObject ) {
			icon = new ImageIcon("resource/transparent/class.png");
		} 
		else if (modelReference instanceof UsecaseObject ) {
			icon = new ImageIcon("resource/transparent/usecase.png");
		}
		else {
			System.out.println("Item Constructor: type error");
			return;
		}
		label = new JLabel(icon);
		
		// add to Layered pane
		width = icon.getIconWidth();
		height = icon.getIconHeight();
		setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		this.text.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		add(label, 1);
		add(this.text, 0);
		
		// set bounds
		moveTo(location);
	}
	
	public void setState(State newState) {
		removeState();
		listener = newState;
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	public void removeState() {
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
	}
	
	public void moveTo(Point location) {
		this.setBounds((int)location.getX(), (int)location.getY(), width, height);	
	}
	
	public void move(int offsetX, int offsetY) {
		location.translate(offsetX, offsetY);
		moveTo(location);
	}

	/** update location by translating location & reset bounds **/
	@Override
	public void update(int offsetX, int offsetY) {
		move(offsetX, offsetY);
	}
	
//	@Override
//	public void mousePressed(MouseEvent e) {
////		System.out.println("item mousePressed: " + e.getPoint());
//		mousePosition = e.getComponent().getParent().getMousePosition();
//		
//		// try to pass event to parent
//		System.out.println("item's location: " + location);
//		e.translatePoint((int)location.getX(), (int)location.getY()); // point on canvas
//		// Reset location, too stupid
////		MouseEvent newe = new MouseEvent((Item) (e.getSource()),0, 0, 0,
////                (int)mousePosition.getX(), (int)mousePosition.getY(), 0, false);
//		e.getComponent().getParent().dispatchEvent(e);
//	}

//	@Override
//	public void mouseDragged(MouseEvent e) {
//		// The Dragged item will moved by itself
//		Point newPosition = e.getComponent().getParent().getMousePosition();
//		double offsetX = newPosition.getX() - mousePosition.getX();
//		double offsetY = newPosition.getY() - mousePosition.getY();
//		mousePosition = newPosition;
//		// Reset X, Y as offset, too stupid
//		// Let canvas handle its group mates to move
//		e.translatePoint(-e.getX(), -e.getY()); // reset to (0, 0)
//		e.translatePoint((int)offsetX, (int)offsetY); // offset
////		MouseEvent newe = new MouseEvent(this,0, 0, 0,
////                (int) offsetX, (int) offsetY, 0, false);
//		e.getComponent().getParent().dispatchEvent(e);
//		
//		// deal in Item by itself
////		System.out.println("item mouseDragged: " + newPosition);
////		System.out.println("item location: " + location);
////		animationController.moveByGroup( (int)offsetX, (int)offsetY );
//	}
	
//	@Override
//	public void mouseReleased(MouseEvent e) {
////		animationController.removeMoveObservers();
//		// pass event to canvas
//		e.translatePoint((int)location.getX(), (int)location.getY());
//		e.getComponent().getParent().dispatchEvent(e);
//	}
	

}
