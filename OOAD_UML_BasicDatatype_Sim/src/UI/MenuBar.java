package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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

}
