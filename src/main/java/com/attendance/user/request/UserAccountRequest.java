/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.user.request;

import com.attendance.util.Fxml;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class UserAccountRequest extends View {

    @FXML
    private JFXButton back;

    @FXML
    private Label department;

    @FXML
    private JFXButton refresh;

    @FXML
    private JFXButton filter;

    @FXML
    private VBox list;

    private FXMLLoader fxml;

    public UserAccountRequest() {
        fxml = Fxml.getUserAccountRequestFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(UserAccountRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {

    }

}
