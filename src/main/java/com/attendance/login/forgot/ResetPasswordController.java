/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.login.forgot;

import com.attendance.user.service.LoginService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.Password;
import com.attendance.util.SystemUtils;
import com.attendance.util.ValidationUtils;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javax.validation.ConstraintViolation;

/**
 *
 * @author pc
 */
public class ResetPasswordController extends View{
    
    
    @FXML
    private Label usertype;

    @FXML
    private Label department;

    @FXML
    private JFXButton proceed;

    @FXML
    private JFXButton back;
    
     @FXML
    private Label message1;
     
      @FXML
    private Label message2;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXPasswordField confirmpassword;
    
    private FXMLLoader fxml;
    private LoginService service;
    
    private ExceptionDialog dialog;

    public ResetPasswordController() {
        fxml = Fxml.getResetPasswordFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        service =  (LoginService) SystemUtils.getContext().getBean("loginservice");
        department.setText(SystemUtils.getDepartment());
        usertype.setText(SystemUtils.getUser().getType());
        
        password.textProperty().addListener((ol,o,n)->{
            if(n.length()<8 || n.length() > 24) {
                message1.setText("Password length must be in between 8 to 24 characters.");
                message1.setStyle("-fx-text-fill : red; -fx-font-family : 'Arial Black',arial; -fx-font-size : 13px;");
            }
            else {
                message1.setText("Password length good");
                message1.setStyle("-fx-text-fill : #2ecc40; -fx-font-family : 'Arial Black',arial; -fx-font-size : 13px;");
            }
        });
        
        confirmpassword.textProperty().addListener((ol,o,n)->{
            if(!n.equals(password.getText())) {
                message2.setText("Password doesn't Match.");
                message2.setStyle("-fx-text-fill : red; -fx-font-family : 'Arial Black',arial; -fx-font-size : 13px;");
            }
            else {
                message2.setText("Password Matched.");
                message2.setStyle("-fx-text-fill : #2ecc40; -fx-font-family : 'Arial Black',arial; -fx-font-size : 13px;");
            }
        });
        
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, this::back);
        
        back.setOnAction(this::back);
        proceed.setOnAction(this::reset);
    }
    
    
    private void reset(ActionEvent evt) {
        if(password.getText().equals(confirmpassword.getText())) {
            Password p = new Password(password.getText());
            Set<ConstraintViolation<Password>> validate = ValidationUtils.getValidator().validate(p);
            if(validate.isEmpty()) {
                SystemUtils.getUser().setPassword(password.getText());
                boolean b = service.updateUser(SystemUtils.getUser());
                if(b) {
                    SystemUtils.getApplication().removeViewFactory("Password Result");
                    SystemUtils.getApplication().addViewFactory("Password Result", ()->new ResetPasswordResultController("Success"));
                    SystemUtils.getApplication().switchView("Password Result");
                    
                }else {
                    SystemUtils.getApplication().removeViewFactory("Password Result");
                    SystemUtils.getApplication().addViewFactory("Password Result", ()->new ResetPasswordResultController("Failed"));
                    SystemUtils.getApplication().switchView("Password Result");
                }
            }else {
                validate.stream().forEach(c->{
                    dialog.showError(this, c.getMessage());
                });
            }
        }
        else {
            dialog.showError(this, "Passwords doesn't match");
        }
    }
    
    private void back(Event evt) {
        if(SystemUtils.getUser().hasSecurityQuestion()) {
            SystemUtils.getApplication().switchView(AppView.SECURITY_QUESTION_VIEW);
        }else {
            SystemUtils.getApplication().switchView(AppView.FORGOT_PASSWORD_VIEW);
        }
    }

    @Override
    protected void updateAppBar(AppBar appBar) {

        appBar.setVisible(false);
    }
    
    
    
}
