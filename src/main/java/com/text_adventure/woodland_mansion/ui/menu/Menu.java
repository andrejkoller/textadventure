package com.text_adventure.woodland_mansion.ui.menu;

import com.text_adventure.woodland_mansion.game.Game;
import com.text_adventure.woodland_mansion.helper.Delay;
import com.text_adventure.woodland_mansion.ui.UserCursor;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Menu extends Pane {
	private VBox titleBox = new VBox();
	private AnchorPane versionAnchor = new AnchorPane();

	private HBox wrapTitleBox = new HBox();
	private HBox wrapMenuBox = new HBox();
	private VBox wrapCreditsBox = new VBox();
	private VBox wrapVersionBox = new VBox();
	private HBox wrapEveryCreditBox = new HBox();

	private VBox menuBox = new VBox();

	private VBox creditsProgrammerBox = new VBox();
	private VBox creditsProgramBox = new VBox();

	private VBox creditsDesignerBox = new VBox();
	private VBox creditsDesignBox = new VBox();

	private ObservableList<MenuButton> menuData = FXCollections.observableArrayList();
	private ObservableList<MenuButton> creditsProgrammerData = FXCollections.observableArrayList();
	private ObservableList<MenuButton> creditsDesignerData = FXCollections.observableArrayList();

	private Delay delay = new Delay();

	public Menu(BorderPane root, Scene scene) {
		wrapTitleBox.setAlignment(Pos.BASELINE_CENTER);
		wrapEveryCreditBox.setAlignment(Pos.CENTER);
		wrapMenuBox.setAlignment(Pos.CENTER);
		wrapVersionBox.setAlignment(Pos.BASELINE_RIGHT);

		initMenuTitle(wrapTitleBox);
		initMenuContent(wrapMenuBox, root, scene);
		initCreditsContent(wrapEveryCreditBox, root);
		initMenuVersion(wrapVersionBox);

		root.setTop(wrapTitleBox);
		root.setCenter(wrapMenuBox);
		root.setBottom(wrapVersionBox);
	}

	public void initMenuTitle(Pane parentBox) {
		MenuTitle title = new MenuTitle("Woodland Mansion");
		titleBox.setAlignment(Pos.BASELINE_CENTER);
		titleBox.getChildren().addAll(title);
		parentBox.getChildren().addAll(titleBox);
	}

	public void initMenuVersion(Pane parentBox) {
		MenuItem version = new MenuItem(new MenuButton("version 0.1"));
		AnchorPane.setBottomAnchor(version, 0.0);
		AnchorPane.setRightAnchor(version, 0.0);
		versionAnchor.getChildren().addAll(version);
		parentBox.getChildren().addAll(versionAnchor);
	}

	public void initMenuContent(Pane parentBox, BorderPane root, Scene scene) {
		var playButton = new MenuButton("Play");
		playButton.setOnMouseClicked(e -> {
			UserCursor loadCursor = new UserCursor();
			loadCursor.loadCursorIcons(0, scene);
			delay.setDelay(2000, () -> {
				root.getChildren().clear();
				Game game = new Game(root, scene);
				loadCursor.setCursor(scene);
				root.getChildren().addAll(game);
			});
		});

		menuData.addAll(playButton);

		var creditButton = new MenuButton("Credits");
		creditButton.setOnMouseClicked(e -> {
			root.setCenter(wrapEveryCreditBox);
		});

		menuData.addAll(creditButton);

		var exitButton = new MenuButton("Exit");
		exitButton.setOnMouseClicked(e -> {
			System.exit(0);
			Platform.exit();
		});

		menuData.addAll(exitButton);

		menuData.forEach(button -> {
			MenuItem menuItem = new MenuItem(button);
			menuBox.setSpacing(40);
			menuBox.setAlignment(Pos.CENTER);
			VBox.setVgrow(menuItem, Priority.NEVER);

			HBox wrapMenuBox = new HBox();
			wrapMenuBox.setAlignment(Pos.CENTER);
			wrapMenuBox.getChildren().addAll(menuItem);

			menuBox.getChildren().addAll(wrapMenuBox);
		});

		parentBox.getChildren().addAll(menuBox);
	}

	public void initCreditsContent(Pane parentBox, BorderPane root) {
		MenuTitle creditProgramTitle = new MenuTitle("Programmer");
		creditProgramTitle.setPadding(new Insets(0, 0, 15, 0));

		var andrejButton = new MenuButton("Andrej");
		creditsProgrammerData.addAll(andrejButton);

		var maxiButton = new MenuButton("Maxi");
		creditsProgrammerData.addAll(maxiButton);

		creditsProgrammerData.forEach(pButton -> {
			MenuItem creditProgramItem = new MenuItem(pButton);
			creditsProgrammerBox.setSpacing(20);
			creditsProgrammerBox.setAlignment(Pos.CENTER);
			VBox.setVgrow(creditProgramItem, Priority.NEVER);

			HBox wrapProgrammerBox = new HBox();
			wrapProgrammerBox.setAlignment(Pos.CENTER);
			wrapProgrammerBox.getChildren().addAll(creditProgramItem);

			creditsProgrammerBox.getChildren().addAll(wrapProgrammerBox);
		});

		creditsProgramBox.setSpacing(20);
		creditsProgramBox.setAlignment(Pos.CENTER);
		VBox.setVgrow(creditsProgramBox, Priority.NEVER);
		creditsProgramBox.getChildren().addAll(creditProgramTitle, creditsProgrammerBox);

		MenuTitle creditDesignTitle = new MenuTitle("Designer");
		creditDesignTitle.setPadding(new Insets(0, 0, 15, 0));

		var saschinkaButton = new MenuButton("Saschinka");
		creditsDesignerData.addAll(saschinkaButton);

		var backButton = new MenuButton("Back");

		backButton.setOnMouseClicked(e -> {
			root.setCenter(wrapMenuBox);
		});

		creditsDesignerData.addAll(backButton);

		creditsDesignerData.forEach(dButton -> {
			MenuItem creditDesignItem = new MenuItem(dButton);
			creditsDesignerBox.setSpacing(80);
			creditsDesignerBox.setAlignment(Pos.CENTER);
			VBox.setVgrow(creditDesignItem, Priority.NEVER);

			HBox wrapDesignerBox = new HBox();
			wrapDesignerBox.setAlignment(Pos.CENTER);
			wrapDesignerBox.getChildren().addAll(creditDesignItem);

			creditsDesignerBox.getChildren().addAll(wrapDesignerBox);
		});

		creditsDesignBox.setSpacing(20);
		creditsDesignBox.setAlignment(Pos.CENTER);
		VBox.setVgrow(creditsDesignBox, Priority.NEVER);
		creditsDesignBox.getChildren().addAll(creditDesignTitle, creditsDesignerBox);

		HBox wrapDesignBox = new HBox();
		wrapDesignBox.setAlignment(Pos.CENTER);
		wrapDesignBox.getChildren().addAll(creditsDesignBox);

		wrapCreditsBox.setSpacing(60);
		wrapCreditsBox.setAlignment(Pos.CENTER);
		wrapCreditsBox.getChildren().addAll(creditsProgramBox, wrapDesignBox);

		parentBox.getChildren().addAll(wrapCreditsBox);
	}
}