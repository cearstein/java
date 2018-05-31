import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator {
	
	public Calculator() {
		//Create base Frame
		Frame mainFrame = new Frame();
		
		//Create footer
		JPanel footer = new JPanel();
		footer.setPreferredSize(new Dimension(250,50));
		mainFrame.add(footer,BorderLayout.SOUTH);
		
		//Create Screen
		Screen display = new Screen();
		display.setBackground(Color.WHITE);		
		mainFrame.add(display, BorderLayout.NORTH);		
		
		//Create Function buttons
		FunctionPad function = new FunctionPad(display);
		mainFrame.add(function, BorderLayout.CENTER);
		
		//Create Number buttons
		NumPad numbers = new NumPad(display,function);
		mainFrame.add(numbers, BorderLayout.WEST);
		
		//Create Memory and Equals buttons
		MiscPad misc = new MiscPad(display, function);
		mainFrame.add(misc, BorderLayout.EAST);
		
		mainFrame.showIt();
	}
	
}

/* MAINFRAME */
class Frame extends JFrame {
	
	public Frame() {
		//Set up dimensions
		super("Calculator");
		super.setSize(new Dimension(250,300));
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void showIt() {
		super.setVisible(true);
	}
}

class Screen extends JPanel {
	JLabel display;
	
	boolean expontPress = false;
	
	double storeNum = 0.0;
	
	public boolean hasDecimal = false;
	public Screen() {
		//Set up Screen Panel Settings
		display = new JLabel("");
		super.setPreferredSize(new Dimension(250,50));
		super.setLayout(new BorderLayout());
		super.add(display, BorderLayout.EAST);
	}
	
	public void postDigit(String digit) {
		//Add digit to end of display
		
		display.setText(display.getText() + digit);
	}
	
	public void postResult(double value) {
		//Replace display with result
		display.setText(Double.toString(value));
	}
	
	public void resetScreen() {
		//Remove any displays on screen
		display.setText("");
	}
	
	public double getValue() {
		//Return current value of screen
		return(Double.parseDouble(display.getText()));
	}
	
	public boolean checkBlank() {
		//Check screen is blank
		if(display.getText() == "") {
			return(true);
		} else {
			return(false);
		}
	}
}

/* NUMPAD */
class NumPad extends JPanel {	
	private JButton[] buttons = new JButton[12];
	Screen display;
	FunctionPad functionPad;
	
	public NumPad(Screen screen, FunctionPad functions) {
		//Create buttons 1-9
		for(int i=1;i<10;i++) {
			buttons[i+2] = new JButton(Integer.toString(i));
			buttons[i+2].setPreferredSize(new Dimension(50,50));		
		}
		//Create buttons 0,'.', C
		buttons[0] = new JButton("0");
		buttons[0].setPreferredSize(new Dimension(50,50));
		buttons[0].addActionListener(new NumberButtonListener());
		
		buttons[1] = new JButton(".");
		buttons[1].setPreferredSize(new Dimension(50,50));
		buttons[1].addActionListener(new NumberButtonListener());
		
		buttons[2] = new JButton("C");
		buttons[2].setPreferredSize(new Dimension(50,50));
		buttons[2].addActionListener(new ClearButtonListener());
		
		//Add action listeners to 1-9
		for(int i=3;i<12;i++) {
			buttons[i].addActionListener(new NumberButtonListener());
		}
		
		//Set up panel settings
		super.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Add buttons
		int loc = 0;
		for(int y=3;y>=0;y--) {
			for(int x=0;x<3;x++) {
				gbc.gridx = x;
				gbc.gridy = y;
				super.add(buttons[loc],gbc);
				loc++;
			}
		}
		
		//Communicate with Screen
		display = screen;
		functionPad = functions;
		
	}
	
	//Create the Button Listeners
	
	private class ClearButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			display.resetScreen();
			functionPad.resetStorage();
			display.expontPress = false;
			display.hasDecimal = false;
			
		}
	}
	
	private class NumberButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton keyPressed = (JButton) event.getSource();
			if(keyPressed.getText() == "." && display.hasDecimal) {
				return;
			}
			if(functionPad.readyToGo) {
				display.resetScreen();
			}
			if(keyPressed.getText() == ".") {
				display.hasDecimal = true;
			}
			functionPad.noLongerReady();
			display.postDigit(keyPressed.getText());
		}
	}

	
}

/* FUNCTION PAD */
class FunctionPad extends JPanel {
	//Declare and Create buttons
	JButton division = new JButton("/");
	JButton multiplication = new JButton("*");
	JButton subtraction = new JButton("-");
	JButton addition = new JButton("+");	
	
	JButton[] buttons = {division,multiplication,subtraction,addition};
	Screen display;
	
	public boolean readyToGo = false;
	
	//Function and Number storage
	private String function = "_";

	
	public FunctionPad(Screen screen) {
			
		//Set Sizes and Color
		for(int i=0;i<4;i++) {
			buttons[i].setPreferredSize(new Dimension(50,50));
			buttons[i].setBackground(Color.CYAN);
			buttons[i].addActionListener(new FunctionButtonListener());
		}
		
		//Set up panel settings
		super.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Add buttons
		for(int y=0;y<4;y++) {
			gbc.gridy = y;
			super.add(buttons[y],gbc);
		}
		
		//Communicate with Screen
		display = screen;
	}
	
	public String getFunction() {
		return(function);
	}
	
	public void resetStorage() {
		function = "_";
		display.storeNum = 0.0;
	}

	public void setFunction(String str) {
		function = str;
	}
	
	public void noLongerReady() {
		readyToGo = false;
	}
	
