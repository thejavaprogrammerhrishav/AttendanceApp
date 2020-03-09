/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.user.controller;

import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public class ProfileController extends View {

    @FXML
    private TextField name;

    @FXML
    private TextField contact;

    @FXML
    private TextField username;

    @FXML
    private CheckBox male;

    @FXML
    private CheckBox female;

    @FXML
    private TextField email;

    @FXML
    private TextField usertype;

    @FXML
    private JFXButton edit;

    @FXML
    private JFXButton back;

    @FXML
    private ImageView image;

    private FXMLLoader fxml;

    public ProfileController() {
        fxml = Fxml.getProfileFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        name.setText(SystemUtils.getUser().getDetails().getName());
        contact.setText(SystemUtils.getUser().getDetails().getContact());
        email.setText(SystemUtils.getUser().getDetails().getEmailId());
        username.setText(SystemUtils.getUser().getUsername());
        usertype.setText(SystemUtils.getUser().getType());
        if(SystemUtils.getUser().getDetails().getGender().equalsIgnoreCase("Male")) {
            male.setSelected(true);
        }else {
            female.setSelected(true);
        }
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, eh->{
            SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
        });
        edit.setOnAction(this::edit);
        back.setOnAction(this::back);
    }
    
    private void edit(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.EDIT_PROFILE_VIEW);
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
    }

}
