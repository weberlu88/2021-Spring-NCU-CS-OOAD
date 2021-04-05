package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GroupGraphic {
	
	/** create a GroupGraphic of Singleton **/ 
	private static GroupGraphic instance = null;
//	private int anotherX; // �ؿ�ϥk�U
//	private int anotherY;
	private int selectedX; // �ؿ�ϥ��W
	private int selectedY;
	private int selectedHeight;
	private int selectedWidth;
	
	private GroupGraphic() {}
	
	public static GroupGraphic getInstance() {
		if (instance == null) {
			instance = new GroupGraphic();
		}
		return instance;
	}
	
	public void setSelectedArea(Point start, Point end) {
		selectedX = (int) Math.min(start.getX(), end.getX());
		selectedY = (int) Math.min(start.getY(), end.getY());
		selectedWidth = (int) Math.abs(start.getX() - end.getX());
		selectedHeight = (int) Math.abs(start.getY() - end.getY());
		System.out.println("[Area] start: " + start);
		System.out.println("[Area] end: " + end);
		System.out.println("[Area] x: " + selectedX + ", y: " + selectedY);
		System.out.println("[Area] w: " + selectedWidth + ", h: " + selectedHeight);
	}
	
	public void clearSelectedArea() {
		selectedX = selectedY = selectedWidth = selectedHeight = 0;
	}
	
	/** ��۷ƹ�ø�s����� **/
	public void drawSelectedArea(Graphics g) {
		int alpha = 85; // 33% transparent
		g.setColor(new Color(110, 219, 181, alpha));
		g.fillRect(selectedX, selectedY, selectedWidth, selectedHeight);
		g.setColor(new Color(110, 219, 181));
		g.drawRect(selectedX, selectedY, selectedWidth, selectedHeight);
	}
	
	/** ø�s Selected item ���s�ժ����m **/
	public void drawSelectedGroups(Graphics g) {
		
	}
	
}
