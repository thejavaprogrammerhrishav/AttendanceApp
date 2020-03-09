/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.file.chooser.controller;

import com.attendance.extra.NewFolderController;
import com.attendance.util.Fxml;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author pc
 */
public class FileChooserOtherController extends View {

    @FXML
    private JFXButton create;

    @FXML
    private JFXButton refresh;

    @FXML
    private JFXButton dontshow;

    private FXMLLoader fxml;

    private FileChooserController controller;

    public FileChooserOtherController(FileChooserController controller) {
        this.controller = controller;
        fxml = Fxml.getFileChooserOtherFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(FileChooserOtherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        create.setOnAction(this::create);
        refresh.setOnAction(this::refresh);
        dontshow.setOnAction(this::dontshow);
    }

    private void create(ActionEvent evt) {
        String newPath = controller.getPath()+"\\"+NewFolderController.show();
        controller.add(newPath);
    }

    private void refresh(ActionEvent evt) {
        controller.refreshList();
    }

    private void dontshow(ActionEvent evt) {
        boolean b;
        if(FileChooserController.hidden){
            b = false;
        }else{
            b = true;
        }
        controller.refreshList(controller.getPath(),b, FileChooserController.viewmode, FileChooserController.order);

    }

}
