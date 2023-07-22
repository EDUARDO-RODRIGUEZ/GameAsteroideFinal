package com.eduardo.app.procesor;

import com.eduardo.app.actor.AsteroidActor;
import com.eduardo.app.actor.BulletActor;
import com.eduardo.app.extructura.Actor;
import com.eduardo.app.screen.GameScreen;
import com.eduardo.app.util.GraphicControl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcesorAsteroide extends Thread {
    private boolean suspend;
    private boolean finish;
    private ExecutorService executor;

    public ProcesorAsteroide() {
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
            int size = GameScreen.stage.getActors("asteroide").size();
            List<Actor> asteroides = GameScreen.stage.getActors("asteroide");
            for (int i = 0; i < size; i++) {
                try {
                    Actor actor = asteroides.get(i);
                    handleAsteroid((AsteroidActor) actor);
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
    }

    private void handleAsteroid(AsteroidActor asteroide) {
        if (!finish) {
            if (asteroide.exceedLimit()) {
                GameScreen.stage.removeActor("asteroide", asteroide);
                return;
            }
            executor.execute(new TaskCollitionNave(asteroide));
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
        executor.shutdown();
        if (suspend) {
            notify();
        }
    }
}
