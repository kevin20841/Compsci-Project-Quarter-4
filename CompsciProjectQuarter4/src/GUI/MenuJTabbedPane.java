package GUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MenuJTabbedPane extends JPanel {
	public MenuJTabbedPane(){
		makeGUI();
	}
	private void makeGUI() {
		JTabbedPane jtp = new JTabbedPane();
		jtp.addTab("Start", new StartPane());

		add(jtp);
		setSize(400, 400);
	}

}
