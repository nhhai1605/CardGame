package view.gui;

import javax.swing.*;
import model.interfaces.GameEngine;
import view.model.ViewModel;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class GameFrame extends JFrame
{
	private GameToolBar gameToolBar;
	private GameStatusBar gameStatusBar;
	private CardPanel cardPanel;
	private SummaryPanel summaryPanel;
	private GameEngine gameEngine;
	private ViewModel viewModel;
	private int INITIAL_FRAME_WIDTH = 1500;
	private int INITIAL_FRAME_HEIGHT = 750;
	private int MINIMUML_FRAME_WIDTH = 800;
	private int MINIMUM_FRAME_HEIGHT = (int) ((MINIMUML_FRAME_WIDTH - 6*20)/5*1.5) + 300;

	public GameFrame(GameEngine gameEngine)
	{
		super("Card Game");
		this.gameEngine = gameEngine;
		viewModel = new ViewModel(this);
		summaryPanel = new SummaryPanel(this);
		gameToolBar = new GameToolBar(this);
		gameStatusBar = new GameStatusBar(this);
		cardPanel = new CardPanel(this);
		setSize(new Dimension(INITIAL_FRAME_WIDTH, INITIAL_FRAME_HEIGHT));
		
		BoxLayout boxLayout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS); 
		setLayout(boxLayout);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.add(gameToolBar);
		gameToolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, gameToolBar.getMinimumSize().height));
		
		mainPanel.add(summaryPanel);
		mainPanel.add(cardPanel);
	
		mainPanel.add(gameStatusBar);
		gameStatusBar.setBorder(BorderFactory.createLineBorder(Color.black));	
		gameStatusBar.setPreferredSize(new Dimension(Integer.MAX_VALUE,25));
		gameStatusBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
		gameStatusBar.setMinimumSize(new Dimension(Integer.MAX_VALUE, 25));



		getContentPane().addComponentListener( new ComponentAdapter()
        {
            public void componentResized(ComponentEvent evt)
            {
            	int panelWidth = getSize().width;
            	int panelHeight = getSize().height;
            	cardPanel.repositionCard(panelWidth, panelHeight);
            	MINIMUM_FRAME_HEIGHT = (int) ((panelWidth - 6*20)/5*1.5) + 300;
        		setMinimumSize(new Dimension(MINIMUML_FRAME_WIDTH, MINIMUM_FRAME_HEIGHT));
            }
        });
		add(mainPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		JLabel tutorial = new JLabel("<html>Press the menu to ADD/REMOVE player<br/><br/>"
				+ "You can only DEAL card when already BET<br/><br/>"
				+ "When all players have been dealt, HOUSE will be dealt automatically<br/><br/>"
				+ "The BUST CARD will have GRAY color<br/><br/>"
				+ "Have fun!</html>");
		JOptionPane.showMessageDialog(this, tutorial, "Tutorial", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public CardPanel getCardPanel()
	{
		return this.cardPanel;
	}
	
	public GameEngine getGameEngine()
	{
		return this.gameEngine;
	}
	
	public GameToolBar getGameToolBar()
	{
		return this.gameToolBar;
	}
	
	public SummaryPanel getSummaryPanel()
	{
		return this.summaryPanel;
	}
	
	public ViewModel getViewModel()
	{
		return this.viewModel;
	}
	
	public GameStatusBar getGameStatusBar()
	{
		return this.gameStatusBar;
	}
	
}
