package de.home_skrobanek.browser.ui;

import de.home_skrobanek.browser.Browser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;


public class SearchTab extends Pane {

    private Button close, activateTab;
    private boolean isActive = false;
    private WebView view;
    private String searchTxt;


    public SearchTab(String title){
        //Init panel
        setLayoutX(0);
        setLayoutY(9);

        setPrefWidth(200);
        setPrefHeight(50);
        setStyle("-fx-background-color: #323740;");
        //getStylesheets().add("searchtab.css");

        //init content

        close = new Button("X");
        close.setLayoutX(160);
        close.setLayoutY(12);
        close.setPrefHeight(25);
        close.setPrefWidth(25);
        close.setStyle("-fx-background-color: #323740; -fx-text-fill: white;");
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
               removeTab();
            }
        });
        close.addEventFilter(MouseEvent.MOUSE_ENTERED, e ->{
            close.setStyle("-fx-background-color: #3d434d; -fx-text-fill: white;");
        });
        close.addEventFilter(MouseEvent.MOUSE_EXITED, e ->{
            close.setStyle("-fx-background-color: #323740; -fx-text-fill: white;");
        });

        activateTab = new Button();
        activateTab.setText(title);
        activateTab.setLayoutX(0);
        activateTab.setLayoutY(0);
        activateTab.setAlignment(Pos.BASELINE_LEFT);
        activateTab.setPrefHeight(getPrefHeight());
        activateTab.setPrefWidth(getPrefWidth());
        activateTab.setStyle("-fx-background-color: #323740; -fx-text-fill: white;");
        activateTab.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                activateThisTab();
            }
        });

        view = new WebView();
        view.setPrefHeight(790);
        view.setPrefWidth(1600);

        AnchorPane.setRightAnchor(view, 0d);
        AnchorPane.setLeftAnchor(view, 0d);
        AnchorPane.setBottomAnchor(view, 0d);
        AnchorPane.setTopAnchor(view, 0d);

        getChildren().add(activateTab);
        getChildren().add(close);

        Browser.mainPane.getChildren().add(this);
    }

    private void activateThisTab(){
        Browser.collector.setActive(this);
    }

    private void removeTab(){
        Browser.mainPane.getChildren().remove(this);
        Browser.collector.removeTab(this);
    }

    public String getTitle(){
        return activateTab.getText();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public WebView getView() {
        return view;
    }

    public void setView(WebView view) {
        this.view = view;
    }

    public String getSearchTxt() {
        return searchTxt;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }
}
