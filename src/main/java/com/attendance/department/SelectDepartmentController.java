/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.department;

import com.attendance.util.AppView;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

/**
 *
 * @author pc
 */
public class SelectDepartmentController extends View {

    @FXML
    private Button back;

    @FXML
    private Button anthropology;

    @FXML
    private Button assamese;

    @FXML
    private Button bengali;

    @FXML
    private Button biotechnology;

    @FXML
    private Button botany;

    @FXML
    private Button computerscience;

    @FXML
    private Button bussiness;

    @FXML
    private Button chemistry;

    @FXML
    private Button commerce;

    @FXML
    private Button ecology;

    @FXML
    private Button economics;

    @FXML
    private Button english;

    @FXML
    private Button geology;

    @FXML
    private Button hindi;

    @FXML
    private Button history;

    @FXML
    private Button mathematics;

    @FXML
    private Button manipuri;

    @FXML
    private Button zoology;

    @FXML
    private Button mass;

    @FXML
    private Button persian;

    @FXML
    private Button philosophy;

    @FXML
    private Button physics;

    @FXML
    private Button political;

    @FXML
    private Button sanskrit;

    @FXML
    private Button statistics;

    private FXMLLoader fxml;

    public SelectDepartmentController() {
        fxml = Fxml.getSelectDepartmentFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(SelectDepartmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        back.setOnAction(this::back);
        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, e -> SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW));
        anthropology.setOnAction(this::anthropology);
        assamese.setOnAction(this::assamese);
        bengali.setOnAction(this::bengali);
        biotechnology.setOnAction(this::biotechnology);
        botany.setOnAction(this::botany);
        computerscience.setOnAction(this::computerscience);
        bussiness.setOnAction(this::bussiness);
        chemistry.setOnAction(this::chemistry);
        commerce.setOnAction(this::commerce);
        ecology.setOnAction(this::ecology);
        economics.setOnAction(this::economics);
        geology.setOnAction(this::geology);
        hindi.setOnAction(this::hindi);
        history.setOnAction(this::history);
        mathematics.setOnAction(this::mathematics);
        manipuri.setOnAction(this::manipuri);
        zoology.setOnAction(this::zoology);
        mass.setOnAction(this::mass);
        persian.setOnAction(this::persian);
        philosophy.setOnAction(this::philosophy);
        physics.setOnAction(this::physics);
        political.setOnAction(this::political);
        sanskrit.setOnAction(this::sanskrit);
        statistics.setOnAction(this::statistics);
        english.setOnAction(this::english);

    }

    private void back(ActionEvent evt) {
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void anthropology(ActionEvent evt) {
        SystemUtils.setDepartment("Anthropology");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void assamese(ActionEvent evt) {
        SystemUtils.setDepartment("Assamese");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void bengali(ActionEvent evt) {
        SystemUtils.setDepartment("Bengali");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void biotechnology(ActionEvent evt) {
        SystemUtils.setDepartment("Biotechnology");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void botany(ActionEvent evt) {
        SystemUtils.setDepartment("Botany");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void computerscience(ActionEvent evt) {
        SystemUtils.setDepartment("Computer Science");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void bussiness(ActionEvent evt) {
        SystemUtils.setDepartment("Bussiness");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void chemistry(ActionEvent evt) {
        SystemUtils.setDepartment("Chemistry");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void commerce(ActionEvent evt) {
        SystemUtils.setDepartment("Commerce");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void ecology(ActionEvent evt) {
        SystemUtils.setDepartment("Ecology & Environmental Science");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void economics(ActionEvent evt) {
        SystemUtils.setDepartment("Economics");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void english(ActionEvent evt) {
        SystemUtils.setDepartment("English");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void geology(ActionEvent evt) {
        SystemUtils.setDepartment("Geology");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void hindi(ActionEvent evt) {
        SystemUtils.setDepartment("Hindi");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void history(ActionEvent evt) {
        SystemUtils.setDepartment("History");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void mathematics(ActionEvent evt) {
        SystemUtils.setDepartment("Mathematics");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void manipuri(ActionEvent evt) {
        SystemUtils.setDepartment("Manipuri");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void zoology(ActionEvent evt) {
        SystemUtils.setDepartment("zoology");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void mass(ActionEvent evt) {
        SystemUtils.setDepartment("Mass Communication");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void persian(ActionEvent evt) {
        SystemUtils.setDepartment("Persian");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void philosophy(ActionEvent evt) {
        SystemUtils.setDepartment("Philosophy");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void physics(ActionEvent evt) {
        SystemUtils.setDepartment("Physics");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void political(ActionEvent evt) {
        SystemUtils.setDepartment("Political");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void sanskrit(ActionEvent evt) {
        SystemUtils.setDepartment("Sanskrit");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }

    private void statistics(ActionEvent evt) {
        SystemUtils.setDepartment("Statistics");
        SystemUtils.getApplication().switchView(AppView.SELECT_LOGIN_VIEW);
    }
}
