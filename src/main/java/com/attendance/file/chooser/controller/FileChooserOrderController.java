/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.file.chooser.controller;

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
public class FileChooserOrderController extends View {

    @FXML
    private JFXButton name;

    @FXML
    private JFXButton modified;

    @FXML
    private JFXButton size;

    private FXMLLoader fxml;

    private FileChooserController controller;

    public FileChooserOrderController(FileChooserController controller) {
        this.controller = controller;
        fxml = Fxml.getFileChooserOrderFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(FileChooserOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        name.setOnAction(this::name);
        modified.setOnAction(this::modified);
        size.setOnAction(this::size);
    }

    private void name(ActionEvent evt) {
        controller.refreshList(controller.getPath(), FileChooserController.hidden, FileChooserController.viewmode, "name");
    }

    private void modified(ActionEvent evt) {
        controller.refreshList(controller.getPath(), FileChooserController.hidden, FileChooserController.viewmode, "modified");
    }

    private void size(ActionEvent evt) {
        controller.refreshList(controller.getPath(), FileChooserController.hidden, FileChooserController.viewmode, "size");
    }

}
