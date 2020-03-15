/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.user.controller;

import com.attendance.image.chooser.ImageChooserController;
import com.attendance.user.model.PersonalDetails;
import com.attendance.user.model.User;
import com.attendance.user.service.LoginService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.attendance.util.ValidationUtils;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.validation.ConstraintViolation;

/**
 *
 * @author pc
 */
public class EditProfileController extends View{
    
    @FXML
    private TextField name;

    @FXML
    private TextField contact;

    @FXML
    private TextField username;

    @FXML
    private CheckBox male;

    @FXML
    private CheckBox female;

    @FXML
    private TextField email;

    @FXML
    private JFXButton update;

    @FXML
    private JFXButton back;

    @FXML
    private ImageView image;

    @FXML
    private JFXButton editname;

    @FXML
    private JFXButton editcontact;

    @FXML
    private JFXButton editemail;

    @FXML
    private JFXButton editusername;

    @FXML
    private JFXButton editimage;

    @FXML
    private Label usertype;
    
    private FXMLLoader fxml;
    private boolean changed;
    private LoginService service;
    private ExceptionDialog dialog;

    public EditProfileController() {
        fxml = Fxml.getEditProfileFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(EditProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        service = (LoginService) SystemUtils.getContext().getBean("loginservice");
        
        init();
        loaddetails();
        buttonactions();
        
        
        male.selectedProperty().addListener((ol,o,n)->{
            if(n) {
                male.setSelected(true);
                female.setSelected(false);
            }
        });
        
        female.selectedProperty().addListener((ol,o,n)->{
            if(n) {
                female.setSelected(true);
                male.setSelected(false);
            }
        });
        
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, e->{
            SystemUtils.getApplication().switchView(AppView.PROFILE_VIEW);
        });
        
        update.setOnAction(this::update);
        back.setOnAction(this::back);
    }
    
    private void init() {
        name.setEditable(false);
        email.setEditable(false);
        contact.setEditable(false);
        username.setEditable(false);
    }
    
    private void loaddetails() {
        PersonalDetails details = SystemUtils.getUser().getDetails();
        name.setText(details.getName());
        contact.setText(details.getContact());
        email.setText(details.getEmailId());
        username.setText(SystemUtils.getUser().getUsername());
        usertype.setText(SystemUtils.getUser().getType());
        if(details.getGender().equalsIgnoreCase("Male")) {
            male.setSelected(true);
        }else {
            female.setSelected(true);
        }
        image.setImage(new Image(new ByteArrayInputStream(SystemUtils.getUser().getImage())));
    }
    
    private void buttonactions() {
        editname.setOnAction(e->name.setEditable(true));
        editemail.setOnAction(e->email.setEditable(true));
        editcontact.setOnAction(e->contact.setEditable(true));
        editusername.setOnAction(e->username.setEditable(true));
        editimage.setOnAction(this::loadimage);
    }
    
    private void loadimage(ActionEvent evt) {
        Image img=image.getImage();
        
        Image newimg=ImageChooserController.show();
        
        if(newimg!=null){
            image.setImage(newimg);
        }
        else{
            image.setImage(img);
        }
    } 
    
    private void update(ActionEvent evt) {
        User org = SystemUtils.getUser();
        User user = new User();
        PersonalDetails details = new PersonalDetails();
        
        user.setUsername(username.getText());
        user.setPassword(org.getPassword());
        user.setId(org.getId());
        user.setType(org.getType());
        user.setDepartment(org.getDepartment());
        user.setStatus(org.getStatus());
        user.setDate(org.getDate());
        
        if(changed) {
            user.setImage(SystemUtils.getByteArrayFromImage(image.getImage()));
        }else {
            user.setImage(org.getImage());
        }
        
        details.setId(org.getDetails().getId());
        details.setName(name.getText());
        details.setEmailId(email.getText());
        details.setContact(contact.getText());
        
        if(male.isSelected()) {
            details.setGender("Male");
        }else if(female.isSelected()) {
            details.setGender("Female");
        }else {
            details.setGender("Unknown");
        }
        
        user.setDetails(details);
        user.setSecurityquestion(org.getSecurityquestion());
        
        
        Set<ConstraintViolation<User>> validate = ValidationUtils.getValidator().validate(user);
        
        if(validate.isEmpty()) {
            boolean updateUser = service.updateUser(user);
            if(updateUser) {
                SystemUtils.setUser(user);
                dialog.showSuccess(this, "Profile Updated Successfully");
            }else {
                dialog.showError(this, "Profile Updation Failed");
            }
        }else {
            validate.stream().forEach(c->{
                dialog.showError(this, c.getMessage());
            });
        }
    }
    
    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.PROFILE_VIEW);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
    
}
