/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.extra;

import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.control.Dialog;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author pc
 */
public class NewFolderController extends AnchorPane {

    @FXML
    private JFXTextField folder;

    @FXML
    private JFXButton cancel;

    @FXML
    private JFXButton ok;
    
    private FXMLLoader fxml;
    private static String value;
    private static Dialog<String> dialog;
    private ExceptionDialog exdialog;

    protected NewFolderController() {
        fxml = Fxml.getNewFolderFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(NewFolderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        exdialog = SystemUtils.getDialog();
        ok.setOnAction(this::ok);
        cancel.setOnAction(this::cancel);
    }
    
    private void ok(ActionEvent evt) {
        value = folder.getText();
        dialog.setResult(value);
        dialog.hide();
    }
    
    private void cancel(ActionEvent evt) {
        dialog.setResult("");
        dialog.hide();
    }
    
    public static String show() {
        dialog = new Dialog<String>();
        dialog.setContent(new NewFolderController());
        dialog.getButtons().clear();
        Optional<String> s = dialog.showAndWait();
        return s.get();
    }
    
    
}
