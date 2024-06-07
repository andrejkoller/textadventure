package MainMenuUI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenuItem extends Pane {
	private Text mainMenuText;

	public MainMenuItem(String name) {
		Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 48);

		mainMenuText = new Text(name);
		mainMenuText.setFont(font);
		mainMenuText.setFill(Color.WHITE);
		getChildren().addAll(mainMenuText);
	}
 
	public double getMenuItemWidth() {
		return mainMenuText.getLayoutBounds().getWidth();
	}

	public double getMenuItemHeight() {
		return mainMenuText.getLayoutBounds().getHeight();
	}
}
