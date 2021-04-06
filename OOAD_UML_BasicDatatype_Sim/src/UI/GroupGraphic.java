package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import UI.animation.Item;

/** ��@�Ӹs�ժ���m��T�A�Ω�ø�s��e���W **/
class Group {
	public int x; // �ؿ�ϥ��W
	public int y;
	public int height; // �ؿ�Ϫ��e
	public int width; 
	
	public Group(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
}

/** �M��ø�s�y����ءz�M�y�s�ծءz�A�� Canvas �� AWT.paint() �Q�I�s�A�O�o��call super()�H�O�dLayedpane��ܹϼh�\��  **/
public class GroupGraphic {
	
	/** create a GroupGraphic of Singleton **/ 
	private static GroupGraphic instance = null;
//	private int anotherX; // �ؿ�ϥk�U
//	private int anotherY;
	private int selectedX; // �ؿ�ϥ��W
	private int selectedY;
	private int selectedHeight; // �ؿ�Ϫ��e
	private int selectedWidth;
	
	/** Selected Item �M�L���B�ͭ� **/
	private ArrayList<Group> groupList = new ArrayList<Group>(); 
	
	private GroupGraphic() {}
	
	public static GroupGraphic getInstance() {
		if (instance == null) {
			instance = new GroupGraphic();
		}
		return instance;
	}
	
	/* ------------------ ����� ----------------- */
	
	public void setSelectedArea(Point start, Point end) {
		selectedX = (int) Math.min(start.getX(), end.getX());
		selectedY = (int) Math.min(start.getY(), end.getY());
		selectedWidth = (int) Math.abs(start.getX() - end.getX());
		selectedHeight = (int) Math.abs(start.getY() - end.getY());
//		System.out.println("[Area] start: " + start);
//		System.out.println("[Area] end: " + end);
//		System.out.println("[Area] x: " + selectedX + ", y: " + selectedY);
//		System.out.println("[Area] w: " + selectedWidth + ", h: " + selectedHeight);
	}
	
	public void clearSelectedArea() {
		selectedX = selectedY = selectedWidth = selectedHeight = 0;
	}
	
	/** �P�_�ǤJ����O�_�����]�t�b����Ϥ� **/
	public boolean withinArea(Point location, int width, int height) {
		if (location.getX() > selectedX && location.getY() > selectedY) // ��m
			if (location.getX() + width < selectedX + selectedWidth) // ��
				if (location.getY() + height < selectedY + selectedHeight) // �e
					return true;
		return false;
	}
	
	/** ��۷ƹ�ø�s����� **/
	public void drawSelectedArea(Graphics g) {
		int alpha = 85; // 33% transparent
		g.setColor(new Color(110, 219, 181, alpha));
		g.fillRect(selectedX, selectedY, selectedWidth, selectedHeight);
		g.setColor(new Color(110, 219, 181));
		g.drawRect(selectedX, selectedY, selectedWidth, selectedHeight);
	}
	
	/* ------------------ �{���s�ծ� ----------------- */
	
	/** ø�s Selected item ���ݸs�ժ���m **/
	public void drawSelectedGroups(Graphics g) {
		int alpha = 85; // 33% transparent
		for(Group group: groupList) {
			g.setColor(new Color(110, 219, 181, alpha));
			g.fillRect(group.x, group.y, group.width, group.height);
			g.setColor(new Color(110, 219, 181));
			g.drawRect(group.x, group.y, group.width, group.height);
		}
	}
	
	/** �p��C�Ӹs�ժ� area ��m�P�j�p�A�s�i groupList **/
	public void setSelectedGroups(ArrayList<Item> members) {
		// min x
		Item item = members.stream()
				.min(Comparator.comparing(n -> n.getLocation().getX()))
				.orElseThrow(NoSuchElementException::new);
		int minX = item.getX();
		// min y
		item = members.stream()
				.min(Comparator.comparing(n -> n.getLocation().getY()))
				.orElseThrow(NoSuchElementException::new);
		int minY = item.getY();
		// max x
		item = members.stream()
				.max(Comparator.comparing(n -> n.getLocation().getX() + n.getWidth() ))
				.orElseThrow(NoSuchElementException::new);
		int maxX = item.getX() + item.getWidth();
		// max y
		item = members.stream()
				.max(Comparator.comparing(n -> n.getLocation().getY() + n.getHeight() ))
				.orElseThrow(NoSuchElementException::new);
		int maxY = item.getY() + item.getHeight();
		System.out.println("min: "+minX+" "+minY+", max: "+maxX+" "+maxY);
		
		groupList.add(new Group(minX, minY, (maxX - minX), (maxY - minY) ));
	}
	
	public void clearSelectedGroups() {
		groupList.clear();
	}
	
	
}
