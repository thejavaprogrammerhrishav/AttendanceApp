/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.user.request;

import com.attendance.user.model.User;
import com.attendance.user.service.LoginService;
import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
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
    private VBox list;

    private FXMLLoader fxml;
    private LoginService service;

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
        service = (LoginService) SystemUtils.getContext().getBean("loginservice");
        service.setParent(this);
        department.setText("Department : "+ SystemUtils.getDepartment());
        
        back.setOnAction(this::back);
        refresh.setOnAction(this::load);
    }
    
    private void load(ActionEvent evt) {
        List<User> users = service.findByStatusAndDepartment("pending", SystemUtils.getDepartment()).stream().filter(f->f.getType().equalsIgnoreCase("Faculty")).collect(Collectors.toList());
        List<UserAccountRequestNode> nodes = users.stream().map(UserAccountRequestNode::new ).collect(Collectors.toList());
        list.getChildren().setAll(nodes);
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
    }

}
