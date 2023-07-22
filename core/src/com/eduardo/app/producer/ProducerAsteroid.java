package com.eduardo.app.producer;

import com.badlogic.gdx.math.Vector3;
import com.eduardo.app.actor.AsteroidActor;
import com.eduardo.app.screen.GameScreen;
import com.eduardo.app.util.GraphicControl;
import com.eduardo.app.util.GraphicsMath;

public class ProducerAsteroid extends Thread {
    private boolean suspend;
    private boolean finish;

    @Override
    public void run() {
        while (true) {
            try {
                if (suspend) wait();
                if (finish) break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            AsteroidActor asteroide = new AsteroidActor();
            asteroide.setPosition(new Vector3(GraphicsMath.getRandom(2, -1), GraphicsMath.getRandom(4, -1), -10.0f));
            asteroide.calcuarlPendiente(new Vector3(GraphicsMath.getRandom(2, -1), GraphicsMath.getRandom(4, -1), 0));
            GameScreen.stage.addActor("asteroide",asteroide);
            GraphicControl.WaitMilliSeconds(500);
        }
    }

    public void suspender() {
        suspend = true;
    }

    public void reanudar() {
        if (suspend) {
            suspend = false;
            notify();
        }
    }

    public void finalizar() {
        finish = true;
        if (suspend) {
            notify();
        }
    }

}
