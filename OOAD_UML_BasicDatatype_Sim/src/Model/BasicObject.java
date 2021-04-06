package Model;

import java.awt.Point;
import java.util.ArrayList;

public class BasicObject extends Shape {
	
	protected String objectName = "Object Name";
	protected ArrayList<Integer> groupIds = new ArrayList<Integer>();
//	protected int width, height; // no need ??
//	protected Port[] ports = new Port[4];
	
	public BasicObject(Point location) {
		super(location);
	}
	
	public String getName() {
		return objectName;
	}
	
	public void setName(String name) {
		objectName = name;
	}
	
	// group related methods
	public boolean hasGroup() {
		return ! groupIds.isEmpty();
	}
	
	public boolean inGroup(int groudId) {
		return groupIds.contains(groudId);
	}
	
	public ArrayList<Integer> getGroupIds() {
		return groupIds;
	}
	
	public void addToGroup(int groudId) {
		if ( inGroup(groudId) )
			return;
		groupIds.add(groudId);
	}
	
	public void removeFromGroup(int groudId) {
		int index = groupIds.indexOf(groudId);
		groupIds.remove(index);
	}

//	public Port getPort(int portIndex) {
//		return ports[portIndex];
//	}
	
//	protected void createPorts() {
//
//		for(int i = 0; i < ports.length; i++) {
//			Port port = new Port();
//			ports[i] = port;
//		}
	
}
