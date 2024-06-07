package MainMenuUI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainTitle extends Pane {
	private Text mainTitle;
	
	public MainTitle(String name) {
		Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 48);
		
		String spread = "";
		for (char c : name.toCharArray()) {
			spread += c + " ";
		}
		
		mainTitle = new Text(spread);
		mainTitle.setFont(font);
		mainTitle.setFill(Color.WHITE);
		getChildren().addAll(mainTitle);
	}

	public double getTitleWidth() {
		return mainTitle.getLayoutBounds().getWidth();
	}
	
	public double getTitleHeight() {
		return mainTitle.getLayoutBounds().getHeight();
	}
}
