package com.wuqihang.syblog.services;


import com.wuqihang.syblog.pojo.Theme;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @author Wuqihang
 */
public interface ThemeService {
    boolean addTheme(File file);
    boolean uploadTheme(MultipartFile multipartFile);
    void deleteTheme(Theme theme);
    boolean zipTheme(Theme theme);
    boolean unzipTheme(File file);
    List<Theme> getAllTheme();
    Theme getTheme(int id);

    Theme setTheme(int id);
}
