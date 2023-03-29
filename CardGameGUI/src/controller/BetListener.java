package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.interfaces.Player;
import view.gui.GameFrame;

public class BetListener implements ActionListener
{
	private GameFrame gameFrame;
	
	public BetListener(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Player selectedPlayer = gameFrame.getGameToolBar().getSelectedPlayer();
		Object[] options = {"OK",
		        "Reset Bet", "Cancel"};
				
		JPanel panel = new JPanel();
		panel.add(new JLabel("Enter Bet for Player: "));
		JTextField betTextField = new JTextField(15);
		betTextField.setText("100");
		panel.add(betTextField);
		int result = JOptionPane.showOptionDialog(null, panel, "Place Bet",
			         JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
			        null, options, null);
		if(betTextField.getText().equals(""))
		{
			betTextField.setText("100");
		}
		if (result == JOptionPane.YES_OPTION)
		{
			String bet = betTextField.getText();
			Boolean isNumber = true;
			for(int i=0; i < bet.length(); i++) 
			{
				if(!Character.isDigit(bet.charAt(i)))
				{
					isNumber = false; 
				}
		    }
			if(isNumber)
			{
				int betNum = Integer.parseInt(bet);
				if(betNum > 0 && betNum <= selectedPlayer.getPoints())
				{
					gameFrame.getGameEngine().placeBet(selectedPlayer, betNum);
					gameFrame.getSummaryPanel().updateBet(selectedPlayer);
					JLabel notification = new JLabel("Bet has been placed!");
					JOptionPane.showMessageDialog(null, notification, "Bet Placed", JOptionPane.INFORMATION_MESSAGE);
					gameFrame.getGameToolBar().getDealButton().setEnabled(true);
				}
				else if(betNum > selectedPlayer.getPoints())
				{
					JLabel notification = new JLabel("Don't have enough points");
					JOptionPane.showMessageDialog(null, notification, "Cannot place bet", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					selectedPlayer.resetBet();
					gameFrame.getSummaryPanel().updateBet(selectedPlayer);
					JLabel notification = new JLabel("Bet has been resetted to 0!");
					JOptionPane.showMessageDialog(null, notification, "Bet Resetted", JOptionPane.INFORMATION_MESSAGE);
					gameFrame.getGameToolBar().getDealButton().setEnabled(false);
				}
				gameFrame.getGameStatusBar().setBetStatus(selectedPlayer, betNum);
			}
			else
			{
				JLabel error = new JLabel("Bet must be a number!");
				JOptionPane.showMessageDialog(null, error, "Invalid Input", JOptionPane.ERROR_MESSAGE);
				gameFrame.getGameToolBar().getDealButton().setEnabled(false);
			}
		}
		else if(result == JOptionPane.NO_OPTION)
		{
			selectedPlayer.resetBet();
			gameFrame.getSummaryPanel().updateBet(selectedPlayer);
			JLabel notification = new JLabel("Bet has been resetted to 0!");
			JOptionPane.showMessageDialog(null, notification, "Bet Resetted", JOptionPane.INFORMATION_MESSAGE);
			gameFrame.getGameToolBar().getDealButton().setEnabled(false);
			gameFrame.getGameStatusBar().setBetStatus(selectedPlayer, 0);
		}
	}
}
