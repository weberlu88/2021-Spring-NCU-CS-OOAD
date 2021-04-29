package controller.animation;

import java.util.ArrayList;

import model.BasicObject;


/** 透過 Observer Pattern 實作select物件和群組移動的動畫效果! 要繪製群組框請找 GroupGraphic **/
public class AnimationController {

	//create an object of Singleton 
	private static AnimationController instance = new AnimationController();
	private MovementSubject movementSubject = new MovementSubject();
	
	//make the constructor private so that this class cannot be instantiated
	private AnimationController() {}
	
	//Get the only object available
	public static AnimationController getInstance(){
	      return instance;
	 }
	
	/** get id of the member who's being dragged, observe all group members **/
	public void addMoveObserversByGroup(BasicObject item) {
		// observe itself
		movementSubject.addObserver(item); 
		// observe all group members 
		ArrayList<Integer> groupIds = item.getBasicObject().getGroupIds();
		groupIds.forEach(id -> {
			for( BasicObject mate: vm.getItemsByGroupId(id) )
				// notes: movementSubject will deal with duplicates
				movementSubject.addObserver( vm.mapItem(mate) ); 
		});
	}
	
	/** clear observer list **/
	public void removeMoveObservers() {
		movementSubject.removeAllObserver(); 
	}
	
	public void moveByGroup(int offsetX, int offsetY) {
		movementSubject.groupMove(offsetX, offsetY); 
	}
	
}
