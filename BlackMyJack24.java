// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
//
// Luca Bernardi
//
// December 14th 2016
//
// This game is an extremely well-made black jack game where the user plays against the dealer.
//
// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// The "BlackMyJack2" class.
public class BlackMyJack24
{
    // declaring static panels
    static JPanel cardPanel = new JPanel (); //(new GridLayout (1, 4, 0, 10));
    static JPanel cardPanel1 = new JPanel (new GridLayout (1, 4, 0, 10));
    static JPanel cardPanelFull = new JPanel (new BorderLayout ());
    static JPanel stayPanel = new JPanel (new GridLayout (4, 1, 0, 10));
    static JPanel betPanel = new JPanel (new GridLayout (8, 1, 0, 10));
    static JPanel charPanel = new JPanel (new GridLayout (1, 3, 5, 5));
    static JPanel gamePanel = new JPanel (new BorderLayout ());
    static JPanel titlePanel = new JPanel (new BorderLayout ());
    static JPanel optionPanel = new JPanel ();
    static JPanel titlePanel1 = new JPanel (new BorderLayout ());
    static JPanel titlePanel2 = new JPanel (new BorderLayout ());

    // declaring static title page
    static ImageIcon casinoBack1 = new ImageIcon ("CASINO BACKGROUND GOOD.jpg");
    static JLabel casinoBack = new JLabel (casinoBack1);
    static JLabel title = new JLabel ("Black My Jack 2.0!");
    static Font titleFont = new Font ("Trebuchet MS", Font.BOLD, 45);

    // declaring static home buttons
    static JButton playButton = new JButton ("Play");
    static JButton exitButton = new JButton ("Exit");
    static Font optionFont = new Font ("Trebuchet MS", Font.BOLD, 35);

    // declaring static menu
    static ImageIcon menuBack1 = new ImageIcon ("Casino Back.jpg");
    static JLabel menuBack = new JLabel (menuBack1);

    // declaring static frames
    static JFrame gameFrame = new JFrame (" Gnarly Bernardi's Black My Jack 2.0!");
    static JFrame charFrame = new JFrame (" Gnarly Bernardi's Black My Jack 2.0!");
    static JFrame menuScreen = new JFrame (" Gnarly Bernardi's Black My Jack 2.0!");

    // character selection title
    static JLabel charSelec = new JLabel ("Please Select Your Character");

    // declaring static character selections
    static ImageIcon casinoBackLuca = new ImageIcon ("Luca Background.jpg");
    static ImageIcon casinoBackNick = new ImageIcon ("Nick Background.jpg");
    static ImageIcon casinoBackChris = new ImageIcon ("Chris Background.jpg");

    static JLabel lucaBack = new JLabel (casinoBackLuca);
    static JLabel nickBack = new JLabel (casinoBackNick);
    static JLabel chrisBack = new JLabel (casinoBackChris);

    static ImageIcon charLuca = new ImageIcon ("Luca Body Trans.png");
    static ImageIcon charNick = new ImageIcon ("Nick Body Trans.png");
    static ImageIcon charChris = new ImageIcon ("Chris Body Trans.png");

    static JButton lucaBtn = new JButton (charLuca);
    static JButton nickBtn = new JButton (charNick);
    static JButton chrisBtn = new JButton (charChris);

    static int characterSelected;

    // static game components
    static Font betFont = new Font ("Trebuchet MS", Font.BOLD, 30);
    static JLabel betLbl = new JLabel ("Bets");
    static JButton bet5 = new JButton ("5");
    static JButton bet10 = new JButton ("10");
    static JButton bet25 = new JButton ("25");
    static JButton bet50 = new JButton ("50");
    static JButton bet100 = new JButton ("100");
    static JButton hitBtn = new JButton (" Hit ");
    static JButton stayBtn = new JButton ("Stand");

    static int cardTot;
    static JLabel cardLbl = new JLabel ("Your Card Total: " + cardTot + " | ");
    static int betTot = 0;
    static JLabel betLbl1 = new JLabel ("Your Bet Total: " + betTot + " | ");
    static int chipTot = 500;
    static JLabel chipLbl = new JLabel ("Total Chips: " + chipTot + " | ");
    static int dealTot;
    static JLabel dealLbl = new JLabel ("Dealer's Card: " + dealTot + "  ");

    static int round = 1;

    // static button handler (onClick)
    static ButtonHandler onClick = new ButtonHandler (); // button handler class

