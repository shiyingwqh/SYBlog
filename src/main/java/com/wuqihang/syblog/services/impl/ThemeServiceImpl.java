package com.wuqihang.syblog.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuqihang.syblog.pojo.Theme;
import com.wuqihang.syblog.services.ThemeService;
import com.wuqihang.syblog.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Wuqihang
 */
@Component
public class ThemeServiceImpl implements ThemeService {
    private final ObjectMapper mapper = new ObjectMapper();
    private final FileUtil fileUtil;

    private final Hashtable<Integer, Theme> cache;

    private final AtomicInteger id = new AtomicInteger(0);

    private final File themeDir;


    private boolean cached = false;

    public ThemeServiceImpl(FileUtil fileUtil, @Value("#{SYConfiguration.themeDir}") String themeDir) throws URISyntaxException, FileNotFoundException {
        this.fileUtil = fileUtil;
        cache = new Hashtable<>();
        URI uri = new URI(themeDir);
        this.themeDir = Paths.get(uri).toFile();
        if (!this.themeDir.isDirectory()) {
            throw new FileNotFoundException("Theme File Error");
        }

    }

    @Override
    public boolean addTheme(File file) {
        return unzipTheme(file);
    }

    @Override
    public boolean uploadTheme(MultipartFile multipartFile) {
        InputStream in;
        try {
            in = multipartFile.getInputStream();
            File file = fileUtil.uploadFile(multipartFile.getName(), in, FileUtil.temp);
            addTheme(file);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void deleteTheme(Theme theme) {
        File dir = theme.getDir();
        dir.delete();
        cache.remove(theme.getId());
    }

    @Override
    public boolean zipTheme(Theme theme) {
        try {
            String s = mapper.writeValueAsString(theme);
            File dir = theme.getDir();
            File info = new File(dir, "info.json");
            boolean created = fileUtil.createTextFile(info, s);
            if (!created) return false;
            File zip = fileUtil.zipFile(theme.getName(), new File(theme.getDir(), theme.getStaticPath()), new File(theme.getDir(), theme.getTemplatesPath()), info);
            if (zip.exists()) {
                return true;
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean unzipTheme(File file) {
        File unzip = fileUtil.unzip(file, themeDir);
        cached = false;
        return unzip != null && unzip.exists();
    }

    @Override
    public List<Theme> getAllTheme() {
        if (!cached) {
            refreshCache();
        }
        return new ArrayList<>(cache.values());
    }

    @Override
    public Theme getTheme(int id) {
        if (!cached) {
            refreshCache();
        }
        return cache.getOrDefault(id, null);
    }

    @Override
    public synchronized Theme setTheme(int id) {
        Theme theme = cache.get(id);
        if (theme != null) {
            // TODO: Set Theme
        }
        return theme;
    }

    private synchronized void refreshCache() {
        if (cached) {
            return;
        }
        id.set(0);
        cache.clear();
        for (File file : Objects.requireNonNull(themeDir.listFiles())) {
            if (file.isDirectory()) {
                File info = new File(file, "info.json");
                if (!info.exists()) {
                    continue;
                }
                try {
                    Scanner scanner = new Scanner(info);
                    String json = scanner.next();
                    Theme theme = mapper.readValue(json, Theme.class);
                    theme.setId(id.get());
                    theme.setDir(file);
                    scanner.close();
                    cache.put(theme.getId(), theme);
                } catch (FileNotFoundException | JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        cached = true;
    }
}
