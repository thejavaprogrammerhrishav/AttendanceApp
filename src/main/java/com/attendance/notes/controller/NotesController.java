/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.notes.controller;

import com.attendance.notes.model.Notes;
import com.attendance.notes.service.NotesService;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class NotesController extends View {

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton filter;

    @FXML
    private JFXButton refresh;

    @FXML
    private VBox list;

    private FXMLLoader fxml;
    private SidePopupView view;

    private Consumer<Notes> consumer;
    private int mode;
    private NotesService service;
    private ExceptionDialog dialog;

    public NotesController(Consumer<Notes> consumer, int mode) {
        this.mode = mode;
        this.consumer = consumer;
        fxml = Fxml.getBrowseNotesFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(NotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        service = (NotesService) SystemUtils.getContext().getBean("notesservice");
        back.setOnAction(this::back);
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, this::back);
        load(null);
        refresh.setOnAction(this::load);
        filter.setOnAction(this::filter);
    }

    private void back(Event evt) {
        if (mode == 0) {
            SystemUtils.getApplication().switchView(AppView.DASHBOARD_VIEW);
        } else if (mode == 1) {
            SystemUtils.getApplication().switchView(AppView.DOWNLOAD_NOTES_VIEW);
        } else {

        }
    }

    private void load(ActionEvent evt) {
        List<Notes> all = service.findByDepartment(SystemUtils.getDepartment());
        List<NotesNodeController> nodes = all.stream().map(m -> {
            return new NotesNodeController(m, consumer);
        }).collect(Collectors.toList());
        list.getChildren().setAll(nodes);
    }

    private void filter(ActionEvent evt) {
        view = new SidePopupView(new NotesFilterController(this, () -> view.hide()), Side.RIGHT, true);
        view.setAutoHide(true);
        view.show();
    }

    public List<NotesNodeController> getList() {
        return service.findByDepartment(SystemUtils.getDepartment()).stream().map(m -> new NotesNodeController(m, consumer)).collect(Collectors.toList());
    }

    public void set(List<NotesNodeController> nodes) {
        list.getChildren().clear();
        list.getChildren().setAll(nodes);
    }

    public void ascending() {
        List<Notes> data = service.sortBydate("asc");
        List<NotesNodeController> collect = data.stream().filter(f -> f.getDepartment().equals(SystemUtils.getDepartment())).map(m -> new NotesNodeController(m, consumer)).collect(Collectors.toList());
        set(collect);
    }

    public void descending() {
        List<Notes> data = service.sortBydate("desc");
        List<NotesNodeController> collect = data.stream().filter(f -> f.getDepartment().equals(SystemUtils.getDepartment())).map(m -> new NotesNodeController(m, consumer)).collect(Collectors.toList());
        set(collect);
    }

    public void file() {
        List<Node> children = list.getChildren();
        List<NotesNodeController> collect = children.stream().map(m -> (NotesNodeController) m).collect(Collectors.toList());
        collect = collect.stream().filter(f -> {
            String name = f.getNotes().getFileName();
            String ext = name.substring(name.lastIndexOf(".") + 1);
            return !(ext.equals("jpg") || ext.equals("png") || ext.equals("gif"));
        }).collect(Collectors.toList());
        set(collect);
    }

    public void image() {
        List<Node> children = list.getChildren();
        List<NotesNodeController> collect = children.stream().map(m -> (NotesNodeController) m).collect(Collectors.toList());
        collect = collect.stream().filter(f -> {
            String name = f.getNotes().getFileName();
            String ext = name.substring(name.lastIndexOf(".") + 1);
            return (ext.equals("jpg") || ext.equals("png") || ext.equals("gif"));
        }).collect(Collectors.toList());
        set(collect);
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
    
}
