package view.model;

import java.util.Deque;
import java.util.LinkedList;

import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class PlayerState
{
	private Player player;
	private String winLoss;
	private int lastResult;
	private Deque<PlayingCard> cardHand;
	private boolean isBust;
	private boolean isDealt;
	
	public PlayerState(Player player)
	{
		this.player = player;
		isBust = false;
		isDealt = false;
		winLoss = "DRAW";
		lastResult = 0;
		cardHand = new LinkedList<PlayingCard>();
	}
	
	public Deque<PlayingCard> getCardHand()
	{
		return cardHand;
	}

	public Player getPlayer()
	{
		return player;
	}

	public String getWinLoss()
	{
		return winLoss;
	}

	public int getLastResult()
	{
		return lastResult;
	}

	public boolean isBust()
	{
		return isBust;
	}
	
	public void addCard(PlayingCard card)
	{
		cardHand.add(card);
	}
	
	public void setCardHand(Deque<PlayingCard> cardHand)
	{	
		this.cardHand = cardHand;
	}

	public void setWinLoss(String winLoss)
	{
		this.winLoss = winLoss;
	}

	public void setLastResult(int lastResult)
	{
		this.lastResult = lastResult;
	}

	public void setBust(boolean isBust)
	{
		this.isBust = isBust;
	}
	
	public boolean isDealt()
	{
		return isDealt;
	}
	
	public void setDealt(boolean isDealt)
	{
		this.isDealt = isDealt;
	}
	
}
