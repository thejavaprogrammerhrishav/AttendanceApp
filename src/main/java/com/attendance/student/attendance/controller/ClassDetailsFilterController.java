/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.student.attendance.controller;

import com.attendance.papers.model.Paper;
import com.attendance.papers.service.PapersService;
import com.attendance.student.service.StudentService;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.TextField;

/**
 *
 * @author pc
 */
public class ClassDetailsFilterController extends View {

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton apply;

    @FXML
    private JFXButton clear;

    @FXML
    private CheckBox filterbyname;

    @FXML
    private CheckBox filterbyacayear;

    @FXML
    private CheckBox filterbysemester;

    @FXML
    private CheckBox filterbyyear;

    @FXML
    private CheckBox filterbyctype;

    @FXML
    private JFXComboBox<String> academicyear;

    @FXML
    private JFXComboBox<String> semester;

    @FXML
    private TextField facultyname;

    @FXML
    private JFXComboBox<String> year;

    @FXML
    private CheckBox filterbypaper;

    @FXML
    private JFXComboBox<String> paper;

    @FXML
    private JFXCheckBox honours;

    @FXML
    private JFXCheckBox pass;

    @FXML
    private CheckBox filterbydate;

    @FXML
    private JFXDatePicker date;

    private FXMLLoader fxml;
    private ClassDetailsController controller;
    private StudentService service;
    private PapersService pservice;
    private ExceptionDialog dialog;

    public ClassDetailsFilterController(ClassDetailsController controller) {
        this.controller = controller;
        fxml = Fxml.getClassDetailsFilterFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(ClassDetailsFilterController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        service = (StudentService) SystemUtils.getContext().getBean("studentservice");
        service.setParent(this);
        pservice = (PapersService) SystemUtils.getContext().getBean("paperservice");
        pservice.setParent(this);

        facultyname.disableProperty().bind(filterbyname.selectedProperty().not());
        academicyear.disableProperty().bind(filterbyacayear.selectedProperty().not());
        semester.disableProperty().bind(filterbysemester.selectedProperty().not());
        year.disableProperty().bind(filterbyyear.selectedProperty().not());
        paper.disableProperty().bind(filterbypaper.selectedProperty().not());
        date.disableProperty().bind(filterbydate.selectedProperty().not());
        honours.disableProperty().bind(filterbyctype.selectedProperty().not());
        pass.disableProperty().bind(filterbyctype.selectedProperty().not());

        academicyear.getItems().setAll("1st", "2nd", "3rd");
        year.getItems().setAll(service.findAllYear());

        academicyear.getSelectionModel().selectedItemProperty().addListener((ol, o, n) -> {
            if (n != null || !n.isEmpty()) {
                if (n.equalsIgnoreCase("1st")) {
                    semester.getItems().setAll("1st", "2nd");
                } else if (n.equalsIgnoreCase("2nd")) {
                    semester.getItems().setAll("3rd", "4th");
                } else {
                    semester.getItems().setAll("5th", "6th");
                }
            }
        });

        semester.getSelectionModel().selectedItemProperty().addListener((ol, o, n) -> {
            if (n != null || !n.isEmpty()) {
                List<Paper> list = pservice.findByDepartment(SystemUtils.getDepartment());
                List<String> _list = list.stream().filter(f -> f.getSemester().equals(n)).map(Paper::getPaperCode).collect(Collectors.toList());
                paper.getItems().setAll(_list);

            }
        });
        
        honours.selectedProperty().addListener((ol,o,n)->{
            if(n) {
                honours.setSelected(true);
                pass.setSelected(false);
            }
            
            
        });
        
        pass.selectedProperty().addListener((ol,o,n)->{
            if(n) {
                pass.setSelected(true);
                honours.setSelected(false);
            }
        });
    }

    private void apply(ActionEvent evt) {
        List<ClassDetailsNodeController> list = controller.getList();

        if (filterbyname.isSelected()) {
            list = list.stream().filter(p -> p.getDetails().getFacultyName().toLowerCase().contains(facultyname.getText().toLowerCase())).collect(Collectors.toList());
        }
        if (filterbyacayear.isSelected()) {
            list = list.stream().filter(p -> p.getDetails().getAcadamicyear().equals(academicyear.getSelectionModel().getSelectedItem())).collect(Collectors.toList());
        }
        if (filterbysemester.isSelected()) {
            list = list.stream().filter(p -> p.getDetails().getSemester().equals(semester.getSelectionModel().getSelectedItem())).collect(Collectors.toList());
        }
        if (filterbyyear.isSelected()) {
            list = list.stream().filter(p -> p.getDetails().getYear() == Integer.parseInt(year.getSelectionModel().getSelectedItem())).collect(Collectors.toList());
        }
        if (filterbypaper.isSelected()) {
            list = list.stream().filter(p -> p.getDetails().getPaper().equals(paper.getSelectionModel().getSelectedItem())).collect(Collectors.toList());
        }
        if (filterbydate.isSelected()) {
            list = list.stream().filter(p -> p.getDetails().getDate().equals(date.getValue().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")))).collect(Collectors.toList());
        }
        if (filterbyctype.isSelected()) {
            String type;
            if(honours.isSelected()) {
                type = "Honours";
            }
            else if(pass.isSelected()) {
                type = "Pass";
            }
            else {
                type = "";
            }
            list = list.stream().filter(p -> p.getDetails().getCoursetype().equalsIgnoreCase(type)).collect(Collectors.toList());
        }
        
        controller.load(list);

    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
    
    

}
