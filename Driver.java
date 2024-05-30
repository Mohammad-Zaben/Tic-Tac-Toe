import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

public class Driver extends Application {

	private BorderPane root;
	private VBox gametype;
	private VBox EasyType;
	private VBox MultiPlayerType;
	private VBox AdvancedType;
	private Button Easystartbtn;
	private Spinner<Integer> Easyspinner;
	private TextField EasyNameTextField;
	private TextField firstPlayer;
	private TextField secondPlayer;
	private Button Easy;
	private Button MultiPlayer;
	private Button Advanced;
	private RadioButton ur;
	private RadioButton cr;
	private RadioButton r1, r2, r3;

	@Override
	public void start(Stage stage) throws Exception {
		displayImage(stage);

		Duration duration = Duration.seconds(2);
		Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
			stage.close();

			root = new BorderPane();
			root.setStyle("-fx-background-image: url('file:///C:/java-2/Algorithm_project4/wp8984736.jpg');"
					+ "-fx-background-size:cover;-fx-background-position: center center;");
			doTypePane();

			Scene scene = new Scene(root);
			Easy.setOnAction(e -> {
				DoEasyType();
				root.setCenter(EasyType);
				Easystartbtn.setOnAction(e1 -> {
					if (!EasyNameTextField.getText().toString().equals(""))
						EasyBtn(stage, scene);
				});
			});

			MultiPlayer.setOnAction(e -> {
				DoMultiPlayer();
				root.setCenter(MultiPlayerType);

				Easystartbtn.setOnAction(e1 -> {
					if (!firstPlayer.getText().toString().equals("") && !secondPlayer.getText().toString().equals(""))
						multBtn(stage, scene);
				});
			});

			Advanced.setOnAction(e -> {
				DoAdvType();
				root.setCenter(AdvancedType);
				Easystartbtn.setOnAction(e1 -> {
					if (!EasyNameTextField.getText().toString().equals(""))
						AdvBtn(stage, scene);
				});
			});

			stage.setScene(scene);
			stage.show();

		}));

		timeline.play();
	}

	private void doTypePane() {
		ToggleGroup toggleGroup = new ToggleGroup();
		gametype = new VBox();
		gametype.setPadding(new Insets(200, 30, 30, 30));
		gametype.setSpacing(40);
		gametype.setStyle("-fx-background-color: rgba(0,0, 0, 0.8);");

		Label l = new Label("Choose the game type: ");
		l.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		r1 = new RadioButton("Easy");
		r1.setToggleGroup(toggleGroup);
		r1.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		r2 = new RadioButton("MultiPlayer");
		r2.setToggleGroup(toggleGroup);
		r2.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		r3 = new RadioButton("Advanced");
		r3.setToggleGroup(toggleGroup);
		r3.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		r1.setSelected(true);
		Easy = new Button("Easy");
		Easy.setStyle("-fx-background-radius: 15;-fx-background-color: #f9f7f3; -fx-text-fill: black;"
				+ " -fx-font-family: Arial; -fx-font-size: 18;-fx-font-weight: bold; -fx-padding: 20px 40px;");
		MultiPlayer = new Button("MultiPlayer");
		MultiPlayer.setStyle("-fx-background-radius: 15;-fx-background-color: #f9f7f3; -fx-text-fill: black;"
				+ " -fx-font-family: Arial; -fx-font-size: 18;-fx-font-weight: bold; -fx-padding: 20px 40px;");
		Advanced = new Button("Advanced");
		Advanced.setStyle("-fx-background-radius: 15;-fx-background-color: #f9f7f3; -fx-text-fill: black;"
				+ " -fx-font-family: Arial; -fx-font-size: 18;-fx-font-weight: bold; -fx-padding: 20px 40px;");

		gametype.getChildren().addAll(l, Easy, MultiPlayer, Advanced);

		root.setLeft(gametype);

	}

	private void DoEasyType() {
		EasyType = new VBox();
		EasyType.setStyle("-fx-background-color: rgba(0,0, 0, 0.4);");
		EasyType.setPadding(new Insets(0, 300, 0, 300));
		EasyType.setAlignment(Pos.CENTER);
		EasyType.setSpacing(40);

		EasyNameTextField = new TextField();
		EasyNameTextField.setPromptText("Player Name");
		EasyNameTextField.setStyle("-fx-background-radius: 10;-fx-font-size: 14; -fx-background-color: #f0efeb;"
				+ " -fx-text-fill: #333333;-fx-font-family: Arial; -fx-font-size: 14;-fx-font-weight: bold;");
		EasyNameTextField.setPrefWidth(200);
		EasyNameTextField.setPrefHeight(40);

		ToggleGroup toggleGroup = new ToggleGroup();
		ur = new RadioButton("Player");
		ur.setToggleGroup(toggleGroup);
		ur.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;");
		ur.setSelected(true);

		cr = new RadioButton("Computer");
		cr.setToggleGroup(toggleGroup);
		cr.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;");

		Label l2 = new Label("Who start firs:");
		l2.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;"
				+ "-fx-background-color: rgba(255, 255, 255, 0.6);");

		HBox radioPane = new HBox();
		radioPane.setSpacing(20);
		radioPane.getChildren().addAll(l2, ur, cr);

		Label l = new Label("Number of rounds: ");
		l.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;"
				+ "-fx-background-color: rgba(255, 255, 255, 0.6);");

		Easyspinner = new Spinner<>(1, 100, 1);
		Easyspinner.setEditable(false);
		Easyspinner.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		HBox roundPane = new HBox();
		roundPane.setSpacing(20);
		roundPane.getChildren().addAll(l, Easyspinner);

		Easystartbtn = new Button("Start");
		Easystartbtn.setStyle("-fx-background-radius: 15;-fx-background-color: #f9f7f3; -fx-text-fill: black;"
				+ " -fx-font-family: Arial; -fx-font-size: 18;-fx-font-weight: bold; -fx-padding: 20px 40px;");

		EasyType.getChildren().addAll(EasyNameTextField, radioPane, roundPane, Easystartbtn);

		// root.setCenter(EasyType);

	}

	private void DoMultiPlayer() {
		MultiPlayerType = new VBox();
		MultiPlayerType.setStyle("-fx-background-color: rgba(0,0, 0, 0.4);");
		MultiPlayerType.setPadding(new Insets(0, 300, 0, 300));
		MultiPlayerType.setAlignment(Pos.CENTER);
		MultiPlayerType.setSpacing(40);

		firstPlayer = new TextField();
		firstPlayer.setPromptText("Player 1 Name");
		firstPlayer.setStyle("-fx-background-radius: 10;-fx-font-size: 14; -fx-background-color: #f0efeb;"
				+ " -fx-text-fill: #333333;-fx-font-family: Arial; -fx-font-size: 14;-fx-font-weight: bold;");
		firstPlayer.setPrefWidth(200);
		firstPlayer.setPrefHeight(40);

		secondPlayer = new TextField();
		secondPlayer.setPromptText("Player 2 Name");
		secondPlayer.setStyle("-fx-background-radius: 10;-fx-font-size: 14; -fx-background-color: #f0efeb;"
				+ " -fx-text-fill: #333333;-fx-font-family: Arial; -fx-font-size: 14;-fx-font-weight: bold;");
		secondPlayer.setPrefWidth(200);
		secondPlayer.setPrefHeight(40);

		ToggleGroup toggleGroup = new ToggleGroup();
		ur = new RadioButton("Player 1");
		ur.setToggleGroup(toggleGroup);
		ur.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;");
		ur.setSelected(true);

		cr = new RadioButton("Player 2");
		cr.setToggleGroup(toggleGroup);
		cr.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;");

		Label l2 = new Label("Who start firs:");
		l2.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;"
				+ "-fx-background-color: rgba(255, 255, 255, 0.6);");

		HBox radioPane = new HBox();
		radioPane.setSpacing(20);
		radioPane.getChildren().addAll(l2, ur, cr);

		Label l = new Label("Number of rounds: ");
		l.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;"
				+ "-fx-background-color: rgba(255, 255, 255, 0.6);");

		Easyspinner = new Spinner<>(1, 100, 1);
		Easyspinner.setEditable(false);
		Easyspinner.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		HBox roundPane = new HBox();
		roundPane.setSpacing(20);
		roundPane.getChildren().addAll(l, Easyspinner);

		Easystartbtn = new Button("Start");
		Easystartbtn.setStyle("-fx-background-radius: 15;-fx-background-color: #f9f7f3; -fx-text-fill: black;"
				+ " -fx-font-family: Arial; -fx-font-size: 18;-fx-font-weight: bold; -fx-padding: 20px 40px;");

		MultiPlayerType.getChildren().addAll(firstPlayer, secondPlayer, radioPane, roundPane, Easystartbtn);

		// root.setCenter(MultiPlayerType);

	}

	private void DoAdvType() {
		AdvancedType = new VBox();
		AdvancedType.setStyle("-fx-background-color: rgba(0,0, 0, 0.4);");
		AdvancedType.setPadding(new Insets(0, 300, 0, 300));
		AdvancedType.setAlignment(Pos.CENTER);
		AdvancedType.setSpacing(40);

		EasyNameTextField = new TextField();
		EasyNameTextField.setPromptText("Player Name");
		EasyNameTextField.setStyle("-fx-background-radius: 10;-fx-font-size: 14; -fx-background-color: #f0efeb;"
				+ " -fx-text-fill: #333333;-fx-font-family: Arial; -fx-font-size: 14;-fx-font-weight: bold;");
		EasyNameTextField.setPrefWidth(200);
		EasyNameTextField.setPrefHeight(40);

		ToggleGroup toggleGroup = new ToggleGroup();
		ur = new RadioButton("Player");
		ur.setToggleGroup(toggleGroup);
		ur.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;");
		ur.setSelected(true);

		cr = new RadioButton("Computer");
		cr.setToggleGroup(toggleGroup);
		cr.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;");

		Label l2 = new Label("Who start firs:");
		l2.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;"
				+ "-fx-background-color: rgba(255, 255, 255, 0.6);");

		HBox radioPane = new HBox();
		radioPane.setSpacing(20);
		radioPane.getChildren().addAll(l2, ur, cr);

		Label l = new Label("Number of rounds: ");
		l.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;"
				+ "-fx-background-color: rgba(255, 255, 255, 0.6);");

		Easyspinner = new Spinner<>(1, 100, 1);
		Easyspinner.setEditable(false);
		Easyspinner.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

		HBox roundPane = new HBox();
		roundPane.setSpacing(20);
		roundPane.getChildren().addAll(l, Easyspinner);

		Easystartbtn = new Button("Start");
		Easystartbtn.setStyle("-fx-background-radius: 15;-fx-background-color: #f9f7f3; -fx-text-fill: black;"
				+ " -fx-font-family: Arial; -fx-font-size: 18;-fx-font-weight: bold; -fx-padding: 20px 40px;");

		AdvancedType.getChildren().addAll(EasyNameTextField, radioPane, roundPane, Easystartbtn);

		// root.setCenter(EasyType);

	}

	private void displayImage(Stage stage) {

		StackPane root = new StackPane();
		root.setStyle("-fx-background-image: url('file:///C:/java-2/Algorithm_project4/hB3n_z.gif');" // set

				+ "-fx-background-size:cover;-fx-background-position: center center;");
		Scene scene = new Scene(root, 400, 400);

		stage.setTitle("Image Display Example");
		stage.setScene(scene);
		stage.setMaximized(true);

		stage.show();

	}

	private void EasyBtn(Stage stage, Scene scene) {
		stage.setMaximized(true);
		int flag = 0;
		if (ur.isSelected())
			flag = 1;
		EasyPane Epane = new EasyPane(stage, scene, EasyNameTextField.getText().toString(), Easyspinner.getValue(),
				flag);

		Scene scene1 = new Scene(Epane, 600, 600);

		stage.close();
		stage.setScene(scene1);
		stage.show();
	}

	private void multBtn(Stage stage, Scene scene) {
		stage.setMaximized(true);
		int flag = 0;
		if (ur.isSelected())
			flag = 1;
		MultyPane Epane = new MultyPane(stage, scene, firstPlayer.getText().toString(),
				secondPlayer.getText().toString(), Easyspinner.getValue(), flag);// new

		Scene scene1 = new Scene(Epane, 600, 600);

		stage.close();
		stage.setScene(scene1);
		stage.show();
	}

	private void AdvBtn(Stage stage, Scene scene) {
		stage.setMaximized(true);
		int flag = 0;
		if (ur.isSelected())
			flag = 1;
		
		Advanced Epane = new Advanced(stage, scene, EasyNameTextField.getText().toString(), Easyspinner.getValue(),
				flag);

		Scene scene1 = new Scene(Epane, 600, 600);

		stage.close();
		stage.setScene(scene1);
		stage.show();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
