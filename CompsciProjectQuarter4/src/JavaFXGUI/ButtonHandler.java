package JavaFXGUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

@SuppressWarnings("restriction")
public class ButtonHandler implements EventHandler<ActionEvent> {
		public static Playable previous = null;
		private String val ;
		private Playable button;
		private OptionSelect parent;
		boolean otherText;
		public ButtonHandler(String opt, Playable b, OptionSelect p) {
			this.val = opt ;
			button = b;
			parent = p;
			otherText = false;
		}
		public ButtonHandler(String opt, Playable b, OptionSelect p, boolean oT) {
			this.val = opt ;
			button = b;
			parent = p;
			otherText = oT;
			
		}
		@Override
		public void handle(ActionEvent event) {
			if (otherText ==false){
				
				parent.addInfo(val);
				if (previous != null && !previous.equals(button)){
					button.play();
					previous.reverse();
				}
				else if (previous == null){
					button.play();
				}
				previous = button;
			}
			else{
				parent.addInfo(parent.textFieldOtherHBox.getText());
				if (previous != null && !previous.equals(button)){
					button.play();
					previous.reverse();
				}
				else if (previous == null){
					button.play();
				}
				previous = button;
			}
		}

	}