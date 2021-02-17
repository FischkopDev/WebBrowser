package de.home_skrobanek.browser.ui;

import de.home_skrobanek.browser.Browser;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Menu {

    private int width, height;
    private AnchorPane settings;

    public Menu(int width, int height){
        this.width = width;
        this.height = height;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            settings = fxmlLoader.load(getClass().getResource("/FXML/settings.fxml").openStream());

            updatePosition();

            settings.setPrefWidth(width);
            settings.setPrefHeight(height);
            settings.setStyle("-fx-background-color:#616975;");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePosition(){
        settings.setLayoutX(Browser.stage.getWidth()-(width+10));
        settings.setLayoutY(105);
    }

    public AnchorPane getSettings(){
        return settings;
    }

    public int getCustomWidth() {
        return width;
    }

    public int getCustomHeight() {
        return height;
    }

}
