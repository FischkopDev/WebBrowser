package de.home_skrobanek.browser.controller;

import de.home_skrobanek.browser.Browser;
import de.home_skrobanek.browser.history.History;
import de.home_skrobanek.browser.utils.BackgroundTask;
import de.home_skrobanek.browser.utils.DefaultSearchEngine;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserController {

    @FXML
    WebView webview;

    @FXML
    TextField searchField;

    @FXML
    AnchorPane webRenderer, parent;

    @FXML
    Button menu, back, forward;

    private History history;

    public void initialize(){
        new BackgroundTask("SearchEngine", new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Browser.collector.getActive().getView().getEngine().load(getClass().getResource("/pages/index.html").toString());
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        history = new History();

        //animation of the menu buttons
        addAnimation(menu);
        addAnimation(forward);
        addAnimation(back);
    }

    @FXML
    protected void search(){
        String searchContent = setupSearchContent(searchField.getText());

        Browser.collector.searchOnTab(searchContent, history);

        searchField.setText(searchContent);
    }

    @FXML
    protected void openMenu(){
        Browser.settingsMenu.getSettings().setVisible(!Browser.settingsMenu.getSettings().isVisible());
        Browser.settingsMenu.updatePosition();
    }

    @FXML
    protected void onBack(){
        String line = Browser.collector.getActive().getLastPage();

        System.out.println(Browser.collector.getActive().getLastPage());
        if(line != null){
            Browser.collector.getActive().removeLastPage();
            Browser.collector.searchOnTab(line, history);
        }
    }

    /*
        Returns the content from a webpage.
     */
    private void getContent(){
        URL ur = null;
        try {
            ur = new URL("http://www.google.com/");
            HttpURLConnection yc =(HttpURLConnection) ur.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String setupSearchContent(String content){
        if(content.contains(".") && !content.contains("http")){
            return "http://"+content;
        }
        else if(content.contains("!g")){
            return null;//TODO implement shortcuts
        }
        else{
            return DefaultSearchEngine.SEARCH_ENGINE + "/search?hl="+DefaultSearchEngine.LANGUAGE + "&q="+content;
        }
    }

    /*
        Makes a small animation when adding a new tab
     */
    private void addAnimation(Button button){
        //animation for nice look
        button.addEventFilter(MouseEvent.MOUSE_ENTERED, e ->{
            button.setStyle("-fx-background-color: #3d434d; -fx-text-fill: white;");
        });
        button.addEventFilter(MouseEvent.MOUSE_EXITED, e ->{
            button.setStyle("-fx-background-color: #505661; -fx-text-fill: white;");
        });
    }
}
