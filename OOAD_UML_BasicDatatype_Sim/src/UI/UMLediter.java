package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class UMLediter extends JFrame {
	
	private ToolBar toolbar;
	private Canvas canvas;
	private MenuBar menubar;
	
	public UMLediter() {
		super("UI still building..."); // frame title
		setLayout(new BorderLayout());
		
		// register components, move these section to a new class then.
		addComponentsToPane(this.getContentPane());
	}

	private void addComponentsToPane(Container pane) {

		menubar = new MenuBar();
		pane.add(menubar, BorderLayout.NORTH);
		
		//Build buttons
		JPanel btnPane = new JPanel();
		btnPane.setLayout(new BoxLayout(btnPane, BoxLayout.PAGE_AXIS));
		JButton[] btnList = new JButton[6];
		
		for (int i = 0; i < btnList.length; i++) {
			btnList[i] = new JButton("Button"+ i);
			btnList[i].setPreferredSize(new Dimension(80, 100));
			btnPane.add(btnList[i]);
		}
		btnPane.setOpaque(true);
		btnPane.setBackground(Color.green);
		pane.add(btnPane, BorderLayout.WEST);
		
		//Build layered panel
		canvas = new Canvas();
		pane.add(canvas, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UMLediter mainWindow = new UMLediter();
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setSize(1000, 600);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}

}
