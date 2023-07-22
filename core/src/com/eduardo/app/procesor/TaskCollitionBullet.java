package com.eduardo.app.procesor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector3;
import com.eduardo.app.actor.AsteroidActor;
import com.eduardo.app.actor.BulletActor;
import com.eduardo.app.actor.ContentInfo;
import com.eduardo.app.actor.SetAsteroideActor;
import com.eduardo.app.extructura.Actor;
import com.eduardo.app.screen.GameScreen;

import java.util.List;

public class TaskCollitionBullet implements Runnable {

    private BulletActor bulletActor;

    public TaskCollitionBullet(BulletActor bulletActor) {
        this.bulletActor = bulletActor;
    }

    @Override
    public void run() {
        List<Actor> asteroides = GameScreen.stage.getActors("asteroide");
        int size = asteroides.size();
        for (int i = 0; i < size; i++) {
            try {
                Actor asteroide = asteroides.get(i);
                if (bulletActor.checkCollition(asteroide)) {
                    GameScreen.destroyAsteroideSound.play();
                    GameScreen.stage.removeActor("bullet", bulletActor);
                    GameScreen.stage.removeActor("asteroide", asteroide);
                    List<Actor> info = GameScreen.stage.getActors("info");
                    ContentInfo contentInfo = (ContentInfo) info.get(0);
                    contentInfo.update(0, 5);
                    new TaskExplode((AsteroidActor) asteroide).start();
                    return;
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        //TODO: REVISAR ELIMINACION ASTEROIDE Y BULLET
        bulletActor.translate(0, 0, -0.1f);
    }
}
