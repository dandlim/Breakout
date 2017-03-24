package edu.macalester.comp124.breakout;


import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsText;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Main program for the breakout game.
 * Worked with Chase Yoo. Random number generator idea from: http://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range.
 */
public class BreakoutGame extends CanvasWindow implements MouseMotionListener {

    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 600;
    private static final int DIAMETER = 20;
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 13;
    private static final int PADDLE_DISTANCE = 500;

    private double dx;
    private double dy = 2;

    public int turnsLeft = 10;
    public int bricksLeft = 100;
    final int pauseTime = 3;

    public Ball ball;
    public Paddle paddle;
    public BrickWall brickWall;
    public GraphicsText gameState;

    public BreakoutGame() {
        super("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);

        gameState = new GraphicsText("You have " + Integer.toString(turnsLeft) + " chances. The game will start in 3 seconds.", 80, 17);
        add(gameState);

        ball = new Ball(240, 300, DIAMETER, DIAMETER, Color.RED);
        add(ball);

        paddle = new Paddle(230, PADDLE_DISTANCE, PADDLE_WIDTH, PADDLE_HEIGHT, Color.PINK);
        add(paddle);

        brickWall = new BrickWall(bricksLeft);
        add(brickWall, 6, 30);

        dx = ThreadLocalRandom.current().nextDouble(-2, 2);

        addMouseMotionListener(this);
    }

    public void waitFiveSeconds(){
        ball.setPosition(240, 300);

        for(int i = 0; i > -1; i--){
            pause(1000);
            gameState.setText("You have " + Integer.toString(turnsLeft) + " chances. Game will start in " + Integer.toString(i) + " seconds.");

            if(i == 0){
                gameState.setText("You have " + Integer.toString(turnsLeft) + " chances. Start!");
            }
        }
    }

    public void removeBrick() {

    }

    public boolean hitTop() {
        if (ball.getY() + (DIAMETER * 2) >= CANVAS_HEIGHT || ball.getY() <= 0) {
            return true;
        }

        return false;
    }

    public boolean hitSides() {
        if (ball.getX() + DIAMETER > CANVAS_WIDTH || ball.getX() < 0) {
            return true;
        }

        return false;
    }

    public boolean hitBottom() {
        if(ball.getY() + (DIAMETER * 2) >= CANVAS_HEIGHT) {
            return true;
        }

        return false;
    }

    public boolean hitPaddle() {
        if (ball.getY() + DIAMETER == PADDLE_DISTANCE &&
                ball.getX() >= paddle.getX() - DIAMETER &&
                ball.getX() + DIAMETER <= paddle.getX() + PADDLE_WIDTH + DIAMETER) {
            return true;
        }

        return false;
    }
    
    public boolean hitBrick() {
        if(getElementAt(ball.getX() + (DIAMETER/2), ball.getY()) == brickWall
                || getElementAt(ball.getX(), ball.getY() + (DIAMETER/2)) == brickWall
                || getElementAt(ball.getX() + DIAMETER, ball.getY() + (DIAMETER/2)) == brickWall
                || getElementAt(ball.getX() + (DIAMETER/2), ball.getY() + DIAMETER) == brickWall) {
            return true;
        }
        
        return false;
    }

    public void run() {
        waitFiveSeconds();

        while(true) {
            ball.setPosition(ball.getX() + dx, ball.getY() + dy);
            pause(pauseTime);

            if(hitTop()) {
                dy *= -1;
            }

            if(hitSides()) {
                dx *= -1;
            }

            if(hitBottom()) {
                turnsLeft--;

                if(turnsLeft == 0) {
                    gameState.setText("You lost all games!");
                    break;
                }

                dx = ThreadLocalRandom.current().nextDouble(-1, 2);
                dy = 2;

                waitFiveSeconds();
            }

            if(hitPaddle()) {
                dy *= -1;
            }

            if(hitBrick()) {
                removeBrick();

                if(bricksLeft == 0) {
                    ball.setPosition(240, 300);
                    gameState.setText("Congrats! You won the game!");
                    break;
                }

                dy *= -1;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddle.setPosition(e.getX() - (PADDLE_WIDTH/2), PADDLE_DISTANCE);

        if(e.getX() <= PADDLE_WIDTH/2){
            paddle.setPosition(0, PADDLE_DISTANCE);
        }

        if(e.getX() >= CANVAS_WIDTH - (PADDLE_WIDTH/2)){
            paddle.setPosition(CANVAS_WIDTH - (PADDLE_WIDTH), PADDLE_DISTANCE);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public static void main(String[] args){
        BreakoutGame prog = new BreakoutGame();
        prog.run();
    }
}
