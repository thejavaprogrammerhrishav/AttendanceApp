/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.attendance;

import com.attendance.util.Fxml;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;

/**
 *
 * @author pc
 */
public class UpdateAttendanceController {
    
    
    @FXML
    private JFXComboBox<?> semester;

    @FXML
    private JFXComboBox<?> paper;

    @FXML
    private FlowPane list;

    @FXML
    private JFXButton update;

    @FXML
    private JFXButton back;
    
    private FXMLLoader fxml;

    public UpdateAttendanceController() {
        fxml = Fxml.getUpdateAttendanceFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(UpdateAttendanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        
    }
    
    
}
