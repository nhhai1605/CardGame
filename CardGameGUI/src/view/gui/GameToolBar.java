package view.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import controller.BetListener;
import controller.PlayerMenuListener;
import controller.DealListener;
import model.SimplePlayer;
import model.interfaces.Player;
import view.gui.ListCellRenderer.PlayerListCellRenderer;
import view.model.PlayerState;

@SuppressWarnings("serial")
public class GameToolBar extends JToolBar
{
	private GameFrame gameFrame;
	private MenuBar menuBar;
	private JComboBox<Player> playerMenu;
	private JButton dealButton;
	private JButton betButton;
	private Player house;

	public GameToolBar(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
		dealButton = new JButton("Deal");
		betButton = new JButton("Bet");
		playerMenu = new JComboBox<Player>();
		house = new SimplePlayer("0", "House", 0);
		gameFrame.getViewModel().addPlayerState(new PlayerState(house));
		playerMenu.addItem(house);
		for (Player p : gameFrame.getGameEngine().getAllPlayers())
		{
			playerMenu.addItem(p);
		}
		playerMenu.setSelectedItem(null);
		menuBar = new MenuBar(gameFrame);
		betButton.addActionListener(new BetListener(gameFrame));
		dealButton.addActionListener(new DealListener(gameFrame));
		playerMenu.addItemListener(new PlayerMenuListener(gameFrame));
		playerMenu.setRenderer(new PlayerListCellRenderer());
		dealButton.setEnabled(false);
		betButton.setEnabled(false);
		this.setBackground(Color.WHITE);
		playerMenu.setBackground(Color.WHITE);
		betButton.setBackground(Color.WHITE);
		dealButton.setBackground(Color.WHITE);
		playerMenu.setFont(new Font("Sans Serif", Font.BOLD, 15));
		dealButton.setFont(new Font("Sans Serif", Font.BOLD, 15));
		betButton.setFont(new Font("Sans Serif", Font.BOLD, 15));
		add(menuBar);
		add(dealButton);
		add(betButton);
		add(playerMenu);
	}
	
	public JComboBox<Player> getComboBox()
	{
		return playerMenu;
	}
	
	public Player getSelectedPlayer()
	{
		return (Player) playerMenu.getSelectedItem();
	} 
	
	public Player getHouse()
	{
		return house;
	}

	public void addPlayer(Player newPlayer)
	{
		gameFrame.getSummaryPanel().refreshTable();
		playerMenu.removeAllItems();
		playerMenu.addItem(house);
		for (Player p : gameFrame.getGameEngine().getAllPlayers())
		{
			playerMenu.addItem(p);
		}
		playerMenu.setSelectedItem(newPlayer);
	}
	
	public void removePlayer(Player player)
	{
		gameFrame.getSummaryPanel().refreshTable();
		playerMenu.removeItem(player);
		playerMenu.setSelectedItem(gameFrame.getGameToolBar().getHouse());
	}
	
	public JButton getDealButton()
	{
		return dealButton;
	}
	
	public JButton getBetButton()
	{
		return betButton;
	}
}
