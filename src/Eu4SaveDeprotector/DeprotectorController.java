/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Eu4SaveDeprotector;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dibujante
 */
public class DeprotectorController implements Initializable {
    private File _file;
    private final SaveFile _saveFile = new SaveFile();
    private final Decoder _decoder = new Decoder();
    @FXML   private Label SavegameNameLabel;
    @FXML   private Label MessagesLabel;
    @FXML    private void handleLoadSavegameClick() throws URISyntaxException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("EU4 Savegames (*.eu4)", "*.eu4"));
        if (DeprotectorApplication.FilePath != null && !"".equals(DeprotectorApplication.FilePath))
            fileChooser.setInitialDirectory(new File(DeprotectorApplication.FilePath));
        File file = fileChooser.showOpenDialog(DeprotectorApplication.Stage);
        if (file != null) {
            SavegameNameLabel.setText("Loaded savegame " + file.getName());
            MessagesLabel.setText("Savegame loaded. Press \"Unprotect and save\" to remove the protection.");
            _file = file;
            DeprotectorApplication.UpdateFilepath(file.toPath().getParent());
        } else {
            SavegameNameLabel.setText("Failed to load file!");
        }
    }
    
    @FXML    private void handleDeprotectAndSaveButtonClick() throws Exception {
        MessagesLabel.setText("Deprotecting file...\n");
        try {
            _saveFile.Save(_decoder.Deprotect(_file), _file.getAbsolutePath().substring(0, _file.getAbsolutePath().length()-4) + "_decoded.eu4");
            MessagesLabel.setText(MessagesLabel.getText() + "Success! File saved as " + _file.getName().substring(0, _file.getName().length()-4) + "_decoded.eu4");
        } catch (Exception ex) {
            MessagesLabel.setText(MessagesLabel.getText() + "Deprotection failed, because: " + ex.getMessage());
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MessagesLabel.setText("Click \"Load Savegame\" to load a savegame.\n(Note: it is likely inside of 'Documents\\Paradox Interactive\\Europa Universalis IV\\save games')");
    }
}