    private static void guiApp ()
    {
	// option panel layout
	optionPanel.setLayout (new GridLayout (1, 4));

	// create a frame
	menuScreen.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // exits prgram when hitting close

	// set fonts
	playButton.setFont (optionFont);
	exitButton.setFont (optionFont);

	// adding action listeners
	playButton.addActionListener (onClick);
	exitButton.addActionListener (onClick);

	// add picture to back ground
	titlePanel1.add (menuBack);

	// setting backgrounds of panels
	optionPanel.setBackground (Color.BLACK);
	titlePanel1.setBackground (Color.BLACK);

	// setting colours of buttons
	playButton.setBackground (Color.BLACK);
	playButton.setForeground (Color.WHITE);
	exitButton.setBackground (Color.BLACK);
	exitButton.setForeground (Color.WHITE);

	// adding buttons to panel
	optionPanel.add (playButton);
	optionPanel.add (exitButton);

	Container contentPane = menuScreen.getContentPane ();

	// adding panel to content pane
	contentPane.add (optionPanel, BorderLayout.SOUTH);
	contentPane.add (titlePanel1, BorderLayout.CENTER);

	// size of frame and ALWAYS set visible to true
	menuScreen.setSize (650, 550);
	menuScreen.setLocationRelativeTo (null);
	menuScreen.setVisible (true);

    }


