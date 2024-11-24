package com.text_adventure.woodland_mansion.game;

import com.text_adventure.woodland_mansion.helper.Delay;
import com.text_adventure.woodland_mansion.ui.UserCursor;
import com.text_adventure.woodland_mansion.ui.menu.Menu;
import com.text_adventure.woodland_mansion.ui.menu.MenuButton;
import com.text_adventure.woodland_mansion.ui.menu.MenuItem;
import com.text_adventure.woodland_mansion.ui.menu.MenuTitle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class GameOver extends Pane {
	private VBox titleBox = new VBox();
	private HBox wrapTitleBox = new HBox();

	private HBox restartTitleBox = new HBox();
	private HBox buttonBox = new HBox();
	private VBox wrapRestartTitleButtonBox = new VBox();

	private ObservableList<MenuButton> gameOverData = FXCollections.observableArrayList();

	private Delay delay = new Delay();

	public GameOver(BorderPane root, Scene scene) {
		wrapTitleBox.setAlignment(Pos.BASELINE_CENTER);
		wrapRestartTitleButtonBox.setAlignment(Pos.CENTER);
		wrapRestartTitleButtonBox.setSpacing(40);

		initGameOverTitle(wrapTitleBox);
		initRestartGameNotification(wrapRestartTitleButtonBox, root, scene, null);

		root.setTop(wrapTitleBox);
		root.setCenter(wrapRestartTitleButtonBox);
	}

	public void initGameOverTitle(Pane parentBox) {
		MenuTitle title = new MenuTitle("GAME OVER");
		titleBox.setAlignment(Pos.BASELINE_CENTER);
		titleBox.getChildren().add(title);
		parentBox.getChildren().addAll(titleBox);
	}

	public void initRestartGameNotification(Pane parentBox, BorderPane root, Scene scene, Room[][][] room) {
		MenuTitle restartTitle = new MenuTitle("Restart?");
		restartTitleBox.setAlignment(Pos.CENTER);
		restartTitleBox.getChildren().addAll(restartTitle);

		var noButton = new MenuButton("No");
		noButton.setOnMouseClicked(e -> {
			getChildren().clear();
			Menu menu = new Menu(root, scene);
			root.getChildren().addAll(menu);
		});

		gameOverData.addAll(noButton);

		var yesButton = new MenuButton("Yes");
		yesButton.setOnMouseClicked(e -> {
			UserCursor loadCursor = new UserCursor();
			loadCursor.loadCursorIcons(0, scene);
			delay.setDelay(2000, () -> {
				root.getChildren().clear();
				Game game = new Game(root, scene);
				loadCursor.setCursor(scene);
				root.getChildren().addAll(game);
			});
		});

		gameOverData.addAll(yesButton);

		gameOverData.forEach(button -> {
			MenuItem buttonItem = new MenuItem(button);
			buttonBox.setSpacing(40);
			buttonBox.setAlignment(Pos.CENTER);
			HBox.setHgrow(buttonItem, Priority.NEVER);
			buttonBox.getChildren().addAll(buttonItem);
		});

		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(80);
		parentBox.getChildren().addAll(restartTitleBox, buttonBox);
	}
}
