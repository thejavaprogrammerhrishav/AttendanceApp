/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.dashboard;

import com.attendance.user.service.LoginService;
import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author pc
 */
public class DashboardController extends View {

    @FXML
    private JFXButton list;

    @FXML
    private JFXButton logout;

    @FXML
    private Label date;

    @FXML
    private Label time;

    @FXML
    private JFXComboBox<?> year;

    @FXML
    private Label first;

    @FXML
    private Label second;

    @FXML
    private Label third;

    @FXML
    private JFXButton attendance;

    @FXML
    private JFXButton notes;

    private LoginService service;
    private FXMLLoader fxml;

    public DashboardController() {
        fxml = Fxml.getDashboardFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        logout.setOnAction(e -> SystemUtils.getApplication().switchView(AppView.SELECT_DEPARTMENT_VIEW));
        service = (LoginService) SystemUtils.getContext().getBean("loginserive");
        date.setText("Date: " + DateTime.now().toString(DateTimeFormat.forPattern("EEEEE, dd MMMMM yyyy")));
        timer();
    }

    private void timer() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(()->{
                        time.setText("Time: "+ DateTime.now().toString("hh : mm : ss a"));
                    });
                    Thread.sleep(995);
                }
            }
        };
        Thread t = new Thread(task);
        t.start();
    }
    
    private void counter() {
       year.getSelectionModel().selectedItemProperty().addListener((ol,o,n)->{
           if(n!=null) {
               
           }
       });
    }

}
