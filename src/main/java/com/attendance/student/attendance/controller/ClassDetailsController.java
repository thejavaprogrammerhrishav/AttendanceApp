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
import com.gluonhq.charm.glisten.control.Alert;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.Alert.AlertType;
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
    private Alert alert;

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
        service.setParent(this);
        initalert();
        department.setText(SystemUtils.getDepartment());

        load(new ActionEvent());

        refresh.setOnAction(this::load);
        filter.setOnAction(this::filter);
        back.setOnAction(this::back);
    }

    private void load(ActionEvent evt) {
        Task<List<ClassDetailsNodeController>> task = new Task<List<ClassDetailsNodeController>>() {
            @Override
            protected List<ClassDetailsNodeController> call() throws Exception {

                List<MyClassDetails> all = service.findByDepartmentFiltered(SystemUtils.getDepartment());
                List<ClassDetailsNodeController> nodes = all.stream().map(m -> {
                    ClassDetailsNodeController cd = new ClassDetailsNodeController(m, ClassDetailsController.this);
                    return cd;
                }).collect(Collectors.toList());
                return nodes;
            }
        };

        task.setOnRunning(e -> {
            alert.showAndWait();
        });

        task.setOnSucceeded(e -> {
            try {
                alert.hide();
                load(task.get());
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(ClassDetailsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        SystemUtils.getService().submit(task);
    }

    private void initalert() {
        alert = new Alert(AlertType.NONE, "Loading...");
        alert.getButtons().clear();
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

    public List<ClassDetailsNodeController> getList() {
        return list.getChildren().stream().map(m -> (ClassDetailsNodeController) m).collect(Collectors.toList());
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }

}
