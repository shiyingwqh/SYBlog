package com.wuqihang.syblog.services.impl;

import com.wuqihang.syblog.services.FileService;
import com.wuqihang.syblog.utils.FileUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author Wuqihang
 */
@Component
public class FileServiceImpl implements FileService {
    private final FileUtil fileUtil;
    private final Map<String, File> cache;
    private volatile boolean cached = false;
    String imgRegex = "(\\S+(\\.(?i)(jpg|png|gif|bmp))$)";


    public FileServiceImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
        this.cache = new Hashtable<>();
    }

    @Override
    public boolean upload(MultipartFile file) {
        InputStream inputStream = null;
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return false;
        }
        try {
            inputStream = file.getInputStream();
            cached = false;
            if (originalFilename.matches(imgRegex)) {
                File upd = fileUtil.uploadImg(originalFilename, inputStream);
                return upd != null && upd.exists();
            }
            File upd = fileUtil.uploadFile(originalFilename, inputStream);
            return upd != null && upd.exists();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String path) {
        path = path.replace("/", File.separator);
        File file = new File(fileUtil.getRoot(), path);
        fileUtil.delete(file);
        cached = false;
    }

    @Override
    public List<String> getAllFilePath() {
        if (!cached) {
            refreshCache();
        }
        return new ArrayList<>(cache.keySet());
    }

    @Override
    public File getFileWithPath(String path) {
        if (!cached) {
            refreshCache();
        }
        return cache.getOrDefault(path, null);
    }

    @Override
    public Map<String, File> getAllFilePathMap() {
        if (!cached) {
            refreshCache();
        }
        return cache;
    }

    private synchronized void refreshCache() {
        if (cached) {
            return;
        }
        cache.clear();
        for (File file : Objects.requireNonNull(fileUtil.getRoot().listFiles())) {
            addFileCache(file, "");
        }
        cached = true;
    }

    private void addFileCache(File root, String path) {
        if (root.isDirectory()) {
            path = path + "/" + root.getName();
            for (File file : Objects.requireNonNull(root.listFiles())) {
                addFileCache(file, path);
            }
        } else {
            cache.put(path + "/" + root.getName(), root);
        }
    }

}
