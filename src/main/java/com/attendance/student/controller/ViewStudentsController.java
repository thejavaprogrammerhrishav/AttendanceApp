/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.controller;

import com.attendance.student.model.Student;
import com.attendance.student.service.StudentService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
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
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class ViewStudentsController extends View {
    
    @FXML
    private JFXButton back;
    
    @FXML
    private JFXButton filter;
    
    @FXML
    private Label department;
    
    @FXML
    private JFXButton refresh;
    
    @FXML
    private VBox list;
    
    private FXMLLoader fxml;
    private StudentService service;
    private SidePopupView view;
    private ExceptionDialog dialog;
    
    public ViewStudentsController() {
        fxml = Fxml.getViewStudentsFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ViewStudentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        service = (StudentService) SystemUtils.getContext().getBean("studentservice");
        department.setText(SystemUtils.getDepartment());
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, eh -> {
            SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
        });
        load((ActionEvent) null);
        back.setOnAction(this::back);
        refresh.setOnAction(this::load);
        filter.setOnAction(this::filter);
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
    }
    
    private void load(ActionEvent evt) {
        List<Student> all = service.findByDepartment(SystemUtils.getDepartment());
        List<ViewStudentNodeController> nodes = all.stream().map(ViewStudentNodeController::new).collect(Collectors.toList());
        load(nodes);
    }
    
    public void load(List<ViewStudentNodeController> nodes) {
        list.getChildren().setAll(nodes);
    }
    
    private void filter(ActionEvent evt) {
        view = new SidePopupView(new ViewStudentFilterController(this), Side.RIGHT, Boolean.TRUE);
        view.show();
    }
    
    public List<Node> getNodeList() {
        return list.getChildren();
    }
    
    public StudentService getService() {
        return service;
    }
    
    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
}
