package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

import java.awt.*;

/**
 * Created by danielimmy on 2017. 3. 23..
 */
public class Paddle extends Rectangle {

    /**
     * Constructor to create the rectangle object and initialize its instance variables.
     * The default creates a rectangle at position x,y with the specified width and height.
     * The rectangle is drawn with a 1 pixel black stroke outline by default.
     *
     * @param x      position
     * @param y      position
     * @param width
     * @param height
     */
    public Paddle(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);

        setFilled(true);
        setFillColor(color);
    }
}
