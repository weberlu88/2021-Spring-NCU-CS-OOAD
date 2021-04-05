package ViewModel;

import java.awt.event.MouseEvent;

import UI.GroupGraphic;
import UI.animation.Item;

public class CreateObjectState extends State {
	
	private String objectType;
	GroupGraphic groupPainter = GroupGraphic.getInstance();
	
	public CreateObjectState(UI.ToolBar.State objectType) {
		this.objectType = objectType.toString();
		// clear selected area
		groupPainter.clearSelectedArea();
		canvas.repaint();
	}

	/** create an usecase or a class**/
	public void mousePressed(MouseEvent e) {
		// create data-component in ViewModel, return ui-component on View
		int depth = canvas.getNextDepth();
		Item item = vm.addItem(objectType, e.getPoint(), depth);
		canvas.add(item, depth);
	}
	

}
