package view.model;

import java.util.Deque;
import java.util.LinkedHashSet;
import java.util.Set;

import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.gui.GameFrame;

public class ViewModel
{
	private GameFrame gameFrame;
	private Set<PlayerState> playerStateCollection;
	public ViewModel(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;		
		playerStateCollection = new LinkedHashSet<PlayerState>();
	} 
	
	public void addPlayerState(PlayerState playerState)
	{
		playerStateCollection.add(playerState);
	}
	
	public void removePlayerState(Player player)
	{
		 playerStateCollection.remove(getPlayerState(player));
	}
	
	public void addCardHand(Player player, Deque<PlayingCard> cardHand)
	{
		 for(PlayerState pS : playerStateCollection)
		 {
			 if(pS.getPlayer().equals(player))
			 {
				 pS.setCardHand(cardHand);
			 }
		 }
	}
	
	public PlayerState getPlayerState(Player player)
	{
		PlayerState playerState = null;
		 for(PlayerState pS : playerStateCollection)
		 {
			 if(pS.getPlayer().equals(player))
			 {
				playerState = pS;
			 }
		 }
		return playerState;
	}
}
