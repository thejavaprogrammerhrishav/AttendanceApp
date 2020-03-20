/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.user.request;

import com.attendance.user.model.User;
import com.attendance.user.service.LoginService;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author pc
 */
public class UserAccountRequestNode extends AnchorPane {

    @FXML
    private Label name;

    @FXML
    private Label email;

    @FXML
    private Label date;

    @FXML
    private Label result;

    @FXML
    private JFXButton accept;

    @FXML
    private JFXButton onhold;

    @FXML
    private JFXButton decline;

    private FXMLLoader fxml;
    private LoginService service;
    private User user;

    public UserAccountRequestNode(User user) {
        this.user = user;
        fxml = Fxml.getUserAccountRequestNodexml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(UserAccountRequestNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        service = (LoginService) SystemUtils.getContext().getBean("loginservice");
        service.setParent(this);

        name.setText(user.getDetails().getName());
        email.setText(user.getDetails().getEmailId());
        date.setText(user.getDate());
        
        disable();
        result.setVisible(false);
        accept.setOnAction(this::accept);
        decline.setOnAction(this::decline);
        onhold.setOnAction(this::onhold);
    }

    private void accept(ActionEvent evt) {
        user.setStatus("Accept");
        hide();
        boolean  b = service.updateUser(user);
        if(b) {
            result.setText("Accepted");
            result.setStyle("-fx-background-color : green;"
                    + "-fx-text-fill : white;"
                    + "-fx-font-family : arial;"
                    + "-fx-font-size : 14;"
                    + "-fx-alignment : center;");
            result.setVisible(true);
        }
    }

    private void decline(ActionEvent evt) {
        user.setStatus("Decline");
        hide();
        boolean  b = service.updateUser(user);
        if(b) {
            result.setText("Declined");
            result.setStyle("-fx-background-color : red;"
                    + "-fx-text-fill : white;"
                    + "-fx-font-family : arial;"
                    + "-fx-font-size : 14;"
                    + "-fx-alignment : center;");
            result.setVisible(true);
        }
        
    }

    private void onhold(ActionEvent evt) {
        user.setStatus("OnHold");
        hide();
        boolean  b = service.updateUser(user);
        if(b) {
            result.setText("OnHold");
            result.setStyle("-fx-background-color : yellow;"
                    + "-fx-text-fill : black;"
                    + "-fx-font-family : arial;"
                    + "-fx-font-size : 14;"
                    + "-fx-alignment : center;");
            result.setVisible(true);
        }
    }

    private void hide() {
        accept.setDisable(false);
        decline.setDisable(false);
        onhold.setDisable(false);
    }

    private void disable() {
        switch(user.getStatus()) {
            case "Accept" : accept.setDisable(true);
            break;
            case "OnHold" : onhold.setDisable(true);
            break;
            case "Decline" : decline.setDisable(true);
            break;
            default: accept.setDisable(false);
                     decline.setDisable(false);
                     onhold.setDisable(false);
        }
    }

}
