import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MineWalkerPanel extends JPanel {
	Frame frame;
	RandomWalk path;
	MineFieldPanel field;
	
	Key key = new Key();
	ScoreBoard score;
	GameButtons gameButtons;
	
	

	public MineWalkerPanel(int size, Frame frm) {
		score  = new ScoreBoard(size);
		frame = frm;
		//Create buttons
		gameButtons = new GameButtons(size, frm, score);
		//Create path
		path = new RandomWalk(gameButtons.getBoardSize());
		path.createWalk();
		//Create Field
		field = new MineFieldPanel(gameButtons.getBoardSize(), path, score, frame);
		//Give buttons access to field
		gameButtons.addMineField(field);
		
		//Layout and Size
		super.setPreferredSize(new Dimension(1100,835));
		super.setLayout(new BorderLayout());
		
		super.add(key,BorderLayout.WEST);
		super.add(score, BorderLayout.EAST);
		super.add(gameButtons, BorderLayout.SOUTH);
		super.add(field, BorderLayout.CENTER);
	}
}

class Key extends JPanel {
	JLabel title = new JLabel("Key", SwingConstants.CENTER);
	JLabel[] labels = new JLabel[8];
	String[] text = {"0 Nearby Mines","1 Nearby Mine","2 Nearby Mines","3 Nearby Mines","Exploded Mine","Start","Destination"};
	Color[] clrs = {Color.GREEN,Color.YELLOW,Color.ORANGE,Color.RED,Color.BLACK,Color.CYAN,Color.MAGENTA};
	
	public Key() {
		//Layout and Size
		super.setPreferredSize(new Dimension(150,800));
		super.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		super.add(title, gbc);
		
		//Create labels
		for(int i=0;i<7;i++) {
			labels[i] = new JLabel(text[i], SwingConstants.CENTER);
			labels[i].setPreferredSize(new Dimension(120,85));
			labels[i].setOpaque(true);
			labels[i].setBackground(clrs[i]);
			
			if(i<5) {
				gbc.gridy = i+1;
			} else {
				gbc.gridy = i+2;
			}
			if(i==4) {
				labels[i].setForeground(Color.WHITE);
			}
			super.add(labels[i],gbc);			
		}
		labels[7] = new JLabel();
		labels[7].setPreferredSize(new Dimension(80,85));
		gbc.gridy = 6;
		super.add(labels[7], gbc);
		
	}
}

class ScoreBoard extends JPanel {
	int lives;
	int score;
	
	JLabel title = new JLabel("Score Board", SwingConstants.CENTER);
	JLabel livesTXT = new JLabel("Lives: ");
	JLabel scoreTXT = new JLabel("Score: ");
	JLabel livesNUM;
	JLabel scoreNUM;
	JLabel buffer = new JLabel("");		
	
	public ScoreBoard(int size) {
		score = size*100;
		lives = size/5;
		scoreNUM = new JLabel(Integer.toString(score*lives));
		livesNUM = new JLabel(Integer.toString(lives));
		//Layout and Size
		super.setPreferredSize(new Dimension(150,800));
		
		//Add Labels
		title.setPreferredSize(new Dimension(150,350));
		super.add(title,BorderLayout.NORTH);
		super.add(livesTXT, BorderLayout.CENTER);
		super.add(livesNUM, BorderLayout.CENTER);
		buffer.setPreferredSize(new Dimension(150,5));
		super.add(buffer, BorderLayout.CENTER);
		super.add(scoreTXT, BorderLayout.CENTER);
		super.add(scoreNUM, BorderLayout.CENTER);
		
	}

	public void updateLives() {
		lives--;
		livesNUM.setText(Integer.toString(lives));
	}
	
	public void updateScore(int change) {
		score -= change;
		scoreNUM.setText(Integer.toString(score*lives));
	}

	public int getLives() {
		return(lives);
	}
	
	public int getScore() {
		return(score);
	}
}

class GameButtons extends JPanel {
	int size;
	JButton mines = new JButton("Show Mines");
	JButton path = new JButton("Show Path");
	JButton giveUp = new JButton("Give Up?");
	
	MineFieldPanel mfp;
	Frame frame;
	ScoreBoard score;
	
	
	public GameButtons(int sze, Frame frm, ScoreBoard scr) {
		size = sze;
		frame = frm;
		score = scr;
		//Layout and Size
		super.setPreferredSize(new Dimension(1100,35));
		
		//Add buttons
		mines.addActionListener(new MineListener());
		super.add(mines);
		
		path.addActionListener(new PathListener());
		super.add(path);
		
		giveUp.addActionListener(new GiveUpListener());
		super.add(giveUp);
		
	}
	
	public void addMineField(MineFieldPanel mineField) {
		mfp = mineField;
	}
	
	public int getBoardSize() {
		System.out.println("Size: " + size);
		return(size);
	}

	public void setSize(int sz) {
		size = sz;
	}
	
	private class PathListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Check if showing or hiding
			if(path.getText() == "Show Path") {
				path.setText("Hide Path");
				//Scan mineField for the path and change background color
				for(MineFieldButton[] buttonArray: mfp.mfButtons) {
					for(MineFieldButton button: buttonArray) {
						if(button.getIsPath() && button.getCurClr() != Color.CYAN && button.getCurClr() != Color.MAGENTA) {
							button.setBackground(Color.BLUE);
						}
					}
				}
				//Remove points for cheating
				score.updateScore(score.getScore());
			} else {
				path.setText("Show Path");
				for(MineFieldButton[] buttonArray: mfp.mfButtons) {
					for(MineFieldButton button: buttonArray) {
						if(button.getIsPath() && button.getText() != "X") {
							button.setBackground(button.getCurClr());
						}
					}
				}
			}
			
		}
	}
	
	private class MineListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Check if showing or hiding
			if(mines.getText() == "Show Mines") {
				mines.setText("Hide Mines");
				//Scan mineField for the mines and change background color
				for(MineFieldButton[] buttonArray: mfp.mfButtons) {
					for(MineFieldButton button: buttonArray) {
						if(button.getIsMine()) {
							button.setBackground(Color.BLACK);
						}
					}
				}
				//remove points for cheating
				score.updateScore(score.getScore());
			} else {
				mines.setText("Show Mines");
				for(MineFieldButton[] buttonArray: mfp.mfButtons) {
					for(MineFieldButton button: buttonArray) {
						if(button.getIsMine()) {
							button.setBackground(button.getCurClr());
						}
					}
				}
			}
			
		}
	}
	
	private class GiveUpListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Show Mines
			for(MineFieldButton[] buttonArray: mfp.mfButtons) {
				for(MineFieldButton button: buttonArray) {
					if(button.getIsMine()) {
						button.setBackground(Color.BLACK);
					}
				}
			}
			//Show Path
			for(MineFieldButton[] buttonArray: mfp.mfButtons) {
				for(MineFieldButton button: buttonArray) {
					if(button.getIsPath() && button.getCurClr() != Color.CYAN && button.getCurClr() != Color.MAGENTA) {
						button.setBackground(Color.BLUE);
					}
				}
			}
			JOptionPane.showMessageDialog(null, "Final Score: 0   (You don't any points for quitting)" + "\nLives: 0   (I\'m taking your lives away too","Wow, you gave up? How pitiful", JOptionPane.PLAIN_MESSAGE, null);
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






