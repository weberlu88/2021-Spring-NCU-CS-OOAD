package abstractClassTest;

import javax.swing.JButton;
import javax.swing.JFrame;

interface BasicObject {
	public abstract void move();
}

@SuppressWarnings("serial")
class Usecase extends JButton implements BasicObject { // 太母湯了，這樣 BasicObject interface 的實作變成 copy-paste 兩份一樣的
	
	@Override
	public void move() {
		System.out.println("usecase move");
	}
}

@SuppressWarnings("serial")
class ClassObject extends JButton implements BasicObject { 
	
	@Override
	public void move() {
		System.out.println("class move");
	}
}

public class MainClass {

	public static void main(String[] args) {
		
		Usecase usecase = new Usecase();
		usecase.move();
		
		JFrame frame = new JFrame("JFrame");;
		frame.setBounds(0,0,400,300);
		frame.add((JButton)usecase);
		frame.setVisible(true);
	}

}
