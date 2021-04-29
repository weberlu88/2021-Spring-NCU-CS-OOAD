package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import controller.IController;

@SuppressWarnings("serial")
public class Canvas extends JLayeredPane {
	
	int counter = 0; // delete it
	private static Canvas instance = null;
	private EventListener listener = null;
	private int nextDepth = 0; 

	/** Build canvas with layered panel **/
	private Canvas() {
		setPreferredSize(new Dimension(800, 600));
	}
	
	public static Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}
	
	@Override
	public void paint(Graphics g) {
        super.paint(g);
        // draw the selected area & selected item's groups
//        groupPainter.drawSelectedArea(g);
//        groupPainter.drawSelectedGroups(g);
//        linePainter.drawLines(g);
//        linePainter.drawLinkingLine(g);
    }
	
	/** In Strategy pattern, the canvas's listener/algorithm can be changed at run time 
	 * by passing various strategies interface 
	 * @param newState : switches canvas's behavior via applying new listener.**/
	public void setController(IController newState) {
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
		listener = newState;
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	
	public int getNextDepth() {
		return nextDepth++; // the bigger the depth is, the upper layer it locates.
	}

	// test code
	public void addObject() {
		ImageIcon icon = new ImageIcon("resource/transparent/class.png");
		JLabel label = new JLabel(icon);
		label.setBounds(100, 200,
                icon.getIconWidth(),
                icon.getIconHeight());
		add(label, 2, 0);
	}
}
