package view;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import app.MainFrame;
import controller.EntityChangeListener;
import model.Entity;

public class RightUpperTabbedPane extends JTabbedPane {

	private ArrayList<RightPanel> tableContainers;

	public RightUpperTabbedPane() {
		tableContainers = new ArrayList<>();
		addChangeListener(new EntityChangeListener());
	}

	public void addWithTitle(RightPanel rightPanel) {
		add(rightPanel.getEntityContainer().getEntity().getName(), rightPanel);
		MainFrame.getInstance().setCurrentRightPanel(rightPanel);
		tableContainers.add(rightPanel);
	}

	public ArrayList<RightPanel> getEntityContainers() {
		return tableContainers;
	}

	public EntityContainer findEntityContainer(Entity entity) {

		for (RightPanel e : tableContainers) {
			if (e.getEntityContainer().getEntity().equals(entity)) {
				return e.getEntityContainer();
			}
		}
		return null;
	}
	public RightPanel getSelectedPanel(){
		return (RightPanel) getComponentAt(getSelectedIndex());
	}
}
