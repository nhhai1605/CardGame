package client;

import java.util.logging.Level;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.gui.GameFrame;

public class GUIClient
{
	public static void main(String[] args)
	{
		final GameEngine gameEngine = new GameEngineImpl();

//		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000),
//				 new SimplePlayer("2", "The Loser", 1000), new SimplePlayer("3", "The King", 1000),
//				 new SimplePlayer("4", "The Joker", 1000), new SimplePlayer("5", "The Queen", 1000),
//				 new SimplePlayer("6", "The Monarch", 1000), new SimplePlayer("7", "The Queen", 1000),
//				 new SimplePlayer("8", "Zorah", 1000), new SimplePlayer("9", "Rathian", 1000)};
		
		Player[] players = new Player[] { new SimplePlayer("1", "The Shark", 1000),
				new SimplePlayer("2", "The Loser", 1000), new SimplePlayer("3", "The King", 1000) };
		for (Player p : players)
		{
			gameEngine.addPlayer(p);
			gameEngine.placeBet(p, 100);
		}

		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		GameEngineCallbackImpl.setAllHandlers(Level.INFO, GameEngineCallbackImpl.logger, true);

		GameFrame gameFrame = new GameFrame(gameEngine);
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(gameFrame));

	}
}
