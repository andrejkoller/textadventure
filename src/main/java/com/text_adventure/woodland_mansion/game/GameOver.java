package com.text_adventure.woodland_mansion.game;

import com.text_adventure.woodland_mansion.helper.Delay;
import com.text_adventure.woodland_mansion.ui.UserCursor;
import com.text_adventure.woodland_mansion.ui.menu.Menu;
import com.text_adventure.woodland_mansion.ui.menu.MenuItem;
import com.text_adventure.woodland_mansion.ui.menu.MenuTitle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GameOver extends Pane {
	private final VBox titleBox = new VBox();
	private final HBox wrapTitleBox = new HBox();

	private final HBox restartTitleBox = new HBox();
	private final HBox buttonBox = new HBox();
	private final VBox wrapRestartTitleButtonBox = new VBox();

	private final ObservableList<MenuItem> gameOverData = FXCollections.observableArrayList();

	private Delay delay;

	public GameOver(BorderPane root, Scene scene) {
		wrapTitleBox.setAlignment(Pos.BASELINE_CENTER);
		wrapRestartTitleButtonBox.setAlignment(Pos.CENTER);
		wrapRestartTitleButtonBox.setSpacing(40);

		initGameOverTitle(this.wrapTitleBox);
		initRestartGameNotification(this.wrapRestartTitleButtonBox, root, scene, null);

		root.setTop(wrapTitleBox);
		root.setCenter(wrapRestartTitleButtonBox);
	}

	public final void initGameOverTitle(Pane parentBox) {
		MenuTitle title = new MenuTitle("GAME OVER");
		titleBox.setAlignment(Pos.BASELINE_CENTER);
		titleBox.getChildren().add(title);
		parentBox.getChildren().addAll(titleBox);
	}

	public final void initRestartGameNotification(Pane parentBox, BorderPane root, Scene scene, Room[][][] room) {
		MenuTitle restartTitle = new MenuTitle("Restart?");
		restartTitleBox.setAlignment(Pos.CENTER);
		restartTitleBox.getChildren().addAll(restartTitle);

		MenuItem declineButton = new MenuItem("No");
		declineButton.setOnMouseClicked(e -> {
			getChildren().clear();
			Menu menu = new Menu(root, scene);
			root.getChildren().addAll(menu);
		});

		gameOverData.addAll(declineButton);

		MenuItem acceptButton = new MenuItem("Yes");
		acceptButton.setOnMouseClicked(e -> {
			UserCursor loadCursor = new UserCursor();
			loadCursor.loadCursorIcons(0, scene);
			this.delay = new Delay();
			delay.setDelay(2000, () -> {
				root.getChildren().clear();
				Game game = new Game(root, scene);
				loadCursor.setCursor(scene);
				root.getChildren().addAll(game);
			});
		});

		gameOverData.addAll(acceptButton);

		buttonBox.getChildren().addAll(acceptButton, declineButton);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(80);
		parentBox.getChildren().addAll(this.restartTitleBox, buttonBox);
	}
}
