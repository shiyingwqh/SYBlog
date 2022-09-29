package com.wuqihang.syblog.controller.api;

import com.wuqihang.syblog.pojo.ResponsePKG;
import com.wuqihang.syblog.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author Wuqihang
 */
@Controller
@RestController
@RequestMapping("api")
public class FileUploadController {
    private final FileService fileService;

    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("upload-file")
    public ResponsePKG uploadFile(@RequestParam MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty() && fileService.upload(file)) {
            return ResponsePKG.ok();
        }
        return ResponsePKG.error(-1, "error");
    }
    @PostMapping("delete-file")
    public ResponsePKG deleteFile(@RequestParam String path) {
        fileService.delete(path);
        return ResponsePKG.ok();
    }

    @PostMapping("get-file-list")
    public ResponsePKG getFileList() {
        List<String> allFilePath = fileService.getAllFilePath();
        return ResponsePKG.returnData(allFilePath);
    }

    @PostMapping("get-file-list-map")
    public ResponsePKG getFileListMap() {
        Map<String, String> map = fileService.getAllFilePathMap();
        return ResponsePKG.returnData(map);
    }
}
