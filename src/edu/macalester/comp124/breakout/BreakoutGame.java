package edu.macalester.comp124.breakout;

import acm.util.*;
import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsObject;
import comp124graphics.GraphicsText;

import java.awt.*;
import java.awt.event.*;

/**
 * The BreakoutGame class that extends CanvasWindow and implements MouseMotionListener.
 * It is the main program for the breakout game in which you have to eliminate
 * all block on the screen by using a ball and a paddle.
 * Created by danielimmy on 2017. 3. 23..
 * Worked in collaboration with Chase Yoo.
 */
public class BreakoutGame extends CanvasWindow implements MouseMotionListener {

    // Specifies the size of the canvas
    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 600;

    // Specifies the size and position of the paddle
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;
    private static final int PADDLE_Y_DIST = 30;

    // Specifies the size, number and position of the bricks
    private static final int BRICKS_PER_ROW = 10;
    private static final int BRICK_ROW = 10;
    private static final int BRICK_SEP = 4;
    private static final int BRICK_DIST = 70;
    private static final int BRICK_WIDTH = (CANVAS_WIDTH - (BRICKS_PER_ROW - 1) * BRICK_SEP) / BRICKS_PER_ROW;
    private static final int BRICK_HEIGHT = 8;

    // Specifies the dimensions of the ball
    private static final int BALL_RADIUS = 10;

    // Sets the speed of the ball
    private static final int DELAY = 10;

    // Determines the velocity of the ball
    private double dx, dy;

    // Keeps track of the number of turns and bricks left
    public int turnsLeft;
    public int bricksLeft;

    // Generates a random number for the starting direction of the ball
    private RandomGenerator numGen;

    // Creates instances of each GraphicsObjects
    public Ball ball;
    public Paddle paddle;
    public Brick brick;

    // Creates an instance of a GraphicsText
    public GraphicsText gameState;

