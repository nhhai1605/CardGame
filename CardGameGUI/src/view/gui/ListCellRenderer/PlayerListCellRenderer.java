package view.gui.ListCellRenderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class PlayerListCellRenderer extends DefaultListCellRenderer
{

	public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index,
			boolean isSelected, boolean cellHasFocus)
	{
		if (value instanceof Player)
		{
			value = ((Player) value).getPlayerName();
		}
		if (index == -1 && value == null)
		{
			value = "Choose Player";
		}
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		return this;
	}
}
