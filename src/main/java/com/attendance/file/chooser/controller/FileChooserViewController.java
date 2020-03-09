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
public class FileChooserViewController extends View {

    @FXML
    private JFXButton all;

    @FXML
    private JFXButton showfolder;

    @FXML
    private JFXButton showfiles;

    private FXMLLoader fxml;

    private FileChooserController controller;

    public FileChooserViewController(FileChooserController controller) {
        this.controller = controller;
        fxml = Fxml.getFileChooserViewFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(FileChooserViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        all.setOnAction(this::all);
        showfolder.setOnAction(this::showfolder);
        showfiles.setOnAction(this::showfiles);
    }

    private void all(ActionEvent evt) {
        controller.refreshList(controller.getPath(), FileChooserController.hidden, "all", FileChooserController.order);
    }

    private void showfolder(ActionEvent evt) {
        controller.refreshList(controller.getPath(), FileChooserController.hidden, "folder", FileChooserController.order);
    }

    private void showfiles(ActionEvent evt) {
        controller.refreshList(controller.getPath(), FileChooserController.hidden, "file", FileChooserController.order);
    }

}
