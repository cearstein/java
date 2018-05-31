import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class MineFieldButton extends JButton {
	private boolean isPath = false;
	private boolean isMine = false;
	private int minesAround = 0;
	private Color curClr;
	private boolean isActive = false;
	private int xLoc;
	private int yLoc;
	private boolean isDiscovered = false;
	
	public MineFieldButton() {
		
	}
	
	public MineFieldButton(int size, Color clr, boolean bPath, boolean bMine, int y, int x) {
		int width = 800/size;
		isPath = bPath;
		isMine = bMine;
		xLoc = x;
		yLoc = y;
		
		//Create it
		super.setPreferredSize(new Dimension(width,width));
		curClr = clr;
		super.setBackground(clr);
		
		if(clr == Color.CYAN) {
			super.setText("0");
			isActive = true;
		}
		
	}
	
	public boolean getIsPath() {
		return(isPath);
	}
	
	public boolean getIsMine() {
		return(isMine);
	}
	
	public void setMinesAround(int numM) {
		minesAround = numM;
	}
	
	public int getMinesAround() {
		return(minesAround);
	}
	
	public void setCurClr(Color clr) {
		curClr = clr;
		super.setBackground(clr);
	}
	
	public Color getCurClr() {
		return(curClr);
	}
	
	public void makeActive() {
		isActive = true;
	}
	
	public void makeInActive() {
		isActive = false;
	}
	
	public boolean getIsActive() {
		return(isActive);
	}
	
	public int getXLoc() {
		return(xLoc);
	}
	
	public int getYLoc() {
		return(yLoc);
	}
	
	public void discover() {
		isDiscovered = true;
	}
	
	public boolean getIsDiscovered() {
		return(isDiscovered);
	}
}
