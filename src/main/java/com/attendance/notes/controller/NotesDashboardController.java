/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.notes.controller;

import com.attendance.file.chooser.controller.util.FileChooserUtils;
import static com.attendance.file.chooser.controller.util.FileChooserUtils.IMAGE_FORMATS;
import com.attendance.notes.model.Notes;
import com.attendance.notes.service.NotesService;
import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 *
 * @author pc
 */
public class NotesDashboardController extends View {

    @FXML
    private Label user;

    @FXML
    private Label department;

    @FXML
    private Label doc;

    @FXML
    private Label img;

    @FXML
    private TextField path;

    @FXML
    private JFXButton search;

    @FXML
    private JFXButton upload;

    @FXML
    private JFXButton download;

    @FXML
    private JFXButton view;

    private FXMLLoader fxml;
    private NotesService service;

    public NotesDashboardController() {
        fxml = Fxml.getNotesDashboardFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(NotesDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        service = (NotesService) SystemUtils.getContext().getBean("notesservice");
        user.setText("User: " + SystemUtils.getUser().getDetails().getName());
        department.setText("Department: " + SystemUtils.getDepartment());
        download.setOnAction(this::download);
        upload.setOnAction(this::upload);
        view.setOnAction(this::view);

    }

    private void count() {
        List<Notes> all = service.findAll();
        long imgs = all.stream().filter(f -> {
            try {
                String name = f.getFileName();
                String ext = name.substring(name.lastIndexOf(".") + 1);
                return IMAGE_FORMATS.contains(ext);
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            }

        }).collect(Collectors.toList()).size();

        long docs = all.size() - imgs;

        doc.setText("" + docs);
        img.setText("" + imgs);
    }

    private void download(ActionEvent evt) {
        DownloadNotesController.parent = "notes dashboard";
        SystemUtils.getApplication().switchView(AppView.DOWNLOAD_NOTES_VIEW);
    }

    private void upload(ActionEvent evt) {
        UploadNotesController.parent = "notes dashboard";
        SystemUtils.getApplication().switchView(AppView.UPLOAD_NOTES_VIEW);
    }

    private void view(ActionEvent evt) {
        SystemUtils.getApplication().removeViewFactory("download notes");
        SystemUtils.getApplication().addViewFactory("download notes", () -> new NotesController(c -> {}, 0));
        SystemUtils.getApplication().switchView("download notes");
    }

}
