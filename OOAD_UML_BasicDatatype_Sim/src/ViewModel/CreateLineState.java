package ViewModel;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import UI.GroupGraphic;
import UI.animation.Item;

public class CreateLineState extends State {
	
	private String lineType;
	private Item scr, dest;
	private int scrPortNumber = 0, destPortNumber = 0; // 先都設N
	GroupGraphic groupPainter = GroupGraphic.getInstance();
	
	public CreateLineState(UI.ToolBar.State lineType) {
		this.lineType = lineType.toString();
		// clear selected area
		groupPainter.clearSelectedArea();
		canvas.repaint();
	}
	
	public CreateLineState() {
		// clear selected area
		groupPainter.clearSelectedArea();
		canvas.repaint();
	}
	
	/** create start point for line, only accept events from UI.Item **/
	public void mousePressed(MouseEvent e) {
		// Record Line::Source::Item & Line::Source::PortNumber
		if (e.getSource() instanceof Item) {
			// Line::Source::Item
			scr = (Item) (e.getSource());
			// Line::Source::PortNumber (determine which port to connect)
			e.getPoint(); 
			System.out.println("[Press] "+ scr);
		}
	}
	
	/** create end point for line, only accept events from UI.Item.
	 * Waring: e.getSource 還是你 pressed 下去的那個 Item, 須自己依位置找出放開在哪個 Item 上 **/
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() instanceof Item) {
			// Line::Destination::Item 
			try {
				Point mousePosition = e.getComponent().getParent().getMousePosition();
				ArrayList<Item> itemsCoverPoint = vm.findItemByLocation(mousePosition);
				dest = itemsCoverPoint.get(0);
			} catch (Exception ex) {
				System.out.println("[Release] get nothing");
//				ex.printStackTrace();
				return;
			}
			// Line::Destination::PortNumber (determine which port to connect)
			e.getPoint(); 
			System.out.println("[Release] "+ dest);
			// store & draw the line
			vm.addLine(scr, scrPortNumber, dest, destPortNumber);
			canvas.repaint();
		}
	}

}
