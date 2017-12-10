import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class LabelCreation extends Label {
	
	public LabelCreation(String text, double width, double height) {
		
		super(text);
		
		setMinWidth(width);
		setMaxWidth(width);
		
		setMinHeight(height);
		setMaxHeight(height);
		
		setPadding(new Insets(0,0,0,width/2 - 20));
		
	}
	
}