/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.main;

import com.attendance.util.AppView;
import static com.attendance.util.AppView.ATTENDANCE_VIEW;
import static com.attendance.util.AppView.DASHBOARD_VIEW;
import static com.attendance.util.AppView.DOWNLOAD_NOTES_VIEW;
import static com.attendance.util.AppView.EDIT_PROFILE_VIEW;
import static com.attendance.util.AppView.FACULTY_DETAILS_VIEW;
import static com.attendance.util.AppView.LOGIN_ACTIVITY_VIEW;
import static com.attendance.util.AppView.NOTES_DASHBOARD_VIEW;
import static com.attendance.util.AppView.PROFILE_VIEW;
import static com.attendance.util.AppView.SELECT_DEPARTMENT_VIEW;
import static com.attendance.util.AppView.SELECT_LOGIN_VIEW;
import static com.attendance.util.AppView.STUDENT_DETAILS_VIEW;
import static com.attendance.util.AppView.UPLOAD_NOTES_VIEW;
import com.attendance.util.SystemUtils;
import com.gluonhq.charm.glisten.application.MobileApplication;
import javafx.scene.Scene;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author pc
 */
public class Start extends MobileApplication{
    
    private static ClassPathXmlApplicationContext app; 

    @Override
    public void init() throws Exception {
        addViewFactory(AppView.SPLASH_VIEW, ()->SystemUtils.getResolver().getSplashView());
        addViewFactory(DASHBOARD_VIEW, ()->SystemUtils.getResolver().getDashboardView());
        addViewFactory(NOTES_DASHBOARD_VIEW, ()->SystemUtils.getResolver().getNotesDashboardView());
        addViewFactory(ATTENDANCE_VIEW, ()->SystemUtils.getResolver().getAttendanceView());
        addViewFactory(PROFILE_VIEW, ()->SystemUtils.getResolver().getProfileView());
        addViewFactory(SELECT_DEPARTMENT_VIEW, ()->SystemUtils.getResolver().getSelectDepartmentView());
        addViewFactory(SELECT_LOGIN_VIEW, ()->SystemUtils.getResolver().getSelectLoginView());
        addViewFactory(EDIT_PROFILE_VIEW, ()->SystemUtils.getResolver().getEditProfileView());
        addViewFactory(UPLOAD_NOTES_VIEW, ()->SystemUtils.getResolver().getUploadNotesView());
        addViewFactory(DOWNLOAD_NOTES_VIEW, ()->SystemUtils.getResolver().getDownloadNotesView());    
        addViewFactory(LOGIN_ACTIVITY_VIEW, ()->SystemUtils.getResolver().getLoginActivityView()); 
        addViewFactory(STUDENT_DETAILS_VIEW, ()->SystemUtils.getResolver().getStudentDetailsView()); 
        addViewFactory(FACULTY_DETAILS_VIEW, ()->SystemUtils.getResolver().getFacultyDetailsView()); 
        
        SystemUtils.setApplication(this);
        applicationInit();
        SystemUtils.setContext(app);
        
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
    
    
    
    
    
}
