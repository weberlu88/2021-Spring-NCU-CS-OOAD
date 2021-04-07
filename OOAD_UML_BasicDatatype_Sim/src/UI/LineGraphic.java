package UI;

import java.awt.Color;
import java.awt.Graphics;

import Model.Line;
import ViewModel.ViewModel;

public class LineGraphic {

	/** create a GroupGraphic of Singleton **/ 
	private static LineGraphic instance = null;
	private static ViewModel vm = ViewModel.getInstance();
	
	private LineGraphic() {}
	
	public static LineGraphic getInstance() {
		if (instance == null) {
			instance = new LineGraphic();
		}
		return instance;
	}
	
	/** 繪製正在畫布上亂畫(x)連線(o)的Line **/
	public void drawLinkingLine(Graphics g) {
		
	}
	
	/** 繪製所有已連線的Line **/
	public void drawLines(Graphics g) {
		vm.getAllLines().forEach(line -> line.draw(g));
	}
}
