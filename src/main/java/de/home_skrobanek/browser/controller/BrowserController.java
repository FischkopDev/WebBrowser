package de.home_skrobanek.browser.controller;

import de.home_skrobanek.browser.Browser;
import de.home_skrobanek.browser.utils.DefaultSearchEngine;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
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
    AnchorPane webRenderer;

    public void initialize(){
        //webview.getEngine().load(getClass().getResource("pages/index.html").toString());
    }

    @FXML
    protected void search(){
        String searchContent = setupSearchContent(searchField.getText());
        System.out.println(searchContent);

        Browser.collector.getActive().getView().getEngine().load(searchContent);
        Browser.collector.getActive().setSearchTxt(searchContent);
        searchField.setText(searchContent);
    }

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
        if(content.contains(".")){
            return "http://"+content;
        }
        if(content.contains("http://")){
            return "http://"+content;
        }
        else{
            return DefaultSearchEngine.SEARCH_ENGINE + "/search?hl="+DefaultSearchEngine.LANGUAGE + "&q="+content;
        }
    }

}
