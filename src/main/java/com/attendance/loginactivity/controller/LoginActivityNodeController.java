/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.loginactivity.controller;

import com.attendance.login.activity.model.LoginActivity;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author pc
 */
public class LoginActivityNodeController extends AnchorPane {

    private static final Image ACTIVE = new Image(LoginActivityNodeController.class.getResourceAsStream("/com/attendance/fxml/images/thumbs-up-icon-66.png"));
    private static final Image NOT_ACTIVE = new Image(LoginActivityNodeController.class.getResourceAsStream("/com/attendance/fxml/images/icons8-cancel-100.png"));

    @FXML
    private Label name;

    @FXML
    private Label ttime;

    @FXML
    private Label date;

    @FXML
    private Label ftime;

    @FXML
    private ImageView image;

    @FXML
    private Label usertype;

    private FXMLLoader fxml;
    private LoginActivity activity;
    private ExceptionDialog dialog;

    public LoginActivityNodeController(LoginActivity activity) {
        this.activity = activity;
        fxml = Fxml.getLoginActivityNodeFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginActivityNodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        name.setText(activity.getName());
        date.setText(activity.getLogindate());
        ttime.setText(activity.getLogintime());
        usertype.setText(activity.getUserType());
        
        if(activity.getStatus().equalsIgnoreCase("Active")) {
            ftime.setVisible(false);
            image.setImage(ACTIVE);
        }else {
            ftime.setText(activity.getLogouttime());
            image.setImage(NOT_ACTIVE);
        }
    }

}
