package Model;

import java.awt.Point;

import UI.animation.Item;

public class ShapeFactory implements ShapeFactoryInterface {

	/** ref: https://www.tutorialspoint.com/design_pattern/factory_pattern.htm **/
	@Override
	public BasicObject getObject(String objectType, Point location) {
		if (objectType == null) {
		     return null;
		  }		
		  if (objectType.equalsIgnoreCase("CLASS")) {
		     return new ClassObject(location);
		     
		  } else if (objectType.equalsIgnoreCase("USECASE")) {
		     return new UsecaseObject(location);
		  }
		return null;
	}

	@Override
	public Line getLine(String lineType, Port start, Port end) {
		// TODO Auto-generated method stub
		// i don't care which line it is...
		return null;
	}
	
	@Override
	public Line getLine(Port start, Port end) {
		return new Line(start, end);
	}

	@Override
	public Line getLine(Item src, int srcPort, Item dest, int destPort) {
		return new Line(src, srcPort, dest, destPort);
	}
	
	public Line getLine(Item src, int srcPort, Item dest, int destPort, String state) {
		System.out.println(".....factory:"+state);
		return new Line(src, srcPort, dest, destPort, state);
	}
	
	

}
