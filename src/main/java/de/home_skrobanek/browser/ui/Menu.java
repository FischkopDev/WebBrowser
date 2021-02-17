package de.home_skrobanek.browser.ui;

import de.home_skrobanek.browser.Browser;
import javafx.scene.layout.Pane;

public class Menu extends Pane {

    private int width, height;

    public Menu(int width, int height){
        this.width = width;
        this.height = height;

        updatePosition();

        setPrefWidth(width);
        setPrefHeight(height);
        setStyle("-fx-background-color:#616975;");
    }

    public void updatePosition(){
        setLayoutX(Browser.stage.getWidth()-(width+10));
        setLayoutY(105);
    }

    public int getCustomWidth() {
        return width;
    }

    public int getCustomHeight() {
        return height;
    }

}