    public static void gamePlay ()
    {
	// setting bet to 0
	betTot = 0;

	// add the cards totals to the frame
	refreshCards ();

	// check if bets are okay
	betcheckMethod ();

	// create gameFrame
	gameFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // exits prgram when hitting close

	// what character was selected
	// luca selected
	if (characterSelected == 1)
	{
	    gamePanel.add (lucaBack);
	}

	// nick selected
	if (characterSelected == 2)
	{
	    gamePanel.add (nickBack);
	}

	// chris selected
	if (characterSelected == 3)
	{
	    gamePanel.add (chrisBack);
	}

	// set hit buttons to false to begin
	hitBtn.setEnabled (false);
	stayBtn.setEnabled (false);

	// set background black
	gamePanel.setBackground (Color.BLACK);

	// set layout of the panel
	cardPanel.setLayout (new BoxLayout (cardPanel, BoxLayout.LINE_AXIS));

	// create (useless) blanks for spacing
	JLabel blank = new JLabel (" ");
	JLabel blank1 = new JLabel (" ");
	JLabel blank2 = new JLabel (" ");
	JLabel blank3 = new JLabel (" ");

	// adjusting the title
	title.setText ("Black My Jack 2.0!");
	title.setFont (titleFont);
	title.setHorizontalAlignment (title.CENTER);
	title.setForeground (Color.WHITE);

	// adjusting good copy of title
	titlePanel2.add (title);
	titlePanel2.setBackground (Color.BLACK);

	// setting fonts of betting column
	betLbl.setFont (betFont);
	betLbl.setHorizontalAlignment (betLbl.CENTER);
	bet5.setFont (betFont);
	bet10.setFont (betFont);
	bet25.setFont (betFont);
	bet50.setFont (betFont);
	bet100.setFont (betFont);

	// adding action listeners and adjusting looks of buttons
	betLbl.setForeground (Color.WHITE);
	bet5.setBackground (Color.RED);
	bet5.addActionListener (onClick);
	bet10.setBackground (new Color (255, 130, 1));
	bet10.addActionListener (onClick);
	bet25.setBackground (Color.YELLOW);
	bet25.addActionListener (onClick);
	bet50.setBackground (Color.GREEN);
	bet50.addActionListener (onClick);
	bet100.setBackground (Color.BLUE);
	bet100.addActionListener (onClick);

	// adding bets to the panel
	betPanel.setBackground (Color.BLACK);
	betPanel.add (blank);
	betPanel.add (betLbl);
	betPanel.add (bet5);
	betPanel.add (bet10);
	betPanel.add (bet25);
	betPanel.add (bet50);
	betPanel.add (bet100);
	betPanel.add (blank1);

	// adding action listeners and adjusting looks of buttons
	hitBtn.setBackground (Color.CYAN);
	hitBtn.addActionListener (onClick);
	stayBtn.setBackground (Color.CYAN);
	stayBtn.addActionListener (onClick);

	// setting font of hit buttons
	hitBtn.setFont (betFont);
	stayBtn.setFont (betFont);

	// adding hit components to the panel
	stayPanel.setBackground (Color.BLACK);
	stayPanel.add (blank2);
	stayPanel.add (hitBtn);
	stayPanel.add (stayBtn);
	stayPanel.add (blank3);

	// setting the label colours and fonts
	cardLbl.setFont (betFont);
	cardLbl.setForeground (Color.WHITE);
	betLbl1.setFont (betFont);
	betLbl1.setForeground (Color.WHITE);
	chipLbl.setFont (betFont);
	chipLbl.setForeground (Color.WHITE);
	dealLbl.setFont (betFont);
	dealLbl.setForeground (Color.WHITE);

	// set panel background
	cardPanel.setBackground (Color.BLACK);

	Container contentPane = gameFrame.getContentPane ();

	// add the pannel to the frame
	contentPane.add (titlePanel2, BorderLayout.NORTH);
	contentPane.add (stayPanel, BorderLayout.EAST);
	contentPane.add (gamePanel, BorderLayout.CENTER);
	contentPane.add (betPanel, BorderLayout.WEST);

	// set frame size
	gameFrame.setSize (1300, 720);
	gameFrame.setLocationRelativeTo (null);

	gameFrame.setVisible (true);

	menuScreen.setVisible (false);
	charFrame.setVisible (false);

	// instruction pane pop up
	int open = JOptionPane.showConfirmDialog (null, "<HTML> Round " + round + " <BR>  <BR> 1) Please Bet To Your Left.  <BR>  <BR>2) Use The 'Hit' And 'Stand' Buttons To Raise 'YourCard Total' At The Bottom Of Your Screen <BR>  <BR>GOAL: Get A Higher Card Total Than The Dealer, WITHOUT Going Over 21. <BR>  <BR>Good Luck!</HTML>", "Welcome to Gnarly Bernardi's Black My Jack 2.0!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
    }


    public static void hitMethod ()
    {
	// when hit or stay is clicked
	bet5.setEnabled (false);
	bet10.setEnabled (false);
	bet25.setEnabled (false);
	bet50.setEnabled (false);
	bet100.setEnabled (false);
    }


    public static void userCardMethod ()
    {
	// if user total goes over 21
	if (cardTot > 21)
	{
	    int over21 = JOptionPane.showConfirmDialog (null, "   Your Total is Over 21.", "You Lose!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

	    round++;

	    refreshCards ();
	    int open = JOptionPane.showConfirmDialog (null, "<HTML> Round " + round + " <BR>  <BR> 1) Please Bet To Your Left.  <BR>  <BR>2) Use The 'Hit' And 'Stand' Buttons To Raise 'YourCard Total' At The Bottom Of Your Screen <BR>  <BR>GOAL: Get A Higher Card Total Than The Dealer, WITHOUT Going Over 21. <BR>  <BR>Good Luck!</HTML>", "Welcome to Gnarly Bernardi's Black My Jack 2.0!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	}
    }


    public static void standMethod ()
    {
	// dealer hits until total is over 16
	do
	{
	    dealTot = dealTot + (int) (Math.random () * 10 + 1);
	}
	while ((dealTot <= 16) && (dealTot < cardTot));


	// if user wins the totals
	if ((dealTot > 21) || ((cardTot > dealTot) && (cardTot < 22)) || (cardTot == dealTot))
	{
	    round++;
	    
	    int dealWin = JOptionPane.showConfirmDialog (null, "   You Win This Round. Your Card Total: " + cardTot + " | The Dealers' Total: " + dealTot, "You Win!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

	    chipTot = chipTot + (betTot * 2);

	    refreshCards ();

	    int open = JOptionPane.showConfirmDialog (null, "<HTML> Round " + round + " <BR>  <BR> 1) Please Bet To Your Left.  <BR>  <BR>2) Use The 'Hit' And 'Stand' Buttons To Raise 'YourCard Total' At The Bottom Of Your Screen <BR>  <BR>GOAL: Get A Higher Card Total Than The Dealer, WITHOUT Going Over 21. <BR>  <BR>Good Luck!</HTML>", "Welcome to Gnarly Bernardi's Black My Jack 2.0!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	}

	// if user loses the totals
	if (dealTot > cardTot)
	{
	    round++;

	    int dealWin = JOptionPane.showConfirmDialog (null, "   You Lose This Round. Your Card Total: " + cardTot + " | The Dealers' Total: " + dealTot, "You Lose!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

	    refreshCards ();

	    int open = JOptionPane.showConfirmDialog (null, "<HTML> Round " + round + " <BR>  <BR> 1) Please Bet To Your Left.  <BR>  <BR>2) Use The 'Hit' And 'Stand' Buttons To Raise 'YourCard Total' At The Bottom Of Your Screen <BR>  <BR>GOAL: Get A Higher Card Total Than The Dealer, WITHOUT Going Over 21. <BR>  <BR>Good Luck!</HTML>", "Welcome to Gnarly Bernardi's Black My Jack 2.0!", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
	}
    }



    public static void refreshCards ()
    {
	// remove everything from all panels and repaint them
	cardPanel.removeAll ();
	cardPanel.repaint ();
	cardPanelFull.removeAll ();
	cardPanelFull.repaint ();

	// set bet back to 0
	betTot = 0;

	// restart card totals
	cardTot = (int) (Math.random () * 11 + 1);

	// if first card is 11
	if (cardTot == 11)
	{
	    cardTot = cardTot + (int) (Math.random () * 10 + 1);
	}

	// if first card is not 11
	else if (cardTot != 11)
	{
	    cardTot = cardTot + (int) (Math.random () * 11 + 1);
	}
	// refresh card label
	cardLbl.setText ("Your Card Total: " + cardTot + " | ");

	// restart dealers card
	dealTot = (int) (Math.random () * 11 + 1);
	dealLbl.setText ("Dealer's Card Total: " + dealTot + "  ");

	// set buttons to true
	bet5.setEnabled (true);
	bet10.setEnabled (true);
	bet25.setEnabled (true);
	bet50.setEnabled (true);
	bet100.setEnabled (true);

	// set buttons to false
	hitBtn.setEnabled (false);
	stayBtn.setEnabled (false);

	// add refreshed totals to the panel
	cardPanel.add (chipLbl);
	cardPanel.add (betLbl1);
	cardPanel.add (cardLbl);
	cardPanel.add (dealLbl);
	cardPanel1.add (exitButton);

	// adjust alignment
	cardPanel.setAlignmentX (JComponent.CENTER_ALIGNMENT);
	cardPanelFull.setAlignmentX (JComponent.CENTER_ALIGNMENT);

	// add panels to one panel
	cardPanelFull.add (cardPanel, BorderLayout.NORTH);
	cardPanelFull.add (cardPanel1, BorderLayout.SOUTH);

	Container contentPane = gameFrame.getContentPane ();
	contentPane.add (cardPanelFull, BorderLayout.SOUTH);

	// check users chip total
	betcheckMethod ();
    }


    public static void betcheckMethod ()
    {
	// if chip tot is more than 100
	if (chipTot > 101)
	{
	    bet5.setEnabled (true);
	    bet10.setEnabled (true);
	    bet25.setEnabled (true);
	    bet50.setEnabled (true);
	    bet100.setEnabled (true);
	}

	// if chip tot less than 100
	if (chipTot <= 99)
	{
	    bet100.setEnabled (false);
	}

	// if chip tot less than 50
	if (chipTot <= 49)
	{
	    bet50.setEnabled (false);
	    bet100.setEnabled (false);
	}

	// if chip tot less than 25
	if (chipTot <= 24)
	{
	    bet25.setEnabled (false);
	    bet50.setEnabled (false);
	    bet100.setEnabled (false);
	}

	// if chip tot less than 10
	if (chipTot <= 9)
	{
	    bet10.setEnabled (false);
	    bet25.setEnabled (false);
	    bet50.setEnabled (false);
	    bet100.setEnabled (false);
	}

	// if chip tot less than 5
	if (chipTot <= 0)
	{
	    bet5.setEnabled (false);
	    bet10.setEnabled (false);
	    bet25.setEnabled (false);
	    bet50.setEnabled (false);
	    bet100.setEnabled (false);
	}
    }


    public static void charSelection ()
    {
	charFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); // exits prgram when hitting close

	// set the background of the character selections
	charSelec.setBackground (Color.BLACK);
	charSelec.setForeground (Color.WHITE);
	charSelec.setFont (titleFont);
	charSelec.setHorizontalAlignment (charSelec.CENTER);
	charSelec.setOpaque (true);

	// set colours and add action listeners to character buttons
	lucaBtn.setBackground (Color.BLACK);
	lucaBtn.addActionListener (onClick);
	nickBtn.setBackground (Color.BLACK);
	nickBtn.addActionListener (onClick);
	chrisBtn.setBackground (Color.BLACK);
	chrisBtn.addActionListener (onClick);

	// add character selections to panel
	titlePanel.add (charSelec);

	// add buttons to panel
	charPanel.add (lucaBtn);
	charPanel.add (nickBtn);
	charPanel.add (chrisBtn);

	// set colour of panel
	charPanel.setBackground (Color.BLACK);

	Container contentPane = charFrame.getContentPane ();

	// add character selection to content pane
	contentPane.add (charPanel, BorderLayout.CENTER);
	contentPane.add (titlePanel, BorderLayout.NORTH);

	// set frame
	charFrame.setSize (800, 600);
	charFrame.setLocationRelativeTo (null);
	charFrame.setVisible (true);
	menuScreen.setVisible (false);
    }


    private static class ButtonHandler implements ActionListener
    {
	public void actionPerformed (ActionEvent e)
	{
	    // if play button selected
	    if (e.getSource () == playButton)
	    {
		charSelection ();
	    }

	    // if exit button selected
	    if (e.getSource () == exitButton)
	    {
		System.exit (0);
	    }

	    // if luca character is selected
	    if (e.getSource () == lucaBtn)
	    {
		characterSelected = 1;
		gamePlay ();
	    }
	    
	    // if nick character is selected
	    else if (e.getSource () == nickBtn)
	    {
		characterSelected = 2;
		gamePlay ();
	    }
	    
	    // if chris character is selected
	    else if (e.getSource () == chrisBtn)
	    {
		characterSelected = 3;
		gamePlay ();
	    }

	    // if bet 5 is selected
	    if (e.getSource () == bet5)
	    {
		betTot = betTot + 5;
		betLbl1.setText ("Your Bet Total: " + betTot + " | ");

		chipTot = chipTot - 5;
		chipLbl.setText ("Total Chip Total: " + chipTot + " | ");

		hitBtn.setEnabled (true);
		stayBtn.setEnabled (true);

		betcheckMethod ();
	    }

	    // if bet 10 is selected
	    if (e.getSource () == bet10)
	    {
		betTot = betTot + 10;
		betLbl1.setText ("Your Bet Total: " + betTot + " | ");

		chipTot = chipTot - 10;
		chipLbl.setText ("  Total Chip Total: " + chipTot + " | ");

		hitBtn.setEnabled (true);
		stayBtn.setEnabled (true);

		betcheckMethod ();
	    }

	    // if bet 25 is selected
	    if (e.getSource () == bet25)
	    {
		betTot = betTot + 25;
		betLbl1.setText ("Your Bet Total: " + betTot + " | ");

		chipTot = chipTot - 25;
		chipLbl.setText ("  Total Chip Total: " + chipTot + " | ");

		hitBtn.setEnabled (true);
		stayBtn.setEnabled (true);

		betcheckMethod ();
	    }

	    // if bet 50 is selected
	    if (e.getSource () == bet50)
	    {
		betTot = betTot + 50;
		betLbl1.setText ("Your Bet Total: " + betTot + " | ");

		chipTot = chipTot - 50;
		chipLbl.setText ("  Total Chip Total: " + chipTot + " | ");

		hitBtn.setEnabled (true);
		stayBtn.setEnabled (true);

		betcheckMethod ();
	    }

	    // if bet 100 is selected
	    if (e.getSource () == bet100)
	    {
		betTot = betTot + 100;
		betLbl1.setText ("Your Bet Total: " + betTot + " | ");

		chipTot = chipTot - 100;
		chipLbl.setText ("  Total Chip Total: " + chipTot + " | ");

		hitBtn.setEnabled (true);
		stayBtn.setEnabled (true);

		betcheckMethod ();
	    }

	    // if hit button is selected
	    if (e.getSource () == hitBtn)
	    {
		cardTot = cardTot + (int) (Math.random () * 11 + 1);
		cardLbl.setText ("Your Card Total: " + cardTot + " | ");
		hitMethod ();
		userCardMethod ();
	    }

	    // if stay button is selected
	    else if (e.getSource () == stayBtn)
	    {
		hitMethod ();

		standMethod ();
	    }
	}
    }


    public static void main (String[] args)
    {
	//Schedule a job for the event-dispatching thread:
	//creating and showing this application's GUI.
	javax.swing.SwingUtilities.invokeLater (new Runnable ()
	{
	    public void run ()
	    {
		guiApp ();
	    }
	}


	);
    }
} // Layouts class


