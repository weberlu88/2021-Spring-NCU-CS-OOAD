package UI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class Canvas extends JLayeredPane {

	/** Build canvas with layered panel **/
	public Canvas() {
		setPreferredSize(new Dimension(800, 600));
		setOpaque(true);
		setBackground(Color.blue);
	}

}
