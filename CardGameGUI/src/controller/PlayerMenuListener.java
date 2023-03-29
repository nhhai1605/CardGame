package controller;

import java.awt.event.*;
import javax.swing.JComboBox;
import model.interfaces.Player;
import view.gui.GameFrame;

public class PlayerMenuListener implements ItemListener
{
	private GameFrame gameFrame;

	public PlayerMenuListener(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0)
	{
		if (arg0.getStateChange() == ItemEvent.SELECTED)
		{
			Object source = arg0.getSource();
			if (source instanceof JComboBox)
			{
				@SuppressWarnings("rawtypes")
				Player selectedPlayer = (Player) ((JComboBox) source).getSelectedItem();
				gameFrame.getGameStatusBar().setPlayerStatus(selectedPlayer);
				
				if(gameFrame.getGameEngine().getAllPlayers().contains(selectedPlayer))
				{
					gameFrame.getGameToolBar().getBetButton().setEnabled(true);
				}
				else
				{
					gameFrame.getGameToolBar().getBetButton().setEnabled(false);
				}
				
				if(selectedPlayer.getBet() > 0)
				{
					gameFrame.getGameToolBar().getDealButton().setEnabled(true);
				}
				else
				{
					gameFrame.getGameToolBar().getDealButton().setEnabled(false);
				}
				
				if(gameFrame.getViewModel().getPlayerState(selectedPlayer).isDealt())
				{
					gameFrame.getGameToolBar().getDealButton().setEnabled(false);
					gameFrame.getGameToolBar().getBetButton().setEnabled(false);
				}
				
				gameFrame.getCardPanel().setPlayer(selectedPlayer);
			}

		}

	}
}
