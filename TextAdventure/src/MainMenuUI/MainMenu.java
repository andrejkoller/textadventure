package MainMenuUI;

import java.util.Arrays;
import java.util.List;

import application.Game;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainMenu extends Pane {
	Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/PixelifySans-Regular.ttf"), 48);

	private Game game = new Game();

	private static final int WIDTH = 1920;
	private static final int HEIGHT = 1080;

	private MainTitle mainTitle;

	private MainMenuItem version;

	private MainMenuItem applyMenu;
	private MainMenuItem backMenu;

	private BorderPane menuLayout;

	private VBox vboxMenu;
	private VBox vboxCredits;

	private VBox menuBox = new VBox();
	private VBox creditBox = new VBox();;
	private HBox optionBox = new HBox();

	private List<String> menuData = Arrays.asList("Play", "Credits", "Exit");
	private List<String> creditData = Arrays.asList("Andrej", "Maxi");

	public MainMenu() {
		version = new MainMenuItem("version 0.1");
		version.setLayoutX(WIDTH - 250);
		version.setLayoutY(HEIGHT / 1);

		mainTitle = new MainTitle("Woodland Mansion");
		mainTitle.setLayoutX((WIDTH / 2) - 250);
		mainTitle.setLayoutY(50);

		vboxMenu = createMenuContent();
		vboxCredits = createCreditsContent();

		menuLayout = new BorderPane();
		menuLayout.setCenter(vboxMenu);
		menuLayout.setLayoutX((WIDTH / 2) - 50);
		menuLayout.setLayoutY(HEIGHT / 2.5);
		getChildren().addAll(menuLayout, mainTitle, version);
	}

	// ADD ITEMS TO CONTENT
	public MainMenuItem addApplyButton() {
		MainMenuItem apply = new MainMenuItem("Apply");
		apply.setMaxHeight(0);
		apply.setMaxWidth(0);
		apply.setStyle("-fx-cursor: hand;");
		apply.setOnMouseClicked(e -> switchPanes(vboxMenu));

		return apply;
	}

	public MainMenuItem addBackButton() {
		MainMenuItem back = new MainMenuItem("Back");
		back.setMaxHeight(0);
		back.setMaxWidth(0);
		back.setStyle("-fx-cursor: hand;");
		back.setOnMouseClicked(e -> switchPanes(vboxMenu));

		return back;
	}

	public VBox addMainMenuItems() {
		menuData.forEach(data -> {
			MainMenuItem item = new MainMenuItem(data);
			item.setOnMouseClicked(e -> {
				if (data == "Play") {
					getChildren().clear();
					getChildren().add(game);
				} else if (data == "Credits") {
					switchPanes(vboxCredits);
				} else if (data == "Exit") {
					System.exit(0);
					Platform.exit();
				}
			});
			item.setStyle("-fx-cursor: hand;");
			item.setMaxHeight(0);
			item.setMaxWidth(0);
			VBox.setVgrow(item, Priority.ALWAYS);
			menuBox.setSpacing(80);
			menuBox.getChildren().addAll(item);
		});

		return menuBox;
	}

	public VBox addMainCreditItems() {
		creditData.forEach(data -> {
			MainMenuItem creditItem = new MainMenuItem(data);
			creditItem.setMaxHeight(0);
			creditItem.setMaxWidth(0);
			creditBox.setSpacing(80);
			creditBox.getChildren().addAll(creditItem);
		});

		return creditBox;
	}

	public HBox addOptionChooseMenu() {
		backMenu = addBackButton();
		applyMenu = addApplyButton();

		optionBox = new HBox(backMenu, applyMenu);
		optionBox.setSpacing(200);

		return optionBox;
	}

	public MainMenuItem addChooseFirstAdventureBox() {
		MainMenuItem firstAdventureItem = new MainMenuItem("Retrieval of Memory");
		firstAdventureItem.setMaxHeight(0);
		firstAdventureItem.setMaxWidth(0);
		firstAdventureItem.setStyle("-fx-cursor: hand;");

		firstAdventureItem.setOnMouseClicked(e -> {
			getChildren().clear();
			getChildren().add(game);
		});

		return firstAdventureItem;
	}

	// CREATE CONTENT
	public VBox createMenuContent() {
		menuBox = addMainMenuItems();

		vboxMenu = new VBox(menuBox);
		vboxMenu.setSpacing(80);

		return vboxMenu;
	}

	public VBox createCreditsContent() {

		creditBox = addMainCreditItems();
		backMenu = addBackButton();

		vboxCredits = new VBox(creditBox, backMenu);
		vboxCredits.setSpacing(120);

		return vboxCredits;
	}

	// SWITCH VIEWS
	public void switchPanes(Pane pane) {
		menuLayout.setCenter(pane);
	}
}
