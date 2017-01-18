package util;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * APK 文件过滤.
 */
public class ApkFileFilter extends FileFilter {
        public String getDescription() {    
            return "APK文件";
        }    
        
        public boolean accept(File file) {
            String name = file.getName();    
            return file.isDirectory() || name.toLowerCase().endsWith(".apk");  // 仅显示目录和apk文件
        }    
    }    