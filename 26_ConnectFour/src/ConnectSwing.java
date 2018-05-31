import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ConnectSwing {
	
	public ConnectSwing(Win win) {
		
		//Create base frame
		Frame mainFrame = new Frame(); 
		
		//Create column buttons
		ColPad columnPad = new ColPad(win);
		mainFrame.add(columnPad, BorderLayout.NORTH);
		
		
		mainFrame.showIt();
	}
	
}

class Frame extends JFrame {
	
	public Frame() {
		//Set up dimensions
		super("Connect Four");
		super.setSize(new Dimension(1050,750));
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void showIt() {
		super.setVisible(true);
	}
}

class ColPad extends JPanel {
	Win tool;
	JButton[] buttons = new JButton[7];
	JLabel[][] labels = new JLabel[6][7];
	char player = 'X';
	
	boolean hasWon = false;
	
	private int colPressed = -1;
	
	public ColPad(Win win) {
		tool = win;
		
		//Layout and Size
		super.setPreferredSize(new Dimension(1050,750));
		super.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = 0;
		
		//Buttons
		for(int i=0;i<7;i++) {
			buttons[i] = new JButton(Integer.toString(i+1));
			buttons[i].setPreferredSize(new Dimension(150,150));
			buttons[i].addActionListener(new ColButtonListener());
			gbc.gridx = i;
			
			super.add(buttons[i], gbc);
		}
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		//Labels
		for(int r=0;r<6;r++) {
			for(int c=0;c<7;c++) {
				labels[r][c] = new JLabel();
				labels[r][c].setOpaque(true);
				labels[r][c].setPreferredSize(new Dimension(150,100));
				labels[r][c].setBackground(Color.GREEN);
				labels[r][c].setBorder(border);
				gbc.gridx = c;
				gbc.gridy = r+1;
				super.add(labels[r][c],gbc);
			}
		}
		
		
	}
	
	
	private class ColButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(!hasWon) {
				JButton keyPressed = (JButton) event.getSource();
				colPressed = Integer.parseInt(keyPressed.getText()) -1;
				
				if(tool.input.checkPossible(colPressed)) {
					tool.input.updateBoard(player);	
					updateColors(tool.input.board);
					
					if(tool.checkColWin() || tool.checkRowWin() || tool.checkDiagWin()) {
						for(int i=0;i<7;i++) {
							String winner;
							if(player == 'X') {
								winner = "RED";
							} else {
								winner = "YELLOW";
							}
							buttons[i].setText(winner + " WINS!");
							hasWon = true;
							
						}
					}
					
					
					
					//Change Player
					if(player == 'X') {
						player = 'O';
					} else {
						player = 'X';
					}
				}	
			}
			
		}
		
		private void updateColors(char[][] array) {
			for(int r=0;r<6;r++) {
				for(int c=0;c<7;c++) {
					if(array[r][c] == 'X') {
						labels[r][c].setBackground(Color.RED);
					} else if(array[r][c] == 'O') {
						labels[r][c].setBackground(Color.YELLOW);
					}
				}
			}
		}
	}
}






