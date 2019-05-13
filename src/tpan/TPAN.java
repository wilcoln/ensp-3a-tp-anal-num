/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author SCrf1
 */
public class TPAN extends Application {
    
    F1 fonction1=new F1();
    @Override
    public void start(Stage primaryStage) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/main.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("JavaFX Graph");
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(TPAN.class, args);
        
    }
    
}
