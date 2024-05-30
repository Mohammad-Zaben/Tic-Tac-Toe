
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
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

public class EasyPane extends BorderPane {

	Button[] ladder = new Button[9];
	private GridPane pane = new GridPane();
	private boolean Player_role;
	ArrayList<Integer> Player = new ArrayList<>();
	ArrayList<Integer> Computer = new ArrayList<>();
	int playerScore;
	int compScore;
	int numRound;
	private Random random = new Random();
	private int PlayerRound;
	private Stage stage1;
	private String name;
	private int count = 1;
	private VBox right;
	private VBox left;
	private Label namel;
	private Label plyyerCod;
	private Label palyerscorel;
	private Label CompScore;
	private Label rounLabel;
	private Scene scene1;

	public EasyPane(Stage stage, Scene scene, String name, int roule, int PlayerRound1) {
		playerScore = 0;
		compScore = 0;
		numRound = roule;
		this.PlayerRound = PlayerRound1;
		stage1 = stage;
		scene1 = scene;
		this.name = name;

		super.setStyle("-fx-background-color: #2b9348;");
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
		namel = new Label("Player Name: " + name);
		palyerscorel = new Label("Player score: " + playerScore);
		plyyerCod = new Label(name + " code: X");
		namel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		palyerscorel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		plyyerCod.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;-fx-text-fill:white;");

		left.getChildren().addAll(namel, palyerscorel, plyyerCod);
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
		Label compl = new Label("Player Name: Computer ");
		CompScore = new Label("Player score: " + compScore);
		Label ComperCod = new Label("Computer code: O");

		compl.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		CompScore.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		ComperCod.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;-fx-text-fill:white;");

		right.getChildren().addAll(compl, CompScore, ComperCod);

		setRight(right);

		DoGame();

	}

	private void DoGame() {
		if (PlayerRound != 1) {
			int randomNumber = random.nextInt(9);
			if (ladder[randomNumber].getText().equals("")) {
				ladder[randomNumber].setText("O");
				PlayerRound = 1;
				Computer.add(randomNumber + 1);

			}
		}

		for (int i = 0; i < ladder.length; i++) {
			int j = i;
			ladder[i].setOnAction(e -> {
				Button btn = (Button) e.getSource();
				if (btn.getText().equals("")) {
					if (PlayerRound == 1) {
						btn.setText("X");
						Player.add(j + 1);
						PlayerRound = 0;
					}
					String result = checkWinner();
					if (result.length() > 0) {
						if (result == "Player One Win") {
							newSence(name + " Is Win in this Round");
							palyerscorel.setText("Player score: " + ++playerScore);
						} else if (result == "Player Two Win") {
							newSence("Computer Is Win in this round");
							CompScore.setText("Player score: " + ++compScore);
						} else if (result == "CAT") {
							newSence("Draw this Round!!!!");
						}

						Duration duration = Duration.seconds(1);
						Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
							for (Button button : ladder) {
								button.setOnAction(null);
								button.setText("");
							}
							Player = new ArrayList<>();
							Computer = new ArrayList<>();
							
							int h1=(numRound/2)+1;
							if(playerScore==h1) {
								hscene();
							}
							int h2=(numRound/2)+1;
							if(compScore==h2) {
								hscene();
							}
							
							if (count >= numRound) {
								OutGame();
								return;
							} else {
								rounLabel.setText("Number of Round " + ++count + "/" + numRound);
								DoGame();
							}

						}));
						timeline.play();

					} else if (PlayerRound != 1) {
						while (true) {
							int randomNumber = random.nextInt(9);
							if (ladder[randomNumber].getText().equals("")) {
								ladder[randomNumber].setText("O");
								PlayerRound = 1;
								Computer.add(randomNumber + 1);
								result = checkWinner();
								if (result.length() > 0) {
									if (result == "Player One Win") {
										newSence(name + " Is Win in this Round");
										palyerscorel.setText("Player score: " + ++playerScore);
									} else if (result == "Player Two Win") {
										newSence("Computer Is Win in this Round");
										CompScore.setText("Player score: " + ++compScore);
									} else if (result == "CAT") {
										newSence("Draw this Round!!!!");
									}
									Duration duration = Duration.seconds(1);
									Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
										for (Button button : ladder) {
											button.setOnAction(null);
											button.setText("");
										}

										Player = new ArrayList<>();
										Computer = new ArrayList<>();
										
										int h1=(numRound/2)+1;
										if(playerScore==h1) {
											hscene();
										}
										int h2=(numRound/2)+1;
										if(compScore==h2) {
											hscene();
										}

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

								break;
							}
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
			if (Player.containsAll(l)) {
				return "Player One Win";

			} else if (Computer.containsAll(l)) {
				return "Player Two Win";

			} else if (Player.size() + Computer.size() == 9) {
				return "CAT";

			}
		}
		return "";
	}

	private void OutGame() {

		stage1.close();
		stage1.setScene(scene1);
		stage1.show();

		if (playerScore > compScore) {
			newSence(name + "Win in the Game");
		} else if (playerScore < compScore) {
			newSence("Computer Win in the Game");
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
	
	 public Stage hscene() {
		 Button yes=new Button("Yes");
		 Button no=new Button("No");
		 
	        Stage stage = new Stage();
	        stage.setTitle("Notification");

	        Label label = new Label("the player is Win,do you want contenu?");
	        label.setStyle("-fx-font-size: 14px;"); // Optional: Adjust font size or other styling

	        VBox root = new VBox(label,yes,no);
	        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

	        Scene scene = new Scene(root, 300, 100);

	        stage.setScene(scene);
	        stage.show();
	        
	        yes.setOnAction(e->{
	        	stage.close();
	        });
	        
	        no.setOnAction(e->{
	        	OutGame();
	        });
	        return stage;
	    }

}
