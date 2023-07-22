package com.eduardo.app.procesor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.eduardo.app.actor.AsteroidActor;
import com.eduardo.app.actor.SetAsteroideActor;
import com.eduardo.app.screen.GameScreen;

import java.util.Timer;
import java.util.TimerTask;

public class TaskExplode extends Thread {
    private AsteroidActor asteroidActor;

    public TaskExplode(AsteroidActor asteroidActor) {
        this.asteroidActor = asteroidActor;
    }

    @Override
    public void run() {
        final SetAsteroideActor setAsteroideActor = new SetAsteroideActor();
        setAsteroideActor.setPosition(asteroidActor.getPosition());
        GameScreen.stage.addActor("explode", setAsteroideActor);
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            private int c = 0;
            @Override
            public void run() {
                if (c == 4) {
                    GameScreen.stage.removeActor("explode", setAsteroideActor);
                    this.cancel();
                    timer.cancel();
                }
                setAsteroideActor.scale(1.15f, 1.15f, 1.15f);
                c++;
            }
        }, 0, 50);
    }

}
