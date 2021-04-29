package controller.animation;

import java.util.HashSet;
import java.util.Set;

public class MovementSubject {
	
	// use array list implementation for collection of observers
	private Set<IObserver> observers = new HashSet<IObserver>();

	public boolean addObserver(IObserver observer) {
		return observers.add(observer);
	}
	
	public boolean removeObserver(IObserver observer) {
		return observers.remove(observer);
	}
	
	public void removeAllObserver() {
		observers.clear();
	}
	
	private void notifyObservers(int offsetX, int offsetY) {
        observers.forEach(observer -> observer.update(offsetX, offsetY));
    }
	
	public void groupMove(int offsetX, int offsetY) { 
		// no need -> «Ê¸Ë notifyObservers() ¥Î
		// move them together
		notifyObservers(offsetX, offsetY);
	}
}
