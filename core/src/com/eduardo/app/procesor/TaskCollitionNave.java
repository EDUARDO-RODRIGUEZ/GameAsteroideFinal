package com.eduardo.app.procesor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.eduardo.app.actor.AsteroidActor;
import com.eduardo.app.actor.ContentInfo;
import com.eduardo.app.actor.SetAsteroideActor;
import com.eduardo.app.extructura.Actor;
import com.eduardo.app.screen.GameScreen;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TaskCollitionNave implements Runnable {

    private AsteroidActor asteroide;

    public TaskCollitionNave(AsteroidActor asteroidActor) {
        this.asteroide = asteroidActor;
    }

    @Override
    public void run() {
        if (asteroide.checkCollition(GameScreen.naveActor)) {
            List<Actor> info = GameScreen.stage.getActors("info");
            ContentInfo contentInfo = (ContentInfo) info.get(0);
            contentInfo.update(-1, 0);
            new TaskExplode((AsteroidActor) asteroide).start();
            GameScreen.damageSound.play();
            GameScreen.naveActor.getModel().materials.get(0).set(ColorAttribute.createDiffuse(Color.RED));
            GameScreen.stage.removeActor("asteroide", asteroide);
            final Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    GameScreen.naveActor.getModel().materials.get(0).set(ColorAttribute.createDiffuse(Color.WHITE));
                    this.cancel();
                    timer.cancel();
                }
            }, 500);
        }
        asteroide.translate((float) (asteroide.getPendienteDx() * 0.01), (float) (asteroide.getPendienteDy() * 0.01), 0.05f);
        asteroide.rotate(asteroide.getRotate(), 1, 0, 0, 2);
    }
}
