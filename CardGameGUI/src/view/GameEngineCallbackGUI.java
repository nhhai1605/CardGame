package view;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.gui.GameFrame;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback
{
	private GameFrame gameFrame;

	public GameEngineCallbackGUI(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				gameFrame.getCardPanel().updateCard(player, card);
			}
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				gameFrame.getViewModel().getPlayerState(player).setBust(true);
				gameFrame.getCardPanel().updateCard(player, card);
			}
		});
	}

	@Override
	public void result(Player player, int result, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				gameFrame.getViewModel().getPlayerState(player).setLastResult(result);
				gameFrame.getSummaryPanel().updateResult(player);
			}
		});
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				gameFrame.getCardPanel().updateCard(card);
			}
		});
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				gameFrame.getViewModel().getPlayerState(gameFrame.getGameToolBar().getHouse()).setBust(true);
				gameFrame.getCardPanel().updateCard(card);
			}
		});
	}

	@Override
	public void houseResult(int result, GameEngine engine)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				gameFrame.getViewModel().getPlayerState(gameFrame.getGameToolBar().getHouse()).setLastResult(result);
				gameFrame.getGameStatusBar().setHouseResultStatus();
			}
		});
		gameFrame.getGameToolBar().getHouse().setResult(result);
	}

}