	private class FunctionButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JButton keyPressed = (JButton) event.getSource();
			
			//Display answer in between operations
			if(function != "_" || display.expontPress) {
				if(function == "/") {
					display.postResult(display.storeNum/display.getValue());
					display.storeNum = display.storeNum/display.getValue();
					function = "_";
				} else if(function == "*") {
					display.postResult(display.storeNum*display.getValue());
					display.storeNum = display.storeNum*display.getValue();
					function = "_";
				} else if(function == "-") {
					display.postResult(display.storeNum-display.getValue());
					display.storeNum = display.storeNum-display.getValue();
					function = "_";
				} else if(function == "+"){
					display.postResult(display.storeNum+display.getValue());
					display.storeNum = display.storeNum+display.getValue();
					function = "_";
				} else {
					double ans = 1.0;
					
					for(int i=0;i<display.getValue();i++) {
						ans = ans *display.storeNum;
					}
					display.postResult(ans);
					display.expontPress = false;
					function = "_";
				}
				
			}
			
			if(!display.checkBlank()) {
				//Determine what key was pressed and set it to function
				function = keyPressed.getText();
				
				//Get current number and store it
				display.storeNum = display.getValue();
				
				//Clear screen
				readyToGo = true;
				display.hasDecimal = false;
				
				
			}
		}
	}
	
}

/* MISC PAD */
class MiscPad extends JPanel {
	//Declare and Create buttons
	JButton retrieveMemory = new JButton("R");
	JButton addMemory = new JButton("M");
	JButton exponent = new JButton("^");
	JButton equals = new JButton("=");	
		
	JButton[] buttons = {retrieveMemory,addMemory,exponent,equals};
	
	//Memory
	double memory = 0.0;
	boolean memoryExist = false;
	
	//Communication
	Screen display;
	FunctionPad functionPad;
	
	public MiscPad(Screen screen, FunctionPad functions) {
		//Set Sizes and Color
		for(int i=0;i<4;i++) {
			buttons[i].setPreferredSize(new Dimension(50,50));
			buttons[i].setBackground(Color.PINK);
		}
		
		//Action listener
		buttons[3].addActionListener(new EqualButtonListener());		
		buttons[2].addActionListener(new ExponentButtonListener());		
		buttons[1].addActionListener(new MemoryStoreButtonListener());
		buttons[0].addActionListener(new MemoryRetrieveButtonListener());
		
		//Set up panel settings
		super.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Add buttons
		for(int y=0;y<4;y++) {
			gbc.gridy = y;
			super.add(buttons[y],gbc);
		}
		
		//Communicate with Screen
		display = screen;
		functionPad = functions;
		
	}
	
	private class MemoryStoreButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!display.checkBlank()) {
				memory = display.getValue();
				memoryExist = true;
				
				functionPad.resetStorage();
				functionPad.readyToGo = true;
				display.hasDecimal = false;	
				display.expontPress = false;
				
				System.out.println(memory);
			}
		}
		
	}
	
	private class MemoryRetrieveButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(memoryExist) {
				display.postResult(memory);
				
				functionPad.resetStorage();
				functionPad.readyToGo = true;
				display.hasDecimal = false;	
				display.expontPress = false;
			}
		}
	}
	
	private class ExponentButtonListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			//Display answer in between operations
			if(functionPad.getFunction() != "_" || display.expontPress) {
				if(functionPad.getFunction() == "/") {
					display.postResult(display.storeNum/display.getValue());
					display.storeNum = display.storeNum/display.getValue();
					functionPad.setFunction("_");
				} else if(functionPad.getFunction() == "*") {
					display.postResult(display.storeNum*display.getValue());
					display.storeNum = display.storeNum*display.getValue();
					functionPad.setFunction("_");
				} else if(functionPad.getFunction() == "-") {
					display.postResult(display.storeNum-display.getValue());
					display.storeNum = display.storeNum-display.getValue();
					functionPad.setFunction("_");
				} else if(functionPad.getFunction() == "+"){
					display.postResult(display.storeNum+display.getValue());
					display.storeNum = display.storeNum+display.getValue();
					functionPad.setFunction("_");
				} else {
					double ans = 1.0;
					
					for(int i=0;i<display.getValue();i++) {
						ans = ans *display.storeNum;
					}
					display.postResult(ans);
					display.expontPress = false;
					functionPad.setFunction("_");
				}
				
			}
			
			if(!display.checkBlank()) {
				//Determine what key was pressed and set it to function
				display.expontPress = true;
				
				//Get current number and store it
				display.storeNum = display.getValue();
				
				//Clear screen
				functionPad.readyToGo = true;
				display.hasDecimal = false;
				
				
			}
		}
	}
	
	private class EqualButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(!display.checkBlank()) {
				//Retrieve required numbers
				double num1;
				double num2 = display.getValue();
				double answer = 1.0;
				
				//Exponent function
				if(display.expontPress) {
					num1 = display.storeNum;
					
					for(int i=0; i<num2;i++) {
						answer = answer*num1;
					}
					
				} else {
					//Retrieve required numbers
					String operation = functionPad.getFunction();
					num1 = display.storeNum;
					
					
					
					//Complete operation
					if(operation == "/") {				
						answer = num1/num2;
					} else if(operation == "*") {
						answer = num1*num2;
					} else if(operation == "-") {
						answer = num1-num2;
					} else {
						answer = num1+num2;
					}
					
				}
				display.postResult(answer);
				
				//Reset commands
				functionPad.resetStorage();
				functionPad.readyToGo = true;
				display.hasDecimal = false;	
				display.expontPress=false;
				
			}
		}
	}
}






