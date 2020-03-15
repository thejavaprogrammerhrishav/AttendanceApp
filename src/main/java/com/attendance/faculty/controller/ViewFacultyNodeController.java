/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.faculty.controller;

import com.attendance.user.model.User;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author pc
 */
public class ViewFacultyNodeController extends AnchorPane {

    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label department;

    private FXMLLoader fxml;
    private User user;
    private ExceptionDialog dialog;

    public ViewFacultyNodeController(User user) {
        this.user = user;
        fxml = Fxml.getViewFacultyNodeFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ViewFacultyNodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        name.setText(user.getDetails().getName());
        department.setText(user.getDepartment());
        image.setImage(new Image(new ByteArrayInputStream(user.getImage())));

        image.setOnMouseClicked(e -> {
            SystemUtils.getApplication().removeViewFactory("FacultyDetails");
            SystemUtils.getApplication().addViewFactory("FacultyDetails", () -> new ViewFacultyDetailsController(user));
            SystemUtils.getApplication().switchView("FacultyDetails");
        });
    }

}
