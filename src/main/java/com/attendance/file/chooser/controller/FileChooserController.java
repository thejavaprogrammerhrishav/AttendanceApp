/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.file.chooser.controller;

import com.attendance.util.Fxml;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.DirectoryService;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class FileChooserController extends View {
    
     @FXML
    private Label path;

    @FXML
    private JFXButton option;

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton show;

    @FXML
    private JFXButton filter;

    @FXML
    private VBox list;
    
    private FXMLLoader fxml;
    private DirectoryService service;
    private static Stack<String> stack = new Stack<>();
    private List<String> roots ;

    public FileChooserController() {
        fxml = Fxml.getFileChooserFxml();
        fxml.setController(this);
        fxml.setRoot(this);
         try {
             fxml.load();
         } catch (IOException ex) {
             Logger.getLogger(FileChooserController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    @FXML
    private void initialize() {
        roots = new ArrayList<>();
        Services.get(DirectoryService.class).ifPresent(c->{
            roots.clear();
            roots.addAll(c.getRootDirs());
        });
    }
    
    private void back(){
        
    }
    
    
}
