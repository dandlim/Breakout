package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

import java.awt.*;

/**
 * The Paddle class that extends Rectangle. It gives specific characteristics of a breakout paddle
 * so the user can use it in the BreakoutGame class.
 * Created by danielimmy on 2017. 3. 23..
 */
public class Paddle extends Rectangle {

    /**
     * Constructor to create the rectangle object and initialize its instance variables.
     * The default creates a rectangle at position x,y with the specified width, height and color.
     * The rectangle is drawn with a 1 pixel black stroke outline by default.
     *
     * @param x      x position of the paddle
     * @param y      y position of the paddle
     * @param width     width of the paddle
     * @param height    height of the paddle
     */
    public Paddle(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);

        setFilled(true);
        setFillColor(color);
    }

    /**
     * The toString method for the Paddle class that returns a string value of the class.
     * @return the string value of the class.
     */
    @Override
    public String toString() {
        return "Paddle{}";
    }
}
