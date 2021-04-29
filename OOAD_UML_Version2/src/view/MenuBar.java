package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.BasicObject;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	private final static String CHG_OBJ_NAME = "Change object name";
	private final static String GROUP = "Group";
	private final static String UNGROUP = "UnGroup";
	JMenu menu;
	JMenuItem mi;

	/** Build menu **/
	public MenuBar() {
		/* --- File menu --- */
		menu = new JMenu("File");
		add(menu);
		
		/* --- Edit menu --- */
		menu = new JMenu("Edit");
		add(menu);
		
		mi = new JMenuItem(CHG_OBJ_NAME);
//		mi.addActionListener(new MenuActionListener());
		menu.add(mi);
		mi = new JMenuItem(GROUP);
//		mi.addActionListener(new MenuActionListener());
		menu.add(mi);
		mi = new JMenuItem(UNGROUP);
//		mi.addActionListener(new MenuActionListener());
		menu.add(mi);
	}
	
	/* --- Listener --- */
	/*
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
		    	int deletedGroupId = performUnGroup();
		    	if (deletedGroupId >= 1) 
					System.out.println("[UnGroup] Deleted groupId is: " + deletedGroupId);
				else if (deletedGroupId == -1)
					System.out.println("[UnGroup] Item doesn't have any group.");
				else
					System.out.println("[UnGroup] Please select an item.");
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
	*/
	
	/** Group the selected item, call function provide by ViewModel **/
	/*
	private int performGroup() {
		// get selected items from vm
		ArrayList<Item> items = vm.getGroupSelected();
		ArrayList<BasicObject> objects = items.stream()
																	 .map(i -> i.getModelReference())
																	 .collect(Collectors.toCollection(ArrayList::new));
		int success = vm.addGroup(objects);
		return success;
	}
	
	private int performUnGroup() {
		// 幫 selected item 解除最外層的 group ，挑 groupId's tail 的群組，remove 整個群組。
		BasicObject selected = vm.getSelected();
		if (selected == null)
			return -2;
		ArrayList<Integer> groupIds = selected.getGroupIds();
		if (groupIds == null || groupIds.isEmpty())
			return -1;
		else {
			int lastGroupId = groupIds.get(groupIds.size() - 1); // array[-1] as target
			vm.removeGroup(lastGroupId);
			return lastGroupId;
		}
	}
	*/

}
