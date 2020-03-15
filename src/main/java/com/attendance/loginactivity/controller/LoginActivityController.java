/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.loginactivity.controller;

import com.attendance.login.activity.model.LoginActivity;
import com.attendance.login.activity.service.LoginActivityService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.AppBar;
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
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class LoginActivityController extends View {

    @FXML
    private VBox list;

    @FXML
    private JFXButton refresh;

    @FXML
    private JFXButton back;

    private FXMLLoader fxml;

    private LoginActivityService service;
    private ExceptionDialog dialog;

    public LoginActivityController() {
        fxml = Fxml.getLoginActivityFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        service = (LoginActivityService) SystemUtils.getContext().getBean("loginservice");
        back.setOnAction(this::back);

        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, eh -> {
            SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
        });
        
        refresh.setOnAction(this::load);

    }

    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
    }

    private void load(ActionEvent evt) {
        List<LoginActivity> list = service.findByDepartment(SystemUtils.getDepartment());
        if (SystemUtils.getUser().getType().equalsIgnoreCase("faculty")) {
            list = list.stream().filter(f->f.getName().equals(SystemUtils.getUser().getDetails().getName())).collect(Collectors.toList());
        }
        
        List<LoginActivityNodeController> nodes = list.stream().map(LoginActivityNodeController::new ).collect(Collectors.toList());
        this.list.getChildren().setAll(nodes);
        
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }

    
}