    /**
     * Constructor for the BreakoutGame class that does not have any input parameters. It initializes the
     * numGen object, the variables that keep track of the number of turns and bricks left and sets
     * a starting text to show on the canvas. Also adds a MouseMotionListener.
     */
    public BreakoutGame() {
        super("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);

        numGen = RandomGenerator.getInstance();

        turnsLeft = 3;
        bricksLeft = 100;

        gameState = new GraphicsText("You have " + Integer.toString(turnsLeft) + " chances. The game will start in 3 seconds.", 30, 17);
        add(gameState);

        addMouseMotionListener(this);
    }

    /**
     * The run method is the main part of the class that will be looped repeatedly
     * until there are either no blocks left or no chances left in the game.
     */
    public void run() {
        gameReady();

        while (gamePlay() && turnsLeft > 0) {
            gamePlay();

        }
        if (bricksLeft == 0) {
            removeAll();
            win();

        }
        if (bricksLeft > 0) {
            removeAll();
            lose();

        }
    }

    /**
     * This method sets up all the parts needed for the breakout game on the canvas.
     */
    private void gameReady() {
        makePaddle();
        makeBall();
        makeBrickWall(getWidth() / 2, BRICK_DIST);
    }

    /**
     * This method creates a paddle and adds it onto the canvas.
     */
    private void makePaddle() {
        double x = getWidth() / 2 - PADDLE_WIDTH / 2;
        double y = getHeight() - PADDLE_Y_DIST - PADDLE_HEIGHT;
        paddle = new Paddle(x, y, PADDLE_WIDTH, PADDLE_HEIGHT, Color.PINK);
        paddle.setFilled(true);
        add(paddle);
    }

    /**
     * This method creates a ball and adds it onto the canvas.
     */
    private void makeBall() {
        double x = getWidth() / 2 - BALL_RADIUS;
        double y = getHeight() / 2 - BALL_RADIUS;
        ball = new Ball(x, y, BALL_RADIUS * 2, BALL_RADIUS * 2, Color.RED);
        ball.setFilled(true);
        add(ball);
    }

    /**
     * This method creates many bricks and eventually forms a brickWall and adds it on the canvas.
     *
     * @param cx Specifies a certain x coordinate to position the brickWall
     * @param cy Specifies a certain y coordinate to position the brickWall
     */
    private void makeBrickWall(double cx, double cy) {
        for (int row = 0; row < BRICK_ROW; row++) {

            for (int column = 0; column < BRICKS_PER_ROW; column++) {
                double x = cx - (BRICKS_PER_ROW * BRICK_WIDTH) / 2 - ((BRICKS_PER_ROW - 1) * BRICK_SEP) / 2 + column * BRICK_WIDTH + column * BRICK_SEP;
                double y = cy + row * BRICK_HEIGHT + row * BRICK_SEP;

                if (row < 2) {
                    brick = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.RED);
                    brick.setFilled(true);
                    add(brick);

                }
                if (row == 2 || row == 3) {
                    brick = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.ORANGE);
                    brick.setFilled(true);
                    add(brick);

                }
                if (row == 4 || row == 5) {
                    brick = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.YELLOW);
                    brick.setFilled(true);
                    add(brick);

                }
                if (row == 6 || row == 7) {
                    brick = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.GREEN);
                    brick.setFilled(true);
                    add(brick);

                }
                if (row == 8 || row == 9) {
                    brick = new Brick(x, y, BRICK_WIDTH, BRICK_HEIGHT, Color.CYAN);
                    brick.setFilled(true);
                    add(brick);

                }

            }

        }
    }

    /**
     * This method is looped everytime the user starts a new chance on the breakout game.
     * The method runs until the player loses their chance or there are no more bricks.
     *
     * @return a boolean value for whether the user gets another try.
     */
    private boolean gamePlay() {
        waitFiveSeconds();
        getVelocity();

        while (true) {
            ballMovement();

            if (ball.getY() >= getHeight()) {
                turnsLeft--;
                return true;

            }
            if (bricksLeft == 0) {
                return false;

            }

        }
    }

    /**
     * This method sets the time of five seconds before the game starts
     * so that the user isn't surprised by the suddenness of the start.
     * It is called everytime a new game starts.
     */
    public void waitFiveSeconds() {
        ball.setPosition(200, 300);

        for (int i = 5; i > -1; i--) {
            pause(1000);
            gameState.setText("You have " + Integer.toString(turnsLeft) + " chances. Game will start in " + Integer.toString(i) + " seconds.");

            if (i == 0) {
                gameState.setText("You have " + Integer.toString(turnsLeft) + " chances. Start!");

            }

        }
    }

    /**
     * A method that gets the velocity of the ball during the game. It
     * also generates a random x direction velocity.
     */
    private void getVelocity() {
        dy = 4.0;
        dx = numGen.nextDouble(1.0, 3.0);

        if (numGen.nextBoolean(0.5)) {
            dx = -dx;

        }
    }

    /**
     * The method that is responsible for keeping the ball in movement.
     * It constantly changes the ball's position. Also creates an instance of a
     * GraphicsObject to check if the ball collides with anything.
     */
    private void ballMovement() {
        ball.move(dx, dy);

        if ((ball.getX() - dx <= 0 && dx < 0) || (ball.getX() + dx >= (getWidth() - BALL_RADIUS * 2) && dx > 0)) {
            dx = -dx;

        }
        if ((ball.getY() - dy <= 0 && dy < 0)) {
            dy = -dy;

        }

        GraphicsObject collision = getCollision();

        if (collision == paddle) {

            if (ball.getY() >= getHeight() - PADDLE_Y_DIST - PADDLE_HEIGHT - BALL_RADIUS * 2 && ball.getY() < getHeight() - PADDLE_Y_DIST - PADDLE_HEIGHT - BALL_RADIUS * 2 + 4) {
                dy = -dy;

            }

        } else if (collision != null) {
            remove(collision);
            bricksLeft--;
            dy = -dy;

        }
        pause(DELAY);
    }

    /**
     * The method that checks for the collision of a GraphicsObject and the ball.
     * This method is called in the ballMovement method.
     * @return a GraphicsObject if there is a collision, or null if not.
     */
    private GraphicsObject getCollision() {

        if ((getElementAt(ball.getX(), ball.getY())) != null) {
            return getElementAt(ball.getX(), ball.getY());

        } else if (getElementAt((ball.getX() + BALL_RADIUS * 2), ball.getY()) != null) {
            return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());

        } else if (getElementAt(ball.getX(), (ball.getY() + BALL_RADIUS * 2)) != null) {
            return getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);

        } else if (getElementAt((ball.getX() + BALL_RADIUS * 2), (ball.getY() + BALL_RADIUS * 2)) != null) {
            return getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2);

        } else {
            return null;

        }
    }

    /**
     * The method shows a message saying the user lost on the canvas.
     */
    private void lose() {
        GraphicsText gameOver = new GraphicsText("Game Over", getWidth() / 2, getHeight() / 2);
        gameOver.move(-gameOver.getWidth() / 2, -gameOver.getHeight());
        gameOver.setColor(Color.RED);
        add(gameOver);
    }

    /**
     * The method shows a message saying the user won on the canvas.
     */
    private void win() {
        GraphicsText win = new GraphicsText("Winner!!", getWidth() / 2, getHeight() / 2);
        win.move(-win.getWidth() / 2, -win.getHeight());
        win.setColor(Color.RED);
        add(win);
    }

    /**
     * This method is responsible in keeping track of the mouse's position during the game.
     * It makes the paddle follow the movement of the mouse.
     *
     * @param e is an instance of MouseEvent and is used to represent the mouse
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if ((e.getX() < getWidth() - PADDLE_WIDTH / 2) && (e.getX() > PADDLE_WIDTH / 2)) {
            paddle.setPosition(e.getX() - PADDLE_WIDTH / 2, getHeight() - PADDLE_Y_DIST - PADDLE_HEIGHT);

        }
    }

    /**
     * This method is responsible in keeping track of the mouse's position during the game.
     * The method will respond to a drag in the mouse.
     *
     * @param e is an instance of MouseEvent and is used to represent the mouse
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * The equals method of the class that compares two objects and whether they are equal
     * @param o The object to be compared
     * @return Boolean value of whether the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BreakoutGame that = (BreakoutGame) o;

        if (Double.compare(that.dx, dx) != 0) return false;
        if (Double.compare(that.dy, dy) != 0) return false;
        if (turnsLeft != that.turnsLeft) return false;
        if (bricksLeft != that.bricksLeft) return false;
        if (numGen != null ? !numGen.equals(that.numGen) : that.numGen != null) return false;
        if (ball != null ? !ball.equals(that.ball) : that.ball != null) return false;
        if (paddle != null ? !paddle.equals(that.paddle) : that.paddle != null) return false;
        if (brick != null ? !brick.equals(that.brick) : that.brick != null) return false;
        return gameState != null ? gameState.equals(that.gameState) : that.gameState == null;
    }

    /**
     * The toString method for the Brick class that returns a string value of the class.
     * @return the string value of the class and each of the instance variables.
     */
    @Override
    public String toString() {
        return "BreakoutGame{" +
                "dx=" + dx +
                ", dy=" + dy +
                ", turnsLeft=" + turnsLeft +
                ", bricksLeft=" + bricksLeft +
                ", numGen=" + numGen +
                ", ball=" + ball +
                ", paddle=" + paddle +
                ", brick=" + brick +
                ", gameState=" + gameState +
                '}';
    }

    /**
     * This is the main method of the class that creates an instance of the BreakoutGame
     * and calls the method run to start the game.
     */
    public static void main(String[] args) {
        BreakoutGame prog = new BreakoutGame();
        prog.run();
    }
}
