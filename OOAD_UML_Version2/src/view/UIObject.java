package view;

import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import controller.IController;
import model.BasicObject;
import model.ClassObject;
import model.UsecaseObject;

@SuppressWarnings("serial")
public class UIObject extends JLayeredPane {
	
	private EventListener listener = null;
	private BasicObject basicObject; // 判斷是哪種物件和其reference
	private JLabel label;
	private JLabel text;
	
	public UIObject(BasicObject basicObject, String text) {
		// 用instanceof判斷哪種物件
		this.basicObject = basicObject;
		this.text = new JLabel(text, JLabel.CENTER);
		ImageIcon icon = getIcon(false);
		label = new JLabel(icon);
		
		// init image & text, add to Layered pane itself
		setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
		basicObject.setWidth(icon.getIconWidth());
		basicObject.setHeight(icon.getIconHeight());
		label.setOpaque(true);
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		this.text.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		add(label, 1);
		add(this.text, 0);
	}
	
	/** get icon for class or object base on its type **/
	private ImageIcon getIcon(boolean selected) {
		ImageIcon icon = null;
		if ( ! selected ) {
			if (basicObject instanceof ClassObject ) 
				icon = new ImageIcon("resource/transparent/class.png");
			else if (basicObject instanceof UsecaseObject ) 
				icon = new ImageIcon("resource/transparent/usecase.png");
		}
		else {
			if (basicObject instanceof ClassObject ) 
				icon = new ImageIcon("resource/transparent/class_selected.png");
			else if (basicObject instanceof UsecaseObject ) 
				icon = new ImageIcon("resource/transparent/usecase_selected.png");
		}
		if ( icon == null )
			System.out.println("Item getIcon(): type error");
		
		return icon;
	}
	
	public void setListener(IController newListener) {
		removeListener();
		listener = newListener;
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	public void removeListener() {
		removeMouseListener((MouseListener) listener);
		removeMouseMotionListener((MouseMotionListener) listener);
	}
	
	/* ------------- Getter Setter --------------- */
	public BasicObject getBasicObject() {
		return basicObject;
	}
	
	public void setName(String name) {
		text.setText(name);
	}

}
