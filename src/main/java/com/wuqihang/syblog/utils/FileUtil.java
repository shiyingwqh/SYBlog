package com.wuqihang.syblog.utils;

import com.wuqihang.syblog.config.SYConfiguration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Wuqihang
 */

@Component
@EnableConfigurationProperties(SYConfiguration.class)
@Getter
public class FileUtil {

    private final String appName;
    private final File root;

    public FileUtil(@Value("#{SYConfiguration.appName}") String appName) {
        this.appName = appName;
        this.root = new File(System.getProperty("user.home") + File.separator + appName + File.separator + "static" + File.separator + "static");
        root.mkdirs();
        new File(root, "img").mkdirs();
    }

    public boolean uploadFile(String fileName, InputStream in) {
        File file = new File(root, fileName);
        return upload(in, file);
    }

    private boolean upload(InputStream in, File file) {
        File newFile = new File(file.getParent(), file.getName());
        String filename = file.getName();
        int last = filename.lastIndexOf('.');
        String name = filename.substring(0, last);
        String ex;
        if (last == -1) {
            ex = "";
        } else {
            ex = filename.substring(last);
        }
        int i = 0;
        while (newFile.exists()) {
            i++;
            newFile = new File(file.getParent(), name + '(' + i + ')' + ex);
        }
        try {
            boolean created = newFile.createNewFile();
            if (created) {
                FileOutputStream fos = new FileOutputStream(newFile);
                byte[] bytes = new byte[4096];
                int len;
                while ((len = in.read(bytes)) > 0) {
                    fos.write(bytes, 0, len);
                }
                fos.close();
                return true;
            }
            return false;
        } catch (IOException e) {
            file.deleteOnExit();
            return false;
        }
    }

    public boolean uploadImg(String fileName, InputStream in) {
        File file = new File(new File(root, "img"), fileName);
        return upload(in, file);
    }

    public void delete(File file) {
        file.delete();
    }
}
