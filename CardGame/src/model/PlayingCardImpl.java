package model;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard
{

	private Suit suit;
	private Value value;
	private int score;

	public PlayingCardImpl(Suit suit, Value value)
	{
		if(suit == null || value == null)
		{
			throw new IllegalArgumentException("must have suit and value");
		}
		this.suit = suit;
		this.value = value;
	}

	public Suit getSuit()
	{
		return this.suit;
	}

	public Value getValue()
	{
		return this.value;
	}

	public int getScore()
	{
		if(this.getValue() == Value.ACE)
		{
			score = 11;
		}
		else if(this.getValue() == Value.KING || this.getValue() == Value.JACK || this.getValue() == Value.QUEEN
				|| this.getValue() == Value.TEN)
		{
			score = 10;
		}
		else if(this.getValue() == Value.NINE)
		{
			score = 9;
		}
		else
		{
			score = 8;
		}
		return score;
	}

	@Override
	public String toString()
	{
		String suit = this.getSuit().toString().toLowerCase();
		suit = suit.toUpperCase().charAt(0) + suit.substring(1);
		String value = this.getValue().toString().toLowerCase();
		value = value.toUpperCase().charAt(0) + value.substring(1);
		return String.format("Suit: %s, Value: %s, Score: %s", suit, value, this.getScore());
	}

	public boolean equals(PlayingCard card)
	{
		return (this.getSuit() == card.getSuit() && this.getValue() == card.getValue());
	}

	@Override
	public boolean equals(Object card)
	{
		boolean equals = false;
		if(card != null && card instanceof PlayingCard)
		{
			equals = (this.getSuit() == ((PlayingCard) card).getSuit()
					&& this.getValue() == ((PlayingCard) card).getValue());

		}
		return equals;
	}

	@Override
	public int hashCode()
	{
		String string = this.suit.toString() + " " + this.value.toString();
		return string.hashCode();
	}
}