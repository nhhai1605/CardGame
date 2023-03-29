package view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controller.DealHouseListener;
import model.interfaces.Player;
import view.model.PlayerState;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel
{
	private DefaultTableModel tableModel;
	private JTable playerTable;
	private GameFrame gameFrame;
	public SummaryPanel(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
		tableModel = new DefaultTableModel();
		tableModel.addColumn("ID");
		tableModel.addColumn("Name");
		tableModel.addColumn("Points");
		tableModel.addColumn("Bet");
		tableModel.addColumn("Result");
		tableModel.addColumn("Win/Lose");
		playerTable = new JTable(tableModel);
		for(Player p : gameFrame.getGameEngine().getAllPlayers())
		{
			tableModel.insertRow(playerTable.getRowCount(), new Object [] {p.getPlayerId(), p.getPlayerName(), p.getPoints(), p.getBet(), p.getResult(), "DRAW"});
			gameFrame.getViewModel().addPlayerState(new PlayerState(p));
		}
		setLayout(new GridLayout(1,1));
		tableModel.addTableModelListener(new DealHouseListener(gameFrame));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(playerTable);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    setPreferredSize(new Dimension(Integer.MAX_VALUE, 150));
	    setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
	    setMinimumSize(new Dimension(Integer.MAX_VALUE, 100));
		playerTable.setDefaultEditor(Object.class, null);
	    playerTable.getTableHeader().setReorderingAllowed(false);
	    playerTable.setRowHeight(20);
	    playerTable.getTableHeader().setFont(new Font("Sans Serif", Font.BOLD, 15));
	    playerTable.setFont(new Font("Sans Serif", Font.BOLD, 15));
	    playerTable.setBackground(Color.WHITE);
	    playerTable.getTableHeader().setBackground(Color.WHITE);
	    scrollPane.getViewport().setBackground(Color.WHITE);
	    add(scrollPane);
	}
	
	public void updateResult(Player player)
	{
		for(int i = 0; i < tableModel.getRowCount(); i++)
		{
			if(player.getPlayerId().equals(tableModel.getValueAt(i, 0)))
			{
				tableModel.setValueAt(gameFrame.getViewModel().getPlayerState(player).getLastResult(), i, 4);
			}
		}
	}
	
	public void updateBet(Player player)
	{
		for(int i = 0; i < tableModel.getRowCount(); i++)
		{
			if(player.getPlayerId().equals(tableModel.getValueAt(i, 0)))
			{
				tableModel.setValueAt(player.getBet(), i, 3);
			}
		}
	}
	
	public void updateWinLoss(Player player)
	{
		for(int i = 0; i < tableModel.getRowCount(); i++)
		{
			if(player.getPlayerId().equals(tableModel.getValueAt(i, 0)))
			{
				tableModel.setValueAt(gameFrame.getViewModel().getPlayerState(player).getWinLoss(), i, 5);
			}
		}
	}
	
	public void updatePoints(Player player)
	{
		for(int i = 0; i < tableModel.getRowCount(); i++)
		{
			if(player.getPlayerId().equals(tableModel.getValueAt(i, 0)))
			{
				tableModel.setValueAt(player.getPoints(), i, 2);
			}
		}
	}
	
	public void refreshTable()
	{
		for(int i = tableModel.getRowCount()-1; i >= 0; i--)
		{
			tableModel.removeRow(i);
		}
		for(Player p : gameFrame.getGameEngine().getAllPlayers())
		{
			tableModel.insertRow(playerTable.getRowCount(), new Object [] {p.getPlayerId(), p.getPlayerName(), p.getPoints(), p.getBet(), gameFrame.getViewModel().getPlayerState(p).getLastResult(), gameFrame.getViewModel().getPlayerState(p).getWinLoss()});
		}
	}
	
	public DefaultTableModel getTableModel()
	{
		return this.tableModel;
	}
	
	public JTable getPlayerTable()
	{
		return this.playerTable;
	}
	
}
