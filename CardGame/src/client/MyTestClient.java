package client;

import java.util.Deque;
import java.util.logging.Level;

import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import validate.Validator;
import view.GameEngineCallbackImpl;

//This is my test client, i modified some input so that i can test through the code.
public class MyTestClient
{
   public static void main(String args[])
   {
      final GameEngine gameEngine = new GameEngineImpl();

      // call method in Validator.jar to test *structural* correctness
      // just passing this does not mean it actually works .. you need to test yourself!
      // pass false if you want to disable logging .. (i.e. once it passes)
      Validator.validate(true);

      // create 5 test players
      Player[] players = new Player[] { new SimplePlayer("2", "The Shark", 1000), new SimplePlayer(
         "1", "The Loser", 500), new SimplePlayer("3", "The Win", 300), new SimplePlayer("4", "The Cat", 500), new SimplePlayer("2", "The Dog", 700) };//2 Player with same ID to check the addPlayer function.

      // add logging callback
      gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

      // Uncomment this to DEBUG your deck of cards creation
      //      Deque<PlayingCard> shuffledDeck = gameEngine.getShuffledHalfDeck();
      //      printCards(shuffledDeck);


      // main loop to add players, place a bet and receive hand
      for (Player player : players)
      {
         gameEngine.addPlayer(player);
         gameEngine.placeBet(player, 700); //I put a high bet to check the placeBet and setBet function.
         gameEngine.dealPlayer(player, 100); 
      }

      //I did check when placeBet and dealPlayer to invalid player as well as trying removePlayer

      // all players have played so now house deals 
      // GameEngineCallBack.houseResult() is called to log all players (after results are calculated)
      gameEngine.dealHouse(10);
      
      //This is used to check the getPlayer function and resetBet and set result to 0 after dealHouse
//      Player test = gameEngine.getPlayer("2");
//      System.out.println(test.toString());
    
   }

   @SuppressWarnings("unused")
   private static void printCards(Deque<PlayingCard> deck)
   {
      for (PlayingCard card : deck)
         GameEngineCallbackImpl.logger.log(Level.INFO, card.toString());
   }
}
