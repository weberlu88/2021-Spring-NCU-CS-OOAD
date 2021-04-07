package ViewModel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import Model.BasicObject;
import Model.Line;
import Model.ShapeFactory;
import UI.GroupGraphic;
import UI.animation.Item;

public class ViewModel {
	
	/** create an object of Singleton **/ 
	private static ViewModel instance = new ViewModel();
	/** 被選取的物件 reference **/
	private BasicObject selected = null;
	private ArrayList<Item> groupSelected = new ArrayList<Item>(); // 被多選的物件，未進行群組
	/** store all items in viewModel **/
	private ArrayList<BasicObject> items = new ArrayList<BasicObject>();
	private HashMap< BasicObject, Item > viewReferenceMap = new HashMap< BasicObject, Item >();
	/** <group-id, items> **/
	private HashMap< Integer, ArrayList<BasicObject> > groups = new HashMap<Integer, ArrayList<BasicObject> >(); 
	private int nextGroupId = 1;
	/** Lines **/
	private ArrayList<Line> lines = new ArrayList<Line>();
	private ShapeFactory factory = new ShapeFactory();

	//make the constructor private so that this class cannot be instantiated
	private ViewModel() {}
	
	//Get the only object available
	public static ViewModel getInstance(){
	      return instance;
	 }
	
	/*------- items related methods --------*/
	public Item addItem(String objectType, Point location, int depth) {
		// create mode object
		BasicObject newObj = factory.getObject(objectType, location);
		newObj.setName(objectType);
		items.add(newObj);
		// create ui object, add its reference to map
		Item item = new Item(newObj, location, objectType, depth);
		viewReferenceMap.put(newObj, item);
		return item;
	}
	
	public ArrayList<BasicObject> getAllItems() {
		return items;
	}
	
	public Item mapItem(BasicObject obj) {
		return viewReferenceMap.get(obj);
	}
	
	/** set new strategy pattern for every items(ui) **/
	public void setStateAllItems(State newState) {
		items.forEach(i -> { 
			viewReferenceMap.get(i).setState(newState);
		});
	}
	
	public void removeStateAllItems() {
		items.forEach(i -> { 
			viewReferenceMap.get(i).removeState();
		});
	}
	
	/** 尋找涵蓋傳入 location 的所有 item，最上層(depth最低)的排在最前面，return null if not found **/
	public ArrayList<Item> findItemByLocation(Point location) {
		ArrayList<Item> itemsInLocation = new ArrayList<Item>();
		for (Item item : viewReferenceMap.values()) {
			if (item.coverPoint(location))
				itemsInLocation.add(item); // item cover the point -> push back in list
		}
		if (itemsInLocation.isEmpty())
			return null;
		else {
			itemsInLocation.sort(Comparator.comparing(Item::getDepth));
			return itemsInLocation;
		}
	}
	
	/*------- group related methods --------*/
	public int getNextGroupId() {
		return nextGroupId;
	}
	
	public int addGroup(ArrayList<BasicObject> items) {
		if (items == null || items.isEmpty() || items.size() ==1 )
			return -1;
		for (BasicObject item : items)
			item.addToGroup(nextGroupId);
		groups.put(nextGroupId, items);
		return nextGroupId++;
	}
	
	public void removeGroup(int id) {
		ArrayList<BasicObject> items = getItemsByGroupId(id);
		if ( items != null ) {
			for (BasicObject item : items)
				item.removeFromGroup(id);
			groups.remove(id);
		}
	}
	
	public ArrayList<BasicObject> getItemsByGroupId(int id) {
		if ( groups.containsKey(id) )
			return groups.get(id);
		return null;
	}
	
	/*------- selected item related methods --------*/
	public void triggerSelect(Item item) {
		removeSelect();
		selected = item.getModelReference(); // record selected object in vm
		item.displaySelected();
	}
	
	public void removeSelect() {
		// change previous selected item icon to original looking, BasicObject selected = null
		if (selected != null) {
			mapItem(selected).displayDeselected();
			selected = null;
		}
	}
	
	public BasicObject getSelected() {
		return selected;
	}

	public void setSelected(BasicObject selected) {
		this.selected = selected;
	}

	public void setSelectedName(String name) {
		selected.setName(name);
		mapItem(selected).setName(name);
	}

	/** 多選物件: find items within area, then push them in selected list **/
	public void selectItemsInArea(GroupGraphic groupPainter) {
		// 遍歷 UI-items，若item在範圍內，加入至群組並將外觀改為selected
		for (Item item : viewReferenceMap.values()) {
			if (groupPainter.withinArea(item.location, item.getWidth(), item.getHeight()) )
				if ( !groupSelected.contains(item) ) {
					groupSelected.add(item);
					item.displaySelected();
				}
		}
	}
	
	public void clearGroupSelect() {
		groupSelected.forEach(item -> { item.displayDeselected(); }); 
		groupSelected.clear();
	}

	public ArrayList<Item> getGroupSelected() {
		return groupSelected;
	}
	
	/*-------line related methods --------*/
	public ArrayList<Line> getAllLines() {
		return lines;
	}
	
	public Line addLine(Item src, int srcPort, Item dest, int destPort) {
		Line newLine = factory.getLine(src, srcPort, dest, destPort);
		lines.add(newLine);
		return newLine;
	}
}
