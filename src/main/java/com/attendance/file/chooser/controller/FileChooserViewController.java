/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.file.chooser.controller;

import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.File;
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
    private Runnable r;
    private ExceptionDialog dialog;

    public FileChooserViewController(FileChooserController controller, Runnable r) {
        this.controller = controller;
        this.r=r;
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
        dialog = SystemUtils.getDialog();
        all.setOnAction(this::all);
        showfolder.setOnAction(this::showfolder);
        showfiles.setOnAction(this::showfiles);
    }

    private void all(ActionEvent evt) {
        FileChooserController.viewmode = "all";
        controller.refresh(new File(controller.path()));
        r.run();
    }

    private void showfolder(ActionEvent evt) {
        FileChooserController.viewmode = "folder";
        controller.refresh(new File(controller.path()));
        r.run();
    }

    private void showfiles(ActionEvent evt) {
        FileChooserController.viewmode = "file";
        controller.refresh(new File(controller.path()));
        r.run();
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
    

}
