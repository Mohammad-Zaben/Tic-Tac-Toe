import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MultyPane extends BorderPane {

	Button[] ladder = new Button[9];
	private GridPane pane = new GridPane();
	private boolean Player_role;
	ArrayList<Integer> Player1 = new ArrayList<>();
	ArrayList<Integer> Player2 = new ArrayList<>();
	int player1score;
	int player2score;
	int numRound;
	private Random random = new Random();
	private int player1Round;
	private Stage stage1;
	private String name1;
	private String name2;
	private int count = 1;
	private VBox right;
	private VBox left;
	private Label name1Label;
	private Label name2Label;
	private Label player1Code;
	private Label Player2Code;
	private Label player1LabelSocre;
	private Label player2ScoreLabel;
	private Label rounLabel;
	private Scene scene1;

	public MultyPane(Stage stage, Scene scene, String name1, String name2, int numround, int PlayerRound1) {
		player1score = 0;
		player2score = 0;
		numRound = numround;
		this.player1Round = PlayerRound1;
		stage1 = stage;
		scene1 = scene;
		this.name1 = name1;
		this.name2 = name2;
		super.setStyle("-fx-background-color: #1565c0;");
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(10);
		pane.setHgap(10);

		for (int i = 0; i < ladder.length; i++) {
			ladder[i] = new Button();
			ladder[i].setStyle("-fx-background-color: #ffffff;");
			ladder[i].setPrefSize(150, 150);
			ladder[i].setFont(Font.font("Verdana", FontWeight.BOLD, 70));
		}
		pane.addRow(0, ladder[0], ladder[1], ladder[2]);
		pane.addRow(1, ladder[3], ladder[4], ladder[5]);
		pane.addRow(2, ladder[6], ladder[7], ladder[8]);

		setCenter(pane);

		left = new VBox();
		left.setSpacing(40);
		left.setPadding(new Insets(100, 10, 100, 10));
		left.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");
		name1Label = new Label("Player1 Name: " + name1);
		player1LabelSocre = new Label("Player1 score: " + player1score);
		player1Code = new Label(name1 + " code: X");
		name1Label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		player1LabelSocre.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		player1Code.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;-fx-text-fill:white;");

		left.getChildren().addAll(name1Label, player1LabelSocre, player1Code);
		setLeft(left);

		HBox buttom = new HBox();
		buttom.setPadding(new Insets(10, 10, 10, 10));
		buttom.setSpacing(1100);
		Button existbtn = new Button("Exit");
		existbtn.setStyle("-fx-background-radius: 15;-fx-background-color: #f9f7f3; -fx-text-fill: black;"
				+ " -fx-font-family: Arial; -fx-font-size: 18;-fx-font-weight: bold; -fx-padding: 20px 40px;");
		existbtn.setOnAction(e -> {
			stage.close();
			stage.setScene(scene);
			stage.show();
		});

		rounLabel = new Label("Number of Round 1/" + numRound);
		rounLabel.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;-fx-text-fill:white;");

		buttom.getChildren().addAll(existbtn, rounLabel);
		setBottom(buttom);

		right = new VBox();
		right.setSpacing(40);
		right.setPadding(new Insets(100, 10, 100, 10));
		right.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");
		name2Label = new Label("Player1 Name: " + name2);
		player2ScoreLabel = new Label("Player1 score: " + player2score);
		Player2Code = new Label("Player2 code: O");

		name2Label.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		player2ScoreLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		Player2Code.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;-fx-text-fill:white;");

		right.getChildren().addAll(name2Label, player2ScoreLabel, Player2Code);

		setRight(right);

		DoGame();

	}

	private void DoGame() {

		for (int i = 0; i < ladder.length; i++) {
			int j = i;
			ladder[i].setOnAction(e -> {
				Button btn = (Button) e.getSource();
				if (btn.getText().equals("")) {
					if (player1Round == 1) {
						btn.setText("X");
						Player1.add(j + 1);
						player1Round = 0;

						String result = checkWinner();
						if (result.length() > 0) {
							if (result == "Player One Win") {
								newSence(name1 + " Is Win in this Round");
								player1LabelSocre.setText("Player score: " + ++player1score);
							} else if (result == "Player Two Win") {
								newSence(name2 + " Is Win in this Round");
								player2ScoreLabel.setText("Player score: " + ++player2score);
							} else if (result == "CAT") {
								newSence("Draw this Round!!!!");
							}
							for (Button button : ladder) {
								button.setOnAction(null);
								button.setText("");
							}
							Duration duration = Duration.seconds(1);
							Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
								for (Button button : ladder) {
									button.setOnAction(null);
									button.setText("");
								}

								Player1 = new ArrayList<>();
								Player2 = new ArrayList<>();

								if (count >= numRound) {
									OutGame();
									return;
								} else {
									rounLabel.setText("Number of Round " + ++count + "/" + numRound);
									DoGame();
								}

							}));
							timeline.play();
						}
					} else if (player1Round != 1) {
						btn.setText("O");
						Player2.add(j + 1);
						player1Round = 1;

						String result = checkWinner();
						if (result.length() > 0) {
							if (result == "Player One Win") {
								newSence(name1 + " Is Win in this Round");
								player1LabelSocre.setText("Player score: " + ++player1score);
							} else if (result == "Player Two Win") {
								newSence(name2 + " Is Win in this Round");
								player2ScoreLabel.setText("Player score: " + ++player2score);
							} else if (result == "CAT") {
								newSence("Draw this Round!!!!");
							}
							for (Button button : ladder) {
								button.setOnAction(null);
								button.setText("");
							}
							Duration duration = Duration.seconds(1);
							Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
								for (Button button : ladder) {
									button.setOnAction(null);
									button.setText("");
								}

								Player1 = new ArrayList<>();
								Player2 = new ArrayList<>();

								if (count >= numRound) {
									OutGame();
									return;
								} else {
									rounLabel.setText("Number of Round " + ++count + "/" + numRound);
									DoGame();
								}

							}));
							timeline.play();
						}
					}
				}
			});
		}
	}

	public String checkWinner() {
		List TopRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midcol = Arrays.asList(2, 5, 8);
		List rightcol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(7, 5, 3);

		List<List> Winning = new ArrayList<List>();
		Winning.add(TopRow);
		Winning.add(midRow);
		Winning.add(botRow);
		Winning.add(leftCol);
		Winning.add(midcol);
		Winning.add(rightcol);
		Winning.add(cross1);
		Winning.add(cross2);

		for (List l : Winning) {
			if (Player1.containsAll(l)) {
				return "Player One Win";

			} else if (Player2.containsAll(l)) {
				return "Player Two Win";

			} else if (Player1.size() + Player2.size() == 9) {
				return "CAT";

			}
		}
		return "";
	}

	private void OutGame() {

		stage1.close();
		stage1.setScene(scene1);
		stage1.show();

		if (player1score > player2score) {
			newSence(name1 + "Win in the Game");
		} else if (player1score < player2score) {
			newSence("Player2 Win in the Game");
		} else {
			newSence("Game is Draw");
		}
	}

	public void newSence(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notification");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();

	}

}
