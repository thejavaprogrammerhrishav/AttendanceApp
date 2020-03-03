/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.attendance;

import com.attendance.util.Fxml;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXCheckBox;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

/**
 *
 * @author pc
 */
public class AttendanceNode extends View{
    
    
    @FXML
    private Label name;

    @FXML
    private Label rollno;

    @FXML
    private JFXCheckBox present;

    @FXML
    private JFXCheckBox absent;
    
    private FXMLLoader fxml;

    public AttendanceNode() {
        fxml = Fxml.getAttendanceNodeFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(AttendanceNode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        
    }
    
    
    
}
