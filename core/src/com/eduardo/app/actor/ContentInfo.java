package com.eduardo.app.actor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.eduardo.app.MainGame;
import com.eduardo.app.component.BuilderTextButton;
import com.eduardo.app.extructura.Actor;
import com.eduardo.app.screen.GameScreen;
import com.eduardo.app.screen.MenuScreen;
import jdk.tools.jmod.Main;
import sun.tools.jstat.Jstat;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class ContentInfo extends Actor {
    private Stage stage;
    private Table table;
    private Label liveLabel;
    private Label scoreLabel;
    public int live;
    public int score;
    private Label.LabelStyle labelStyle;
    private BuilderTextButton builderTextButton;
    private MainGame mainGame;
    private boolean gameOver;

    public ContentInfo(int live, final MainGame mainGame) {
        super(MainGame.assetManager);
        this.mainGame = mainGame;
        this.live = live;
        this.score = 0;
        builderTextButton = new BuilderTextButton();

        labelStyle = new Label.LabelStyle();
        builderTextButton.getParameter().color = Color.WHITE;
        builderTextButton.getParameter().size = 20;
        labelStyle.font = builderTextButton.getGenerator().generateFont(builderTextButton.getParameter());

        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        liveLabel = new Label("live :", labelStyle);
        scoreLabel = new Label("score :", labelStyle);

        liveLabel.setAlignment(Align.left);
        scoreLabel.setAlignment(Align.right);
        table.top().right();
        table.add(liveLabel);
        table.row();
        table.add(scoreLabel);
        table.padRight(40);
        update(0, 0);
        Gdx.input.setInputProcessor(stage);
    }

    public synchronized void update(int live, int score) {
        this.live += live;
        this.score += score;
        if (this.live == 0 && !gameOver) {
            liveLabel.setText("Live: " + this.live);
            stage.addActor(gameOver());
            ((GameScreen) mainGame.getScreen()).suspender();
            return;
        }
        liveLabel.setText("Live: " + this.live);
        scoreLabel.setText("Score: " + this.score);
    }

    private Table gameOver() {
        Table gameOver = new Table();
        gameOver.setFillParent(true);

        Label text = new Label("GAME OVER", labelStyle);
        text.setFontScale(3);

        gameOver.pad(10);
        gameOver.add(text);
        gameOver.center();
        return gameOver;
    }

    @Override
    protected void draw(ModelBatch batch, PerspectiveCamera camera, Environment environment) {
        stage.act();
        stage.draw();
    }

    @Override
    protected void dispose() {
        stage.dispose();
    }
}
