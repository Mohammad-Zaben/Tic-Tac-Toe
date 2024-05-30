import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Advanced extends BorderPane {
	// String computer = "O", computer = "X";
	Button[] ladder = new Button[9];
	private GridPane pane = new GridPane();
	private boolean Player_role;
	
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
	private final int MAX_Depth = 9;
	char computer = 'O', player = 'X';
	private TextArea textarea = new TextArea();
	private char board[][] = new char[3][3];
	private static int W = 0, L = 0, D = 0;
	private Button ok = new Button("ok");
	private Stage st;

	public Advanced(Stage stage, Scene scene, String name, int roule, int PlayerRound1) {
		playerScore = 0;
		compScore = 0;
		numRound = roule;
		this.PlayerRound = PlayerRound1;
		stage1 = stage;
		scene1 = scene;
		this.name = name;

		super.setStyle("-fx-background-color: #c1121f;");
		pane.setAlignment(Pos.CENTER);
		pane.setVgap(10);
		pane.setHgap(10);

		for (int i = 0; i < ladder.length; i++) {
			ladder[i] = new Button(" ");
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
		right.setPadding(new Insets(100, 10, 10, 10));
		right.setStyle("-fx-background-color: rgba(0, 0, 0, 0.6);");
		Label compl = new Label("Player Name: Computer ");
		CompScore = new Label("Player score: " + compScore);
		Label ComperCod = new Label("Computer code: O");

		compl.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		CompScore.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:white;");
		ComperCod.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;-fx-text-fill:white;");

		textarea.setMaxWidth(280);
		textarea.setMaxHeight(600);
		textarea.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;-fx-text-fill:black;");
		right.getChildren().addAll(compl, CompScore, ComperCod, textarea);

		setRight(right);

		DoGame();

	}

	private void DoGame() {

		if (PlayerRound != 1) {
			int randomNumber = random.nextInt(9);
			if (ladder[randomNumber].getText().equals(" ")) {
				ladder[randomNumber].setText("O");
				PlayerRound = 1;

			}
		}

		for (int i = 0; i < 9; i++) {
			int j = i;
			ladder[i].setOnAction(e -> {
				textarea.clear();
				Button btn = (Button) e.getSource();
				if (btn.getText() == " " && PlayerRound == 1) {
					btn.setText("X");
					int result = checkWinner();
					if (result > -2) {
						 st=null;
						if (result == 1) {
							st=newSence(name + " Is Win in this Round");
							palyerscorel.setText("Player score: " + playerScore);
						} else if (result == -1) {
							System.out.println("the computer result" + result);
							st=newSence("Computer Is Win in this Round");
							CompScore.setText("Player score: " + compScore);
						} else if (result == 0) {
							st=newSence("Draw this Round!!!!");
						}
						// Duration duration = Duration.seconds(1);
						// Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
						ok.setOnAction(ev -> {
							
							textarea.clear();
							for (Button button : ladder) {
								button.setOnAction(null);
								button.setText(" ");
							}

							if (count >= numRound) {
								 OutGame();
								return;
							} else {
								rounLabel.setText("Number of Round " + ++count + "/" + numRound);
								DoGame();
							}
                              st.close();
						});
						// timeline.play();
					}

					Move m = findBestMove(ladder);
					int move = convert2DTo1D(board, m.row, m.col);
					textarea.appendText("\n\nso the Computer select " + (move + 1));
					ladder[move].setText("O");

					result = checkWinner();
					if (result > -2) {
						st=null;
						if (result == 1) {
							st=newSence(name + " Is Win in this Round");
							palyerscorel.setText("Player score: " + playerScore);
						} else if (result == -1) {
							st=newSence("Computer Is Win in this Round");
							CompScore.setText("Player score: " + compScore);
						} else if (result == 0) {
							st=newSence("Draw this Round!!!!");
						}
						// Duration duration = Duration.seconds(1);
						// Timeline timeline = new Timeline(new KeyFrame(duration, event -> {
						ok.setOnAction(ev -> {
							textarea.clear();
							for (Button button : ladder) {
								button.setOnAction(null);
								button.setText(" ");
							}


							if (count >= numRound) {
								 OutGame();
								return;
							} else {
								rounLabel.setText("Number of Round " + ++count + "/" + numRound);
								DoGame();
							}

							st.close();
						});
						// timeline.play();
					}
				}
			});
		}
	}


	public int convert2DTo1D(char[][] array2D, int rowIndex, int colIndex) {
		int rows = array2D.length;
		int columns = array2D[0].length;
		System.out.println(rowIndex + "    " + rowIndex);

		if ((rowIndex < 3 && rowIndex > -1) && (colIndex < 3 && colIndex > -1))
			return rowIndex * columns + colIndex;

		return -1;
	}

	class Move {
		int row, col;
	};

	Move findBestMove(Button ladder[]) {
		int count = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				board[i][j] = ladder[count].getText().toCharArray()[0];
				count++;
			}

		int bestVal = -1000;
		Move bestMove = new Move();
		bestMove.row = -1;
		bestMove.col = -1;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				// Check if cell is empty
				if (board[i][j] == ' ') {
					// Make the move
					board[i][j] = computer;

					int boxNum = convert2DTo1D(board, i, j) + 1;
					textarea.appendText("\n\nthe box has Number " + boxNum + " :\n");
					System.out.println("the box has Number " + boxNum + " :\n");
					int moveVal = minimax(board, false);
					L = 0;
					W = 0;
					D = 0;

					// Undo the move
					board[i][j] = ' ';

					if (moveVal > bestVal) {
						bestMove.row = i;
						bestMove.col = j;
						bestVal = moveVal;
					}
				}
			}
		}

		System.out.printf("The value of the best Move " + "is : %d\n\n", bestVal);

		return bestMove;
	}

	int minimax(char board[][], Boolean isMax) {

		int score = evaluate(board);
		// If Maximizer has won the game
		// return his/her evaluated score
		if (score == 1) {
			if (W == 0) {
				textarea.appendText("Win\n ");
				W = 1;
			}
			return score;
		}
		// If Minimizer has won the game
		// return his/her evaluated score
		if (score == -1) {
			if (L == 0) {
				textarea.appendText("Loss\n ");
				L = 1;
			}
			return score;

		}
		// If there are no more moves and
		// no winner then it is a tie
		if (isMovesLeft(board) == false) {
			if (D == 0) {
				textarea.appendText("Draw\n ");
				D = 1;
			}
			return 0;

		}
		// If this maximizer's move
		if (isMax) {
			int best = -1000;

			// Traverse all cells
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					// Check if cell is empty
					if (board[i][j] == ' ') {
						// Make the move
						board[i][j] = computer;

						// the maximum value
						best = Math.max(best, minimax(board, !isMax));

						// Undo the move
						board[i][j] = ' ';
					}
				}
			}
			return best;
		}

		// If this minimizer's move
		else {
			int best = 1000;

			// Traverse all cells
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					// Check if cell is empty
					if (board[i][j] == ' ') {
						// Make the move
						board[i][j] = player;

						// Call minimax recursively and choose
						// the minimum value
						best = Math.min(best, minimax(board,!isMax));

						// Undo the move
						board[i][j] = ' ';
					}
				}
			}
			return best;
		}
	}

	int evaluate(char b[][]) {
		// Checking for Rows for X or O victory.
		for (int row = 0; row < 3; row++) {
			if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
				if (b[row][0] == computer)
					return 1;
				else if (b[row][0] == player)
					return -1;
			}
		}

		// Checking for Columns for X or O victory.
		for (int col = 0; col < 3; col++) {
			if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
				if (b[0][col] == computer)
					return 1;

				else if (b[0][col] == player)
					return -1;
			}
		}

		// Checking for Diagonals for X or O victory.
		if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
			if (b[0][0] == computer)
				return 1;
			else if (b[0][0] == player)
				return -1;
		}

		if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
			if (b[0][2] == computer)
				return 1;
			else if (b[0][2] == player)
				return -1;
		}

		// Else if none of them have won then return 0
		return 0;
	}

	Boolean isMovesLeft(char board[][]) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] == ' ')
					return true;
		return false;
	}

	public int checkWinner() {

		char board[][] = {
				{ ladder[0].getText().charAt(0), ladder[1].getText().charAt(0), ladder[2].getText().charAt(0) },
				{ ladder[3].getText().charAt(0), ladder[4].getText().charAt(0), ladder[5].getText().charAt(0) },
				{ ladder[6].getText().charAt(0), ladder[7].getText().charAt(0), ladder[8].getText().charAt(0) } };

		// Check rows and columns
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				System.out.println("first");
				return getWinnerValue(board[i][0]);
			}

			if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				System.out.println("second");

				return getWinnerValue(board[0][i]);
			}
		}

		// Check diagonals
		if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			System.out.println("therd");

			return getWinnerValue(board[0][0]);
		}

		if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			System.out.println("forth");

			return getWinnerValue(board[0][2]);
		}

		// Check for a draw
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					System.out.println("fif");

					return -2; // Game is not finished yet
				}
			}
		}
		System.out.println("batata");

		return 0; // It's a draw
	}

	private int getWinnerValue(char winner) {
		// return (winner == 'X') ? 1 : -1;
		if (winner == 'X') {
			playerScore++;
			return 1;
		} else {
			compScore++;
			return -1;
		}
	}

	private void OutGame() {

		// stage1.close();
		// stage1.setScene(scene1);
		// stage1.show();

		if (playerScore > compScore) {
			newSence(name + "Win in the Game");
		} else if (playerScore < compScore) {
			newSence("Computer Win in the Game");
		} else {
			newSence("Game is Draw");
		}
	}

	/*public void newSence(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Notification");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.show();
		
		alert.getButtonTypes().add(ok);

	}*/
	 public Stage newSence(String message) {
	        Stage stage = new Stage();
	        stage.setTitle("Notification");

	        Label label = new Label(message);
	        label.setStyle("-fx-font-size: 14px;"); // Optional: Adjust font size or other styling

	        VBox root = new VBox(label,ok);
	        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

	        Scene scene = new Scene(root, 300, 100);

	        stage.setScene(scene);
	        stage.show();
	        
	        return stage;
	    }

}
