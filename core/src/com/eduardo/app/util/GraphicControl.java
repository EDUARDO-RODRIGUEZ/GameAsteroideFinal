package com.eduardo.app.util;

import com.badlogic.gdx.Gdx;

public class GraphicControl {
    public static void WaitNextFrame() {
        double timeStart = System.currentTimeMillis();
        double delta = Gdx.graphics.getDeltaTime();
        while (((System.currentTimeMillis() - timeStart) / 1000) < delta) ;
    }

    public static void WaitMilliSeconds(int milliseconds) {
        double timeStart = System.currentTimeMillis();
        while (((System.currentTimeMillis() - timeStart)) < milliseconds) ;
    }
}
