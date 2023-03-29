package controller;

import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

import model.SimplePlayer;
import model.interfaces.Player;
import view.gui.GameFrame;
import view.model.PlayerState;

public class AddPlayerListener implements ActionListener
{
	private GameFrame gameFrame;

	public AddPlayerListener(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{

		Object[] options = {"Add Player", "Cancel"};
				
		JPanel panel = new JPanel();
		JTextField idTextField, playerNameTextField, initialPointsTextField;
		idTextField = new JTextField(15);
		playerNameTextField = new JTextField(15);
		initialPointsTextField = new JTextField(15);
		
		int max = 0;
		for(Player p : gameFrame.getGameEngine().getAllPlayers())
		{
			if(p.getPlayerId().charAt(0) == 'I' && p.getPlayerId().charAt(1) == 'D' && p.getPlayerId().length() >= 3)
			{
				String num = p.getPlayerId().substring(2);
				Boolean isNumber = true;
				for(int i=0; i < num.length(); i++) 
				{
					if(!Character.isDigit(num.charAt(i)))
					{
						isNumber = false; 
					}
			    }
				if(isNumber)
				{
					if(Integer.parseInt(num) > max)
					{
						max = Integer.parseInt(num);
					}
				}
			}
		}
		max++;
		idTextField.setText("ID" + String.valueOf(max));
		initialPointsTextField.setText("1000");
		playerNameTextField.setText("playerID" + max);
		
		JLabel idLabel, playerNameLabel, initialPointsLabel;
		idLabel = new JLabel("Player ID: ");
		playerNameLabel = new JLabel("Player Name:");
		initialPointsLabel = new JLabel("Initial Points: ");
		
		panel.setLayout(new GridLayout(4,2));
		panel.add(idLabel);
		panel.add(idTextField);
		panel.add(playerNameLabel);
		panel.add(playerNameTextField);
		panel.add(initialPointsLabel);
		panel.add(initialPointsTextField);

		int result = JOptionPane.showOptionDialog(null, panel, "Add Player",
			         JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
			        null, options, null);
		if(idTextField.getText().equals(""))
		{
			idTextField.setText("ID" + String.valueOf(max));
		}
		if(playerNameTextField.getText().equals(""))
		{
			playerNameTextField.setText("playerID" + max);
		}
		if(initialPointsTextField.getText().equals(""))
		{
			initialPointsTextField.setText("1000");
		}
		if (result == JOptionPane.YES_OPTION)
		{
			String initialPointsString = initialPointsTextField.getText();		
			Boolean isNumber = true;
			for(int i=0; i < initialPointsString.length(); i++) 
			{
				if(!Character.isDigit(initialPointsString.charAt(i)))
				{
					isNumber = false; 
				}
		    }
			if(isNumber)
			{
				Player newPlayer = new SimplePlayer(idTextField.getText(), playerNameTextField.getText(), Integer.parseInt(initialPointsString));
				gameFrame.getGameEngine().addPlayer(newPlayer);
				gameFrame.getViewModel().addPlayerState(new PlayerState(newPlayer));
				gameFrame.getGameToolBar().addPlayer(newPlayer);
				gameFrame.getGameStatusBar().setGameStatus();
				gameFrame.getGameStatusBar().setAddRemoveStatus(newPlayer, true);
			}
			else
			{
				JLabel error = new JLabel("Initial points must be number!");
				JOptionPane.showMessageDialog(null, error, "Invalid Input", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
