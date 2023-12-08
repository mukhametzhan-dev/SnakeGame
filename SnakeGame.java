import java.util.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class SnakeGame extends Application {

    private static final int WIDTH = 20; 
    private static final int HEIGHT = 20; 
    private static final int SIZE = 20; 
    private int SPEED = 5;
    private int score = 0;
    private ArrayList<Integer> records = new ArrayList<>();
    private int maxres = 0;

    //private Deque<Point> snake = new ArrayDeque<>(); 
    private Deque<cord> snake = new LinkedList<>();
    public Direction direction = Direction.RIGHT; 

    private cord food; 

    private boolean gameOver = false; 
    private boolean gamePaused = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Canvas canvas = new Canvas(WIDTH * SIZE, HEIGHT * SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane(canvas);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
    KeyCode keyCode = event.getCode();
    switch (keyCode) {
        case W:
            if (direction != Direction.DOWN) {
                direction = Direction.UP;
            }
            break;
        case S:
            if (direction != Direction.UP) {
                direction = Direction.DOWN;
            }
            break;
        case A:
            if (direction != Direction.RIGHT) {
                direction = Direction.LEFT;
            }
            break;
        case D:
            if (direction != Direction.LEFT) {
                direction = Direction.RIGHT;
            }
            break;
        case P:
            gamePaused = !gamePaused;
            break;
        case R:
            initGame();
            break;
        default:
            break;
    }
});


        primaryStage.setScene(scene);
        primaryStage.setTitle("ZHYLAN");
        primaryStage.setResizable(false);
        primaryStage.show();

        initGame();

        new AnimationTimer() {
            private long lastUpdateTime;

            @Override
            public void handle(long now) {
                if (now - lastUpdateTime >= 1_000_000_000 / SPEED) { 
                    lastUpdateTime = now;
                    if (!gameOver && !gamePaused) {
                        move();
                        checkCollision();
                        paint(gc);
                        showScore(gc);
                        showMax(gc);
                    }
                }
            }
        }.start();
    }
    private void showScore(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillText("Score: " + (snake.size() - 1 ), 10, 20); 
    }
    private void showMax(GraphicsContext gc){
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillText("Max score: " + (maxres),20,40);
    }
    private void initGame() {
        snake.clear();
        snake.add(new cord(WIDTH / 2, HEIGHT / 2));
        gf();
        gameOver = false;
        gamePaused = false;
    }


   private void move() {
    cord headLocation = snake.getFirst();
    cord newHead = null;
    switch (direction) {
        case UP:
            newHead = new cord(headLocation.getX(), (headLocation.getY() - 1 + HEIGHT) % HEIGHT);
            break;
        case DOWN:
            newHead = new cord(headLocation.getX(), (headLocation.getY() + 1) % HEIGHT);
            break;
        case LEFT:
            newHead = new cord((headLocation.getX() - 1 + WIDTH) % WIDTH, headLocation.getY());
            break;
        case RIGHT:
            newHead = new cord((headLocation.getX() + 1) % WIDTH, headLocation.getY());
            break;
        default:
            break;
    }

    if (snake.contains(newHead)) {
        gameOver = true;
        showGameOverDialog();
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


private void checkCollision() {
    cord headLocation = snake.getFirst();
    Iterator<cord> iterator = snake.iterator();

    while (iterator.hasNext()) {
        cord point = iterator.next();
        if (point != headLocation && point.equals(headLocation)) {
            gameOver = true;
            showGameOverDialog();
            break;
        }
    }
}





// ...

private void gf() {
    boolean validPosition;
    int x, y;
    Random random = new Random();

    do {
        validPosition = true;
        x = random.nextInt(WIDTH);
        y = random.nextInt(HEIGHT);

        for (cord point : snake) {
            if (point.getX() == x && point.getY() == y) {
                validPosition = false;
                break;
            }
        }
    } while (!validPosition);

    food = new cord(x, y);
}


    private void paint(GraphicsContext gc) {

        gc.clearRect(0, 0, WIDTH * SIZE, HEIGHT * SIZE);


        gc.setFill(javafx.scene.paint.Color.GREEN);
        for (cord point : snake) {
            gc.fillRect(point.getX() * SIZE, point.getY() * SIZE, SIZE, SIZE);
        }

        gc.setFill(javafx.scene.paint.Color.DARKGREEN);
        score++;
        showScore(gc);
        showMax(gc);
        cord head = snake.getFirst();
        gc.fillRect(head.getX() * SIZE, head.getY() * SIZE, SIZE, SIZE);


        gc.setFill(javafx.scene.paint.Color.RED);
        gc.fillRect(food.getX() * SIZE, food.getY() * SIZE, SIZE, SIZE);
        //gc.drawImage(food.image, food.getX() * SIZE, food.getY() * SIZE, SIZE, SIZE);
    }


    private void showGameOverDialog() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("GAME OVER");
        alert.setHeaderText(null);
        alert.setContentText("GAME OVER：" + (snake.size() - 1) + "   CURRENT SPEED: " + " " + (int)SPEED);
        records.add(snake.size() - 1 );
        for(int i:records){
            maxres= Math.max(i,maxres);
        }



        SPEED = 5;
        alert.show();

        alert.setOnHidden(event -> {

            initGame(); 
        });
    }


 private enum Direction {
     UP, DOWN, LEFT, RIGHT
  }



    public static void main(String[] args) {
        launch(args);
    }

}
