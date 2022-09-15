package com.wuqihang.syblog.controller.api;

import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.utils.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Wuqihang
 */
@Controller
@RestController
@RequestMapping("api")
public class FileUploadController {
    private final FileUtil fileUtil;

    public FileUploadController(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @PostMapping("upload-file")
    public ResponsePKG uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            try {
                InputStream inputStream = file.getInputStream();
                if (fileUtil.uploadImg(String.valueOf(System.currentTimeMillis()), inputStream)) {
                    inputStream.close();
                    return ResponsePKG.ok();
                }
                inputStream.close();
                return ResponsePKG.error(-1, "error");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ResponsePKG.error(-1, "error");
    }
}
