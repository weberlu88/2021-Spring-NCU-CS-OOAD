package ViewModel;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import Model.BasicObject;
import Model.ShapeFactory;
import UI.animation.Item;

public class ViewModel {
	
	/** create an object of Singleton **/ 
	private static ViewModel instance = new ViewModel();
	/** �Q��������� reference **/
	private BasicObject selected = null;
	/** store all items in viewModel **/
	private ArrayList<BasicObject> items = new ArrayList<BasicObject>();
	private HashMap< BasicObject, Item > viewReferenceMap = new HashMap< BasicObject, Item >();
	/** <group-id, items> **/
	private HashMap< Integer, ArrayList<BasicObject> > groups = new HashMap<Integer, ArrayList<BasicObject> >(); 
	private int nextGroupId = 1;
	private ShapeFactory factory = new ShapeFactory();

	//make the constructor private so that this class cannot be instantiated
	private ViewModel() {}
	
	//Get the only object available
	public static ViewModel getInstance(){
	      return instance;
	 }
	
	/*------- items related methods --------*/
	public Item addItem(String objectType, Point location) {
		// create mode object
		BasicObject newObj = factory.getObject(objectType, location);
		items.add(newObj);
		// create ui object, add its reference to map
		Item item = new Item(newObj, location, objectType);
		viewReferenceMap.put(newObj, item);
		return item;
	}
	
	public ArrayList<BasicObject> getAllItems() {
		return items;
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
	
	// group related methods
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
	
	// selected item related methods
	public BasicObject getSelected() {
		return selected;
	}

	public void setSelected(BasicObject selected) {
		this.selected = selected;
	}
}