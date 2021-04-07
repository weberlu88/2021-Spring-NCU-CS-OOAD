package Model;

import java.awt.Point;

import UI.animation.Item;

public class Port {
	public static final int N = 0;
	public static final int W = 1;
	public static final int S = 2;
	public static final int E = 3;
	public static final Integer[] ports = new Integer[] {N, W, S, E};
	private int port;
	private Item item;
	
	public Port(Item item, int whichPort) {
		if (whichPort >= 4 || whichPort < 0) {
			System.out.println("[Port Construtor] invalid port number");
			return;
		}
		setItem(item);
		setPort(whichPort);
	}
	
	/** 回傳 port 在畫布上什麼位置 **/
	public Point getLocation() {
		int itemX = (int) item.location.getX();
		int itemY = (int) item.location.getY();
		switch (port) {
		case N:
			return new Point(itemX + item.getWidth()/2, itemY);
		case W:
			return new Point(itemX, itemY + item.getHeight()/2);
		case S:
			return new Point(itemX + item.getWidth()/2, itemY + item.getHeight());
		case E:
			return new Point(itemX + item.getWidth(), itemY + item.getHeight()/2);
		}
		return null;
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
}
