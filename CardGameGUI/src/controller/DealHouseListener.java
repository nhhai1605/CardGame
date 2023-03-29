package controller;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import model.interfaces.Player;
import view.gui.GameFrame;

public class DealHouseListener implements TableModelListener 
{
	private GameFrame gameFrame;
	
	public DealHouseListener(GameFrame gameFrame)
	{
		this.gameFrame = gameFrame;
	}
	
	@Override
	public void tableChanged(TableModelEvent arg0)
	{
		gameFrame.getSummaryPanel().revalidate();
		gameFrame.getSummaryPanel().repaint();
		int hasDealt = 0;
		for(Player p : gameFrame.getGameEngine().getAllPlayers())
		{
			if(p.getResult() != 0)
			{
				hasDealt ++;
			}
		}
		if(hasDealt == gameFrame.getGameEngine().getAllPlayers().size() && hasDealt > 0)
		{
			JLabel notification = new JLabel("Press OK to Continue");
			JOptionPane.showMessageDialog(null, notification, "House Ready", JOptionPane.INFORMATION_MESSAGE);
			gameFrame.getViewModel().getPlayerState(gameFrame.getGameToolBar().getHouse()).setDealt(true);
			gameFrame.getViewModel().getPlayerState(gameFrame.getGameToolBar().getHouse()).setBust(false);
			gameFrame.getCardPanel().setPlayer(gameFrame.getGameToolBar().getHouse());
			gameFrame.getCardPanel().resetPanel();
			gameFrame.getGameToolBar().getComboBox().setSelectedItem(gameFrame.getGameToolBar().getHouse());
			Thread thread = new Thread()
			{
				@Override
				public void run()
				{
					gameFrame.getGameEngine().dealHouse(100);
				}
			};
			thread.start();	
			try
			{
				thread.join();
			} catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			for(Player p : gameFrame.getGameEngine().getAllPlayers())
			{
				if(gameFrame.getViewModel().getPlayerState(p).getLastResult() > gameFrame.getGameToolBar().getHouse().getResult())
				{
					gameFrame.getViewModel().getPlayerState(p).setWinLoss("WIN");
				}
				else if(gameFrame.getViewModel().getPlayerState(p).getLastResult() < gameFrame.getGameToolBar().getHouse().getResult())
				{
					gameFrame.getViewModel().getPlayerState(p).setWinLoss("LOSS");
				}
				else
				{
					gameFrame.getViewModel().getPlayerState(p).setWinLoss("DRAW");
				}
			}
			
			//Update all players result, points, win loss
			for(Player p : gameFrame.getGameEngine().getAllPlayers())
			{
				gameFrame.getViewModel().getPlayerState(p).setDealt(false);
				gameFrame.getSummaryPanel().updateResult(p);
				gameFrame.getSummaryPanel().updatePoints(p);
				gameFrame.getSummaryPanel().updateWinLoss(p);
			}
			gameFrame.getViewModel().getPlayerState(gameFrame.getGameToolBar().getHouse()).setDealt(false);
			Object[] options = {"Yes", "No"};
			JLabel playAgain = new JLabel("Play Again?");
			int resultOptionPane = JOptionPane.showOptionDialog(null, playAgain, String.format("House Result: %s", gameFrame.getGameToolBar().getHouse().getResult()),
				         JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				        null, options, null);
			if(resultOptionPane == JOptionPane.NO_OPTION)
			{
				System.exit(0);
			}
			else
			{
				for(Player p : gameFrame.getGameEngine().getAllPlayers())
				{
					if(p.getPoints() == 0)
					{
						gameFrame.getGameEngine().removePlayer(p);
						gameFrame.getViewModel().removePlayerState(p);
						gameFrame.getGameToolBar().removePlayer(p);
					}
				}
				gameFrame.getGameStatusBar().setPlayerStatus(gameFrame.getGameToolBar().getHouse());
				gameFrame.getGameStatusBar().setGameStatus();
			}
		}
	}
}
