package SwingGUI;


import javax.swing.JFrame;

// TODO CHANGE TO JAVAFX, ADD BOOTSTRAP CSS LIBRARY

public class GUIController {
	
	public static void main(String[] a) {
	    JFrame mainFrame = new JFrame();
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    MenuJTabbedPane  menuTabbedPane = new MenuJTabbedPane();

	    menuTabbedPane.addJTabbedPaneTab("Enter ID", new EnterIdPane());
	    mainFrame.add(menuTabbedPane);
	    mainFrame.setSize(500, 500);
	    mainFrame.setVisible(true);
	}	  
}