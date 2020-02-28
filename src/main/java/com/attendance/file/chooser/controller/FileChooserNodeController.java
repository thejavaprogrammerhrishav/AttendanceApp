/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.file.chooser.controller;

import com.attendance.util.Fxml;
import com.attendance.util.ImageUtils;
import com.attendance.util.ImageUtils.Icons;
import com.sun.imageio.plugins.common.ImageUtil;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author pc
 */
public class FileChooserNodeController extends AnchorPane{
    
    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label size;

    @FXML
    private Label date;
    
    private FXMLLoader fxml;
    
    private FileChooserController controller;
    private String path;
    
    public FileChooserNodeController(FileChooserController controller, String path) {
        this.controller = controller;
        this.path = path;
        fxml = Fxml.getFileChooserNodeFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(FileChooserNodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        File f = new File(this.path);
        if(f.isDirectory()) {
            image.setImage(ImageUtils.getImage(Icons.FOLDER));
        }
        size.setText("");
        date.setText("");
        
        image.setOnMouseClicked(e->{
            
        });
     }
    
    
    
}
