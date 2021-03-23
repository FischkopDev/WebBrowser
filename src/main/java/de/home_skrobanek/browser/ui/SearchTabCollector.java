package de.home_skrobanek.browser.ui;

import de.home_skrobanek.browser.Browser;
import de.home_skrobanek.browser.history.History;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;

import java.util.ArrayList;

/*
    This class collects all tabs which are open.
 */
public class SearchTabCollector {

    //List which saves all tabs
    private ArrayList<SearchTab> collection = new ArrayList<>();

    //button to add/open more tabs
    private Button add;

    // x-Axe size of the tab
    private static final int TAB_SIZE = 200;

    public SearchTabCollector(){
        add = new Button("+");
        add.setLayoutY(0);
        add.setPrefWidth(50);
        add.setPrefHeight(50);
        add.setStyle("-fx-background-color: #323740; -fx-text-fill: white;");
        add.setFont(new Font(16));
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                SearchTab tab = new SearchTab("Neuer Tab", SearchTab.TabType.DEFAULT_TAb);
                setActive(tab);
                addSearchTab(tab);
            }
        });
        add.addEventFilter(MouseEvent.MOUSE_ENTERED, e ->{
            add.setStyle("-fx-background-color: #3d434d; -fx-text-fill: white;");
        });
        add.addEventFilter(MouseEvent.MOUSE_EXITED, e ->{
            add.setStyle("-fx-background-color: #323740; -fx-text-fill: white;");
        });

    }

    /*
        This method starts a new search request. The tab will show the webpage on
        the UI.
     */
    public void searchOnTab(String searchContent, History history){
        Browser.collector.getActive().getView().getEngine().load(searchContent);
        Browser.collector.getActive().setSearchTxt(searchContent);
        Browser.collector.getActive().setLastPage(searchContent);

        history.addNewEntry(searchContent, searchContent);
    }

    public void addButton(){
        Browser.mainPane.getChildren().add(add);
    }

    /*
        this will add a new tab.
     */
    public void addSearchTab(SearchTab tab){
        collection.add(tab);
        setActive(tab);
        setLayout();
    }

    /*
        Set another tab as selected and shows the content of it.
     */
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
                    if(!collection.get(i).isInternSetting())
                        pane.getChildren().add(collection.get(i).getView());
                    else
                        pane.getChildren().add(collection.get(i).getIntern());
                    search.setText(collection.get(i).getSearchTxt());
                }
                else{
                    System.err.println("ERROR: Pane not initialized");
                }
            }
        }
    }

    /*
        Selects the current active tab
     */
    public SearchTab getActive(){
        for(int i = 0; i < collection.size(); i++){
            if(collection.get(i).isActive()){
                return collection.get(i);
            }
        }
        return null;
    }

    /*
        removes and close the current tab
     */
    public void removeTab(SearchTab tab){
        if(collection.size() > 1) {
            collection.remove(tab);
            setActive(collection.get(collection.size()-1));
            setLayout();
        }
        else{
            Browser.stopProgram();
        }
    }

    /*
        UI Layout will be changed by this method.
        e.g First tab left than next tab and so on...
     */
    private void setLayout(){
        int tmpX = 0;
        for(int i = 0; i < collection.size(); i++){
            collection.get(i).setLayoutX(tmpX);

            tmpX+=TAB_SIZE+5;
        }
        add.setLayoutX(tmpX);
    }
}
