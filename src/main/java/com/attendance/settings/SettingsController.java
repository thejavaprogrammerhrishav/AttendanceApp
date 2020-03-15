/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.settings;

import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.StorageService;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author pc
 */
public class SettingsController extends View {

    @FXML
    private TextField host;

    @FXML
    private TextField url;

    @FXML
    private TextField database;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirmpassword;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton load;

    @FXML
    private JFXButton back;

    @FXML
    private TextField driver;

    private FXMLLoader fxml;
    private File file;
    private ExceptionDialog dialog;

    public SettingsController() {
        fxml = Fxml.getSettingsFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        Services.get(StorageService.class).ifPresent(c -> {
            c.getPrivateStorage().ifPresent(cx -> {
                file = new File(cx.getAbsolutePath() + "/settings.sys");
            });

        });
        back.setOnAction(this::back);
        save.setOnAction(this::save);
        load.setOnAction(this::load);
    }

    private void save(ActionEvent evt) {
        FileOutputStream fout = null;
        try {
            if (password.getText().equals(confirmpassword.getText())) {
                Properties prop = new Properties();
                prop.setProperty("db.url", url.getText());
                prop.setProperty("db.name", database.getText());
                prop.setProperty("db.username", username.getText());
                prop.setProperty("db.password", password.getText());
                prop.setProperty("db.driver.class.name", driver.getText());
                prop.setProperty("db.host", host.getText());
                fout = new FileOutputStream(file);
                prop.store(fout, null);
                fout.close();
                dialog.showSuccess(this, "Settings Saved Successfully");
            } else {
                dialog.showSuccess(this, "Passwords Doesn't Match");
            }
        } catch (IOException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void load(ActionEvent evt) {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(file));

            url.setText(prop.getProperty("db.url"));
            database.setText(prop.getProperty("db.name"));
            username.setText(prop.getProperty("db.username"));
            password.setText(prop.getProperty("db.password"));
            driver.setText(prop.getProperty("db.driver.class.name"));
            host.setText(prop.getProperty("db.host"));
        } catch (IOException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.HOME);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }

}
