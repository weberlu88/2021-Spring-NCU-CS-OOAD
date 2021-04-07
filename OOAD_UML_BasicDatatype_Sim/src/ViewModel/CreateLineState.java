package ViewModel;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import UI.GroupGraphic;
import UI.LineGraphic;
import UI.animation.Item;

public class CreateLineState extends State {
	
	private String lineType;
	private Item scr, dest;
	private int scrPortNumber = -1, destPortNumber = -1; // Dev時先都設N
	GroupGraphic groupPainter = GroupGraphic.getInstance();
	LineGraphic linePainter = LineGraphic.getInstance();
	
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
	@Override
	public void mousePressed(MouseEvent e) {
		// Record Line::Source::Item & Line::Source::PortNumber
		if (e.getSource() instanceof Item) {
			// Line::Source::Item
			scr = (Item) (e.getSource());
			// Line::Source::PortNumber (determine which port to connect)
			scrPortNumber = scr.atWhichPort( e.getPoint() );
			System.out.println("[Press] port"+scrPortNumber+", "+scr);
			// draw linking-line
			Point mousePosition = e.getComponent().getParent().getMousePosition();
			linePainter.setLinkingStart(mousePosition);
			linePainter.setLinkingEnd(null);
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// draw linking-line 在螢幕上拉來拉去的線
		if (e.getSource() instanceof Item) {
			Point mousePosition = e.getComponent().getParent().getMousePosition();
			linePainter.setLinkingEnd(mousePosition);
			canvas.repaint();
		}
	}
	
	/** create end point for line, only accept events from UI.Item.
	 * Waring: e.getSource 還是你 pressed 下去的那個 Item, 須自己依位置找出放開在哪個 Item 上 **/
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getSource() instanceof Item) {
			// clear linking line
			linePainter.clearLinkingLine();
			canvas.repaint();
			// Line::Destination::Item 
			Point mousePosition;
			try {
				mousePosition = e.getComponent().getParent().getMousePosition();
				ArrayList<Item> itemsCoverPoint = vm.findItemByLocation(mousePosition);
				dest = itemsCoverPoint.get(0);
			} catch (Exception ex) {
				System.out.println("[Release] get nothing");
//				ex.printStackTrace();
				return;
			}
			if (scr == dest) // 不能自己連自己
				return;
			// Line::Destination::PortNumber (determine which port to connect)
			// 須將滑鼠座標轉換為在 dest 上的座標 (0,0) ~ (w, h)
			Point pointRelateToDest = mousePosition;
			pointRelateToDest.translate(-dest.location.x, -dest.location.y);
			destPortNumber = dest.atWhichPort( pointRelateToDest );
			System.out.println("[Release] port"+destPortNumber+", "+dest);
			// store & draw the line
			vm.addLine(scr, scrPortNumber, dest, destPortNumber);
			canvas.repaint();
		}
	}

}
