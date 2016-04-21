package GUI;


import javax.swing.JFrame;



public class StartGUI {
	public static void main(String[] a) {
	    JFrame f = new JFrame();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.add(new MenuJTabbedPane());
	    f.setSize(500, 500);
	    f.setVisible(true);
	  }
}