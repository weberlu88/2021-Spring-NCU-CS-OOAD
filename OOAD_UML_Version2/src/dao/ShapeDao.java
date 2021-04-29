package dao;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import model.BasicObject;
import model.Shape;

public class ShapeDao { // can change to class Shape?

	//create BasicObjectDao of SingleObject
	private static ShapeDao instance = new ShapeDao();
	
	// Store shapes
	private Set<Shape> objects = new HashSet<Shape>();
	// Store groups
	private Set<Group>;
	private Set<GroupRelation>;

	//make the constructor private so that this class cannot be instantiated
	private ShapeDao() {}
	
	//Get the only object available
	public static ShapeDao getInstance(){
		return instance;
	}
	
	/* ---- Getter ---- */
	public Set<Shape> getAllShapes() {
		return objects;
	}
	
	public Set<BasicObject> getAllBasicObjects() {
		return objects.stream()
				.filter(BasicObject.class::isInstance)
				.map(BasicObject.class::cast)
				.collect(Collectors.toSet());
	}
	
	public Set<Shape> getAllLines() {
		return null;
	}
	
	/* ---- Add a single element ---- */
	public boolean add(Shape shape) {
		return objects.add(shape);
	}
	
	/* ---- Remove a single element ---- */
	public boolean remove(Shape shape) {
		return objects.remove(shape);
	}
	
	public Set<BasicObject> getByGroup() {
		
	}
}
