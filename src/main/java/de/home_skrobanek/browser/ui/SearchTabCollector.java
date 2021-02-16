package de.home_skrobanek.browser.ui;

import de.home_skrobanek.browser.Browser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class SearchTabCollector {

    private ArrayList<SearchTab> collection = new ArrayList<>();
    private Button add;

    private static final int TAB_SIZE = 200;

    public SearchTabCollector(){
        add = new Button("+");
        add.setLayoutY(10);
        add.setPrefWidth(50);
        add.setPrefHeight(50);
        add.setFont(new Font(16));
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                SearchTab tab = new SearchTab("Neuer Tab");
                setActive(tab);
                addSearchTab(tab);
            }
        });

    }

    public void addButton(){
        Browser.mainPane.getChildren().add(add);
    }

    public void addSearchTab(SearchTab tab){
        collection.add(tab);
        setActive(tab);
        setLayout();
    }

    public void setActive(SearchTab tab){
        for(int i = 0; i < collection.size(); i++){
            if(collection.get(i) != tab){
                collection.get(i).setActive(false);
            }
            else{
                collection.get(i).setActive(true);
                AnchorPane pane = (AnchorPane) Browser.mainPane.lookup("#webRenderer");
                TextField search = (TextField) Browser.mainPane.lookup("#searchField");

                if(pane != null) {
                    pane.getChildren().clear();
                    pane.getChildren().add(collection.get(i).getView());
                    search.setText(collection.get(i).getSearchTxt());
                    System.out.println("Pane set");
                }
                else{
                    System.err.println("ERROR: Pane not initialized");
                }
            }
        }
    }

    public SearchTab getActive(){
        for(int i = 0; i < collection.size(); i++){
            if(collection.get(i).isActive()){
                System.out.println(collection.get(i).getTitle());
                return collection.get(i);
            }
        }
        return null;
    }

    public void removeTab(SearchTab tab){
        if(collection.size() > 1) {
            collection.remove(tab);
            setActive(collection.get(collection.size()-1));
            setLayout();
        }
        else{
            System.exit(0);
        }
    }

    private void setLayout(){
        int tmpX = 0;
        for(int i = 0; i < collection.size(); i++){
            collection.get(i).setLayoutX(tmpX);

            tmpX+=TAB_SIZE+5;
        }

        add.setLayoutX(tmpX);
    }
}