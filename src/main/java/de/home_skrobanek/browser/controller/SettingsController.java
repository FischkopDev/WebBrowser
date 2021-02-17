package de.home_skrobanek.browser.controller;

import de.home_skrobanek.browser.Browser;
import de.home_skrobanek.browser.ui.SearchTab;
import javafx.fxml.FXML;

public class SettingsController {

    @FXML
    protected void onLanguageSetting(){
        //open the tab with the given content
        SearchTab tab = new SearchTab("Language Settings", SearchTab.TabType.LANGUAGE);
        Browser.collector.addSearchTab(tab);
        Browser.collector.setActive(tab);

        //hide the menu
        Browser.settingsMenu.getSettings().setVisible(!Browser.settingsMenu.getSettings().isVisible());
        Browser.settingsMenu.updatePosition();
    }
}
