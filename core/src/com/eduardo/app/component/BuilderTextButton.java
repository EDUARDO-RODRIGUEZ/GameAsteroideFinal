package com.eduardo.app.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.*;

public class BuilderTextButton {
    private TextButtonStyle textButtonStyle;
    private String text;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontParameter parameter;

    public BuilderTextButton() {
        this.text = "defaulf";
        this.textButtonStyle = new TextButtonStyle();
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal("font/vaca.ttf"));
        this.parameter = new FreeTypeFontParameter();
    }

    public FreeTypeFontParameter getParameter() {
        return parameter;
    }

    public FreeTypeFontGenerator getGenerator() {
        return generator;
    }

    public TextButtonStyle getTextButtonStyle() {
        return textButtonStyle;
    }

    public TextButton getTextButton() {
        return new TextButton(this.text, this.textButtonStyle);
    }

    public void setText(String text) {
        this.text = text;
    }
}
