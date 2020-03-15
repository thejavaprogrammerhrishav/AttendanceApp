/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.file.chooser.controller;

import com.attendance.file.chooser.controller.util.FileChooserUtils;
import static com.attendance.file.chooser.controller.util.FileChooserUtils.ALL;
import static com.attendance.file.chooser.controller.util.FileChooserUtils.AUDIO;
import static com.attendance.file.chooser.controller.util.FileChooserUtils.IMAGE;
import static com.attendance.file.chooser.controller.util.FileChooserUtils.PDF;
import static com.attendance.file.chooser.controller.util.FileChooserUtils.VIDEO;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.DirectoryService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.layout.layer.SidePopupView;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
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
    
    private ExceptionDialog exdialog;

    private FXMLLoader fxml;
    private DirectoryService service;
    public static Stack<String> stack = new Stack<>();

    public static String viewmode = "all";
    public static String order = "name";
    public static boolean hidden = false;
    public String type;
    public static List<String> roots;
    private int filetype;
    public static String selpath = "";
    public static Consumer<String> dialog;
    public static boolean nameorder = true;
    public static boolean modifiedorder = true;
    public static boolean sizeorder = true;

    protected FileChooserController(String type, int filetype) {
        this.filetype = filetype;
        this.type = type;
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
        exdialog = SystemUtils.getDialog();
        this.getApplication().getAppBar().setVisible(false);
        this.getApplication().setSwatch(null);
        Services.get(DirectoryService.class).ifPresent(c -> {
            roots = new ArrayList<String>(c.getRootDirs());
            for (String s : roots) {
                s = s.replace("\\", "/");
                list.getChildren().add(new FileChooserNodeController(this, s, true));
            }
        });

        back.setOnAction(this::back);
        filter.setOnAction(this::show);
        option.setOnAction(this::other);
        show.setOnAction(this::order);
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, this::back);
        proceed.setOnAction(this::proceed);
        cancel.setOnAction(this::cancel);
    }

    private void back(Event evt) {
        stack.pop();
        String s = path();
        if (stack.size() == 1) {
            s = s + "/";
        }
        path.setText(s);
        refresh(new File(s));
    }

    public void setPath(String path) {
        this.path.setText(path);
    }

    public void refresh(File f) {
        list.getChildren().clear();
        File[] li = f.listFiles();

        if (li == null) {
            for (String s : roots) {
                list.getChildren().add(new FileChooserNodeController(this, s, true));
            }
        } else {
            List<File> temp = new ArrayList<>(Arrays.asList(li));
            List<File> fl;
            if (!hidden) {
                fl = temp.stream().filter(fd -> !fd.isHidden()).collect(Collectors.toList());
            } else {
                fl = new ArrayList<>(temp);
            }

            if (viewmode.equalsIgnoreCase("folder")) {
                fl = fl.stream().filter(fd -> fd.isDirectory()).collect(Collectors.toList());
            } else if (viewmode.equalsIgnoreCase("file")) {
                fl = fl.stream().filter(fd -> fd.isFile()).collect(Collectors.toList());
            } else {

            }

            fl.stream().filter(fx -> fx.isDirectory()).forEach(c -> {
                list.getChildren().add(new FileChooserNodeController(this, c.getName(), true));
            });

            fl.stream().filter(fx -> !fx.isDirectory()).filter(fx->FileChooserUtils.validate(fx.getAbsolutePath(), filetype)).forEach(c -> {
                list.getChildren().add(new FileChooserNodeController(this, c.getName(), false));
            });
        }
    }

    public void order(String ord) {
        List<FileChooserNodeController> dirs;
        List<FileChooserNodeController> fils;
        ObservableList<Node> nodes = list.getChildren();
        List<FileChooserNodeController> files = nodes.stream().map(m -> (FileChooserNodeController) m).collect(Collectors.toList());
        if (ord.equalsIgnoreCase("name")) {
            dirs = get(files, true);
            fils = get(files, false);
            if (nameorder) {
                Collections.sort(dirs, (s1, s2) -> s1.getPath().compareTo(s2.getPath()));
                Collections.sort(fils, (s1, s2) -> s1.getPath().compareTo(s2.getPath()));
                nameorder = false;
            } else {
                Collections.sort(dirs, (s1, s2) -> -s1.getPath().compareTo(s2.getPath()));
                Collections.sort(fils, (s1, s2) -> -s1.getPath().compareTo(s2.getPath()));
                nameorder = true;
            }

        } else if (ord.equalsIgnoreCase("lastmodified")) {
            dirs = get(files, true);
            fils = get(files, false);
            if (modifiedorder) {
                Collections.sort(dirs, (s1, s2) -> s1.getLastModified().compareTo(s2.getLastModified()));
                Collections.sort(fils, (s1, s2) -> s1.getLastModified().compareTo(s2.getLastModified()));
                modifiedorder = false;
            } else {
                Collections.sort(dirs, (s1, s2) -> -s1.getLastModified().compareTo(s2.getLastModified()));
                Collections.sort(fils, (s1, s2) -> -s1.getLastModified().compareTo(s2.getLastModified()));
                modifiedorder = true;
            }
        } else if (ord.equalsIgnoreCase("size")) {
            dirs = get(files, true);
            fils = get(files, false);
            if (sizeorder) {
                //Collections.sort(dirs, (s1, s2) -> s1.getSize().compareTo(s2.getSize()));
                Collections.sort(fils, (s1, s2) -> s1.getSize().compareTo(s2.getSize()));
                sizeorder = false;
            } else {
                // Collections.sort(dirs, (s1, s2) -> -s1.getSize().compareTo(s2.getSize()));
                Collections.sort(fils, (s1, s2) -> -s1.getSize().compareTo(s2.getSize()));
                sizeorder = true;
            }
        } else {
            dirs = new ArrayList<>();
            fils = new ArrayList<>();
            list.getChildren().clear();
            list.getChildren().setAll(files);
        }
        list.getChildren().clear();
        list.getChildren().setAll(dirs);
        list.getChildren().addAll(fils);
    }

    public void decolor() {
        list.getChildren().stream().map(m -> (FileChooserNodeController) m).forEach(c -> {
            c.reset();
            c.setColored(false);
        });
    }

    private List<FileChooserNodeController> get(List<FileChooserNodeController> list, boolean directory) {
        if (directory) {
            return list.stream().filter(f -> f.isDirectory()).collect(Collectors.toList());
        } else {
            return list.stream().filter(f -> !f.isDirectory()).collect(Collectors.toList());
        }
    }

    public int size() {
        return stack.size();
    }

    public String path() {
        String s = FileChooserController.stack.stream().collect(Collectors.joining("/"));
        return s;
    }

    private void show(ActionEvent evt) {
        view = new SidePopupView(new FileChooserViewController(this, () -> view.hide()), Side.RIGHT, true);
        view.setAutoHide(true);
        view.show();
    }

    private void other(ActionEvent evt) {
        view = new SidePopupView(new FileChooserOtherController(this, () -> view.hide()), Side.RIGHT, true);
        view.setAutoHide(true);
        view.show();
    }

    private void order(ActionEvent evt) {
        view = new SidePopupView(new FileChooserOrderController(this, () -> view.hide()), Side.RIGHT, true);
        view.setAutoHide(true);
        view.show();
    }

    private void proceed(ActionEvent evt) {
        if (type.equalsIgnoreCase("directory")) {
            if(new File(selpath).isDirectory()){
                dialog.accept(selpath);
            }
            else{
                dialog.accept("");
            }
        } else {
            dialog.accept(selpath);
        }
        stack.clear();
            MobileApplication.getInstance().switchToPreviousView();
            MobileApplication.getInstance().removeViewFactory("chooser");
    }

    private void cancel(ActionEvent evt) {
        dialog.accept("");
        stack.clear();
        MobileApplication.getInstance().switchToPreviousView();
        MobileApplication.getInstance().removeViewFactory("chooser");
    }

    public static void show(String type, int file, Consumer<String> run) {
        dialog = run;
        MobileApplication app = MobileApplication.getInstance();
        
        app.addViewFactory("chooser", () -> new FileChooserController(type, file));
        app.switchView("chooser");
    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }

}
