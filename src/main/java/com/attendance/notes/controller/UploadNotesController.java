/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.notes.controller;

import com.attendance.file.chooser.controller.FileChooserController;
import com.attendance.file.chooser.controller.util.FileChooserUtils;
import com.attendance.notes.model.Notes;
import com.attendance.notes.service.NotesService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author pc
 */
public class UploadNotesController extends View {

    @FXML
    private TextField path;

    @FXML
    private JFXButton upload;

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton pdf;

    @FXML
    private JFXButton file;

    @FXML
    private JFXButton txt;

    @FXML
    private JFXButton ward;

    @FXML
    private JFXButton ppt;

    @FXML
    private JFXButton excel;

    private FXMLLoader fxml;

    private NotesService service;
    public static String parent;
    private ExceptionDialog dialog;

    public UploadNotesController() {
        fxml = Fxml.getUploadNotesFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(UploadNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        service = (NotesService) SystemUtils.getContext().getBean("notesservice");

        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, this::back);
        
        path.setEditable(false);

        pdf.setOnAction(this::pdf);
        ppt.setOnAction(this::ppt);
        ward.setOnAction(this::word);
        file.setOnAction(this::file);
        excel.setOnAction(this::excel);
        txt.setOnAction(this::txt);
        upload.setOnAction(this::upload);
        back.setOnAction(this::back);

    }

    private void pdf(ActionEvent evt) {
        FileChooserController.show("all", FileChooserUtils.PDF, c -> path.setText(c));
    }

    private void file(ActionEvent evt) {
        FileChooserController.show("all", FileChooserUtils.ALL, c -> path.setText(c));

    }

    private void txt(ActionEvent evt) {
        FileChooserController.show("all", FileChooserUtils.TXT, c -> path.setText(c));

    }

    private void excel(ActionEvent evt) {
        FileChooserController.show("all", FileChooserUtils.EXCEL, c -> path.setText(c));

    }

    private void word(ActionEvent evt) {
        FileChooserController.show("all", FileChooserUtils.DOCUMENT, c -> path.setText(c));

    }

    private void ppt(ActionEvent evt) {
        FileChooserController.show("all", FileChooserUtils.PPT, c -> path.setText(c));

    }

    private void upload(ActionEvent evt) {
        File f = new File(path.getText());
        if (f.isFile()) {
            FileInputStream fin = null;
            try {
                Notes notes = new Notes();
                notes.setDepartment(SystemUtils.getDepartment());
                notes.setFacultyName(SystemUtils.getUser().getDetails().getName());
                fin = new FileInputStream(f.getAbsolutePath());
                byte[] b = new byte[(int)f.length()];
                fin.read(b);
                fin.close();
                notes.setFile(b);
                notes.setFileName(f.getName());
                double d = (double) f.length()/(1024*1024);
                notes.setFileSize(Double.parseDouble(new DecimalFormat("#0.00").format(d)));
                notes.setUploadDate(DateTime.now().toString(DateTimeFormat.forPattern("dd-MM-yyyy")));
                Integer saveNotes = service.saveNotes(notes);
                if(saveNotes>0) {
                    dialog.showSuccess(this,"Notes Uploaded Successfully");
                }else {
                    dialog.showError(this,"Notes Upload Failed");
                }
            } catch (IOException ex) {
                Logger.getLogger(UploadNotesController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fin.close();
                } catch (IOException ex) {
                    Logger.getLogger(UploadNotesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         }
    }

    private void back(Event evt) {
        if (parent.equalsIgnoreCase("dashboard")) {
            SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
        }
        if (parent.equalsIgnoreCase("notesdashboard")) {
            SystemUtils.getApplication().switchView(AppView.NOTES_DASHBOARD_VIEW);
        }
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
 }
