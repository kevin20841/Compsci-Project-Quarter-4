package SwingGUI;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
public class MenuJTabbedPane extends JPanel {
	private JTabbedPane jtp;
	public MenuJTabbedPane(){
		jtp = new JTabbedPane();
		jtp.addTab("Start", new StartPane());


		add(jtp);
		setSize(400, 400);	
	}
	
	public void addJTabbedPaneTab(String name, JPanel panel){
		jtp.addTab(name, panel);
	}

}
