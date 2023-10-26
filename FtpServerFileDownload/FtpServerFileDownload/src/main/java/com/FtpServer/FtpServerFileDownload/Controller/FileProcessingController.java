package com.FtpServer.FtpServerFileDownload.Controller;

import com.FtpServer.FtpServerFileDownload.Service.FileProcessingService;
import com.FtpServer.FtpServerFileDownload.Service.FtpDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class FileProcessingController {

    @Autowired
    private FileProcessingService fileProcessingService;

    @GetMapping("/read")
    public String downloadFile() {
        fileProcessingService.processFile();
        return "File read successfully!";
    }
}
