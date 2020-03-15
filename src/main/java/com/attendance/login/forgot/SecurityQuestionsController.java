/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.login.forgot;

import com.attendance.user.model.SecurityQuestion;
import com.attendance.util.AppView;
import com.attendance.util.ExceptionDialog;
import com.attendance.util.Fxml;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.application.MobileApplication.MobileEvent;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.TextField;
import com.gluonhq.charm.glisten.mvc.View;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

/**
 *
 * @author pc
 */
public class SecurityQuestionsController extends View {

    @FXML
    private TextField ans3;

    @FXML
    private TextField ans1;

    @FXML
    private TextField ans2;

    @FXML
    private JFXComboBox<String> question1;

    @FXML
    private JFXComboBox<String> question2;

    @FXML
    private JFXComboBox<String> question3;

    @FXML
    private JFXButton proceed;

    @FXML
    private JFXButton update;

    @FXML
    private Label department;

    @FXML
    private Label faculty;

    private FXMLLoader fxml;
    
    private ExceptionDialog dialog;

    public SecurityQuestionsController() {
        fxml = Fxml.getSecurityQuestionsFxml();
        fxml.setController(this);
        fxml.setRoot(this);
        try {
            fxml.load();
        } catch (IOException ex) {
            Logger.getLogger(SecurityQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void initialize() {
        dialog = SystemUtils.getDialog();
        question1.getItems().setAll(getQuestions1());
        question2.getItems().setAll(getQuestion2());
        question3.getItems().setAll(getQuestion3());

        question1.setDisable(true);
        question2.setDisable(true);
        question3.setDisable(true);

        department.setText(SystemUtils.getDepartment());
        faculty.setText(SystemUtils.getUser().getType());

        this.addEventHandler(MobileEvent.BACK_BUTTON_PRESSED, this::back);
        if (SystemUtils.getUser().hasSecurityQuestion()) {
            question1.getSelectionModel().select(SystemUtils.getUser().getSecurityquestion().getQuestion1());
            question2.getSelectionModel().select(SystemUtils.getUser().getSecurityquestion().getQuestion2());
            question3.getSelectionModel().select(SystemUtils.getUser().getSecurityquestion().getQuestion3());

        }

        proceed.setOnAction(this::proceed);

    }

    private List<String> getQuestions1() {
        return Arrays.asList("What is your favourate colour?",
                "What is the name of your college?",
                "How many depertment are there in your college or school?",
                "What is the passout year of your college or school?",
                "What is your fabourate car?",
                "What is your phone number?",
                "How many number of faculty member are there in your college depertment?",
                "What is the name of your idol?",
                "In which city or town was your first job?",
                "What is the name of your first boss?",
                "What is the zip code of your college or school?",
                "What is the number of digit in Postal Index Number (PIN) in India?",
                "What is the address of your college or school?",
                "What is the name of the favourate teacher of your college or school?",
                "What is the name of the principal of your college or school?",
                "In which year your college or school was estabilshed?",
                "What is your childhood name?",
                "What is your surname?",
                "What was the name of your pet?",
                "When did your parents meet?");
    }

    private List<String> getQuestion2() {
        return Arrays.asList(" What was the amount of your first salary?",
                " What was the name of your first school you attended?",
                " What was the first company you worked for?",
                " Who was your most memorable elementry school teacher?",
                " What is the nickname of your youngest sibling?",
                " In which city did you meet your spouse or significant other?",
                " What is your oldest cousin's first and last name?",
                " What was your childhood phone number including area code?",
                " What was the last name of the next door neighbour where you grew up?",
                " From which school did you pass matric exam?",
                " What street did you live in childhood?",
                " What is the name of the first school you attended?",
                " What is your maternal mother's maiden name?",
                " What is your paternal mother's maiden name?",
                " What is the name of your first mobile phone?",
                " What is the name of the Prime Minister of your country?",
                " What is the model of your first car?",
                " When did the last census take place in India?",
                " How many neighbouring countries are there in India?",
                " What is the number of states in your country?");
    }

    private List<String> getQuestion3() {
        return Arrays.asList("How many number of districts are there in your state?",
                " How many number of articles are there in Indian constitution?",
                " Who is your favourate TV anchor/host?",
                " Who is your favourate news reporter?",
                " Who is your favourate Politician?",
                " Who is your favourate Prime Minister?",
                " What is your favourate country?",
                " What is your favourate Place?",
                " Who is your favourate singer?",
                " Who is your favourate Bollywood Actor?",
                " Who is your favourate Bollywood Actress?",
                " What is your favourate Film?",
                " What is your favourate Book?",
                " What is your favourate Serial?",
                " What is your favourate Game?",
                " Who is your favourate Cricketer?",
                " What is your favourate TV channel?",
                " What is your favourate Animal?",
                " What is your favourate Fruit?",
                " What is your favourate dish?");
    }

    private void back(MobileEvent evt) {
        SystemUtils.getApplication().switchView(AppView.FORGOT_PASSWORD_VIEW);
    }

    private void proceed(ActionEvent evt) {
        SecurityQuestion sec = SystemUtils.getUser().getSecurityquestion();
        boolean b1 = ans1.getText().equals(sec.getAnswer1());
        boolean b2 = ans2.getText().equals(sec.getAnswer2());
        boolean b3 = ans3.getText().equals(sec.getAnswer3());
        
        if(b1 && b2 && b3) {
            SystemUtils.getApplication().switchView(AppView.RESET_PASSWORD_VIEW);
        }

    }

    @Override
    protected void updateAppBar(AppBar appBar) {
        appBar.setVisible(false);
    }
    
    
}
