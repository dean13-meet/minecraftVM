package minecraft;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Display extends JComponent {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	GUI hostProgram;
	JFrame hostFrame;
	//Not Supporting offsets.
	/*
	int xOffSet;
	int yOffSet;*/
	//Note: To support offsets, change all draw methods, and change in the constructor below
	// this.setBounds(0,0,w, h); to this.setBounds(xOffSet,yOffSet,w, h);
	int width;
	int height;

	public Display(int w, int h, JFrame f, GUI program) {
		//Not Supporting offsets.
		/*this.x = x;
		this.y = y;*/
		width = w;
		height = h;
		hostFrame = f;
		hostProgram = program;
		this.setBounds(0,0,width, height);

	}

	abstract protected void setPanel();

	protected abstract void paintComponent(Graphics g);
}
