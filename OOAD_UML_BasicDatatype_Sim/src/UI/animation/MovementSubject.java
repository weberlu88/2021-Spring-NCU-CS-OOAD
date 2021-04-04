package UI.animation;

import java.awt.Point;
import java.util.ArrayList;

public class MovementSubject {

	// use array list implementation for collection of observers
	private ArrayList<IObserver> observers = new ArrayList<IObserver>();

	public void addObserver(IObserver observer) {
		observers.add(observer);
	}
	
	public void removeObserver(IObserver observer) {
		observers.remove(observer);
	}
	
	public void removeAllObserver() {
		observers.clear();
	}
	
	public void notifyObservers(int offsetX, int offsetY) {
        observers.forEach(observer -> observer.update(offsetX, offsetY));
    }
	
	public void groupMove(int offsetX, int offsetY) { // no need?
		// move them together
		notifyObservers(offsetX, offsetY);
	}
}
