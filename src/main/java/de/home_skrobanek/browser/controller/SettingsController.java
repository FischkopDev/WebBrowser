package de.home_skrobanek.browser.controller;

import de.home_skrobanek.browser.Browser;
import de.home_skrobanek.browser.ui.SearchTab;
import javafx.fxml.FXML;

public class SettingsController {

    @FXML
    protected void onLanguageSetting(){
        openInternMenu("Language Settings", SearchTab.TabType.HISTORY);
    }

    @FXML
    protected void onHistorySettings(){
        openInternMenu("History", SearchTab.TabType.HISTORY);
    }

    @FXML
    protected void onWebengine(){

    }

    private void openInternMenu(String name, SearchTab.TabType type){
        //open the tab with the given content
        SearchTab tab = new SearchTab(name, type);
        Browser.collector.addSearchTab(tab);
        Browser.collector.setActive(tab);

        //hide the menu
        Browser.settingsMenu.getSettings().setVisible(!Browser.settingsMenu.getSettings().isVisible());
        Browser.settingsMenu.updatePosition();
    }
}
