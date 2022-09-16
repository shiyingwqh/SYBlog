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
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wuqihang
 */
@Component
public class FileServiceImpl implements FileService {
    private final FileUtil fileUtil;
    String imgRegex = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";


    public FileServiceImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public boolean upload(MultipartFile file) {
        InputStream inputStream = null;
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return false;
        }try {
            inputStream = file.getInputStream();
            if (originalFilename.matches(imgRegex)) {
                return fileUtil.uploadImg(originalFilename, inputStream);
            }
            return fileUtil.uploadFile(originalFilename, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }finally {
            try {
                inputStream.close();
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
    }

    @Override
    public List<String> getAllFilePath() {
        ArrayList<String> names = new ArrayList<>();
        File[] files = fileUtil.getRoot().listFiles();
        if (files == null) return names;
        for (File file : files) {
            getAllName(file, "", names);
        }

        return names;
    }

    @Override
    public File getFileWithPath(String path) {
        if (path == null || path.equals("")) return null;
        String[] split = path.split("/");
        File file = fileUtil.getRoot();
        for (String s : split) {
            s = URLDecoder.decode(s);
            file = new File(file, s);
        }
        if (file.exists()) return null;
        return file;
    }

    public void getAllName(File file, String path, List<String> names) {
        if (file.isDirectory()) {
            path = path + "/" + file.getName();
            File[] files = file.listFiles();
            if (files == null) return;
            for (File file1 : files) {
                getAllName(file1, path, names);
            }
        } else {
            names.add(path + "/" + URLEncoder.encode(file.getName()));
        }
    }

}
