/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.faculty.controller;

import com.attendance.user.model.User;
import com.attendance.user.service.LoginService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
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
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class ViewFacultyListController extends View {

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton refresh;

    @FXML
    private Label department;

    @FXML
    private VBox list;

    private FXMLLoader fxml;

    private LoginService service;
    
    private ExceptionDialog dialog;

    public ViewFacultyListController() {
        fxml = Fxml.getViewFacultyFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ViewFacultyListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        service = (LoginService) SystemUtils.getContext().getBean("loginservice");
        department.setText(SystemUtils.getDepartment());
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, eh->{
            SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
        });
        load((ActionEvent)null);
        back.setOnAction(this::back);
        refresh.setOnAction(this::load);
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
    }
    
     public void load(List<ViewFacultyNodeController> nodes) {
        list.getChildren().setAll(nodes);
    }
     
     private void load(ActionEvent evt) {
        List<User> list = service.findByDepartment(SystemUtils.getDepartment());
        list = list.stream().filter(f->f.getType().equalsIgnoreCase("Faculty") || f.getType().equalsIgnoreCase("HOD")).collect(Collectors.toList());
        List<ViewFacultyNodeController> nodes = list.stream().map(ViewFacultyNodeController::new).collect(Collectors.toList());
        load(nodes);
     }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
     
     

}
