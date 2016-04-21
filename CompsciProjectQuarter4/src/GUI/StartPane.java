package GUI;

import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPane extends JPanel {

	
	public StartPane(){
		setLayout(new BorderLayout());
		JButton inButton = new JButton("Coming In");
		JPanel panel1 = new JPanel();
		panel1.add(inButton);
		add(panel1, BorderLayout.CENTER);
		
		JButton outButton = new JButton("Coming Out");
		JPanel panel2 = new JPanel();
		panel2.add(outButton);
		add(panel2, BorderLayout.LINE_START);
		setPreferredSize(new Dimension(410, 400));
	}
}
