/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.login.forgot;

import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 *
 * @author pc
 */
public class ResetPasswordResultController extends View {

    private static final Image SUCCESS = new Image(ResetPasswordResultController.class.getResourceAsStream("/com/attendance/images/success.png"));
    private static final Image FAILED = new Image(ResetPasswordResultController.class.getResourceAsStream("/com/attendance/images/fail.png"));

    @FXML
    private JFXButton login;

    @FXML
    private ImageView image;

    @FXML
    private Label bigmessage;

    @FXML
    private Label message1;

    @FXML
    private Label message2;

    @FXML
    private Label department;

    @FXML
    private Label usertype;

    private FXMLLoader fxml;
    private String type;

    public ResetPasswordResultController(String type) {
        this.type = type;
        fxml = Fxml.getResetPasswordResultFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ResetPasswordResultController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        department.setText(SystemUtils.getDepartment());
        usertype.setText(SystemUtils.getType());

        if (type.equalsIgnoreCase("Success")) {
            image.setImage(SUCCESS);
            bigmessage.setText("Success");
            bigmessage.setTextFill(Color.LIME);
            message1.setText("Your account password has been successfully changed");
            message2.setText("You can now login to your account");
            login.setText("Login to your account");
        } else {
            bigmessage.setText("Failed");
            bigmessage.setTextFill(Color.RED);
            message1.setText("Failed to change password");
            message2.setText("Please try again");
            image.setImage(FAILED);
            login.setText("Try Again");
        }
        
        login.setOnAction(this::go);
    }

    private void go(ActionEvent evt) {
        if(login.getText().equalsIgnoreCase("Login to your Account")) {
            SystemUtils.getApplication().switchView(AppView.SELECT_DEPARTMENT_VIEW);
        }else {
            SystemUtils.getApplication().switchView(AppView.RESET_PASSWORD_VIEW);
        }
    }
}
