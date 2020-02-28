/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.attendance.util;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

/**
 *
 * @author pc
 */
public class ImageUtils {
    
    public static enum Icons {
        PDF("PDF"), APK("APK"),AUDIO("AUDIO"),CPP("CPP"),C("C"),C_SHARP("C_SHARP"),CSS("CSS"),CSV("CSV"),DATABASE("DATABASE"),FILE("FILE"),FOLDER("FOLDER"),GIF("GIF"),HTML("HTML"),
        JAVA("JAVA"),JPG("JPG"),JS("JS"),EXCEL("EXCEL"),PPT("PPT"),DOC("DOC"),PNG("PNG"),PYTHON("PYTHON"),RTF("RTF"),TXT("TXT"),VIDEO("VIDEO"),ZIP("ZIP");
    
        private final String value;
        
        private Icons(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }

    private static Map<String, Image> images = new HashMap<>();
    
    public static void init() {
        images.put("PDF", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/pdf.png")));
        images.put("APK", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/apk.png")));
        images.put("AUDIO", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/audio.png")));
        images.put("CPP", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/cpp.png")));
        images.put("C", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/c.png")));
        images.put("C_SHARP", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/c_sharp.png")));
        images.put("CSS", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/css.png")));
        images.put("CSV", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/csv.png")));
        images.put("DATABASE", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/database.png")));
        images.put("FILE", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/file.png")));
        images.put("FOLDER", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/folder.png")));
        images.put("GIF", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/gif.png")));
        images.put("HTML", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/html.png")));
        images.put("JAVA", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/java.png")));
        images.put("JPG", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/jpg.png")));
        images.put("JS", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/js.png")));
        images.put("EXCEL", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/excel.png")));
        images.put("PPT", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/ppt.png")));
        images.put("DOC", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/doc.png")));
        images.put("PNG", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/png.png")));
        images.put("PYTHON", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/python.png")));
        images.put("RTF", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/rtf.png")));
        images.put("TXT", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/txt.png")));
        images.put("VIDEO", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/video.png")));
        images.put("ZIP", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/choooser/zip.png")));
    }
    
    public static Image getImage(Icons icon){
        return images.get(icon.getValue());
    }
}
