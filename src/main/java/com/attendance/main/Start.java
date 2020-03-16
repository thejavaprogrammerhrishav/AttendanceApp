/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.main;

import com.attendance.util.AppView;
import static com.attendance.util.AppView.ABOUT_US_VIEW;
import static com.attendance.util.AppView.ATTENDANCE_VIEW;
import static com.attendance.util.AppView.CLASS_DETAILS_VIEW;
import static com.attendance.util.AppView.DASHBOARD_VIEW;
import static com.attendance.util.AppView.DOWNLOAD_NOTES_VIEW;
import static com.attendance.util.AppView.EDIT_PROFILE_VIEW;
import static com.attendance.util.AppView.FACULTY_DETAILS_VIEW;
import static com.attendance.util.AppView.FORGOT_PASSWORD_VIEW;
import static com.attendance.util.AppView.LOGIN_ACTIVITY_VIEW;
import static com.attendance.util.AppView.NOTES_DASHBOARD_VIEW;
import static com.attendance.util.AppView.PROFILE_VIEW;
import static com.attendance.util.AppView.RESET_PASSWORD_VIEW;
import static com.attendance.util.AppView.SECURITY_QUESTION_VIEW;
import static com.attendance.util.AppView.SELECT_DEPARTMENT_VIEW;
import static com.attendance.util.AppView.SELECT_LOGIN_VIEW;
import static com.attendance.util.AppView.SETTINGS_VIEW;
import static com.attendance.util.AppView.STUDENT_DETAILS_VIEW;
import static com.attendance.util.AppView.UPLOAD_NOTES_VIEW;
import com.attendance.util.ImageUtils;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.down.Services;
import com.gluonhq.charm.down.plugins.DirectoryService;
import com.gluonhq.charm.down.plugins.StorageService;
import com.gluonhq.charm.glisten.application.MobileApplication;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Provider;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author pc
 */
public class Start extends MobileApplication {

    private static ClassPathXmlApplicationContext app;
    private static File f;

    @Override
    public void init() throws Exception {
        ImageUtils.init();
        
        loadSettings();
        SystemUtils.setApplication(this);
        addViewFactory(AppView.HOME, () -> SystemUtils.getResolver().getSplashView());
        addViewFactory(DASHBOARD_VIEW, () -> SystemUtils.getResolver().getDashboardView());
        addViewFactory(NOTES_DASHBOARD_VIEW, () -> SystemUtils.getResolver().getNotesDashboardView());
        addViewFactory(ATTENDANCE_VIEW, () -> SystemUtils.getResolver().getAttendanceView());
        addViewFactory(PROFILE_VIEW, () -> SystemUtils.getResolver().getProfileView());
        addViewFactory(SELECT_DEPARTMENT_VIEW, () -> SystemUtils.getResolver().getSelectDepartmentView());
        addViewFactory(SELECT_LOGIN_VIEW, () -> SystemUtils.getResolver().getSelectLoginView());
        addViewFactory(EDIT_PROFILE_VIEW, () -> SystemUtils.getResolver().getEditProfileView());
        addViewFactory(UPLOAD_NOTES_VIEW, () -> SystemUtils.getResolver().getUploadNotesView());
        addViewFactory(DOWNLOAD_NOTES_VIEW, () -> SystemUtils.getResolver().getDownloadNotesView());
        addViewFactory(LOGIN_ACTIVITY_VIEW, () -> SystemUtils.getResolver().getLoginActivityView());
        addViewFactory(STUDENT_DETAILS_VIEW, () -> SystemUtils.getResolver().getStudentDetailsView());
        addViewFactory(FACULTY_DETAILS_VIEW, () -> SystemUtils.getResolver().getFacultyDetailsView());
        addViewFactory(FORGOT_PASSWORD_VIEW, () -> SystemUtils.getResolver().getForgotPasswordView());
        addViewFactory(SECURITY_QUESTION_VIEW, () -> SystemUtils.getResolver().getSecurityQuestionView());
        addViewFactory(RESET_PASSWORD_VIEW, () -> SystemUtils.getResolver().getResetPasswordView());

        addViewFactory(CLASS_DETAILS_VIEW, () -> SystemUtils.getResolver().getClassDetailsView());

        addViewFactory(SETTINGS_VIEW, () -> SystemUtils.getResolver().getSettingsView());
        addViewFactory(ABOUT_US_VIEW, () -> SystemUtils.getResolver().getAboutUsView());

        
        applicationInit();
        SystemUtils.setContext(app);
        SystemUtils.init();

    }

    @Override
    public void postInit(Scene scene) {
        this.setSwatch(null);
        this.getAppBar().setVisible(false);

    }

    private void applicationInit() {
        app = new ClassPathXmlApplicationContext();
        app.setConfigLocation("/com/attendance/xml/Application.xml");
        app.refresh();
    }

    private static void loadSettings() {

        try {
            Services.get(StorageService.class).ifPresent(c -> {
                c.getPrivateStorage().ifPresent(cx -> {
                    f = new File(cx.getAbsolutePath() + "/settings.sys");
                });

            });

            Properties prop = new Properties();
            prop.load(new FileInputStream(f));

            System.setProperty("db.url", prop.getProperty("db.url"));
            System.setProperty("db.name", prop.getProperty("db.name"));
            System.setProperty("db.username", prop.getProperty("db.username"));
            System.setProperty("db.password", prop.getProperty("db.password"));
            System.setProperty("db.driver.class.name", prop.getProperty("db.driver.class.name"));
            System.setProperty("db.host", prop.getProperty("db.host"));
        } catch (IOException ex) {
//            System.setProperty("db.driver.class.name","com.mysql.cj.jdbc.Driver");
//            System.setProperty("db.name", "attendance");
//            System.setProperty("db.username", "hrishav");
//            System.setProperty("db.password", "hrishav");
//            System.setProperty("db.url", "jdbc:mysql://192.168.2.6:3306/attendance?createDatabaseIfNotExist=true");
//            System.setProperty("db.host", "192.168.1.6");
        }
    }
}
