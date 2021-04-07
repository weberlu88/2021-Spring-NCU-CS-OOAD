package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import Model.Line;
import ViewModel.ViewModel;

public class LineGraphic {

	/** create a GroupGraphic of Singleton **/ 
	private static LineGraphic instance = null;
	private static ViewModel vm = ViewModel.getInstance();
	private Point linkingStart, linkingEnd;
	
	private LineGraphic() {}
	
	public static LineGraphic getInstance() {
		if (instance == null) {
			instance = new LineGraphic();
		}
		return instance;
	}
	
	/** ø�s���b�e���W�õe(x)�s�u(o)��Line **/
	public void drawLinkingLine(Graphics g) {
		// �]�� Line ����ݭn Item �A�ҥH���t�~�e
		if(linkingStart != null && linkingEnd != null) {
			g.setColor(Color.black);
			g.drawLine(linkingStart.x, linkingStart.y, linkingEnd.x, linkingEnd.y);
		}
	}
	
	/** ø�s�Ҧ��w�s�u��Line **/
	public void drawLines(Graphics g) {
		vm.getAllLines().forEach(line -> line.draw(g));
	}
	
	/* ------------- Getter Setter -------------*/
	public Point getLinkingEnd() {
		return linkingEnd;
	}

	public void setLinkingEnd(Point linkingEnd) {
		this.linkingEnd = linkingEnd;
	}

	public Point getLinkingStart() {
		return linkingStart;
	}

	public void setLinkingStart(Point linkingStart) {
		this.linkingStart = linkingStart;
	}
	
	public void clearLinkingLine() {
		linkingStart = linkingEnd = null;
	}

}
