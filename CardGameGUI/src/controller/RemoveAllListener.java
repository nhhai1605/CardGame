package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.interfaces.Player;
import view.gui.GameFrame;

public class RemoveAllListener implements ActionListener
{
	private GameFrame gameFrame;

	public RemoveAllListener(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object[] options = {"Remove", "Cancel"};
		JLabel confirm = new JLabel("Do you want to remove all players ?");
		int result = JOptionPane.showOptionDialog(null, confirm, "Remove All Player",
		         JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
		        null, options, null);
		if (result == JOptionPane.OK_OPTION)
		{
			int result2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove ALL players?", null, JOptionPane.WARNING_MESSAGE);
			if(result2 == JOptionPane.OK_OPTION)
			{
				for(Player player : gameFrame.getGameEngine().getAllPlayers())
				{
					gameFrame.getGameEngine().removePlayer(player);
					gameFrame.getViewModel().removePlayerState(player);
					gameFrame.getGameToolBar().removePlayer(player);
				}
				gameFrame.getGameStatusBar().setGameStatus();
				gameFrame.getGameStatusBar().setAddRemoveStatus(null, false);

			}
			
		}

	}

}
