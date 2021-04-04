package UI.animation;

import java.awt.Point;

public interface IObserver {
	
	/** called by the subject to update the observer's location of any change 
	 * @return **/
	public void update(int offsetX, int offsetY);
}
