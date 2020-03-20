/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.util;

import com.attendance.login.activity.model.LoginActivity;
import com.attendance.login.activity.service.LoginActivityService;
import com.attendance.user.model.User;
import com.gluonhq.charm.glisten.application.MobileApplication;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author pc
 */
public class SystemUtils {

    public static final long KB = 1024;
    public static final long MB = 1024 * 1024;
    public static final long GB = 1024 * 1024 * 1024;

    private static MobileApplication application;
    private static ViewResolver resolver = null;
    private static ClassPathXmlApplicationContext context;
    private static User user;
    private static String department;
    private static String type;
    private static LoginActivity activity;
    private static LoginActivityService act;

    public static void init() {
        if (resolver == null) {
            resolver = new ViewResolver();
        }
        act = (LoginActivityService) context.getBean("loginactivityservice");
    }

    public static ViewResolver getResolver() {
        return resolver;
    }

    public static void setApplication(MobileApplication application) {
        SystemUtils.application = application;
    }

    public static MobileApplication getApplication() {
        return application;
    }

    public static void setContext(ClassPathXmlApplicationContext context) {
        SystemUtils.context = context;
    }

    public static ClassPathXmlApplicationContext getContext() {
        return context;
    }

    public static void setUser(User user) {
        SystemUtils.user = user;
    }

    public static User getUser() {
        return user;
    }

    public static void setDepartment(String department) {
        SystemUtils.department = department;
    }

    public static String getDepartment() {
        return department;
    }

    public static String getFileSize(File f) {
        DecimalFormat frt = new DecimalFormat("#0.00");
        long length = f.length();
        double size = 0.0;
        String unit = "";
        if (length < KB) {
            size = length;
            unit = "B";
        } else if (length > KB && length < MB) {
            size = length / KB;
            unit = "KB";

        } else if (length > MB && length < GB) {
            size = length / MB;
            unit = "MB";

        } else {
            size = length / GB;
            unit = "GB";

        }
        return frt.format(size) + " "+ unit;
    }
    
     public static byte[] getByteArrayFromImage(Image img) {
        BufferedImage image = SwingFXUtils.fromFXImage(img, null);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", bout);
        } catch (IOException ex) {
            Logger.getLogger(SystemUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bout.toByteArray();
    }

    public static void setType(String type) {
        SystemUtils.type = type;
    }

    public static String getType() {
        return type;
    }
     
     public static ExceptionDialog getDialog() {
         return (ExceptionDialog) SystemUtils.getContext().getBean("dialog");
     }

    public static void setActivity(LoginActivity activity) {
        SystemUtils.activity = activity;
    }

    public static LoginActivity getActivity() {
        return activity;
    }
    
    public static void logout(LoginActivity activity) {
            activity.setStatus("NotActive");
        activity.setLogouttime(DateTime.now().toString(DateTimeFormat.forPattern("hh:mm:ss a")));
        act.updateactivity(activity);
        
        SystemUtils.setDepartment("");
        SystemUtils.setActivity(null);
        SystemUtils.getApplication().switchView(AppView.HOME);
    }

    public static ExecutorService getService() {
        return Executors.newSingleThreadExecutor();
    }

}
