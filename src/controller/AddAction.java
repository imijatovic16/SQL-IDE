package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import app.MainFrame;
import view.ASUFDialog;
import view.InfToolBar;
import view.RightPanel;

public class AddAction extends AbstractInfAction {

	public AddAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("/images/add/add.png"));
		putValue(NAME, "add");
		putValue(SHORT_DESCRIPTION, "Add");
		putValue(LARGE_ICON_KEY, loadIcon("/images/add/add.png"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MainFrame.getInstance().setAddOrSortDialog(new ASUFDialog());
		MainFrame.getInstance().getAddOrSortDialog().makeGUI(ASUFDialog.ADD);
		MainFrame.getInstance().getAddOrSortDialog().setVisible(true);
	}
}

