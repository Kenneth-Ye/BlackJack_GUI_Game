//Name: Kenneth
//Date: May 21, 2022
//Program Name: GameBoard
//Purpose: Create a GUI that runs a blackjack game

package main;

//gui imports
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//blackjack import
import main.util.blackjack.Deck;
import main.util.blackjack.Blackjack;

//import account login methods from LoginScreen
import main.LoginScreen;


public class GameBoard 
{
  //constants
  private static Color backgroundGreenColor = new Color(39, 119, 20);
  private static Color backgroundBlueColor = new Color(34, 162, 218);
  private Double currentMoneyValue = 10000.00;
  private Double currentBetValue;
  private Double recentScore;
  private Double databaseHighScore;

  //objects
  private static JFrame mainJFrame = new JFrame("Blackjack");
  private Deck playingDeck = new Deck(); //create the playing deck
  private Deck playerDeck = new Deck(); //deck for players hand
  private Deck dealerDeck = new Deck(); //deck for dealers hand
  private SpriteSplitter spriteSplitter = new SpriteSplitter();
  private Blackjack blackjack = new Blackjack();
  private LoginScreen loginScreen = new LoginScreen();
  
  //GUI buttons labels and panels for the main game
  //buttons so all methods can see them
  private JButton hitButton = new JButton("Hit"); 
  private JButton stayButton = new JButton("Stay");
  private JButton doubleButton = new JButton("Double Down");
  private JButton cashOutButton = new JButton("Cash Out");
  private JButton exitButton = new JButton("Exit");
  
  //labels that need global scope
  private JLabel errorMessage = new JLabel("Enter a bet to start playing! (type number and press enter)");
  private JLabel currentMoney = new JLabel("Current Money: $10000");
  private JLabel currentBet = new JLabel("Current Bet: ");
  private JLabel personalBest = new JLabel("Personal Best: 0");
  private JLabel globalBest = new JLabel("Global Best: ");
  private JLabel playerCount = new JLabel("");
  private JLabel dealerCount = new JLabel("");
  
  
  //panels that need global scope
  private static JPanel dealerCardPanel = initializePanel(0, 0, 600, 250, 2, 7, backgroundGreenColor);
  private static JPanel playerCardPanel = initializePanel(0, 250, 600, 250, 2, 7, backgroundBlueColor);
  
  //GUI objects for the login screen
  //make a jpanel for the login text fields and buttons
  private static JPanel loginPanel = new JPanel();
  
  //text fields that need global scope
  private JNumberTextField betEntry = new JNumberTextField();
  
 //login text fields that need global scope 
  private static JTextField usernameField = new JTextField();
  private static JPasswordField passwordField = new JPasswordField();
  
  //declare user and pw vars
  private String username;
  private String password;
	
  
  //method to show/hide the global jpanels
  public static void toggleGlobalPanel(boolean onOff)
  {
	  dealerCardPanel.setVisible(onOff);
	  playerCardPanel.setVisible(onOff);
  }
  
  public static boolean emptyFields()
  {
	  if (usernameField.getText().equals("") || passwordField.getText().equals(""))
	  {
		  return false;
	  }
	  return true;
  }
  
