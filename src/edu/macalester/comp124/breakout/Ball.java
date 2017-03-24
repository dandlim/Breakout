package edu.macalester.comp124.breakout;

import comp124graphics.Ellipse;

import java.awt.*;

/**
 * Created by danielimmy on 2017. 3. 23..
 */
public class Ball extends Ellipse{
    /**
     * Constructor to create the ellipse object and initialize its instance variables.
     * The default creates an ellipse at position x,y with a bounding rectangle of width and height.
     * The ellipse is drawn with a 1 pixel black stroke outline by default.
     *
     * @param x      position
     * @param y      position
     * @param width  of the bounding rectangle
     * @param height of the bounding rectangle
     */
    public Ball(double x, double y, double width, double height, Color color) {

        super(x, y, width, height);

        setFilled(true);
        setFillColor(color);
    }
}
