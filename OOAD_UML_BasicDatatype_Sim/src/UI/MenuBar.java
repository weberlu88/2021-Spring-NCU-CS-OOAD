package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import Model.BasicObject;
import UI.animation.Item;
import ViewModel.ViewModel;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private final static String CHG_OBJ_NAME = "Change object name";
	private final static String GROUP = "Group";
	private final static String UNGROUP = "UnGroup";
	JMenu menu;
	JMenuItem mi;
	
	ViewModel vm = ViewModel.getInstance();

	/** Build menu **/
	public MenuBar() {
		/* --- File menu --- */
		menu = new JMenu("File");
		add(menu);
		
		/* --- Edit menu --- */
		menu = new JMenu("Edit");
		add(menu);
		
		mi = new JMenuItem(CHG_OBJ_NAME);
		mi.addActionListener(new MenuActionListener());
		menu.add(mi);
		mi = new JMenuItem(GROUP);
		mi.addActionListener(new MenuActionListener());
		menu.add(mi);
		mi = new JMenuItem(UNGROUP);
		mi.addActionListener(new MenuActionListener());
		menu.add(mi);
	}
	
	/* --- Listener --- */
	class MenuActionListener implements ActionListener {
		  public void actionPerformed(ActionEvent e) {
		    System.out.println("Selected: " + e.getActionCommand());
		    
		    switch (e.getActionCommand()) {
		    case CHG_OBJ_NAME: 
		    	if (vm.getSelected() != null) {
		    		String name = vm.getSelected().getName();
			    	name = showChangeNameDialog(name);
			    	if (name != null)
			    		vm.setSelectedName(name);
		    	}
		    	break;
		    	
		    case GROUP:
		    	int nextGroupId = performGroup();
		    	if (nextGroupId >= 1) 
					System.out.println("[Group] New groupId is: " + nextGroupId);
				else
					System.out.println("[Group] No item to group.");
		    	break;
		    	
		    case UNGROUP:
		    	break;
		    }

		  }
	}
	
	private String showChangeNameDialog(String name) {
		
		String s = (String)JOptionPane.showInputDialog(this,
                "Please enter object's name:\n",
                name);
		if (s != null && !s.isEmpty() && !s.equals(name)  )
			return s;
		return null;
	}
	
	/** Group the selected item, call function provide by ViewModel **/
	private int performGroup() {
		// get selected items from vm
		ArrayList<Item> items = vm.getGroupSelected();
		ArrayList<BasicObject> objects = items.stream()
																	 .map(i -> i.getModelReference())
																	 .collect(Collectors.toCollection(ArrayList::new));
		int success = vm.addGroup(objects);
		return success;
	}

}
