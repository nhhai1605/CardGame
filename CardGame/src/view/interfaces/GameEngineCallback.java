package view.interfaces;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;

/**
 * Assignment interface for Further Programming to notify client of GameEngine
 * events<br>
 * i.e. the state of the cards as dealt
 * 
 * @author Caspar Ryan
 */
public interface GameEngineCallback
{
	/**
	 * <pre>
	 * called for each card as the house is dealing to a Player, use this to
	 * update your display for each card or log to console
	 * 
	 * &#64;param player
	 *            the Player who is receiving cards
	 * &#64;param card
	 *            the next card that was dealt
	 * &#64;param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * &#64;see model.interfaces.GameEngine
	 * </pre>
	 */
	public void nextCard(Player player, PlayingCard card, GameEngine engine);

	/**
	 * <pre>
	  * called when the card causes the player to bust 
	  * this method is called instead of {@link #nextCard(Player, PlayingCard, GameEngine)} 
	  * this method is called before {@link #result(Player, int, GameEngine)} 
	  * use this to update your display for each card or log to console
	  * 
	  * NOTE: If player gets 42 exactly then this method IS NOT called
	  *     
	  * &#64;param player
	  *             the Player who is receiving cards
	  * &#64;param card
	  *             the bust card that was dealt
	  * &#64;param engine 
	  *             a convenience reference to the engine so the receiver can call
	  *             methods if necessary
	  * &#64;see model.interfaces.GameEngine
	 * </pre>
	 */
	public void bustCard(Player player, PlayingCard card, GameEngine engine);

	/**
	 * <pre>
	 * called after the player has bust with final result (result is score prior
	 * to the last card that caused the bust)
	 * 
	 * Called from {@link GameEngine#dealPlayer(Player, int)} 
	 * 
	 * &#64;param player
	 *            the current Player
	 * &#64;param result
	 *            the final score of the hand
	 * &#64;param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * &#64;see model.interfaces.GameEngine
	 * </pre>
	 */
	public void result(Player player, int result, GameEngine engine);

	/**
	 * <pre>
	 * called as the house is dealing their own hand, use this to update your
	 * display for each card or log to console
	 *     
	 * &#64;param card
	 *            the next card that was dealt
	 * &#64;param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * &#64;see model.interfaces.GameEngine
	 * </pre>
	 */
	public void nextHouseCard(PlayingCard card, GameEngine engine);

	/**
	 * <pre>
	 * HOUSE version of 
	 * {@link GameEngineCallback#bustCard(Player, PlayingCard, GameEngine)}
	 * 
	 * &#64;param card
	 *            the bust card that was dealt
	 * &#64;param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * &#64;see model.interfaces.GameEngine
	 * </pre>
	 */
	public void houseBustCard(PlayingCard card, GameEngine engine);

	/**
	 * <pre>
	 * called when the HOUSE has bust with final result (result is score prior to the last card
	 * that caused the bust)
	 * 
	 * NOTE: If house gets 42 exactly then this method IS NOT called
	 *     
	 * PRE-CONDITION: This method should only be called AFTER bets have been updated on all Players 
	 * so this callback can log Player results
	 * 
	 * Called from {@link GameEngine#dealHouse(int)} 
	 * 
	 * &#64;param result
	 *            the final score of the dealers (house) hand
	 * &#64;param engine
	 *            a convenience reference to the engine so the receiver can call
	 *            methods if necessary
	 * &#64;see model.interfaces.GameEngine
	 * </pre>
	 */
	public void houseResult(int result, GameEngine engine);
}
