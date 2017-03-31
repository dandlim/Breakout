package edu.macalester.comp124.breakout;

import comp124graphics.Ellipse;

import java.awt.*;

/**
 * The Ball class that extends Ellipse. It gives specific characteristics of a breakout ball
 * so the user can use it in the BreakoutGame class.
 * Created by danielimmy on 2017. 3. 23..
 */
public class Ball extends Ellipse{

    /**
     * Constructor to create the ellipse object and initialize its instance variables.
     * The default creates an ellipse at position x,y with a bounding rectangle of width, height and color.
     * The ellipse is drawn with a 1 pixel black stroke outline by default.
     *
     * @param x      x position of the ball
     * @param y      y position of the ball
     * @param width  of the bounding rectangle
     * @param height of the bounding rectangle
     */
    public Ball(double x, double y, double width, double height, Color color) {

        super(x, y, width, height);

        setFilled(true);
        setFillColor(color);
    }

    /**
     * The toString method for the Ball class that returns a string value of the class.
     * @return the string value of the class.
     */
    @Override
    public String toString() {
        return "Ball{}";
    }
}
