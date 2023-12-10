                                    // * Asa Raqymdy erekşe meiırımdı Allanyñ atymen bastaimyn
import java.util.*;
import javafx.scene.control.Toggle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.DARKGREEN;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.BLUE;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.application.Platform;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image; // vnes
import javafx.scene.control.Dialog;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.VBox;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
public class SnakeGame extends Application {
    private boolean ended;
    private boolean bet = false;
    private List<lvl2> runningtntshka = new ArrayList<>();
    private int lvl = 0;
    private List<prepi> tnts = new ArrayList<>();
    private AnimationTimer animationTimer;
    private Scene mainMenuScene;
    private int scount = 0;
    private int counter  = 0;
    private static final int ekranW = 20; 
    private static final int ekranH = 20; 
    private static final int SIZE = 20; 
    private int SPEED = 5;
    private int score = 0;
    private int th = 0;
    private ArrayList<Integer> records = new ArrayList<>();
    private int maxres = 0;
    private boolean gameOverFlag = false;
    private Deque<cord> snake = new LinkedList<>();
    public Direction direction = Direction.R; 
    private Image iImage;
    private cord food; 
    private long keys = 0;
    private long cycleco = 0;
    private boolean gameOver = false; 
    private boolean gamePaused = false;
    private long levels  = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
         Hyperlink aboutLink = new Hyperlink("About Project");aboutLink.setOnAction(event -> readme());
        iImage = new Image("tnt.png");
        Button playButton = new Button("Play");
        Button changeLevel = new Button("Change  Level");
        Button backTheme = new Button("Switch Theme");
        backTheme.setPrefSize(100,40);
        backTheme.setStyle("-fx-background-color: purple; -fx-text-fill: white; -fx-font-size: 14; -fx-font-family: Calibri;");
        Button exitButton = new Button("Exit");
        playButton.setPrefSize(100, 40);
        changeLevel.setPrefSize(100, 40);
        exitButton.setPrefSize(100, 40);
        playButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        changeLevel.setStyle("-fx-font-size: 14;");
        exitButton.setStyle("-fx-background-color: red; -fx-text-fill:white; -fx-font-size: 14;");
        playButton.setOnAction(e -> startGame(primaryStage));
        changeLevel.setOnAction(e -> changelvl(primaryStage));
        exitButton.setOnAction(e -> primaryStage.close());
        backTheme.setOnAction(e -> switchtheme(primaryStage));

         VBox mB = new VBox(playButton, changeLevel, backTheme, exitButton, aboutLink);
        mB.setSpacing(10);
        mB.setStyle("-fx-background-color: lightgray; -fx-padding: 10px;");
        mainMenuScene = new Scene(mB, 300, 200);

        primaryStage.setScene(mainMenuScene);
        primaryStage.setTitle("Snake Game");
        primaryStage.show();
        scount++;
    }
    private void readme(){
        System.out.print("readmecreated");
        String ss = "https://mukhametzhan-dev.github.io/";
        getHostServices().showDocument(ss);
    }
    private void switchtheme(Stage primaryStage) {

        Scene currentScene = primaryStage.getScene();
        String imagePath = getClass().getResource("chess.jpg").toExternalForm();

    currentScene.getRoot().setStyle(
        "-fx-background-image: url('" + imagePath + "');" +
        "-fx-background-repeat: stretch;" +
        "-fx-background-size: cover;"
    );

        //currentScene.getStylesheets().add(getClass().getResource("temka.css").toExternalForm());
    }
   
            private void comp(Direction a , Direction b){
                levels++;
                System.out.println(levels); //chislo level
            if(direction != b){
                direction = a;
            }
        }
        private void tpause(){
            scount++;
            System.out.println(scount);
            if(!gamePaused){gamePaused=true;} 
            else{gamePaused=false;}
            System.out.print("");

        }
    private void startGame(Stage primaryStage){

        if(animationTimer != null){
            animationTimer.stop();

            System.out.println(scount);
        }

        Canvas canvas = new Canvas(ekranW * SIZE, ekranH * SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane(canvas);

        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root);
        if(th == 1){

            Scene currentScene = primaryStage.getScene();
            //currentScene.getStylesheets().add(getClass().getResource("temka.css").toExternalForm());
        }
        scene.setOnKeyPressed(event -> {
    KeyCode keyCode = event.getCode();
    keys++; // debuuug
    switch (keyCode) {
            case W:
            comp(Direction.U, Direction.D);
            break;
        case S:
            comp(Direction.D, Direction.U);
            break;
        case A:
            comp(Direction.L, Direction.R);
            break;
        case D:
            comp(Direction.R, Direction.L);
            break;
        case P:
            tpause();
            break;

        default:
            break;
    }
});


primaryStage.setScene(scene);
        cycleco++;
        primaryStage.setTitle("ZHYLAN");
        primaryStage.setResizable(false);
        primaryStage.show();

        gm();

animationTimer = new AnimationTimer() {
    private long lastUpdateTime;

    @Override
    public void handle(long now) {
        if (now - lastUpdateTime >= 1_000_000_000 / SPEED) { 
            lastUpdateTime = now;
            System.out.println("updated");
            System.out.println("seted");
            System.out.println("checked collizom");
            if (!gameOver && !gamePaused) {
                move(primaryStage);
                checkCollision(primaryStage);
                if(lvl ==3) {colwithobs(primaryStage);}
                if(lvl == 2){trans();Colwithrunning(primaryStage);}
                display(gc);
                showScore(gc);
                showMax(gc);
            }
        }
    }
};
animationTimer.start();  


    }
