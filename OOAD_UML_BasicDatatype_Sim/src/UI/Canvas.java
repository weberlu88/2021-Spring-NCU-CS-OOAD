package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import UI.animation.AnimationController;
import UI.animation.Item;
import ViewModel.CreateObjectState;
import ViewModel.State;

@SuppressWarnings("serial")
public class Canvas extends JLayeredPane {
	
	int counter = 0; // delete it
	private static Canvas instance = null;
	private EventListener listener = null;
	private int nextDepth = Integer.MAX_VALUE;

	/** Build canvas with layered panel **/
	private Canvas() {
//		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(800, 600));
//		setBounds(0, 0, 800, 600);
//		setOpaque(true);
//		setBackground(Color.blue);

//		addObject(); //test
		
	}
	
	public static Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}
	
	/** In Strategy pattern, the canvas's listener/algorithm can be changed at run time 
	 * by passing various strategies interface 
	 * @param newState : switches canvas's behavior via applying new listener.**/
	public void setState(State newState) {
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
		listener = newState;
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	public int getNextDepth() {
		return nextDepth--; // the bigger the depth is, the upper layer it locates.
	}

	public void addObject() {
		ImageIcon icon = new ImageIcon("resource/transparent/class.png");
		JLabel label = new JLabel(icon);
		label.setBounds(100, 200,
                icon.getIconWidth(),
                icon.getIconHeight());
		add(label, 2, 0);
		
//		label = new JLabel(icon);
//		label.setBounds(60, 60,
//                icon.getIconWidth(),
//                icon.getIconHeight());
//		add(label, 1, 0);
	}

	/** add ui-item on canvas **/
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		System.out.println("click at "+ e.getPoint());
//		String objType;
//		if((++counter)%2 == 0) 
//			objType = "class";
//		else
//			objType = "usecase";
//		
//		Item item = new Item(objType, e.getPoint(), "hello");
//		add(item, 1);
//	}

//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
////		animationController.addMoveObserversByGroup(this);
////		System.out.println("canvas mousePressed: " + e.getPoint());
//		System.out.println("canvas event: " + e);
////		System.out.println("mouse abs position: " + MouseInfo.getPointerInfo().getLocation());
//		System.out.println("");
//	}
}
