/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.notes.controller;

import com.attendance.util.Fxml;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;

/**
 *
 * @author pc
 */
public class NotesFilterController extends View {

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton apply;

    @FXML
    private CheckBox filterbyuname;

    @FXML
    private CheckBox filterbydate;

    @FXML
    private CheckBox filterbyfname;

    @FXML
    private JFXTextField uploader;

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXButton asc;

    @FXML
    private JFXButton desc;

    @FXML
    private JFXButton file;

    @FXML
    private JFXButton image;

    @FXML
    private JFXTextField filename;

    private FXMLLoader fxml;
    
    private NotesController controller;
    private Runnable run;
    

    public NotesFilterController(NotesController controller,Runnable run) {
        this.controller = controller;
        this.run = run;
        fxml = Fxml.getNotesFilterFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(NotesFilterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        uploader.disableProperty().bind(filterbyuname.selectedProperty().not());
        date.disableProperty().bind(filterbydate.selectedProperty().not());
        filename.disableProperty().bind(filterbyfname.selectedProperty().not());
        apply.setOnAction(this::apply);
        asc.setOnAction(this::ascending);
        desc.setOnAction(this::descending);
        file.setOnAction(this::file);
        image.setOnAction(this::image);
        
    }
    
    private void apply(ActionEvent evt) {
        List<NotesNodeController> list = controller.getList();
        if(filterbyuname.isSelected()) {
            list = list.stream().filter(f->f.getNotes().getFacultyName().toLowerCase().contains(uploader.getText().toLowerCase())).collect(Collectors.toList());
        }
        if(filterbydate.isSelected()) {
            list = list.stream().filter(f->f.getNotes().getUploadDate().equals(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(date.getValue()))).collect(Collectors.toList());
        }
        if(filterbyfname.isSelected()) {
            list = list.stream().filter(f->f.getNotes().getFileName().toLowerCase().contains(filename.getText().toLowerCase())).collect(Collectors.toList());
        }
        controller.set(list);
        run.run();
    }
    
       private void ascending(ActionEvent evt) {
           controller.ascending();
           run.run();
       }
       
       private void descending(ActionEvent evt) {
           controller.descending();
           run.run();
       }
       
       private void file(ActionEvent evt) {
           controller.file();
           run.run();
       } 
       
       private void image(ActionEvent evt) {
           controller.image();
           run.run();
       }
 
}
