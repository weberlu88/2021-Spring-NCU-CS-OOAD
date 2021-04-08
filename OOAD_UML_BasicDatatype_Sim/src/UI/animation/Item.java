package UI.animation;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
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
import Model.Port;

@SuppressWarnings("serial")
public class Item extends JLayeredPane implements IObserver {
//	public int id;
	private int depth;
	public Point location; // ���W��
	public Point mousePosition; // �ƹ����bcanvas�W���I�Anot ���W��
	private int width, height;
	private EventListener listener = null;
	private BasicObject modelReference; // �P�_�O���ت���M��reference
	private JLabel label;
	private JLabel text;
	
//	public AnimationController animationController = AnimationController.getInstance();
//	public ViewModel vm = ViewModel.getInstance();
	
	public Item(BasicObject modelReference, Point location, String text, int depth) {
		// ��instanceof�P�_���ت���
		this.modelReference = modelReference;
		this.depth = depth;
		this.location = location;
		this.text = new JLabel(text, JLabel.CENTER);
		ImageIcon icon = getIcon(false);
		label = new JLabel(icon);
		
		// add to Layered pane
		width = icon.getIconWidth();
		height = icon.getIconHeight();
		setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		label.setOpaque(true);
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
	
	/** Weather the point is inside the scope of Item or not. **/
	public boolean coverPoint(Point point) {
		if (point.getX() >= location.getX() && location.getY() >= location.getY()) // ��m
			if (point.getX() <= location.getX() + width) // ��
				if (point.getY() <= location.getY() + height) // �e
					return true;
		return false;
	}
	
	/** determine the point location on which port, -1 as not inside, �Ѧ� haVancy ����k **/
	public int atWhichPort(Point point) {
		// get 4 ���I & �����I
//		int x = (int) location.getX(), y = (int) location.getY(); //�k�W ���W ���U �k�U
		Point[] apexs = {new Point(width, 0), new Point(0, 0), new Point(0, height), new Point(width, height) };
		Point center = new Point(width/2, height/2);
		// �N item �̹﨤�u�e�� 4 ���T����
		// �P�_ param ���I�b���ӤT���Τ��A�^�Ǹ� port number
		// N (0,1,center), W (1,2,center), S (2,3,center), E (3,0,center)
		for (int i = 0; i < apexs.length; i++) {
			Polygon t = new Polygon();
			int secondIndex = ((i + 1) % 4);
			t.addPoint(apexs[i].x, apexs[i].y);
			t.addPoint(apexs[secondIndex].x, apexs[secondIndex].y);
			t.addPoint(center.x, center.y);
			
			if (t.contains(point)) {
				return Port.ports[i];
			}
		}
		return -1;
	}

	/** Core method of moving: Update location by translating location & reset bounds.
	 * Makes both changes at View & ViewModel. **/
	@Override
	public void update(int offsetX, int offsetY) {
		move(offsetX, offsetY); // move on View
//		modelReference.move(offsetX, offsetY); // move in ViewModel
	}
	
	private ImageIcon getIcon(boolean selected) {
		ImageIcon icon = null;
		if ( ! selected ) {
			if (modelReference instanceof ClassObject ) 
				icon = new ImageIcon("resource/transparent/class.png");
			else if (modelReference instanceof UsecaseObject ) 
				icon = new ImageIcon("resource/transparent/usecase.png");
		}
		else {
			if (modelReference instanceof ClassObject ) 
				icon = new ImageIcon("resource/transparent/class_selected.png");
			else if (modelReference instanceof UsecaseObject ) 
				icon = new ImageIcon("resource/transparent/usecase_selected.png");
		}
		if ( icon == null )
			System.out.println("Item getIcon(): type error");
		
		return icon;
	}
	
	/** change image if selected, displaying ports **/
	public void displaySelected() {
		ImageIcon icon = getIcon(true);
		label.setIcon(icon);
	}
	/** change image if not selected, displaying original image **/
	public void displayDeselected() {
		ImageIcon icon = getIcon(false);
		label.setIcon(icon);
	}

	/* ------------- Getter Setter --------------- */
	public BasicObject getModelReference() {
		return modelReference;
	}
	
	public void setName(String name) {
		text.setText(name);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDepth() {
		return depth;
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
