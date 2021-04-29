package view;

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
		super("UML Editor (version 2)"); // frame title
		setLayout(new BorderLayout());
		
		// register components, move these section to a new class then.
		addComponentsToPane(this.getContentPane());
	}

	private void addComponentsToPane(Container pane) {

		// build menu bar
		menubar = new MenuBar();
		pane.add(menubar, BorderLayout.NORTH);
		
		// build tool bar
		toolbar = new ToolBar();
		pane.add(toolbar, BorderLayout.WEST);
		
		// build layered panel as canvas
		canvas = Canvas.getInstance();
		pane.add(canvas, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		// start point of main program
		UMLediter mainWindow = new UMLediter();
		mainWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainWindow.setSize(1000, 600);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setVisible(true);
	}

}
