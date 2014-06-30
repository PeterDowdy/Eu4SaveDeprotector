/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Eu4SaveDeprotector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dibujante
 */
public class DeprotectorApplication extends Application {
    
    public static Stage Stage;
    public static String FilePath = "";

    static void UpdateFilepath(Path resolve) throws URISyntaxException {
        PrintWriter writer = null;
        try {
            FilePath = resolve.toString();
            File f = new File("config");
            writer = new PrintWriter(f, "UTF-8");
            writer.print(FilePath);
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(DeprotectorApplication.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (writer != null)
            writer.close();
        }
    }
    
    @Override
    public void start(Stage primaryStage) throws IOException, URISyntaxException {
        File f = new File("config");
        if (f.exists())
            FilePath = Files.lines(f.toPath()).findFirst().orElse("");
        else
            FilePath = "";
        Stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("DeprotectorApplication.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
