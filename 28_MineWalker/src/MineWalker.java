import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MineWalker {

	public static void main(String[] args) {
		//Create base frame
		Frame mainFrame = new Frame();
		mainFrame.game();
		
		
		
		
		

	}
	
	public static Frame game() {
		Object[] sizes = {5,10,15,20,25,30,40,50};
		Object modeSelection = JOptionPane.showInputDialog(null, "Select a Board size", "Welcom to MineWalker", JOptionPane.PLAIN_MESSAGE, null, sizes, sizes[0]);		
		
		//Create base frame
		Frame mainFrame = new Frame();
		
		//Create overall Panel
		MineWalkerPanel mwPanel = new MineWalkerPanel((int) modeSelection, mainFrame);
		mainFrame.add(mwPanel);
		
		
		mainFrame.showIt();
		return(mainFrame);
	}

}

class Frame extends JFrame {
	
	public Frame() {
		//Set up dimensions
		super("Mine Walker");
		super.setSize(new Dimension(1100,835));
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void showIt() {
		super.setVisible(true);
	}
	
	public void game() {
		Object[] sizes = {5,10,15,20,25,30,40,50};
		Object modeSelection = JOptionPane.showInputDialog(null, "Select a Board size", "Welcom to MineWalker", JOptionPane.PLAIN_MESSAGE, null, sizes, sizes[0]);		
		
		//Create overall Panel
		MineWalkerPanel mwPanel = new MineWalkerPanel((int) modeSelection, this);
		this.add(mwPanel);
		
		
		this.showIt();
		
	}
}