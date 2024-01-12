package com.iiitd.stickhero;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pillar extends Rectangle implements Game{
    private int gapDistance;

    public Pillar(double width, double height) {
        super(width, height);
        setFill(Color.BLACK);
    }

    public int getGapDistance() {
        return gapDistance;
    }

    public void setGapDistance(int gapDistance) {
        this.gapDistance = gapDistance;
    }
}
