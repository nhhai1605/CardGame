package controller;

import java.awt.event.*;
import model.interfaces.Player;
import view.gui.GameFrame;

public class DealListener implements ActionListener
{
	private GameFrame gameFrame;	
	
	public DealListener(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Player selectedPlayer = gameFrame.getGameToolBar().getSelectedPlayer();
		gameFrame.getViewModel().getPlayerState(selectedPlayer).setBust(false);
		gameFrame.getViewModel().getPlayerState(selectedPlayer).setDealt(true);
		gameFrame.getCardPanel().resetPanel();
		gameFrame.getGameToolBar().getDealButton().setEnabled(false);
		gameFrame.getGameToolBar().getBetButton().setEnabled(false);
		new Thread()
		{
			@Override
			public void run()
			{
				gameFrame.getGameEngine().dealPlayer(selectedPlayer, 100);
			}
		}.start();
		gameFrame.getGameStatusBar().setPlayerStatus(selectedPlayer);
		gameFrame.getGameStatusBar().setGameStatus();
	}
}
