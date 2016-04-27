package JavaFXGUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
@SuppressWarnings("restriction")

public class EnterIDTab extends Tab {
	private boolean goingIn;
	private MenuTabPane parent;
	public EnterIDTab(MenuTabPane p, String title, boolean gIn){
		goingIn = gIn;
		parent = p;
		
		setText(title);
		BorderPane content = new BorderPane();

		VBox imageHBox = new VBox();
		imageHBox.setAlignment(Pos.CENTER);
		imageHBox.setPadding(new Insets(15, 12, 15, 12));
		imageHBox.setSpacing(10);
		
		
		Image photoID  = new Image("img/image.png");
		ImageView photoIDView = new ImageView();
		photoIDView.setImage(photoID);
		photoIDView.setFitWidth(300);
		photoIDView.setPreserveRatio(true);
		photoIDView.setSmooth(true);
		photoIDView.setCache(true);

		HBox labelHBox = new HBox();
		imageHBox.setPadding(new Insets(15, 12, 15, 12));
		imageHBox.setSpacing(10);
		
		Label studentIDLabel = new Label("Enter Student ID: ");
		Button submitButton = new Button("Submit");
		submitButton.setPrefSize(100, 20);
		
		HBox textFieldHBox = new HBox();
		textFieldHBox.setPadding(new Insets(15, 12, 15, 12));
		textFieldHBox.setSpacing(10);
		
		
		TextField textField = new TextField ();
		textField.setPrefColumnCount(8);
		
		labelHBox.getChildren().addAll(studentIDLabel);
		labelHBox.setAlignment(Pos.CENTER);
		
		textFieldHBox.getChildren().addAll(textField);
		textFieldHBox.setAlignment(Pos.CENTER);
		
		imageHBox.getChildren().addAll(photoIDView, labelHBox, textFieldHBox, submitButton);
		
		content.setCenter(imageHBox);
		setContent(content);
	}
	public void die(){
		parent.getTabs().remove(this);
	}
}
