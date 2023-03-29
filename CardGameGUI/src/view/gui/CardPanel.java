package view.gui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.model.PlayerState;



@SuppressWarnings("serial")
public class CardPanel extends JPanel
{
	private PlayerState playerState;
	private GameFrame gameFrame;

	private int PANEL_HEIGHT = 750;
	private int PANEL_WIDTH = 1500;

	private int CARD_X = 20;
	private int CARD_WIDTH = (int) ((PANEL_WIDTH - 6 * CARD_X) / 5);
	private int CARD_HEIGHT = (int) (CARD_WIDTH * 1.5);
	private int CARD_Y = (int) ((PANEL_HEIGHT - CARD_HEIGHT)/ 2);
	private int SIZE = CARD_WIDTH/4;
	private final int ARC = 40;

	
	public CardPanel(GameFrame gameFrame)
	{
		this.setBackground(Color.WHITE);
		this.gameFrame = gameFrame;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{	
		super.paintComponent(g);
		int count = 1;
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
		if(this.playerState != null)
		{
			for(PlayingCard card : playerState.getCardHand())
			{
				if(playerState.isBust() && count == playerState.getCardHand().size())
				{
					g.setColor(Color.LIGHT_GRAY);
				}
				else
				{
					g.setColor(Color.WHITE);
				}
				if(card.getSuit() == Suit.SPADES || card.getSuit() == Suit.CLUBS)
				{
					BufferedImage image = null;
					try
					{
						File file = new File("src" + File.separator + "img" + File.separator + card.getSuit().toString().toLowerCase() + ".png");
						image = ImageIO.read(file);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					g.fillRoundRect(CARD_X*count + CARD_WIDTH*(count-1), CARD_Y, CARD_WIDTH, CARD_HEIGHT, ARC, ARC);
					g.setColor(Color.BLACK);
					g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
					String cardChar = (card.getValue() == Value.EIGHT) ? cardChar = "8" : (card.getValue() == Value.NINE) ? cardChar = "9" : String.valueOf(card.getValue().toString().charAt(0));
					g.drawString(cardChar, CARD_X*count + CARD_WIDTH*(count-1) + 15, CARD_Y + 35);
					g.drawString(cardChar , CARD_X*count + CARD_WIDTH*(count-1) + CARD_WIDTH - 30, CARD_Y + CARD_HEIGHT - 15);
					g.drawImage(image, CARD_X*count + CARD_WIDTH*(count-1) - SIZE/2 + CARD_WIDTH/2, CARD_Y - SIZE/2 + CARD_HEIGHT/2, SIZE, SIZE , this);
				}
				else if(card.getSuit() == Suit.HEARTS || card.getSuit() == Suit.DIAMONDS)
				{
					BufferedImage image = null;
					try
					{						
						File file = new File("src" + File.separator + "img" + File.separator + card.getSuit().toString().toLowerCase() + ".png");
						image = ImageIO.read(file);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					g.fillRoundRect(CARD_X*count + CARD_WIDTH*(count-1), CARD_Y, CARD_WIDTH, CARD_HEIGHT, ARC, ARC);
					g.setColor(Color.RED);
					g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
					String cardChar = (card.getValue() == Value.EIGHT) ? cardChar = "8" : (card.getValue() == Value.NINE) ? cardChar = "9" : String.valueOf(card.getValue().toString().charAt(0));
					g.drawString(cardChar , CARD_X*count + CARD_WIDTH*(count-1) + 15, CARD_Y + 35);
					g.drawString(cardChar , CARD_X*count + CARD_WIDTH*(count-1) + CARD_WIDTH - 30, CARD_Y + CARD_HEIGHT - 15);
					g.drawImage(image, CARD_X*count + CARD_WIDTH*(count-1) - SIZE/2 + CARD_WIDTH/2, CARD_Y - SIZE/2 + CARD_HEIGHT/2, SIZE, SIZE , this);
				}
				g.setColor(Color.BLACK);
				g.drawRoundRect(CARD_X*count + CARD_WIDTH*(count-1), CARD_Y, CARD_WIDTH, CARD_HEIGHT, ARC, ARC);
				count++;
			}
		}
	}
	
	//This is for reposition the card when resizing the frame
	public void repositionCard(int frameWidth, int frameHeight)
	{
		CARD_WIDTH = (int) ((frameWidth - 6*CARD_X)/5 - 3.75);
		CARD_HEIGHT = (int) (CARD_WIDTH * 1.5);
		CARD_Y = (int) ((frameHeight - CARD_HEIGHT - 250) / 2);
		SIZE = CARD_WIDTH / 4;
	}
	
	//These 2 updateCard methods are used to add a card into a player hand
	public void updateCard(Player player, PlayingCard newCard)
	{
		if(player != null)
		{
			gameFrame.getViewModel().getPlayerState(player).addCard(newCard);
		}
		this.playerState = gameFrame.getViewModel().getPlayerState(player);
		update(this.getGraphics());
	}
	
	public void updateCard(PlayingCard newCard)
	{
		gameFrame.getViewModel().getPlayerState(gameFrame.getGameToolBar().getHouse()).addCard(newCard);
		this.playerState = gameFrame.getViewModel().getPlayerState(gameFrame.getGameToolBar().getHouse());
		update(this.getGraphics());
	}

	
	//This is used to set the player state of card panel, this is called when jcombobox is changed
	public void setPlayer(Player player)
	{
		this.playerState = gameFrame.getViewModel().getPlayerState(player);
		update(this.getGraphics());
	}
	
	//This is for clearing player hand, resulting in clearing the panel
	public void resetPanel()
	{
		if(this.playerState != null)
		{
			this.playerState.getCardHand().clear();
			this.update(this.getGraphics());	
		}
	}
}
