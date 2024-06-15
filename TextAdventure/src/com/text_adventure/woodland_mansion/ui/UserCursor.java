package com.text_adventure.woodland_mansion.ui;

import java.util.Arrays;
import java.util.List;

import com.text_adventure.woodland_mansion.helper.Delay;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class UserCursor extends Pane {
	private Image cursorIconSelect = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_select.png");
	private Image cursorIconTap = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_select_tap.png");
	
	private Image inputCursor = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_pen.png");

	private Image cursorWork1 = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_work1.png");
	private Image cursorWork2 = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_work2.png");
	private Image cursorWork3 = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_work3.png");
	private Image cursorWork4 = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_work4.png");
	private Image cursorWork5 = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_work5.png");
	private Image cursorWork6 = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_work6.png");
	private Image cursorWork7 = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_work7.png");
	private Image cursorWork8 = new Image("/com/text_adventure/woodland_mansion/assets/icons/cursor_work8.png");

	private List<Image> cursorWork = Arrays.asList(cursorWork1, cursorWork2, cursorWork3, cursorWork4, cursorWork5, cursorWork6,
			cursorWork7, cursorWork8);

	Delay delay = new Delay();

	public UserCursor() {
	}

	public void setCursor(Scene scene) {
		scene.setCursor(new ImageCursor(cursorIconSelect));
		scene.setOnMousePressed(e -> scene.setCursor(new ImageCursor(cursorIconTap)));
		scene.setOnMouseReleased(e -> scene.setCursor(new ImageCursor(cursorIconSelect)));
	}

	public void loadCursorIcons(int cursorID, Scene scene) {
		var icon = cursorWork.get(cursorID);
		scene.setCursor(new ImageCursor(icon));
		if (cursorID < cursorWork.size() - 1) {
			delay.setDelay(200, () -> {
				loadCursorIcons(cursorID + 1, scene);
			});
		}
	}
	
	public void setInputCursor(UserInputField input) {
		input.setCursor(new ImageCursor(inputCursor));
	}
}