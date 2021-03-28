package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	private int ToolNum = 6;
	JButton button = null;
	
	public ToolBar() {
		super(VERTICAL);
		
		JButton selectBtn = makeButton("select", "select");
		add(selectBtn);
		
		JButton associateBtn = makeButton("association_line", "associate");
		add(associateBtn);
		
		JButton generalBtn = makeButton("generation_line", "general");
		add(generalBtn);
		
		JButton compositeBtn = makeButton("composition_line", "composite");
		add(compositeBtn);
		
		JButton classBtn = makeButton("classes", "class");
		add(classBtn);
		
		JButton usecaseBtn = makeButton("use_case", "usecase");
		add(usecaseBtn);
		
	}
	
	protected JButton makeButton(String imageName, String actionCommand) {
		// init button
		JButton button = new JButton();
		ImageIcon icon = new ImageIcon("resource/"+ imageName + ".png");
		button.setIcon(icon);
		button.setActionCommand(actionCommand);
	    button.setToolTipText(actionCommand + " button");
	    button.setFocusable(false); //?
	    button.setBorderPainted(false);
	    button.setRolloverEnabled(true);
	    
	    // add listener
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("clicked: " + e.getActionCommand());
	    	}
	    });

		return button;
	}

}
