package ViewModel;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import UI.GroupGraphic;
import UI.LineGraphic;
import UI.animation.Item;
import ViewModel.State;
//import UI.ToolBar.State;

public class CreateLineState extends State {
	
	private String lineType;
	private Item scr, dest;
	private int scrPortNumber = -1, destPortNumber = -1; // Dev�ɥ����]N
	GroupGraphic groupPainter = GroupGraphic.getInstance();
	LineGraphic linePainter = LineGraphic.getInstance();
	
	public CreateLineState(String lineType) {
		this.lineType = lineType;
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
		// draw linking-line �b�ù��W�Ԩөԥh���u
		if (e.getSource() instanceof Item) {
			Point mousePosition = e.getComponent().getParent().getMousePosition();
			linePainter.setLinkingEnd(mousePosition);
			canvas.repaint();
		}
	}
	
	/** create end point for line, only accept events from UI.Item.
	 * Waring: e.getSource �٬O�A pressed �U�h������ Item, ���ۤv�̦�m��X��}�b���� Item �W **/
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
			if (scr == dest) // ����ۤv�s�ۤv
				return;
			// Line::Destination::PortNumber (determine which port to connect)
			// ���N�ƹ��y���ഫ���b dest �W���y�� (0,0) ~ (w, h)
			Point pointRelateToDest = mousePosition;
			pointRelateToDest.translate(-dest.location.x, -dest.location.y);
			destPortNumber = dest.atWhichPort( pointRelateToDest );
			System.out.println("[Release] port"+destPortNumber+", "+dest);
			// store & draw the line
			vm.addLine(scr, scrPortNumber, dest, destPortNumber, lineType);
			canvas.repaint();
		}
	}

}