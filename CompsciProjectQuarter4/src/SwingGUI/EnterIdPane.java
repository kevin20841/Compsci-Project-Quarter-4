package SwingGUI;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class EnterIdPane extends JPanel{
	public EnterIdPane(){
		JTextField IDTextField = new JTextField();
		IDTextField.setColumns(6);
		
		// TODO Determine font size
		Font font1 = new Font("SansSerif", Font.BOLD, 20);
		IDTextField.setFont(font1);
		
		add(IDTextField);
		
	}
}
