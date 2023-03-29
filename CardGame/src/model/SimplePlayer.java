package model;

import model.interfaces.Player;

public class SimplePlayer implements Player
{
	private int initialPoints, bet = 0, result = 0;
	private String id, playerName;

	public SimplePlayer(String id, String playerName, int initialPoints)
	{
		if(id == null || playerName == null)
		{
			throw new IllegalArgumentException("must have id and playerName");
		}
		this.id = id;
		this.playerName = playerName;
		this.initialPoints = initialPoints;
	}

	@Override
	public String getPlayerName()
	{
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName)
	{
		this.playerName = playerName;
	}

	@Override
	public int getPoints()
	{
		return initialPoints;
	}

	@Override
	public void setPoints(int points)
	{
		initialPoints = points;
	}

	@Override
	public String getPlayerId()
	{
		return id;
	}

	@Override
	public boolean setBet(int bet)
	{
		boolean betIsSet = false;
		if(bet > 0 && bet <= initialPoints)
		{
			this.bet = bet;
			betIsSet = true;
		}
		return betIsSet;
	}

	@Override
	public int getBet()
	{
		return bet;
	}

	@Override
	public void resetBet()
	{
		this.bet = 0;
	}

	@Override
	public int getResult()
	{
		return result;
	}

	@Override
	public void setResult(int result)
	{
		this.result = result;
	}

	@Override
	public boolean equals(Player player)
	{
//		return (this.id.equals(player.getPlayerId())) || (this.playerName.equals(player.getPlayerName()));
		return this.id.equals(player.getPlayerId());
	}

	@Override
	public boolean equals(Object player)
	{
		boolean equals = false;
		if(player != null && player instanceof Player)
		{

//			equals = (this.id.equals(((Player) player).getPlayerId())) || (this.playerName.equals(((Player) player).getPlayerName()));
			equals = this.id.equals(((Player) player).getPlayerId());

		}
		return equals;
	}

	@Override
	public int hashCode()
	{
		return id.hashCode();
	}

	@Override
	public int compareTo(Player player)
	{
		return id.compareTo(player.getPlayerId());
	}

	@Override
	public String toString()
	{
		return String.format("Player: id=%s, name=%s, bet=%s, points=%s, RESULT .. %s", id, playerName, bet,
				initialPoints, result);
	}

}