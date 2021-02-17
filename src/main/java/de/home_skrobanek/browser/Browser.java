package de.home_skrobanek.browser;

import de.home_skrobanek.browser.controller.BrowserController;
import de.home_skrobanek.browser.ui.SearchTab;
import de.home_skrobanek.browser.ui.SearchTabCollector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Browser extends Application {

    public static BrowserController mainController;
    public static AnchorPane mainPane;
    public static SearchTabCollector collector;
    public static final double VERSION = 1.0;

    public static void main(String[]args){
        collector = new SearchTabCollector();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        mainPane = fxmlLoader.load(getClass().getResource("/FXML/main.fxml").openStream());
        mainController = (BrowserController) fxmlLoader.getController();

        Scene mainScene = new Scene(mainPane, 1600, 900);

        collector.addButton();

        stage.setScene(mainScene);
        stage.setTitle("Browser Version "+ VERSION+ " | Timo Skrobanek");
        stage.setResizable(true);


        stage.show();

        SearchTab tab = new SearchTab("Hallo");
        collector.addSearchTab(tab);
        collector.setActive(tab);
    }

    public static void stopProgram(){

        System.exit(0);
    }
}
