package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import UI.animation.Item;

/** 單一個群組的位置資訊，用於繪製於畫布上 **/
class Group {
	public int x; // 框選區左上
	public int y;
	public int height; // 框選區長寬
	public int width; 
	
	public Group(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}
}

/** 專門繪製『選取框』和『群組框』，於 Canvas 中 AWT.paint() 被呼叫，記得先call super()以保留Layedpane顯示圖層功能  **/
public class GroupGraphic {
	
	/** create a GroupGraphic of Singleton **/ 
	private static GroupGraphic instance = null;
//	private int anotherX; // 框選區右下
//	private int anotherY;
	private int selectedX; // 框選區左上
	private int selectedY;
	private int selectedHeight; // 框選區長寬
	private int selectedWidth;
	
	/** Selected Item 和他的朋友們 **/
	private ArrayList<Group> groupList = new ArrayList<Group>(); 
	
	private GroupGraphic() {}
	
	public static GroupGraphic getInstance() {
		if (instance == null) {
			instance = new GroupGraphic();
		}
		return instance;
	}
	
	/* ------------------ 選取框 ----------------- */
	
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
	
	/** 判斷傳入物件是否完全包含在選取區內 **/
	public boolean withinArea(Point location, int width, int height) {
		if (location.getX() > selectedX && location.getY() > selectedY) // 位置
			if (location.getX() + width < selectedX + selectedWidth) // 長
				if (location.getY() + height < selectedY + selectedHeight) // 寬
					return true;
		return false;
	}
	
	/** 跟著滑鼠繪製選取區 **/
	public void drawSelectedArea(Graphics g) {
		int alpha = 85; // 33% transparent
		g.setColor(new Color(110, 219, 181, alpha));
		g.fillRect(selectedX, selectedY, selectedWidth, selectedHeight);
		g.setColor(new Color(110, 219, 181));
		g.drawRect(selectedX, selectedY, selectedWidth, selectedHeight);
	}
	
	/* ------------------ 現有群組框 ----------------- */
	
	/** 繪製 Selected item 所屬群組的位置 **/
	public void drawSelectedGroups(Graphics g) {
		int alpha = 85; // 33% transparent
		for(Group group: groupList) {
			g.setColor(new Color(110, 219, 181, alpha));
			g.fillRect(group.x, group.y, group.width, group.height);
			g.setColor(new Color(110, 219, 181));
			g.drawRect(group.x, group.y, group.width, group.height);
		}
	}
	
	/** 計算每個群組的 area 位置與大小，存進 groupList **/
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
