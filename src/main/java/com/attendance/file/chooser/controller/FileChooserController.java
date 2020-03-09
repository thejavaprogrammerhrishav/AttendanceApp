/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.file.chooser.controller;

import com.attendance.util.Fxml;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.DirectoryService;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.Dialog;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author pc
 */
public class FileChooserController extends View {

    @FXML
    private Label path;

    @FXML
    private JFXButton option;

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton show;

    @FXML
    private JFXButton filter;

    @FXML
    private VBox list;

    @FXML
    private JFXButton proceed;

    @FXML
    private JFXButton cancel;

    private SidePopupView view;

    private FXMLLoader fxml;
    private DirectoryService service;
    public static Stack<String> stack = new Stack<>();
    public static String selectedpath = "";

    public static String viewmode = "all";
    public static String order = "name";
    public static boolean hidden = false;
    public static String type;
    private List<String> roots;

    public static Dialog<String> dialog;

    protected FileChooserController(String type) {
        FileChooserController.type = type;
        fxml = Fxml.getFileChooserFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(FileChooserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        roots = new ArrayList<>();
        Services.get(DirectoryService.class).ifPresent(c -> {
            roots.clear();
            roots.addAll(c.getRootDirs());
        });
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, e -> {
            stack.pop();
            refreshList();
        });

        option.setOnAction(this::option);
        show.setOnAction(this::show);
        filter.setOnAction(this::filter);
    }

    public void refreshList() {
        refreshList(stack.stream().collect(Collectors.joining("\\")), false, "All", "Name");
    }

    public void refreshList(String path, boolean showhidden, String vm, String s) {
        File f = new File(path);
        File[] files = f.listFiles();
        list.getChildren().clear();
        List<File> filelist = Arrays.asList(files);
        if(type.equalsIgnoreCase("directory")) {
            filelist = filelist.stream().filter(ff->ff.isDirectory()).collect(Collectors.toList());
        }
        List<File> fl;
        if (showhidden) {
            fl = new ArrayList<>(filelist);
            hidden = true;
        } else {
            fl = filelist.stream().filter(p -> !p.isHidden()).collect(Collectors.toList());
            hidden = false;
        }
        if (vm.equalsIgnoreCase("All")) {
            List<FileChooserNodeController> collect = fl.stream().map(m -> {
                FileChooserNodeController fc = new FileChooserNodeController(this, getPath());
                return fc;
            }).collect(Collectors.toList());
            load(collect);
            viewmode = "all";
        } else if (vm.equalsIgnoreCase("Folder")) {
            List<FileChooserNodeController> collect = fl.stream().filter(p -> p.isDirectory()).map(m -> {
                FileChooserNodeController fc = new FileChooserNodeController(this, getPath());
                return fc;
            }).collect(Collectors.toList());
            load(collect);
            viewmode = "folder";
        } else {
            List<FileChooserNodeController> collect = fl.stream().filter(p -> !p.isDirectory()).map(m -> {
                FileChooserNodeController fc = new FileChooserNodeController(this, getPath());
                return fc;
            }).collect(Collectors.toList());
            load(collect);
            viewmode = "file";
        }

        if (s.equalsIgnoreCase("Name")) {
            List<FileChooserNodeController> collect = list.getChildren().stream().map(m -> (FileChooserNodeController) m).collect(Collectors.toList());
            Collections.sort(collect, (s1, s2) -> s1.getName().compareTo(s2.getName()));
            load(collect);
            order = "name";
        } else if (s.equalsIgnoreCase("Modified")) {
            List<FileChooserNodeController> collect = list.getChildren().stream().map(m -> (FileChooserNodeController) m).collect(Collectors.toList());
            Collections.sort(collect, (s1, s2) -> s1.lastmodified().compareTo(s2.lastmodified()));
            load(collect);
            order = "modified";
        } else {
            List<FileChooserNodeController> collect = list.getChildren().stream().map(m -> (FileChooserNodeController) m).collect(Collectors.toList());
            Collections.sort(collect, (s1, s2) -> s1.getsize().compareTo(s2.getsize()));
            load(collect);
            order = "size";
        }

    }

    private void load(List<FileChooserNodeController> list) {
        this.list.getChildren().clear();
        this.list.getChildren().setAll(list);
    }

    public String getPath() {
        return stack.stream().collect(Collectors.joining("\\"));
    }

    public void add(String path) {
        FileChooserNodeController node = new FileChooserNodeController(this, path);
        this.list.getChildren().add(node);
    }

    private void option(ActionEvent evt) {
        view = new SidePopupView(new FileChooserOtherController(this), Side.RIGHT, true);
        view.show();
    }

    private void filter(ActionEvent evt) {
        view = new SidePopupView(new FileChooserOrderController(this), Side.RIGHT, true);
        view.show();
    }

    private void show(ActionEvent evt) {
        view = new SidePopupView(new FileChooserViewController(this), Side.RIGHT, true);
        view.show();
    }

    private void proceed(ActionEvent evt) {
        dialog.setResult(getPath());
    }
    
    private void cancel(ActionEvent evt) {
        dialog.setResult("");
    }
    
    
    public static String show(String type) {
        dialog = new Dialog<>(true);
        dialog.setContent(new FileChooserController(type));
        Optional<String> res = dialog.showAndWait();
        return res.get();
    }
}
