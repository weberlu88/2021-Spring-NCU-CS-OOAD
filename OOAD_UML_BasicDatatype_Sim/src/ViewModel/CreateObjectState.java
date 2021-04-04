package ViewModel;

import java.awt.event.MouseEvent;

import UI.animation.Item;

public class CreateObjectState extends State {
	
	private String objectType;
	
	public CreateObjectState(UI.ToolBar.State objectType) {
		this.objectType = objectType.toString();
	}

	/** create an usecase or a class**/
	public void mousePressed(MouseEvent e) {
		// create data-component in ViewModel, return ui-component on View
		int depth = canvas.getNextDepth();
		Item item = vm.addItem(objectType, e.getPoint(), depth);
		canvas.add(item, depth);
	}
	

}
