package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import controller.CreateObjectController;
import controller.MainController;
import controller.Mode;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	private ArrayList<JButton> btnArray = new ArrayList<JButton>();
	JButton button = null;
	MainController mainController;
	
	public ToolBar() {
		super(VERTICAL);
		mainController = MainController.getInstance();
		
		JButton selectBtn = makeButton("select", Mode.SELECT);
		add(selectBtn);
		btnArray.add(selectBtn);
		
		JButton associateBtn = makeButton("association_line", Mode.ASSOCIATE);
		add(associateBtn);
		btnArray.add(associateBtn);
		
		JButton generalBtn = makeButton("generation_line", Mode.GENERAL);
		add(generalBtn);
		btnArray.add(generalBtn);
		
		JButton compositeBtn = makeButton("composition_line", Mode.COMPOSITE);
		add(compositeBtn);
		btnArray.add(compositeBtn);
		
		JButton classBtn = makeButton("classes", Mode.CLASS);
		add(classBtn);
		btnArray.add(classBtn);
		
		JButton usecaseBtn = makeButton("use_case", Mode.USECASE);
		add(usecaseBtn);
		btnArray.add(usecaseBtn);
		
	}
	
	protected JButton makeButton(String imageName, Mode actionCommand) {
		// init button
		JButton button = new JButton();
		ImageIcon icon = new ImageIcon("resource/"+ imageName + ".png");
		button.setIcon(icon);
		button.setActionCommand(actionCommand.toString());
	    button.setToolTipText(actionCommand.toString() + " button");
	    button.setFocusable(false); //?
	    button.setBorderPainted(false);
	    button.setRolloverEnabled(true);
	    
	    // add listener
	    button.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		// reset all buttons background, and change pressed btn's background
	    		for(JButton btn: btnArray)
	    			btn.setBackground(UIManager.getColor ( "Panel.background" ));
	    		((JButton) e.getSource()).setBackground(new Color(255, 204, 255));
	    		
	    		// dynamic switch modes via MainContoller (strategies)
	    		System.out.println("clicked: " + e.getActionCommand());
	    		mainController.setMode(actionCommand);
	    	}
	    });

		return button;
	}

}
