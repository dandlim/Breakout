package edu.macalester.comp124.breakout;

import comp124graphics.GraphicsGroup;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by danielimmy on 2017. 3. 23..
 */
public class BrickWall extends GraphicsGroup {

    private ArrayList<Brick> brickWall = new ArrayList<>();

    final int DISTANCE_BTW = 4;
    final double BRICK_WIDTH = 45;
    final double BRICK_HEIGHT = 10;

    /**
     * Contructor for making BrickWall.
     * @param numBrick
     */
    public BrickWall(int numBrick) {

        int numBrickRows = numBrick / 10;

        Color[] colors = {Color.RED, Color.RED, Color.YELLOW, Color.YELLOW, Color.GREEN, Color.GREEN, Color.CYAN, Color.CYAN, Color.BLUE, Color.BLUE};

        for (int i = 0; i < colors.length; i++){

            for (int j = 0; j < numBrickRows; j++){

                Brick brick = new Brick(6, 3, BRICK_WIDTH, BRICK_HEIGHT, colors[j]); // 나중에이거다시수정
                brickWall.add(brick);
                add(brick, (i * BRICK_WIDTH) + (i * DISTANCE_BTW), (j * BRICK_HEIGHT) + (j * DISTANCE_BTW));
            }
        }
    }
}
