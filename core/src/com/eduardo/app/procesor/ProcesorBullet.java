package com.eduardo.app.procesor;

import com.eduardo.app.actor.AsteroidActor;
import com.eduardo.app.actor.BulletActor;
import com.eduardo.app.extructura.Actor;
import com.eduardo.app.screen.GameScreen;
import com.eduardo.app.util.GraphicControl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcesorBullet extends Thread {
    private boolean suspend;
    private boolean finish;
    private ExecutorService executor;

    public ProcesorBullet() {
        this.executor = Executors.newFixedThreadPool(3);
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (suspend) wait();
                if (finish) break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GraphicControl.WaitNextFrame();
            int size = GameScreen.stage.getActors("bullet").size();
            List<Actor> bullets = GameScreen.stage.getActors("bullet");
            for (int i = 0; i < size; i++) {
                try {
                    Actor actor = bullets.get(i);
                    handleBullet((BulletActor) actor);
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }

    private void handleBullet(BulletActor bullet) {
        if (finish)
            return;
        if (bullet.exceedLimit()) {
            GameScreen.stage.removeActor("bullet", bullet);
            return;
        }
        executor.execute(new TaskCollitionBullet(bullet));
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
        executor.shutdown();
        if (suspend) {
            notify();
        }
    }

}