  //method to create a login screen
  public void loginSplashScreen()
  {
	//set up the database
	loginScreen.rowIniatialize();
	LoginScreen.fileToDatabase();
	
	//setting up the JFrame
	mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	mainJFrame.setResizable(false);
	mainJFrame.setLocationRelativeTo(null);
	mainJFrame.setLayout(null);
	
	//hide global jpanels to make login screen pretty
	toggleGlobalPanel(false);
	
	
	//add image to jpanel
	BufferedImage blackjackTableImage = null; //new buffered image
	//try catch for io errors
	try {
		blackjackTableImage = ImageIO.read(new File("/Users/kennethye/Downloads/blackjacktable.jpg"));
	} catch (IOException e1) { 
		e1.printStackTrace();
	}
	//add picture as a label to loginScreen
	JLabel picLabel = new JLabel(new ImageIcon(blackjackTableImage));
	//set bounds of image
	picLabel.setBounds(0, 0, 1075, 700);
	
	//set up layoutmanger of loginpanel
	loginPanel.setLayout(null);
	
	
    //create login labels
	JLabel loginTitle = new JLabel("Culminating Blackjack Table!");
    JLabel usernameLabel = new JLabel("Username: ");
    JLabel passwordLabel = new JLabel("Password: ");
    JLabel notificationLabel = new JLabel("Enter information and press button to create account or login.");
    
	//create account and login buttons
	JButton createAccountButton = new JButton("Create Account");
	JButton loginButton = new JButton("Login");
	
	//configure size and location of buttons
	createAccountButton.setBounds(400, 400, 200, 75);
	loginButton.setBounds(400, 500, 200, 75);
	
	//configure size and location of the fields
	usernameField.setBounds(400, 175, 300, 50);
	passwordField.setBounds(400, 275, 300, 50);
	
	//configure size, color, and location of labels
	loginTitle.setBounds(250, 45, 1000, 200);
	loginTitle.setFont(new Font("SansSerif", 5, 40));
	loginTitle.setForeground(backgroundBlueColor);
	usernameLabel.setBounds(200, 100, 400, 200);
	usernameLabel.setFont(new Font("Serif", 2, 40));
	usernameLabel.setForeground(backgroundBlueColor);
	passwordLabel.setBounds(200, 200, 400, 200);
	passwordLabel.setFont(new Font("Serif", 2, 40));
	passwordLabel.setForeground(backgroundBlueColor);
	notificationLabel.setBounds(275, 550, 500, 100);
	notificationLabel.setFont(new Font("SansSerif", 5, 12));
	
	//add labels, buttons, and fields to loginpanel 
	loginPanel.add(loginTitle);
	loginPanel.add(usernameLabel);
	loginPanel.add(passwordLabel);
	loginPanel.add(usernameField);
	loginPanel.add(passwordField);
	loginPanel.add(createAccountButton);
	loginPanel.add(loginButton);
	loginPanel.add(notificationLabel);
	loginPanel.add(picLabel);

	//configure loginpanel 
    loginPanel.setBounds(0, 0, 1075, 700); //set the bounds of panel
	loginPanel.setBackground(backgroundGreenColor); //set color
	
	//add login panel to jframe
	mainJFrame.add(loginPanel);
	
    //configure mainJFrame
    mainJFrame.setSize(1075, 700);
    mainJFrame.setVisible(true); 
    
    //action listener for create account button
    createAccountButton.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
        	//set vars to the text entered
        	username = usernameField.getText();
        	password = passwordField.getText();
        	
        	//if username entered is a duplicate
        	if (loginScreen.checkForUsernameDuplicate(username)) 
        	{
        		notificationLabel.setText("Username Taken");
        	}
        	else //unique username
        	{
        		if (emptyFields())
        		{
            		//create the account
            		loginScreen.createAccount(username, password);
            		notificationLabel.setText("Account Created");
            		//update file database
            		loginScreen.updateFile();
            		
            		//change scene to the game
            		removeLoginScreen();
            		userInterface();
        		}
        		else
        		{
            		notificationLabel.setText("NO EMPTY FIELDS");
        		}
        	}
        }
      });
    
    //action listener for the login button
    loginButton.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
        	//set vars to text entered
        	username = usernameField.getText();
        	password = passwordField.getText();
        	
        	//if correct username and password
        	if (loginScreen.successfulLogin(username, password))
        	{
        		//notify user and change scene to the game
        		notificationLabel.setText("Logged In Successfully");
        		removeLoginScreen();
        		userInterface();
        	}
        	else
        	{
        		//not a successfull login notify user
        		notificationLabel.setText("Username or Password Wrong");
        	}
        }
      });
  }
  
  //method to strip the login screen off the jframe and prepare the jframe for gameboard
  public void removeLoginScreen()
  {
	  loginPanel.setVisible(false);
  }
  
  
  //the main gui and game runner
  public void userInterface()
  {
	//show the jpanels 
	toggleGlobalPanel(true);
	
    //setting up the JFrame
    mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainJFrame.setResizable(false);
    mainJFrame.setLocationRelativeTo(null);
    mainJFrame.setLayout(null);
    
    // set up all the diffrent jpanels for the gui
    JPanel valueCounter = initializePanel(600, 0, 100, 500, 2, 1, new Color(34, 218, 175));
    JPanel menuButtons = initializePanel(700, 0, 200, 500, 2, 2, backgroundGreenColor);
    JPanel betMenu = initializePanel(0, 500, 300, 100, 1, 2, backgroundGreenColor);
    JPanel messageMenu = initializePanel(300, 500, 600, 100, 1, 2, backgroundGreenColor);
    JPanel highScore = initializePanel(900, 0, 175, 600, 2, 1, backgroundGreenColor);
    JPanel errorMessagePanel = initializePanel(0, 600, 1075, 100, 1, 2, backgroundBlueColor);


    //add labels first for better exitButton placement 
    errorMessagePanel.add(currentBet);
    errorMessagePanel.add(errorMessage);
    messageMenu.add(currentMoney);
    highScore.add(personalBest);
    highScore.add(globalBest);
    
    //add labels to proper panel
    valueCounter.add(dealerCount);
    valueCounter.add(playerCount);

    //add buttons to menu panel
    menuButtons.add(hitButton);
    menuButtons.add(stayButton);
    menuButtons.add(doubleButton);
    menuButtons.add(cashOutButton);
    messageMenu.add(exitButton);

   
    
    //set up custom text field for number entries
    betEntry.setColumns(10);
    betEntry.setFormat(JNumberTextField.DECIMAL);
    betEntry.setMaxLength(5);
    betEntry.setAllowNegative(false);
    betEntry.setPrecision(2);


    //add custom text field to the proper jpanel with a label
    betMenu.add(new JLabel("       Enter Bet: "));
    betMenu.add(betEntry); 
    
    //turn off the buttons before a bet is entered 
    buttonSwitch(false);
    
    //set up high score panels
	personalBest.setText("Personal Best: " + Double.toString(loginScreen.highScoreGetter(username)));
	globalBest.setText("Global Best: " + Double.toString(loginScreen.globalHighScoreGetter()));
    
    //action listener for the exit button
    exitButton.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
        	//update file and exit
        	loginScreen.updateFile();
        	System.exit(0);
        }
      });
    
    //action listener for betEntry
    betEntry.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
        	//if bet entered is invalid
        	if ((Double.parseDouble(betEntry.getText()) > currentMoneyValue) || (Double.parseDouble(betEntry.getText()) < 0.01))
        	{
        		errorMessage.setText("Invalid Bet");
        	}
        	else //bet is valid
        	{
        		currentBetValue = Double.parseDouble(betEntry.getText()); //set current bet value to bet entered
        		currentBet.setText("Current Bet: " + betEntry.getText()); //display current bet
        		errorMessage.setText(""); //get rid of any lingering error messages on the gui
        		currentMoney.setText("Current Money: " + (currentMoneyValue - currentBetValue));
        	    playingDeck.shuffle(); //shuffle the playing deck
        		betEntry.setEnabled(false); //lock the bet in dont allow user to change bet
        		buttonSwitch(true); //turn buttons on

        	    //deal a hand to the player and dealer
        	    playerDeck.draw(playingDeck);
        	    dealerDeck.draw(playingDeck);
        	    playerDeck.draw(playingDeck);
        	    dealerDeck.draw(playingDeck);
        	    
        	    //create count layouts (value of hands)
        	    dealerCount.setText("Count : ???");
        	    playerCount.setText("Count : " + playerDeck.cardsValue());
        	    

        	    //put the player's hand into image form
        	    addHandToPanel(playerDeck, playerCardPanel);
        	    
        	    //dealers hand, 1 card hidden
        	    dealerCardPanel.add(spriteSplitter.faceDownCardJLabel()); //first card is facedown for the dealer
        	    addHandToPanel(dealerDeck, dealerCardPanel, true); //then only reveal the second car
        	}
        }
      });
    

    //action listener for the hit button
    hitButton.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
        	hitAction(); //hit method
        }
      });
    
    //action listener for double button
    doubleButton.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
        	//if the user has enough money to double their bet
        	if (2*currentBetValue <= currentMoneyValue)
        	{
        		currentBetValue = currentBetValue*2; //double their bet
        		currentMoney.setText("Current Money: " + (currentMoneyValue - currentBetValue)); //update current monet
        		hitAction(); //hit method
        	}
        	else // if player doesnt have enough money to double the bet
        	{
        		errorMessage.setText("Invalid: Not Enough Money To Double Down"); //notify user
        	}
        }
      });
    
    //stay button's action listener
	stayButton.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
        	 showDealerHand(); //show dealers hand
	         evaluateResult(); //determine the winner and deal money prizes
        }
      });    
    
	//action listener for cashOutButton
    cashOutButton.addActionListener(new ActionListener() { 
        @Override
        public void actionPerformed(ActionEvent e) {
        	//recent score is current money value at time of cashing out
        	recentScore = currentMoneyValue;
        	//get the account's high score in the database
        	databaseHighScore = loginScreen.highScoreGetter(username);
        	
        	//if  a new high score is set 
        	if (databaseHighScore < recentScore)
        	{
        		// set the high score to the new high score 
        		loginScreen.changeScore(recentScore, username); 
        		loginScreen.updateFile();
        		
        		//update personal best
        		personalBest.setText("Personal Best: " + Double.toString(loginScreen.highScoreGetter(username)));
        		
        		//update global max in case recentScore is a global high score
        		globalBest.setText("Global Best: " + Double.toString(loginScreen.globalHighScoreGetter()));
        		
        		//notify user of new high score
        		errorMessage.setText("NEW HIGH SCORE!!!");
        	}
        	else //no new high score
        	{
        		//tell user no high score
        		errorMessage.setText("No New High Score :(");
        	}
        	//set up gui for next game of blackjack
        	endRound(true);
        }
      });
    

    //start of game
    
    //create the deck 
    playingDeck.createFullDeck(); //populate the playing deck
    
    //configure mainJFrame
    mainJFrame.setSize(1075, 700);
    mainJFrame.setVisible(true); 
   }
  
 
  //initialize a jpanel with setbounds, color and a layoutmanager
  public static JPanel initializePanel(int posX, int posY, int width, int height, int gridDimensionX, int gridDimensionY, Color color)
  {
    JPanel panel = new JPanel(); //intialize a jpanel
    panel.setBackground(color); //change to desired color
    panel.setBounds(posX, posY, width, height); //set the bounds of panel
    panel.setLayout(new GridLayout(gridDimensionX, gridDimensionY)); //add gridlayout manager
    mainJFrame.add(panel); //add to the jframe
    return panel;
  }
  
  //take a deck and add all the cards of the deck as images to a target panel
  public void addHandToPanel(Deck deck, JPanel targetPanel)
  {
	ArrayList<String> arrayOfCards = deck.toArrayList(); //create arraylist with each element as a string representation of a card
	String card; //string represntation of card
	JLabel cardLabel; //Jlabel to be added to target panel
	for (int i=0; i < arrayOfCards.size(); i++) //loop through every card
	{
		card = arrayOfCards.get(i); //set card to string of the card we are looping through 
		cardLabel = spriteSplitter.returnCardJLabel(card); //get the card image and set as jlabel
		targetPanel.add(cardLabel); //add the jlabel to the target panel
	}
  } 
  
  //add a card that was most recently dealt onto the jpanel target
  //overload method
  public void addHandToPanel(Deck deck, JPanel targetPanel, boolean draw)
  {
	  ArrayList<String> arrayOfCards = deck.toArrayList(); //create arraylist with each element as a string representation of a card
	  JLabel cardLabel = spriteSplitter.returnCardJLabel(arrayOfCards.get(arrayOfCards.size()-1)); //Jlabel to be added to target panel
	  targetPanel.add(cardLabel); //add the card to target panel
  }
  
  //method to switch on or off the main buttons
  public void buttonSwitch(boolean onOff)
  {
	  //turn off or on the buttons
	  hitButton.setEnabled(onOff);
	  doubleButton.setEnabled(onOff);
	  stayButton.setEnabled(onOff);
	  cashOutButton.setEnabled(!onOff);
  }
  
  //method that makes the game hit 
  public void hitAction()
  {
	  playerDeck.draw(playingDeck); //deal a card to players hand
	  addHandToPanel(playerDeck, playerCardPanel, true); //display the card drawn
	  playerCount.setText("Count : " + playerDeck.cardsValue()); //update the count
	  currentBet.setText("Current Bet: " + Double.toString(currentBetValue)); //update bet
	  if (blackjack.hasBusted(playerDeck.cardsValue())) //if player busts
	  {
	  	currentMoneyValue -= currentBetValue; //take bet away
	  	currentBet.setText("Current Bet: "); //reset current Bet
	  	currentMoney.setText("Current Money: " + currentMoneyValue); //update current money
	  	betEntry.setEnabled(true); //reopen bets
	  	errorMessage.setText("Player Busted: Player Loses"); //notify player that they lost the round
	  	endRound(); //reset for  a new round of betting
	  	
	  	//if zero money start a new game
	  	zeroMoney();
	  }
  }
  
  //end the round and prepare gui for the next
  public void endRound()
  {
	//reset the dealer and plaryer card panels
  	playerCardPanel.removeAll();
  	dealerCardPanel.removeAll();
  	playerCardPanel.updateUI();
  	dealerCardPanel.updateUI();
  	
  	//reset current bet
  	currentBet.setText("Current Bet: ");
  	
  	//reset the counts
  	playerCount.setText("Count: ");
  	dealerCount.setText("Count: ");
  	
  	//move all the dealt card back into the main playing deck
    playerDeck.moveAllCardsToDeck(playingDeck); //transfer users hand back to playing deck
    dealerDeck.moveAllCardsToDeck(playingDeck); //transfer dealers hand back to playing deck 
    
    //turn off the buttons, only play after bet is entered
    buttonSwitch(false);
    
    //open betentry field
    betEntry.setEnabled(true);
  }
  
  //end the round and prepare gui for a new game of blackjack
  //overload endRound method
  public void endRound(boolean newGame)
  {
	//reset the dealer and plaryer card panels
  	playerCardPanel.removeAll();
  	dealerCardPanel.removeAll();
  	playerCardPanel.updateUI();
  	dealerCardPanel.updateUI();
  	
  	//reset current bet
  	currentBet.setText("Current Bet: ");
  	currentMoney.setText("Current Money: 10,000.00");
  	
  	//reset bet value and money value
  	currentBetValue = 0.00;
  	currentMoneyValue = 10000.00;
  	
  	//reset the counts
  	playerCount.setText("Count: ");
  	dealerCount.setText("Count: ");
  	
  	//move all the dealt card back into the main playing deck
    playerDeck.moveAllCardsToDeck(playingDeck); //transfer users hand back to playing deck
    dealerDeck.moveAllCardsToDeck(playingDeck); //transfer dealers hand back to playing deck 
    
    //turn off the buttons, only play after bet is entered
    buttonSwitch(false);
    
    //open betentry field
    betEntry.setEnabled(true);
  }
  
  //method to show the dealers whole hand
  public void showDealerHand()
  {
    buttonSwitch(false);
    
	//reveal the hole card by restting panel then adding dealers whole hand
	dealerCardPanel.removeAll();
	dealerCardPanel.updateUI();
	
	// dealer 17 mechanism (draw at below 16 and stand at 17)
	while ((dealerDeck.cardsValue() < 17)) //if dealer deck value is less than 17
	{   
		dealerDeck.draw(playingDeck); //deal dealer a card  
		addHandToPanel(dealerDeck, dealerCardPanel, true); //add that card to the panel
	}
  }
  
  //method to get the winner 
  public void evaluateResult()
  {
	  //reveal dealers hand value
	dealerCount.setText("Count : " + dealerDeck.cardsValue());
	
	//determine the winner
	
	//if player wins or dealer busts
	if ((blackjack.hasBusted(dealerDeck.cardsValue())) || (playerDeck.cardsValue() > dealerDeck.cardsValue()))  
	{
		//if player has a blackjack pay 3 to 2
		if (playerDeck.cardsValue() == 21)
		{
			currentMoneyValue += 1.5*currentBetValue;  
		}
		else // pay 1 to 1 if player wins normally
		{
			currentMoneyValue += currentBetValue; 
		}
		errorMessage.setText("Player Wins"); //notify the player that they won
		currentMoney.setText("Current Money: " + currentMoneyValue); //update current money
	}
	else if (dealerDeck.cardsValue() > playerDeck.cardsValue()) //if dealer wins
	{
		//subtract bet, notify user they lost and update current money
		errorMessage.setText("Dealer Wins");
		currentMoneyValue -= currentBetValue;
		currentMoney.setText("Current Money: " + currentMoneyValue);
		endRound();
		zeroMoney();
	}      	
	else if (dealerDeck.cardsValue() == playerDeck.cardsValue()) //if push occurs
	{
		currentMoney.setText("Current Money: " + currentMoneyValue); //update current money 
		errorMessage.setText("Dealer and Player Draw"); //notify user
	}
	//set up gui for next round of blackjack
	endRound();
  }
  
  //method to reset the gui for a new game if the user hits 0 dollars
  public void zeroMoney()
  {
	 //if user goes broke set s special message
	 if (currentMoneyValue == 0)
	 {
		 //if user goes broke, start a new game, and notify them
		 endRound(true);
		 errorMessage.setText("You have no more Money, New Game started");
	 }
  }
}
