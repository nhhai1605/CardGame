package view.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import controller.AddPlayerListener;
import controller.RemoveAllListener;
import controller.RemovePlayerListener;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar
{
	private GameFrame gameFrame;
	private JMenu menu;
	private JMenuItem addNewPlayer;
	private JMenuItem removePlayer;
	private JMenuItem removeAllPlayer;

	public MenuBar(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		menu = new JMenu("Menu");
		addNewPlayer =new JMenuItem("Add New Player");  
		removePlayer =new JMenuItem("Remove Player"); 
		removeAllPlayer = new JMenuItem("Remove All Player");
		addNewPlayer.addActionListener(new AddPlayerListener(gameFrame));
		removePlayer.addActionListener(new RemovePlayerListener(gameFrame));
		removeAllPlayer.addActionListener(new RemoveAllListener(gameFrame));
		menu.add(addNewPlayer);
		menu.add(removePlayer);
		menu.add(removeAllPlayer);
		add(menu);
		this.setBackground(Color.WHITE);
		addNewPlayer.setBackground(Color.WHITE);
		removePlayer.setBackground(Color.WHITE);
		removeAllPlayer.setBackground(Color.WHITE);
		menu.setFont(new Font("Sans Serif", Font.BOLD, 15));
		addNewPlayer.setFont(new Font("Sans Serif", Font.BOLD, 15));
		removePlayer.setFont(new Font("Sans Serif", Font.BOLD, 15));
		removeAllPlayer.setFont(new Font("Sans Serif", Font.BOLD, 15));
	}	
}
