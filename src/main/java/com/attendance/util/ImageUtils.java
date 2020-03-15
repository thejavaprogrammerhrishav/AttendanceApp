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
        images.put("PDF", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/pdf.png")));
        images.put("APK", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/apk.png")));
        images.put("AUDIO", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/audio.png")));
        images.put("CPP", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/cpp.png")));
        images.put("C", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/c.png")));
        images.put("C_SHARP", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/c_sharp.png")));
        images.put("CSS", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/css.png")));
        images.put("CSV", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/csv.png")));
        images.put("DATABASE", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/database.png")));
        images.put("FILE", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/file.png")));
        images.put("FOLDER", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/folder.png")));
        images.put("GIF", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/gif.png")));
        images.put("HTML", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/html.png")));
        images.put("JAVA", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/java.png")));
        images.put("JPG", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/jpg.png")));
        images.put("JS", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/js.png")));
        images.put("EXCEL", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/excel.png")));
        images.put("PPT", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/ppt.png")));
        images.put("DOC", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/doc.png")));
        images.put("PNG", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/png.png")));
        images.put("PYTHON", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/python.png")));
        images.put("RTF", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/rtf.png")));
        images.put("TXT", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/txt.png")));
        images.put("VIDEO", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/video.png")));
        images.put("ZIP", new Image(ImageUtils.class.getResourceAsStream("/com/attendance/images/chooser/zip.png")));
    }
    
    public static Image getImage(Icons icon){
        return images.get(icon.getValue());
    }
}
