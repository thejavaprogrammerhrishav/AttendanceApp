/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.faculty.controller;

import com.attendance.user.model.User;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

/**
 *
 * @author pc
 */
public class ViewFacultyDetailsController extends View {
    
    @FXML
    private JFXButton close;

    @FXML
    private JFXCheckBox male;

    @FXML
    private JFXCheckBox female;

    @FXML
    private TextField facultyname;

    @FXML
    private TextField contact;

    @FXML
    private TextField email;

    @FXML
    private TextField department;

    @FXML
    private TextField date;

    private FXMLLoader fxml;
    private User user;
    private ExceptionDialog exdialog;
    
    public ViewFacultyDetailsController(User user) {
        this.user = user;
        fxml = Fxml.getVIewFacultyDetailsFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ViewFacultyDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        exdialog = SystemUtils.getDialog();
        facultyname.setText(user.getDetails().getName());
        contact.setText(user.getDetails().getContact());
        email.setText(user.getDetails().getEmailId());
        date.setText(user.getDate());
        if(user.getDetails().getGender().equalsIgnoreCase("Male")) {
            male.setSelected(true);
        }else {
            female.setSelected(true);
        }
        
        close.setOnAction(e->SystemUtils.getApplication().switchToPreviousView());
    }
    
    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
    

}
