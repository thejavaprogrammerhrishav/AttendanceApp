/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.splash;

import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.LifecycleService;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author pc
 */
public class SplashController extends View {
    
    
    @FXML
    private JFXButton proceed;

    @FXML
    private JFXButton settings;

    @FXML
    private JFXButton about;
    
    @FXML
    private JFXButton exit;
    
    private FXMLLoader fxml;
    private ExceptionDialog dialog;
    

    public SplashController() {
        fxml = Fxml.getSplashFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(SplashController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        proceed.setOnAction(this::proceed);
        settings.setOnAction(this::settings);
        about.setOnAction(this::about);
        exit.setOnAction(this::exit);
    }
    
    private void proceed(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.SELECT_DEPARTMENT_VIEW);
    }
    
    private void settings(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.SETTINGS_VIEW);
    }
    
    private void about(ActionEvent evt){
        SystemUtils.getApplication().switchView(AppView.ABOUT_US_VIEW);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
    private void exit(ActionEvent evt) {
        Services.get(LifecycleService.class).ifPresent(c->c.shutdown());
    }
    
    
}
