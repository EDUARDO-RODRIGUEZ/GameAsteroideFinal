package com.eduardo.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.eduardo.app.screen.MenuScreen;
import com.eduardo.app.util.Assets;

import java.util.HashMap;
import java.util.Map;

public class MainGame extends Game {
    public static AssetManager assetManager = new AssetManager();

    public static Map<String, Screen> screens = new HashMap<>();

    public MainGame() {
        loadAssets();
    }

    public void loadAssets() {
        assetManager.load(Assets.NAVE_OBJ, Model.class);
        assetManager.load(Assets.MISSILE_OBJ, Model.class);
        assetManager.load(Assets.ASTEROIDE_OBJ, Model.class);
        assetManager.load(Assets.ASTEROIDE_OBJ, Model.class);
        assetManager.load(Assets.EXPLODE_OBJ, Model.class);
        assetManager.load(Assets.SPACE_SOUND, Sound.class);
        assetManager.load(Assets.DESTROY_ASTEROIDE_SOUND, Sound.class);
        assetManager.load(Assets.BUTTON_SELECT_SOUND, Sound.class);
        assetManager.load(Assets.BULLET_SOUND, Sound.class);
        assetManager.load(Assets.DAMAGE_SOUND, Sound.class);
        assetManager.load(Assets.BACKGROUND_MENU, Texture.class);
    }

    @Override
    public void create() {
        assetManager.finishLoading();
        Screen screen = new MenuScreen(this);
        screens.put("menuScreee", screen);
        setScreen(screen);
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        getScreen().dispose();
    }
}
