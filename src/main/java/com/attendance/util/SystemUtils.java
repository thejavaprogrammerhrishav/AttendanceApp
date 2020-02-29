/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.util;

import com.attendance.user.model.User;
import com.gluonhq.charm.glisten.application.MobileApplication;
import java.io.File;
import java.text.DecimalFormat;
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

    public static void init() {
        if (resolver == null) {
            resolver = new ViewResolver();
        }
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

}
