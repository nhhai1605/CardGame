package model;

import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;

import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.interfaces.GameEngine;

public class GameEngineImpl implements GameEngine
{
	private Deque<PlayingCard> cardDeck;
	private Collection<Player> playerCollection;
	private Collection <GameEngineCallback> callbacks;

	public GameEngineImpl()
	{
		playerCollection = new LinkedList<Player>();
		callbacks = new LinkedList<GameEngineCallback>();
		cardDeck = getShuffledHalfDeck();
	}

	@Override
	public void dealPlayer(Player player, int delay) throws IllegalArgumentException
	{
		if(delay < 0 || delay > 1000 || playerCollection.contains(player) == false) //this is used to check the input is valid (delay time and player is valid)
		{
			throw new IllegalArgumentException("invalid input");
		}
		int score = 0;
		boolean stopDealing = false;
		while (stopDealing == false && player.getBet() > 0)  //This is used to check if the player bet or not.
		{
			int cardScore = cardDeck.getFirst().getScore();
			if(score + cardScore < BUST_LEVEL)
			{
				for(GameEngineCallback callback : callbacks)
				{
					callback.nextCard(player, cardDeck.getFirst(), this);
				}
				score += cardScore;
			}
			else if(score + cardScore == BUST_LEVEL)
			{
				for(GameEngineCallback callback : callbacks)
				{
					callback.nextCard(player, cardDeck.getFirst(), this);
				}
				score += cardScore;
				for(GameEngineCallback callback : callbacks)
				{
					callback.result(player, score, this);
				}
				player.setResult(score);
				stopDealing = true;

			}
			else
			{
				for(GameEngineCallback callback : callbacks)
				{
					callback.bustCard(player, cardDeck.getFirst(), this);
				}
				for(GameEngineCallback callback : callbacks)
				{
					callback.result(player, score, this);
				}
				player.setResult(score);
				stopDealing = true;
			}
			cardDeck.removeFirst();
			if(cardDeck.size() == 0)
			{
				cardDeck = getShuffledHalfDeck();
			}
		}
		try
		{
			Thread.sleep(delay);
		}
		catch (InterruptedException e)
		{
			System.out.println("Got interrupted");
		}
	}

	@Override
	public void dealHouse(int delay) throws IllegalArgumentException
	{
		if(delay < 0)
		{
			throw new IllegalArgumentException("delay time must be larger than 0");
		}
		int score = 0;
		boolean stopDealing = false;

		while (stopDealing == false)
		{
			int cardScore = cardDeck.getFirst().getScore();
			if(score + cardScore < BUST_LEVEL)
			{
				for(GameEngineCallback callback : callbacks)
				{
					callback.nextHouseCard(cardDeck.getFirst(), this);
				}
				score += cardScore;
			}
			else if(score + cardScore == BUST_LEVEL)
			{
				for(GameEngineCallback callback : callbacks)
				{
					callback.nextHouseCard(cardDeck.getFirst(), this);
				}
				score += cardScore;
				for (Player player : playerCollection)
				{
					applyWinLoss(player, score);
				}
				for(GameEngineCallback callback : callbacks)
				{
					callback.houseResult(score, this);
				}
				for (Player player : playerCollection)
				{
					player.resetBet();
					player.setResult(0);
				}
				stopDealing = true;
			}
			else
			{
				for(GameEngineCallback callback : callbacks)
				{
					callback.houseBustCard(cardDeck.getFirst(), this);
				}
				for (Player player : playerCollection)
				{
					applyWinLoss(player, score);
				}
				for(GameEngineCallback callback : callbacks)
				{
					callback.houseResult(score, this);
				}
				for (Player player : playerCollection)
				{
					player.resetBet();
					player.setResult(0);
				}
				stopDealing = true;
			}
			
			cardDeck.removeFirst();
			
			if(cardDeck.size() == 0)
			{
				cardDeck = getShuffledHalfDeck();
			}
		}
		try
		{
			Thread.sleep(delay);
		}
		catch (InterruptedException e)
		{
			System.out.println("Got interrupted");
		}

	}

	@Override
	public void applyWinLoss(Player player, int houseResult)
	{
		if(player.getResult() > houseResult)
		{
			player.setPoints(player.getPoints() + player.getBet());
		}
		else if(player.getResult() < houseResult)
		{
			player.setPoints(player.getPoints() - player.getBet());
		}
	}

	@Override
	public void addPlayer(Player player)
	{
		Iterator<Player> iterator = playerCollection.iterator();
		while(iterator.hasNext())
		{
			if(iterator.next().equals(player))
			{
				iterator.remove();
			}
		}
		playerCollection.add(player);
	}

	@Override
	public Player getPlayer(String id)
	{
		Player player = null;
		for (Player p : playerCollection)
		{
			if(p.getPlayerId().equals(id))
			{
				player = p;
			}
		}
		return player;

	}

	@Override
	public boolean removePlayer(Player player)
	{
		return playerCollection.remove(player);
	}

	@Override
	public boolean placeBet(Player player, int bet)
	{
		boolean betIsSet = false;
		if(bet > 0 && bet <= player.getPoints() && playerCollection.contains(player) == true) //this is used to check the input if the player is in the collection
		{
			player.setBet(bet);
			betIsSet = true;
		}
		return betIsSet;
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		this.callbacks.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback)
	{
		return callbacks.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers()
	{
		Collection<Player> sortedPlayers = new LinkedList<Player>();
		Object[] playersArray = new Player[] {};
		Object mid;
		playersArray = playerCollection.toArray();
		for (int i = 0; i < playersArray.length; i++)
		{
			for (int j = i + 1; j < playersArray.length; j++)
			{
                if (((Player)playersArray[i]).getPlayerId().compareTo(((Player) playersArray[j]).getPlayerId()) > 0) 
                {
                	mid =  playersArray[i];
                	playersArray[i] = playersArray[j];
                	playersArray[j] = mid;
                }
			}
		}
		for (int i = 0; i < playersArray.length; i++)
		{
			sortedPlayers.add((Player) playersArray[i]);
		}
		return sortedPlayers;
	}

	@Override
	public Deque<PlayingCard> getShuffledHalfDeck()
	{
		Deque<PlayingCard> newCardDeck = new LinkedList<PlayingCard>();
		for (int i = 0; i < Suit.values().length; i++)
		{
			for (int j = 0; j < Value.values().length; j++)
			{
				newCardDeck.add(new PlayingCardImpl(Suit.values()[i], Value.values()[j]));
			}
		}
		Collections.shuffle((List<?>) newCardDeck);
		return newCardDeck;
	}
	
}