private void changelvl(Stage primaryStage) {
    Dialog<Void> dialog = new Dialog<>();
    dialog.setTitle("Change Level");
    dialog.setHeaderText("Choose Level Options");

    RadioButton easyRadioButton = new RadioButton("Easy (Default Level)");
    RadioButton borderDeathRadioButton = new RadioButton("Snake has two moving blocks ");
    RadioButton isRadioButton = new RadioButton("Board has TNTs)");

    ToggleGroup toggleGroup = new ToggleGroup();
    easyRadioButton.setToggleGroup(toggleGroup);
    borderDeathRadioButton.setToggleGroup(toggleGroup);
    isRadioButton.setToggleGroup(toggleGroup);

    VBox vbox = new VBox(easyRadioButton, borderDeathRadioButton, isRadioButton);
    dialog.getDialogPane().setContent(vbox);

    ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
    ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
    dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

    dialog.setResultConverter(buttonType -> {
        if (buttonType == okButtonType) {
            applyLevelOptions(easyRadioButton, borderDeathRadioButton, isRadioButton);
        }
        return null;
    });

    dialog.showAndWait();
}




    private void applyLevelOptions(RadioButton easyRadioButton, RadioButton borderDeathRadioButton, RadioButton isRadioButton) {
    if (isRadioButton.isSelected()) {
        lvl = 3;
    } else if (borderDeathRadioButton.isSelected()) {
        lvl = 2;
    } else {
        lvl = 1; 
    }
}

      private void createTnts() {

            tnts.add(new prepi(5, 5));
            tnts.add(new prepi(5, 6));
            tnts.add(new prepi(6, 5));
            tnts.add(new prepi(6, 6));

            tnts.add(new prepi(15, 15));
            tnts.add(new prepi(15, 16));
            tnts.add(new prepi(16, 15));
            tnts.add(new prepi(16, 16));


    }


    
    private void showScore(GraphicsContext gc) {
        gc.setFill(BLACK);
        gc.fillText("Score: " + (snake.size() - 1 ), 10, 20); 
    }
    private void showMax(GraphicsContext gc){
        gc.setFill(BLACK);
        gc.fillText("Max score: " + (maxres),20,40);
    }
    private void gm() {
        snake.clear();
        snake.add(new cord(ekranW / 2, ekranH / 2));
        snake.add(new cord(ekranW / 2 - 1, ekranH / 2));
        snake.add(new cord(ekranW / 2 - 2, ekranH / 2));

        gf(); 
        gameOver = false;
        gamePaused = false;
        if(lvl == 3){
        createTnts();

    }
    if (lvl == 2) {
        createrunningtnt();
        counter++;
    }}


   private void move(Stage primaryStage) {
    cord headLocation = snake.getFirst();
    cord newHead = null;
    switch (direction) {
        case U:
            newHead = new cord(headLocation.getX(), (headLocation.getY() - 1 + ekranH) % ekranH);
            break;
        case D:
            newHead = new cord(headLocation.getX(), (headLocation.getY() + 1) % ekranH);
            break;
        case L:
            newHead = new cord((headLocation.getX() - 1 + ekranW) % ekranW, headLocation.getY());
            break;
        case R:
            newHead = new cord((headLocation.getX() + 1) % ekranW, headLocation.getY());
            break;
        default:
            break;
    }

    if (snake.contains(newHead)) {
        gameOver = true;
        showGameOverDialog(primaryStage,mainMenuScene);
        return;
    }

    snake.addFirst(newHead);
    if (newHead.equals(food)) {
        SPEED++;
        gf();
    } else {
        snake.removeLast();
    }
}


