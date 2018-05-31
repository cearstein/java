import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MineFieldPanel extends JPanel {
	public MineFieldButton[][] mfButtons;
	public ArrayList<Point> mineLoc;
	private ScoreBoard score;
	private Frame frame;
	
	public MineFieldPanel(int size, RandomWalk path, ScoreBoard scr, Frame frm) {
		boolean onPath = false;
		boolean isMine = false;
		score = scr;
		frame = frm;
		
		//Create array of buttons and path
		mfButtons = new MineFieldButton[size][size];
		
		//Layout and Size
		super.setPreferredSize(new Dimension(800,800));
		super.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		
		//Create mines
		Random rand = new Random();
		int numOfMines = ((size*size)-(2*size))/4 ;
		mineLoc = new ArrayList<Point>();
		do {
			onPath = false;
			//Create point with random (X,Y)
			Point tempPoint = new Point(rand.nextInt(size),rand.nextInt(size));
			//Check to see if it is on the Path
			for(Point p:path.getPath()) {
				if(tempPoint.getX() == p.getX() && tempPoint.getY() == p.getY()) {
					onPath = true;
				}
			}
			
			//If not on path, add to mine list
			if(!onPath) {
				mineLoc.add(tempPoint);
			}
			
		}while(mineLoc.size() < numOfMines);
		
		//Add buttons to panel
		for(int r=0;r<size;r++) {
			gbc.gridy = r;
			for(int c=0;c<size;c++) {
				onPath = false;
				isMine = false;
				gbc.gridx = c;
				
				//See if it is the start or end
				if(r==(size-1) && c==0) {
					mfButtons[r][c] = new MineFieldButton(size,Color.CYAN,true,false,r,c);
				} else if(r==0 && c==(size-1)) {
					mfButtons[r][c] = new MineFieldButton(size,Color.MAGENTA,true,false,r,c);
				} else {
					//Now to check if the button is on the path
					for(Point p:path.getPath()) {
						if(c == p.getX() && r == p.getY()) {
							onPath = true;
						}
					}
					//Check if button is a mine
					for(Point bp: mineLoc) {
						if(c == bp.getX() && r== bp.getY()) {
							isMine = true;
						}
					}
					mfButtons[r][c] = new MineFieldButton(size,Color.WHITE,onPath,isMine,r,c);
				}
				
				mfButtons[r][c].setBorder(new LineBorder(Color.BLACK));
				mfButtons[r][c].addActionListener(new MFButtonListener());
				super.add(mfButtons[r][c],gbc);
			}
		}
		
		//Add MinesAround value
		for(int r=0;r<mfButtons.length;r++) {
			for(int c=0;c<mfButtons[0].length;c++) {
				if(!mfButtons[r][c].getIsMine()) {
					int count = 0;
					
					//Check above
					if(r-1>=0) {
						if(mfButtons[r-1][c].getIsMine()) {
							count++;
						}
					}
					//Check below
					if(r+1<size) {
						if(mfButtons[r+1][c].getIsMine()) {
							count++;
						}
					}
					//Check left
					if(c-1>=0) {
						if(mfButtons[r][c-1].getIsMine()) {
							count++;
						}
					}
					//CheckRight
					if(c+1<size) {
						if(mfButtons[r][c+1].getIsMine()) {
							count++;
						}
					}
					
					
					mfButtons[r][c].setMinesAround(count);
				}
			}
		}
		
		
	}
	
	private class MFButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			MineFieldButton keyPressed = (MineFieldButton) event.getSource();
			if(!keyPressed.getIsMine() && keyPressed.getIsActive()) {
				if(keyPressed.getCurClr() == Color.MAGENTA) {
					//ShowMines
					for(MineFieldButton[] buttonArray: mfButtons) {
						for(MineFieldButton button: buttonArray) {
							if(button.getIsMine()) {
								button.setBackground(Color.BLACK);
							}
						}
					}
					JOptionPane.showMessageDialog(null, "Final Score: " + score.getScore()*score.getLives() + "\nLives: " + score.getLives(), "You Win!", JOptionPane.PLAIN_MESSAGE, null);
					//Play again?
					int reply = JOptionPane.showConfirmDialog(null, "Do you want to play again?","TEST",JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_NO_OPTION) {
						Frame mnFrame = new Frame();
						mnFrame.game();
						frame.dispose();
					} else {
						frame.dispose();
					}
					
				} else {
					int mines = keyPressed.getMinesAround();
					//Update score based on current risk
					MineFieldButton oldButton = new MineFieldButton();
					int scoreUp = 5;
					//Find button that was active 
					for(int r=0;r<mfButtons.length;r++) {
						for(int c=0;c<mfButtons.length;c++) {
							if(mfButtons[r][c].getText() == "0") {
								oldButton = mfButtons[r][c];
							}
						}
					}
					if(!keyPressed.getIsDiscovered()) {
						if(oldButton.getCurClr() == Color.YELLOW) {
							scoreUp = 4;
						}
						if(oldButton.getCurClr() == Color.ORANGE) {
							scoreUp = 2;
						}
					}
					
					score.updateScore(scoreUp);
					
					keyPressed.discover();
					//Change color
					if(mines == 0) {
						keyPressed.setCurClr(Color.GREEN);
					} else if(mines == 1) {
						keyPressed.setCurClr(Color.YELLOW);
					} else if(mines == 2) {
						keyPressed.setCurClr(Color.ORANGE);
					} else if(mines == 3) {
						keyPressed.setCurClr(Color.RED);
					}
					
					//Set other mines to false
					for(int r=0;r<mfButtons.length;r++) {
						for(int c=0;c<mfButtons.length;c++) {
							mfButtons[r][c].makeInActive();
							mfButtons[r][c].setText("");
							mfButtons[r][c].setBorder(new LineBorder(Color.BLACK));
						}
					}
					keyPressed.setBorder(new LineBorder(Color.WHITE));
					keyPressed.setText("0");
					
					//Set adjacent mines to active
					int x = keyPressed.getXLoc();
					int y = keyPressed.getYLoc();
					
					//Check above
					if(y-1>=0) {
						mfButtons[y-1][x].makeActive();
					}
					//Check below
					if(y+1<mfButtons.length) {
						mfButtons[y+1][x].makeActive();
					}
					//Check left
					if(x-1>=0) {
						mfButtons[y][x-1].makeActive();
					}
					//CheckRight
					if(x+1<mfButtons.length) {
						mfButtons[y][x+1].makeActive();
					}
				}
			} else if(keyPressed.getIsMine() && keyPressed.getIsActive()) {
				//IF you clicked a mine
				if(!keyPressed.getIsDiscovered()) {
					score.updateLives();
					score.updateScore(100);
				}
				keyPressed.discover();
				keyPressed.setCurClr(Color.BLACK);
				
				if(score.getLives() == 0) {
					//Show Path
					for(MineFieldButton[] buttonArray: mfButtons) {
						for(MineFieldButton button: buttonArray) {
							if(button.getIsPath() && button.getCurClr() != Color.CYAN && button.getCurClr() != Color.MAGENTA) {
								button.setBackground(Color.BLUE);
							}
						}
					}
					//ShowMines
					for(MineFieldButton[] buttonArray: mfButtons) {
						for(MineFieldButton button: buttonArray) {
							if(button.getIsMine()) {
								button.setBackground(Color.BLACK);
							}
						}
					}
					JOptionPane.showMessageDialog(null, "YOU LOSE!","Wow, you lost?", JOptionPane.PLAIN_MESSAGE, null);
					//Play again?
					int reply = JOptionPane.showConfirmDialog(null, "Do you want to play again?","TEST",JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_NO_OPTION) {
						Frame mnFrame = new Frame();
						mnFrame.game();
						frame.dispose();
					} else {
						frame.dispose();
					}
				}
			}
		}
	}
	

}
