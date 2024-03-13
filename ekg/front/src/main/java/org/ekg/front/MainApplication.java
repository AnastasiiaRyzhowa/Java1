package org.ekg.front;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ekg.front.utils.ContextHolder;

import java.io.IOException;

public class MainApplication extends Application {

    public static ContextHolder getContextHolder() {
        return contextHolder;
    }

    private static ContextHolder contextHolder;

    private static Scene scene;

    public static void setContextHolder(ContextHolder contextHolder){
        MainApplication.contextHolder = contextHolder;
    }

    @Override
    public void start(Stage stage) throws IOException {
        setContextHolder(new ContextHolder());
        scene = new Scene(loadFXML("auth"), 1024, 768);
        stage.setScene(scene);
        stage.show();
    }


    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static void main(String[] args) {
        launch();
    }


}