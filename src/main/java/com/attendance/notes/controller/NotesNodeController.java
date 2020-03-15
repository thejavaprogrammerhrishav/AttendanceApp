/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.notes.controller;

import com.attendance.notes.model.Notes;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.ImageUtils;
import com.attendance.util.ImageUtils.Icons;
import com.attendance.util.SystemUtils;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author pc
 */
public class NotesNodeController extends AnchorPane{
    
    
    @FXML
    private ImageView image;

    @FXML
    private Label name;

    @FXML
    private Label size;

    @FXML
    private Label date;

    @FXML
    private Label by;

    @FXML
    private JFXButton proceed;
    
    private FXMLLoader fxml;
    private Notes notes;
    private Consumer<Notes> consumer;
    private ExceptionDialog dialog;
    
    public NotesNodeController(Notes notes,Consumer<Notes> consumer) {
        this.notes = notes;
        this.consumer = consumer;
        fxml = Fxml.getNotesNodeFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(NotesNodeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        name.setText(notes.getFileName());
        size.setText(notes.getFileSize()+" MB");
        String ext = notes.getFileName().substring(notes.getFileName().lastIndexOf(".")+1);
        switch(ext) {
            case"jpg" : image.setImage(ImageUtils.getImage(Icons.JPG));
            break;
            case"png" : image.setImage(ImageUtils.getImage(Icons.PNG));
            break; 
            case"gif" : image.setImage(ImageUtils.getImage(Icons.GIF));
            break;
            case"pdf" : image.setImage(ImageUtils.getImage(Icons.PDF));
            break;
            case"docx" :
                case"doc" : image.setImage(ImageUtils.getImage(Icons.DOC));
           
            break;
            default : image.setImage(ImageUtils.getImage(Icons.FILE));
        }
        date.setText(notes.getUploadDate());
        by.setText("By: "+notes.getFacultyName());
        proceed.setOnAction(this::proceed);
        
    }

    public Notes getNotes() {
        return notes;
    }
    
    
    
    
    private void proceed(ActionEvent evt){
        consumer.accept(notes);
        SystemUtils.getApplication().switchToPreviousView();
    }
    
}
