package view.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class GameStatusBar extends JPanel 
{
	private GameFrame gameFrame;
	private JLabel playerStatusLabel;
	private JLabel gameStatusLabel;
	private JLabel functionStatusLabel;

	public GameStatusBar(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
		setLayout(new GridLayout(1,3));
		playerStatusLabel = new JLabel(" Player selected: ", JLabel.LEFT);
		gameStatusLabel = new JLabel("0 / " + gameFrame.getGameEngine().getAllPlayers().size() + " Players Have Been Dealt ", JLabel.RIGHT);
		functionStatusLabel = new JLabel("", JLabel.CENTER);
		add(playerStatusLabel);
		add(functionStatusLabel);
		add(gameStatusLabel);    
		playerStatusLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		functionStatusLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));
		gameStatusLabel.setFont(new Font("Sans Serif", Font.BOLD, 15));

		this.setBackground(Color.WHITE);
	}
	
	public void setPlayerStatus(Player player)
	{	
		if(player != null)
		{
			String hasDealt = (gameFrame.getViewModel().getPlayerState(player).isDealt()) ? "Dealt" : "Not Dealt";
			playerStatusLabel.setText(" Player selected: " + player.getPlayerName() + " (" + hasDealt + ")");
		}
		else
		{
			playerStatusLabel.setText(" Player selected: ");
		}
	}
	
	public void setGameStatus()
	{
		int numOfPlayers = 0;
		for(Player p : gameFrame.getGameEngine().getAllPlayers())
		{
			if(gameFrame.getViewModel().getPlayerState(p).isDealt())
			{
				numOfPlayers++;
			}
		}
		gameStatusLabel.setText(numOfPlayers + " / " + gameFrame.getGameEngine().getAllPlayers().size() + " Players Have Been Dealt ");
	}
	
	public void setHouseResultStatus()
	{
		functionStatusLabel.setText("House result: " + gameFrame.getViewModel().getPlayerState(gameFrame.getGameToolBar().getHouse()).getLastResult());
	}
	public void setAddRemoveStatus(Player player, boolean isAdded)
	{	
		if(player != null)
		{
			if(isAdded)
			{
				functionStatusLabel.setText(player.getPlayerName() + " has been added");
			}
			else
			{
				functionStatusLabel.setText(player.getPlayerName() + " has been removed");
			}	
		}
		else
		{
			functionStatusLabel.setText("All players have been removed");
		}
	}
	
	public void setBetStatus(Player player, int bet)
	{
		if(bet > 0)
		{
			functionStatusLabel.setText(player.getPlayerName() + " has placed " + bet + " points");
		}
		else
		{
			functionStatusLabel.setText(player.getPlayerName() + " has resetted bet ");
		}
	}
}
