package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

import java.awt.*;

/**
 * The Brick class that extends Rectangle. It gives specific characteristics of a breakout brick
 * so the user can use it in the BreakoutGame class.
 * Created by danielimmy on 2017. 3. 23..
 */
public class Brick extends Rectangle {

    /**
     * Constructor to create the rectangle object and initialize its instance variables.
     * The default creates a rectangle at position x,y with the specified width, height and color.
     * The rectangle is drawn with a 1 pixel black stroke outline by default.
     *
     * @param x      x position of the brick
     * @param y      y position of the brick
     * @param width     width of the brick
     * @param height    height of the brick
     */
    public Brick(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);

        setFilled(true);
        setFillColor(color);
    }

    /**
     * The toString method for the Brick class that returns a string value of the class.
     * @return the string value of the class.
     */
    @Override
    public String toString() {
        return "Brick{}";
    }
}
