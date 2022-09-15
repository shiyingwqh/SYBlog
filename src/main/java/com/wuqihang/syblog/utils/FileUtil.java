package com.wuqihang.syblog.utils;

import com.wuqihang.syblog.config.SYConfiguration;
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
@EnableConfigurationProperties(SYConfiguration.class)
@Component
public class FileUtil {
    @Value("#{SYConfiguration.appName}")
    private String appName;
    private final File root;

    public FileUtil() {
        this.root = new File("~/" + appName + "/static");
    }

    public boolean uploadImg(String fileName, InputStream in) {
        File file = new File(new File(root, "img"), fileName);
        try {
            boolean newFile = file.createNewFile();
            if (newFile) {
                FileOutputStream fos = new FileOutputStream(file);
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

}
