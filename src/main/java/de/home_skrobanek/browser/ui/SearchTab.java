package de.home_skrobanek.browser.ui;

import de.home_skrobanek.browser.Browser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.util.ArrayList;


public class SearchTab extends Pane {

    private Button close, activateTab;
    private boolean isActive = false;
    private boolean isInternSetting = false;
    private WebView view;
    private AnchorPane intern;
    private String searchTxt;

    ArrayList<String> lastPage = new ArrayList<>();


    public SearchTab(String title, TabType type){
        //Init panel
        setLayoutX(0);
        setLayoutY(0);

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

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            switch(type){
                case DEFAULT_SEARCH_ENGINE:
                    isInternSetting = true;
                    intern = fxmlLoader.load(getClass().getResource("/FXML/searchEngine.fxml").openStream());
                    break;
                case DEFAULT_TAb:
                    isInternSetting = false;

                    view = new WebView();
                    view.setPrefHeight(790);
                    view.setPrefWidth(1600);
                    break;
                case LANGUAGE:
                    intern = fxmlLoader.load(getClass().getResource("/FXML/language.fxml").openStream());
                    isInternSetting = true;
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        setConstrains();

        getChildren().add(activateTab);
        getChildren().add(close);

        Browser.mainPane.getChildren().add(this);
    }

    private void setConstrains(){
        if(!isInternSetting) {
            AnchorPane.setRightAnchor(view, 0d);
            AnchorPane.setLeftAnchor(view, 0d);
            AnchorPane.setBottomAnchor(view, 0d);
            AnchorPane.setTopAnchor(view, 0d);
        }
        else{
            AnchorPane.setRightAnchor(intern, 0d);
            AnchorPane.setLeftAnchor(intern, 0d);
            AnchorPane.setBottomAnchor(intern, 0d);
            AnchorPane.setTopAnchor(intern, 0d);
        }
    }

    public void setLastPage(String page){
        System.out.println(page);
        lastPage.add(page);
    }

    public String getLastPage(){
        if(lastPage.size() > 1) {
            return lastPage.get(lastPage.size() - 2);
        }
        else {
            return null;
        }
    }

    public void removeLastPage(){
        lastPage.remove(lastPage.size()-1);
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

    public AnchorPane getIntern(){
        return intern;
    }

    public boolean isInternSetting(){
        return isInternSetting;
    }

    public void setSearchTxt(String searchTxt) {
        this.searchTxt = searchTxt;
    }

    public enum TabType {
        /*
            The DEFAULT_TAB is the standard tab, which allows to use
            the Internet.
         */
        DEFAULT_TAb,
        /*
            This tab is a settings menu in the browser.
         */
        DEFAULT_SEARCH_ENGINE,
        ADDONS,
        INFORMATION,
        LANGUAGE;
    }
}
