/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.file.chooser.controller;

import com.attendance.util.Fxml;
import com.attendance.util.ImageUtils;
import com.attendance.util.ImageUtils.Icons;
import com.attendance.util.SystemUtils;
import com.sun.imageio.plugins.common.ImageUtil;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
            name.setText(f.getName());
        }
        size.setText("");
        date.setText("");
        
        image.setOnMouseClicked(e->{
            FileChooserController.stack.push(f.getName());
            controller.refreshList();
        });
        
        if(!f.isDirectory()) {
            String ext = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("."));
            name.setText(f.getName());
            image.setImage(ImageUtils.getImage(Icons.valueOf(ext.toUpperCase())));
            size.setText(SystemUtils.getFileSize(f));
            date.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date(f.lastModified())));
        }
     }
    
    public String getName(){
        return name.getText();
    }
    
    public Long lastmodified() {
        return new File(path).lastModified();
    }
    
    public Long getsize() {
        return new File(path).length();
    }
    
}
