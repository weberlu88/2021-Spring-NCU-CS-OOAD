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
	
	/** ø�s���b�e���W�õe(x)�s�u(o)��Line **/
	public void drawLinkingLine(Graphics g) {
		
	}
	
	/** ø�s�Ҧ��w�s�u��Line **/
	public void drawLines(Graphics g) {
		vm.getAllLines().forEach(line -> line.draw(g));
	}
}
