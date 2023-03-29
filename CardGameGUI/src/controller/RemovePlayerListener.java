package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.interfaces.Player;
import view.gui.GameFrame;
import view.gui.ListCellRenderer.PlayerListCellRenderer;

public class RemovePlayerListener implements ActionListener
{
	private GameFrame gameFrame;

	public RemovePlayerListener(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object[] options = {"Remove", "Cancel"};
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Choose player to remove: ");
		JComboBox <Player> playerBox = new JComboBox<Player>();
		for(Player p : gameFrame.getGameEngine().getAllPlayers())
		{
			playerBox.addItem(p);
		}
		playerBox.setRenderer(new PlayerListCellRenderer());
		panel.add(label);
		panel.add(playerBox);
		int result = JOptionPane.showOptionDialog(null, panel, "Remove Player",
		         JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
		        null, options, null);
		if (result == JOptionPane.OK_OPTION)
		{
			int result2 = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove this player?", null, JOptionPane.WARNING_MESSAGE);
			if(result2 == JOptionPane.OK_OPTION)
			{
				Player selectedPlayer = (Player)playerBox.getSelectedItem();
				gameFrame.getGameEngine().removePlayer(selectedPlayer);
				gameFrame.getViewModel().removePlayerState(selectedPlayer);
				gameFrame.getGameToolBar().removePlayer(selectedPlayer);
				gameFrame.getGameStatusBar().setGameStatus();
				gameFrame.getGameStatusBar().setAddRemoveStatus(selectedPlayer, false);
			}
		}

	}

}
