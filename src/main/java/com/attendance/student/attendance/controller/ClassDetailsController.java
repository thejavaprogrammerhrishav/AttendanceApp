/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.attendance.controller;

import com.attendance.student.attendance.model.MyClassDetails;
import com.attendance.studentattendance.service.AttendanceService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
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
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class ClassDetailsController extends View {

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton filter;
    
    @FXML
    private JFXButton refresh;

    @FXML
    private Label department;

    @FXML
    private VBox list;

    private FXMLLoader fxml;
    private AttendanceService service;
    
    private SidePopupView view;
    private ExceptionDialog dialog;

    public ClassDetailsController() {
        fxml = Fxml.getClassDetailsFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ClassDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        service = (AttendanceService) SystemUtils.getContext().getBean("attendanceservice");
        load((ActionEvent)null); 
        refresh.setOnAction(this::load);
        filter.setOnAction(this::filter);
        back.setOnAction(this::back);
    }
    
    private void load(ActionEvent evt) {
        List<MyClassDetails> all = service.findByDepartmentFiltered(SystemUtils.getDepartment());
        List<ClassDetailsNodeController> nodes = all.stream().map(m->{
            ClassDetailsNodeController cd = new ClassDetailsNodeController(m, this);
            return cd;
        }).collect(Collectors.toList());
        load(nodes);
    }
    
    private void filter(ActionEvent evt) {
        view = new SidePopupView(new ClassDetailsFilterController(this), Side.RIGHT, Boolean.TRUE);
        view.setAutoHide(true);
        view.show();
    }
    
    public void load(List<ClassDetailsNodeController> list) {
        this.list.getChildren().clear();
        this.list.getChildren().setAll(list);
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
    }

    public List<ClassDetailsNodeController> getList(){
        return list.getChildren().stream().map(m->(ClassDetailsNodeController)m).collect(Collectors.toList());
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
    
}
