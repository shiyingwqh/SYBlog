package com.wuqihang.syblog.utils;

import com.wuqihang.syblog.config.SYConfiguration;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author Wuqihang
 */

@Component
@EnableConfigurationProperties(SYConfiguration.class)
@Getter
public class FileUtil {

    private final File root;

    private final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static final File temp = new File(System.getProperty("java.io.tmpdir"));

    public FileUtil() {
        this.root = new File(System.getProperty("user.home") + File.separator + "SYBlog" + File.separator + "static" + File.separator + "static");
        boolean mkdirs = root.mkdirs();
        boolean img = new File(root, "img").mkdirs();
        if (mkdirs && img) {
            logger.info("Dir Created");
        }
    }

    public File uploadFile(String fileName, InputStream in, File outDir) {
        File file = new File(outDir, fileName);
        return upload(in, file);
    }

    public File uploadFile(String fileName, InputStream in) {
        File file = new File(root, fileName);
        return upload(in, file);
    }

    private File upload(InputStream in, File file) {
        File newFile = new File(file.getParent(), file.getName());
        String filename = file.getName();
        int last = filename.lastIndexOf('.');
        String ex;
        String name;
        if (last != -1) {
            name = filename.substring(0, last);
            ex = filename.substring(last);
        } else {
            ex = "";
            name = filename;
        }

        int i = 0;
        while (newFile.exists()) {
            i++;
            newFile = new File(file.getParent(), name + '_' + i + ex);
        }
        try {
            boolean created = newFile.createNewFile();
            if (created) {
                FileOutputStream fos = new FileOutputStream(newFile);
                in2out(in, fos);
                fos.close();
                logger.info("File '" + filename + "' Upload");
                return newFile;
            }
            return null;
        } catch (IOException e) {
            file.delete();
            return null;
        }
    }

    public File uploadImg(String fileName, InputStream in) {
        File file = new File(new File(root, "img"), fileName);
        return upload(in, file);
    }

    public void delete(File file) {
        file.delete();
    }

    public File zipFile(String name, File... files) {
        String zipName = name + ".zip";
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(zipName);
            ZipOutputStream out = new ZipOutputStream(fileOutputStream);
            for (File file : files) {
                compress(out, file, file.getName());
            }
            out.close();
        } catch (IOException e) {
            return null;
        }
        return new File(zipName);
    }

    private void compress(ZipOutputStream out, File source, String root) throws IOException {
        if (source.isDirectory()) {
            out.putNextEntry(new ZipEntry(root + "/"));
            for (File file : Objects.requireNonNull(source.listFiles())) {
                compress(out, file, root + "/" + file.getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(root));
            FileInputStream in = new FileInputStream(source);
            in2out(in, out);
            in.close();
        }
    }

    public File unzip(File file) {
        return unzip(file, file.getParentFile());
    }

    public File unzip(File file, File outDir) {
        return unzip(file, outDir, file.getName().split("\\.")[0]);
    }

    public File unzip(File file, File outDir, String outputDirName) {
        try {
            if (!outDir.isDirectory()) {
                return null;
            }
            ZipFile zipFile = new ZipFile(file);
            File res = new File(outDir, outputDirName);
            if (res.mkdirs()) {
                Enumeration<? extends ZipEntry> entries = zipFile.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    File p = new File(res, entry.getName());
                    if (entry.isDirectory()) {
                        boolean mk = p.mkdirs();
                        if (!mk) {
                            res.delete();
                            return null;
                        }
                    } else {
                        if (!p.createNewFile()) {
                            res.delete();
                            return null;
                        }
                        InputStream in = zipFile.getInputStream(entry);
                        OutputStream out = Files.newOutputStream(p.toPath());
                        in2out(in, out);
                        out.close();
                        in.close();
                    }
                }
                return res;
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    private void in2out(InputStream in, OutputStream out) throws IOException {
        byte[] bytes = new byte[4096];
        int len;
        while ((len = in.read(bytes)) > 0) {
            out.write(bytes, 0, len);
        }
    }

    public boolean createTextFile(File file, String s) {
        if (!file.exists()) {
            try {
                boolean newFile = file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        return false;
    }
}
