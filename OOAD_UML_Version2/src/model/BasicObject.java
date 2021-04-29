package model;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import controller.animation.IObserver;
import view.UIObject;

public class BasicObject implements Shape, IObserver {
	
	protected Point location; // 左上角
	protected Point mousePosition; // 滑鼠按在canvas上的點，not 左上角
	protected int width, height;
	protected int depth;
	protected String objectName = "Object Name";
	protected Set<Integer> groupIds = new HashSet<Integer>(); // need normalize
	protected UIObject uiObject;
	
	public BasicObject() {}
	public BasicObject(Point location, int depth) {
		this.location = location;
		this.depth = depth;
		this.uiObject = new UIObject(this, this.getClass().getName());
	}
	
	/* ---- implement IObserver's update() method ---- */ 
	@Override
	public void update(int offsetX, int offsetY) {
		move(offsetX, offsetY);
	}

	/* ---- move the location in both model and view  ---- */
	@Override
	public void move(Point offset) {
		location.translate(offset.x, offset.y);
		renderUiElement();
	}

	@Override
	public void move(int dx, int dy) {
		location.translate(dx, dy);
		renderUiElement();
	}

	@Override
	public void moveTo(Point location) {
		this.location.setLocation(location);
		renderUiElement();
	}
	
	public void renderUiElement() {
		uiObject.setBounds(location.x, location.y, width, height);
	}
	
	/* ---- group related methods ---- */
	public boolean hasGroup() {
		return ! groupIds.isEmpty();
	}
	
	public boolean inGroup(int groudId) {
		return groupIds.contains(groudId);
	}
	
	public Set<Integer> getGroupIds() {
		return groupIds;
	}
	
	public boolean addGroup(int groudId) {
		return groupIds.add(groudId);
	}
	
	public boolean removeGroup(int groudId) {
		return groupIds.remove(groudId);
	}
	
	/* ---- Getter & Setter ---- */
	public UIObject getUiObject() {
		return uiObject;
	}
	public String getName() {
		return objectName;
	}
	public void setName(String name) {
		objectName = name;
		if (uiObject != null) 
			uiObject.setName(name);
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public Point getLocation() {
		return location;
	}
	public void setLocation(Point location) {
		this.location = location;
	}
	public Point getMousePosition() {
		return mousePosition;
	}
	public void setMousePosition(Point mousePosition) {
		this.mousePosition = mousePosition;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
}
