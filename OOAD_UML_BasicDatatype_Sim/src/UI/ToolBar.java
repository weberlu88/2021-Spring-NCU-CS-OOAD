package UI;

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

import ViewModel.CreateLineState;
import ViewModel.CreateObjectState;
import ViewModel.SelectItemState;
import ViewModel.SelectObjectState;
import ViewModel.ViewModel;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar {
	private ArrayList<JButton> btnArray = new ArrayList<JButton>();
	JButton button = null;
	private Canvas canvas;
	private ViewModel vm;
	
	public enum State{
		SELECT,
		ASSOCIATE,
		GENERAL,
		COMPOSITE,
		CLASS,
		USECASE
	}
	
	public ToolBar() {
		super(VERTICAL);
		canvas = Canvas.getInstance();
		vm = ViewModel.getInstance();
		
		JButton selectBtn = makeButton("select", State.SELECT);
		add(selectBtn);
		btnArray.add(selectBtn);
		
		JButton associateBtn = makeButton("association_line", State.ASSOCIATE);
		add(associateBtn);
		btnArray.add(associateBtn);
		
		JButton generalBtn = makeButton("generation_line", State.GENERAL);
		add(generalBtn);
		btnArray.add(generalBtn);
		
		JButton compositeBtn = makeButton("composition_line", State.COMPOSITE);
		add(compositeBtn);
		btnArray.add(compositeBtn);
		
		JButton classBtn = makeButton("classes", State.CLASS);
		add(classBtn);
		btnArray.add(classBtn);
		
		JButton usecaseBtn = makeButton("use_case", State.USECASE);
		add(usecaseBtn);
		btnArray.add(usecaseBtn);
		
	}
	
	protected JButton makeButton(String imageName, State actionCommand) {
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
	    		
	    		// dynamic switch states (strategies)
	    		System.out.println("clicked: " + e.getActionCommand());
	    		switch ( State.valueOf(e.getActionCommand()) ) {
	    		case SELECT: // 選取
	    			canvas.setState( new SelectObjectState()  );
	    			vm.setStateAllItems( new SelectItemState() );
	    			break;
				case ASSOCIATE: // 畫線3種
					canvas.setState( new CreateLineState(State.ASSOCIATE.toString()) );
					vm.setStateAllItems( new CreateLineState(State.ASSOCIATE.toString()) );
					break;
				case COMPOSITE:
					canvas.setState( new CreateLineState(State.COMPOSITE.toString()) );
					vm.setStateAllItems( new CreateLineState(State.COMPOSITE.toString()) );
					break;
				case GENERAL:
					canvas.setState( new CreateLineState(State.GENERAL.toString()) );
					vm.setStateAllItems( new CreateLineState(State.GENERAL.toString()) );
					break;
				case CLASS: // 建立2種Object
					canvas.setState( new CreateObjectState(State.CLASS) );
					vm.removeStateAllItems();
					break;
				case USECASE:
					canvas.setState( new CreateObjectState(State.USECASE) );
					vm.removeStateAllItems();
					break;
				default:
					System.out.println("error: no such state in enum");
					break;
	    		}
	    	}
	    });

		return button;
	}

}
