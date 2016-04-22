package SwingGUI;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPane extends JPanel {

	public StartPane(){
		setLayout(new BorderLayout());
		

		JPanel container = new JPanel();
		container.setLayout(new GridLayout(3,2));
		
		container.add(new JLabel());
		container.add(new JLabel());
		
		JButton inButton = new JButton("Coming In");
		container.add(inButton);
		
		JButton outButton = new JButton("Coming Out");
		container.add(outButton);
		
		JPanel printContainer = new JPanel(new FlowLayout());
		
		JButton viewLogsButton = new JButton("View Logs");
		printContainer.add(viewLogsButton);
		
		add(printContainer, BorderLayout.PAGE_END);
		add(container, BorderLayout.CENTER);
		
		
		
		setPreferredSize(new Dimension(410, 400));
	}
}
