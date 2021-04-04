package UI.animation;

import java.awt.Point;

public class AnimationController {
	
	//create an object of Singleton 
	private static AnimationController instance = new AnimationController();
	public MovementSubject movementSubject = new MovementSubject();
	
	//make the constructor private so that this class cannot be instantiated
	private AnimationController() {}
	
	//Get the only object available
	public static AnimationController getInstance(){
	      return instance;
	 }
	
	/** get id of the member who's being dragged, observe all group members **/
	public void addMoveObserversByGroup(Item item) {
		// observe all group members 
		// ......
		movementSubject.addObserver(item); // demo one object
	}
	
	/** clear observer list **/
	public void removeMoveObservers() {
		movementSubject.removeAllObserver(); 
	}
	
	public void moveByGroup(int offsetX, int offsetY) {
		movementSubject.notifyObservers(offsetX, offsetY); 
	}
}