private void checkCollision(Stage primaryStage) {
    cord headLocation = snake.getFirst();
    Iterator<cord> iterator = snake.iterator();

    while (iterator.hasNext()) {
        cord point = iterator.next();
        if (point != headLocation && point.equals(headLocation)) {
            gameOver = true;
            showGameOverDialog(primaryStage,mainMenuScene);
            break;

        }
    }
}
private void gf() {
    boolean validPosition;
    int x, y;
    Random random = new Random();

    do {

        validPosition = true;
        x = random.nextInt(ekranW);
        y = random.nextInt(ekranH);
        if(lvl == 3){
             for (prepi i : tnts) {
            if (i.getX() <= x && x <= i.getX() + 1 &&
                i.getY() <= y && y <= i.getY() + 1) {
                validPosition = false;
                break;
            }
        }
        }

        // cordinate find ponit

        for (cord point : snake) {
            if (point.getX() == x && point.getY() == y) {
                validPosition = false;
                break;
            }
        }
    } while (!validPosition);

    food = new cord(x, y);
}
private void createrunningtnt() {
    runningtntshka.add(new lvl2(5, 5, 1, 0)); 
    runningtntshka.add(new lvl2(10, 15, 0, -1)); 

}
private void trans() {

    for (lvl2 i : runningtntshka) {
        i.move();
        System.out.print("moved ok");
        //move
    }
}
private void Colwithrunning(Stage primaryStage) {
    cord headLocation = snake.getFirst();

    for (lvl2 j : runningtntshka) {
        if (j.getX() == headLocation.getX() && j.getY() == headLocation.getY()) {
            gameOver = true;
            showGameOverDialog(primaryStage, mainMenuScene);
            break;
        }
    }
}


    private void display(GraphicsContext gc) {

        gc.clearRect(0, 0, ekranW * SIZE, ekranH * SIZE);


        gc.setFill(GREEN);
        for (cord point : snake) {
            gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
        }

        gc.setFill(DARKGREEN);
        score++;
        showScore(gc);
        showMax(gc);
        cord head = snake.getFirst();
        gc.fillRect(head.getX() * SIZE, head.getY() * SIZE, SIZE, SIZE);


        gc.setFill(RED);
        gc.fillRect(food.getX() * SIZE, food.getY() * SIZE, SIZE, SIZE);
        if(lvl==3){
           
            for (prepi i : tnts) {
            gc.drawImage(iImage, i.getX() * SIZE, i.getY() * SIZE, SIZE * 2, SIZE * 2);
        }
        }
        if (lvl == 2) {
        gc.setFill(javafx.scene.paint.Color.ORANGE);
        for (lvl2 j : runningtntshka) {
            gc.fillRect(j.getX() * SIZE, j.getY() * SIZE, SIZE, SIZE);
        }
    }

    }
private void colwithobs(Stage primaryStage) {
    cord headLocation = snake.getFirst();

    for (prepi i : tnts) {
        int iX = i.getX();
        int iY = i.getY();

        if ((iX <= headLocation.getX() && headLocation.getX() <= iX + 1) &&
            (iY <= headLocation.getY() && headLocation.getY() <= iY + 1)) {
            gameOver = true;
            showGameOverDialog(primaryStage,mainMenuScene);
            break;
        }
    }
}

    private void genprep(GraphicsContext gc){
         gc.setFill(BLUE);
        for (prepi i : tnts) {
            gc.fillRect(i.getX() * SIZE, i.getY() * SIZE, SIZE, SIZE);
        }
    }


private void showGameOverDialog(Stage primaryStage, Scene mainMenuScene) {
    if (ended) {
        return;
    }

    ended = true;

    animationTimer.stop();

    String gameOverText = "GAME OVER: " + (snake.size() - 1) + "\n" + "  CURRENT SPEED: " + " " + (int) SPEED;
    Text gameOverLabel = new Text(gameOverText);
    System.out.println();
    gameOverLabel.setFont(Font.font("Monospace", 16)); // Set the font family and size
    gameOverLabel.setFill(Color.RED); // Set the text color

    StackPane gameOverPane = new StackPane(gameOverLabel);
    gameOverPane.setAlignment(Pos.CENTER);
    Scene gameOverScene = new Scene(gameOverPane, 250, 150);

    PauseTransition pause = new PauseTransition(Duration.seconds(2));

    pause.setOnFinished(event -> {
        ended = false;
        System.out.print("");
        primaryStage.setScene(mainMenuScene);
        gm();
        SPEED=5;
        animationTimer.start();
    });

    pause.play();

    primaryStage.setScene(gameOverScene);
}





private enum Direction {
     U, D, L, R
  }



    public static void main(String[] args) {
        System.out.print("launched");
        launch(args);
    }

}
